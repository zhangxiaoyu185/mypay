package com.xiaoyu.lingdian.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.xiaoyu.lingdian.entity.NotifyLog;
import com.xiaoyu.lingdian.entity.PayConstant;
import com.xiaoyu.lingdian.entity.PayRecord;
import com.xiaoyu.lingdian.entity.RefundLog;
import com.xiaoyu.lingdian.entity.UnifiedNotify;
import com.xiaoyu.lingdian.enums.NotifyType;
import com.xiaoyu.lingdian.service.NotifyLogService;
import com.xiaoyu.lingdian.service.NotifyService;
import com.xiaoyu.lingdian.service.PayRecordService;
import com.xiaoyu.lingdian.service.RefundLogService;
import com.xiaoyu.lingdian.tool.StringUtil;
import com.xiaoyu.lingdian.tool.http.HttpUtils;

@Service("notifyService")
public class NotifyServiceImpl implements NotifyService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("BASE_LOGGER");

	@Autowired
	private NotifyLogService notifyLogService;
	
	@Autowired
	private PayRecordService payRecordService;
	
	@Autowired
	private RefundLogService refundLogService;
	
	@Scheduled(cron="0 0/1 *  * * ? ")
	@Override
	public void notifySchedule(){
		List<NotifyLog> list = notifyLogService.selectByQueryDate(new Date());
		for(NotifyLog notifyLog : list) {
			notifyRequester(notifyLog);
		}
	}
	
	@Override
	public void notifyRequester(NotifyLog notifyLog) {
		try {
			if(null == notifyLog) {
				return;
			}
			if(NotifyType.PAY.equals(notifyLog.getNotifyType())) {//支付成功通知
				payNotify(notifyLog);
			} else if(NotifyType.REFUND.equals(notifyLog.getNotifyType())) {//退款成功通知
				refundNotify(notifyLog);
			} else {
			}
		} catch (Exception e) {
			LOGGER.error("notify third system error:", e);
		}
	}
	
	public void payNotify(NotifyLog notifyLog) {
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "text/xml; charset=utf-8");
		
		if(StringUtil.isEmpty(notifyLog.getTradeNo())) {
			return;
		}
		
		Date currDate = new Date();
		String notifyUrl = null;
		
		NotifyLog notify = notifyLogService.selectNotifyLogByTradeNo(notifyLog.getTradeNo());
		if(null == notify) {//还未通知
			PayRecord record = payRecordService.selectByTradeNo(notifyLog.getTradeNo());
			if(null != record && !StringUtil.isEmpty(record.getNotifyUrl())) {
				notifyUrl = record.getNotifyUrl();
				notifyLog.setPayNo(record.getPayNo());
				notifyLog.setOrderNo(record.getOrderNo());
				notifyLog.setNotifyDate(currDate);
				//调用第三方通知地址，告知支付成功
				UnifiedNotify unifiedNotify = buildNotifyParams(notifyLog);
				unifiedNotify.setPayChannel(record.getPayChannel());
				unifiedNotify.setNotifyType(NotifyType.PAY);
				unifiedNotify.setTradeType(record.getTradeType());
				LOGGER.info("unified pay notify param:{}",JSON.toJSONString(unifiedNotify));
				String str = HttpUtils.sendPost(notifyUrl, JSON.toJSONString(unifiedNotify), headerMap);
				LOGGER.info("unified pay notify return:{}",str);
				if(!StringUtil.isEmpty(str) && PayConstant.SUCCESS.equals(str.trim())) {//通知成功
					notifyLog.setNotifyResult(PayConstant.SUCCESS);
				} else {
					notifyLog.setNotifyResult(PayConstant.FAIL);
					long nextNotifyMills = currDate.getTime() + PayConstant.NOTIFY_MAPS.get(1);//第一次重试时间
					notifyLog.setNextNotifyDate(new Date(nextNotifyMills));
				}
				notifyLog.setNotifyTimes(1);//第一次通知
				notifyLog.setNotifyUrl(notifyUrl);
				notifyLog.setCreateDate(currDate);
				notify = notifyLogService.selectNotifyLogByTradeNo(notifyLog.getTradeNo());
				if (null == notify) {
					//保存通知日志
					notifyLogService.insert(notifyLog);
				}
			}
		} else {//已通知过
			if(!PayConstant.SUCCESS.equals(notify.getNotifyResult())) {//通知不成功
				PayRecord record = payRecordService.selectByTradeNo(notify.getTradeNo());
				notifyUrl = notify.getNotifyUrl();
				notify.setNotifyDate(currDate);
				//调用第三方通知地址，告知支付成功
				UnifiedNotify unifiedNotify = buildNotifyParams(notify);
				unifiedNotify.setPayChannel(record.getPayChannel());
				unifiedNotify.setNotifyType(NotifyType.PAY);
				unifiedNotify.setTradeType(record.getTradeType());
				LOGGER.info("unified pay notify param:{}",JSON.toJSONString(unifiedNotify));
				String str = HttpUtils.sendPost(notifyUrl, JSON.toJSONString(unifiedNotify), headerMap);
				LOGGER.info("unified pay notify return:{}",str);
				if(!StringUtil.isEmpty(str) && PayConstant.SUCCESS.equals(str.trim())) {//通知成功
					notify.setNotifyResult(PayConstant.SUCCESS);
				} else {
					notify.setNotifyResult(PayConstant.FAIL);
					if(notify.getNotifyTimes().intValue() <= PayConstant.MAX_NOTIFY_TIMES) {//小于等于最大推送次数
						long nextNotifyMills = currDate.getTime() + PayConstant.NOTIFY_MAPS.get(notify.getNotifyTimes());
						notify.setNextNotifyDate(new Date(nextNotifyMills));
					} else {
						notify.setNextNotifyDate(null);
					}
				}
				notify.setNotifyTimes(notify.getNotifyTimes() + 1);
				notify.setUpdateDate(currDate);
				notifyLogService.updateNullable(notify);
			}
		}
	}
	
	public void refundNotify(NotifyLog notifyLog) {
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "text/xml; charset=utf-8");
		
		if(StringUtil.isEmpty(notifyLog.getRefundNo())) {
			return;
		}
		
		Date currDate = new Date();
		RefundLog refundLog = new RefundLog();
		String notifyUrl = null;
		
		NotifyLog notify = notifyLogService.selectByRefundNo(notifyLog);
		if(null == notify) {//还未通知
			refundLog = refundLogService.selectByOutTradeNo(notifyLog.getOutTradeNo());
			if(null != refundLog && !StringUtil.isEmpty(refundLog.getNotifyUrl())) {
				notifyUrl = refundLog.getNotifyUrl();
				notifyLog.setNotifyDate(currDate);
				//调用第三方通知地址，告知退款成功
				UnifiedNotify unifiedNotify = buildNotifyParams(notifyLog);
				unifiedNotify.setNotifyType(NotifyType.REFUND);
				LOGGER.info("unified refund notify param:{}",JSON.toJSONString(unifiedNotify));
				String str = HttpUtils.sendPost(notifyUrl, JSON.toJSONString(unifiedNotify), headerMap);
				LOGGER.info("unified refund notify return:{}",str);
				
				if(!StringUtil.isEmpty(str) && PayConstant.SUCCESS.equals(str.trim())) {//通知成功
					notifyLog.setNotifyResult(PayConstant.SUCCESS);
				} else {
					notifyLog.setNotifyResult(PayConstant.FAIL);
					long nextNotifyMills = currDate.getTime() + PayConstant.NOTIFY_MAPS.get(1);//第一次重试时间
					notifyLog.setNextNotifyDate(new Date(nextNotifyMills));
				}
				notifyLog.setNotifyTimes(1);//第一次通知
				notifyLog.setNotifyUrl(notifyUrl);
				notifyLog.setCreateDate(currDate);
				//保存通知日志
				notifyLogService.insert(notifyLog);
			}
		} else {//已通知过
			if(!PayConstant.SUCCESS.equals(notify.getNotifyResult())) {//通知不成功
				notifyUrl = notify.getNotifyUrl();
				notify.setNotifyDate(currDate);
				//调用第三方通知地址，告知退款成功
				UnifiedNotify unifiedNotify = buildNotifyParams(notify);
				unifiedNotify.setNotifyType(NotifyType.REFUND);
				LOGGER.info("unified refund notify param:{}",JSON.toJSONString(unifiedNotify));
				String str = HttpUtils.sendPost(notifyUrl, JSON.toJSONString(unifiedNotify), headerMap);
				LOGGER.info("unified refund notify return:{}",str);
				
				if(!StringUtil.isEmpty(str) && PayConstant.SUCCESS.equals(str.trim())) {//通知成功
					notify.setNotifyResult(PayConstant.SUCCESS);
				} else {
					notify.setNotifyResult(PayConstant.FAIL);
					if(notify.getNotifyTimes().intValue() <= PayConstant.MAX_NOTIFY_TIMES) {//小于等于最大推送次数
						long nextNotifyMills = currDate.getTime() + PayConstant.NOTIFY_MAPS.get(notify.getNotifyTimes());
						notify.setNextNotifyDate(new Date(nextNotifyMills));
					} else {
						notify.setNextNotifyDate(null);
					}
				}
				notify.setNotifyTimes(1 + notify.getNotifyTimes());
				notify.setUpdateDate(currDate);
				notifyLogService.updateNullable(notify);
			}
		}
	}
	
	private UnifiedNotify buildNotifyParams(NotifyLog notify) {
		UnifiedNotify unifiedNotify = new UnifiedNotify();
		unifiedNotify.setNotifyContent(notify.getNotifyContent());
		unifiedNotify.setOrderNo(notify.getOrderNo());
		unifiedNotify.setRefundNo(notify.getRefundNo());
		unifiedNotify.setPayNo(notify.getPayNo());
		unifiedNotify.setFee(notify.getFee());
		unifiedNotify.setTradeTime(notify.getTradeDate());
		unifiedNotify.setOutTradeNo(notify.getOutTradeNo());
		unifiedNotify.setRefundDate(notify.getRefundDate());
		unifiedNotify.setRefundFee(notify.getRefundFee());
		unifiedNotify.setNotifyDate(notify.getNotifyDate());
		return unifiedNotify;
	}
	
}