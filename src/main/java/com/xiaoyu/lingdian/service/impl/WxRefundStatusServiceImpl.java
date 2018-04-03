package com.xiaoyu.lingdian.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.xiaoyu.lingdian.core.spring.adapter.PropertyHolder;
import com.xiaoyu.lingdian.entity.NotifyLog;
import com.xiaoyu.lingdian.entity.PayConstant;
import com.xiaoyu.lingdian.entity.PayRecord;
import com.xiaoyu.lingdian.entity.WxRefundQuery;
import com.xiaoyu.lingdian.entity.WxRefundRequest;
import com.xiaoyu.lingdian.entity.WxRefundResponse;
import com.xiaoyu.lingdian.enums.NotifyType;
import com.xiaoyu.lingdian.enums.RefundStatus;
import com.xiaoyu.lingdian.enums.TradeType;
import com.xiaoyu.lingdian.enums.WxRefundType;
import com.xiaoyu.lingdian.service.NotifyService;
import com.xiaoyu.lingdian.service.PayRecordService;
import com.xiaoyu.lingdian.service.WxRefundQueryService;
import com.xiaoyu.lingdian.service.WxRefundRequestService;
import com.xiaoyu.lingdian.service.WxRefundResponseService;
import com.xiaoyu.lingdian.service.WxRefundStatusService;
import com.xiaoyu.lingdian.tool.StringUtil;
import com.xiaoyu.lingdian.tool.Utils;
import com.xiaoyu.lingdian.tool.http.HttpsUtils;
import com.xiaoyu.lingdian.vo.WxRefundDto;
import com.xiaoyu.lingdian.vo.WxRefundQueryOutDto;

@Component
public class WxRefundStatusServiceImpl implements WxRefundStatusService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("BASE_LOGGER");
	
	@Autowired
	private WxRefundRequestService wxRefundRequestService;
	
	@Autowired
	private WxRefundResponseService wxRefundResponseService;
	
	@Autowired
	private WxRefundQueryService wxRefundQueryService;
	
	@Autowired
	private NotifyService notifyService;
	
	@Autowired
	private PayRecordService payRecordService;
	
	@Scheduled(cron="0 0/1 *  * * ? ")
	@Override
	public void queryRefundSchedule() {
		List<WxRefundQuery> list = wxRefundQueryService.selectByQueryDate(new Date());
		for(WxRefundQuery wxRefundQuery : list) {
			wxQueryRefund(wxRefundQuery);
		}
	}
	
	public void wxQueryRefund(WxRefundQuery wxRefundQuery) {
		try {
			if(null != wxRefundQuery && !StringUtil.isEmpty(wxRefundQuery.getTradeNo())) {
				WxRefundDto wxRefundDto = new WxRefundDto();
				wxRefundDto.setAppid(wxRefundQuery.getAppid());
				wxRefundDto.setMch_id(wxRefundQuery.getMchId());
				wxRefundDto.setDevice_info(PayConstant.WX_PAY_DEVICE_INFO);
				wxRefundDto.setNonce_str(Utils.getRandomString(PayConstant.WX_PAY_NONCE_STR_LENGTH));
				wxRefundDto.setOut_trade_no(wxRefundQuery.getTradeNo());
				
				PayRecord payRecord = payRecordService.selectByOutTradeNo(wxRefundQuery.getTransactionId());
				if(null == payRecord) {
					return;
				}
				String key = "";
				if(TradeType.WX_APP.equals(payRecord.getTradeType())) {
					key = PropertyHolder.getProperty("wx.ourselfKey");
				} else {
					key = PropertyHolder.getProperty("wx.key");
				}
				
				String signStr = Utils.md5Sign(wxRefundDto.getRefundParametersMap(), key);
				wxRefundDto.setSign(signStr);
				//保存请求记录
				WxRefundRequest wxRefundRequest = convertWxRefundRequest(wxRefundDto,wxRefundQuery);
				wxRefundRequestService.insert(wxRefundRequest);
				
				String url = PropertyHolder.getProperty("wx.refundquery");

				LOGGER.info("call wx query refund request param:{}",wxRefundDto.toString());
				String result = HttpsUtils.post(url, Utils.toXml(wxRefundDto));
				LOGGER.info("call wx query refund response:{}",result);
				
				if(!StringUtil.isEmpty(result)) {
					WxRefundQueryOutDto wxRefundOutDto = Utils.toBean(result, WxRefundQueryOutDto.class);
					
					NotifyLog notifyLog = new NotifyLog();
					notifyLog.setNotifyType(NotifyType.REFUND);
					notifyLog.setTradeNo(wxRefundQuery.getTradeNo());
					notifyLog.setOutTradeNo(wxRefundQuery.getTransactionId());
					notifyLog.setPayNo(payRecord.getPayNo());
					notifyLog.setOrderNo(payRecord.getOrderNo());
					notifyLog.setRefundNo(wxRefundQuery.getRefundNo());
					notifyLog.setRefundDate(new Date());
					if(null != wxRefundOutDto.getRefund_fee()) {
						notifyLog.setRefundFee(new BigDecimal(String.valueOf((double)wxRefundOutDto.getRefund_fee() / 100)));
					}
					
					if(PayConstant.WX_PAY_RETURN_CODE_SUCCESS.equals(wxRefundOutDto.getReturn_code()) && PayConstant.WX_PAY_RETURN_CODE_SUCCESS.equals(wxRefundOutDto.getResult_code())) {
						wxRefundQuery.setRefundResult(wxRefundOutDto.getRefund_status_0());
						notifyLog.setRefundNo(wxRefundQuery.getRefundNo());
						if(RefundStatus.REFUND_SUCCESS.getName().equals(wxRefundOutDto.getRefund_status_0())) {//退款成功
							//notify
							notifyLog.setNotifyContent(RefundStatus.REFUND_SUCCESS.getName());
							wxRefundQuery.setRefundResult(RefundStatus.REFUND_SUCCESS.getName());
						} else if(RefundStatus.REFUND_PROCESSING.getName().equals(wxRefundOutDto.getRefund_status_0())) {//退款处理中
							//设置下一次查询时间
							Date now = new Date();
							if(wxRefundQuery.getQueryTimes().intValue() < PayConstant.MAX_QUERY_TIMES) {
								long nextNotifyMills = now.getTime() + PayConstant.REFUND_QUERY_MAPS.get(wxRefundQuery.getQueryTimes() + 1);
								wxRefundQuery.setNextDate(new Date(nextNotifyMills));
								wxRefundQuery.setQueryTimes(wxRefundQuery.getQueryTimes() + 1);
							} else {
								wxRefundQuery.setNextDate(null);
							}
						} else if(RefundStatus.REFUND_NOTSURE.getName().equals(wxRefundOutDto.getRefund_status_0())) {//需要重新发起退款
							//notify
							notifyLog.setNotifyContent(RefundStatus.REFUND_NOTSURE.getName());
						} else if(RefundStatus.REFUND_CHANGE.getName().equals(wxRefundOutDto.getRefund_status_0())) {//原卡号被冻结，资金回流到商户账户，做线下退款
							//notify
							notifyLog.setNotifyContent(RefundStatus.REFUND_CHANGE.getName());
							wxRefundQuery.setRefundResult(RefundStatus.REFUND_SUCCESS.getName());
						} else {
							//notify
							wxRefundQuery.setRefundResult(RefundStatus.REFUND_FAIL.getName());
						}
						//更新退款订单状态
						wxRefundQueryService.update(wxRefundQuery);
						
						//除了退款处理中状态，其它需要通知第三方退款结果
						if(!RefundStatus.REFUND_PROCESSING.getName().equals(wxRefundOutDto.getRefund_status_0())) {
							notifyService.notifyRequester(notifyLog);
						}
					}
					//保存返回日志
					WxRefundResponse wxRefundResponse = convertWxRefundResponse(wxRefundOutDto);
					wxRefundResponseService.insert(wxRefundResponse);
				}
			}
		} catch (Exception e) {
			LOGGER.error("wx query refund error:", e);
		}
	}	
	
	private WxRefundResponse convertWxRefundResponse(WxRefundQueryOutDto wxRefundQueryOutDto) {
		WxRefundResponse wxRefundResponse = new WxRefundResponse();
		if(null != wxRefundQueryOutDto) {
			wxRefundResponse.setRefundResponseType(WxRefundType.REFUND_APPLY);
			wxRefundResponse.setResultCode(wxRefundQueryOutDto.getResult_code());
			wxRefundResponse.setReturnMsg(wxRefundQueryOutDto.getReturn_msg());
			wxRefundResponse.setReturnCode(wxRefundQueryOutDto.getReturn_code());
			wxRefundResponse.setErrCode(wxRefundQueryOutDto.getErr_code());
			wxRefundResponse.setErrCodeDes(wxRefundQueryOutDto.getErr_code_des());
			wxRefundResponse.setNonceStr(wxRefundQueryOutDto.getNonce_str());
			wxRefundResponse.setSign(wxRefundQueryOutDto.getSign());
			wxRefundResponse.setTransactionId(wxRefundQueryOutDto.getTransaction_id());
			wxRefundResponse.setTradeNo(wxRefundQueryOutDto.getOut_trade_no());
			wxRefundResponse.setRefundNo(wxRefundQueryOutDto.getOut_refund_no_0());
			wxRefundResponse.setWxRefundNo(wxRefundQueryOutDto.getRefund_id_0());
			wxRefundResponse.setRefundChannel(wxRefundQueryOutDto.getRefund_channel_0());
			if(null != wxRefundQueryOutDto.getRefund_fee()) {
				wxRefundResponse.setRefundFee(new BigDecimal(String.valueOf((double)wxRefundQueryOutDto.getRefund_fee() / 100)));
			}
			if(null != wxRefundQueryOutDto.getTotal_fee()) {
				wxRefundResponse.setTotalFee(new BigDecimal(String.valueOf((double)wxRefundQueryOutDto.getTotal_fee() / 100)));
			}
			if(null != wxRefundQueryOutDto.getCash_fee()) {
				wxRefundResponse.setCashFee(new BigDecimal(String.valueOf((double)wxRefundQueryOutDto.getCash_fee() / 100)));
			}
			wxRefundResponse.setRefundStatus(wxRefundQueryOutDto.getRefund_status_0());
			wxRefundResponse.setCreateDate(new Date());
		}
		return wxRefundResponse;
	}
	
	private WxRefundRequest convertWxRefundRequest(WxRefundDto wxRefundDto,WxRefundQuery wxRefundQuery) {
		WxRefundRequest wxRefundRequest = new WxRefundRequest();
		if(null != wxRefundDto) {
			String appId = PropertyHolder.getProperty("wx.appId");
			String mchId = PropertyHolder.getProperty("wx.matchId");
			wxRefundRequest.setAppid(appId);
			wxRefundRequest.setMchId(mchId);
			wxRefundRequest.setRefundRequestType(WxRefundType.REFUND_QUERY);
			wxRefundRequest.setNonceStr(wxRefundDto.getNonce_str());
			wxRefundRequest.setSign(wxRefundDto.getSign());
			wxRefundRequest.setTransactionId(wxRefundQuery.getTransactionId());
			wxRefundRequest.setTradeNo(wxRefundDto.getOut_trade_no());
			wxRefundRequest.setRefundNo(wxRefundQuery.getRefundNo());
			if(null != wxRefundDto.getTotal_fee()) {
				wxRefundRequest.setTotalFee(new BigDecimal(String.valueOf((double)wxRefundDto.getTotal_fee() / 100)));
			}
			if(null != wxRefundDto.getRefund_fee()) {
				wxRefundRequest.setRefundFee(new BigDecimal(String.valueOf((double)wxRefundDto.getRefund_fee() / 100)));
			}
			wxRefundRequest.setOpUserId(wxRefundDto.getOp_user_id());
			wxRefundRequest.setCreateDate(new Date());
		}
		return wxRefundRequest;
	}
	
}