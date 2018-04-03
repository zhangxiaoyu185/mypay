package com.xiaoyu.lingdian.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.spring.adapter.PropertyHolder;
import com.xiaoyu.lingdian.entity.NotifyLog;
import com.xiaoyu.lingdian.entity.PayConstant;
import com.xiaoyu.lingdian.entity.PayRequest;
import com.xiaoyu.lingdian.entity.WxPayResponse;
import com.xiaoyu.lingdian.enums.NotifyType;
import com.xiaoyu.lingdian.enums.WxResponseType;
import com.xiaoyu.lingdian.service.NotifyService;
import com.xiaoyu.lingdian.service.PayCommonService;
import com.xiaoyu.lingdian.service.PayErrorLogService;
import com.xiaoyu.lingdian.service.WxNotifyService;
import com.xiaoyu.lingdian.service.WxPayResponseService;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.Utils;
import com.xiaoyu.lingdian.vo.WxNotifyDto;
import com.xiaoyu.lingdian.vo.WxNotifyOutDto;

@Service("wxNotifyService")
public class WxNotifyServiceImpl implements WxNotifyService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("BASE_LOGGER");
	
	@Autowired
	private WxPayResponseService wxPayResponseService;
	
	@Autowired
	private NotifyService notifyService;
	
	@Autowired
	private PayCommonService payCommonService;
	
	@Autowired
	private PayErrorLogService payErrorLogService;
	
	@Override
	public String notifyHandle(String str) {
		WxNotifyDto notifyDto = new WxNotifyDto();
		try {
			LOGGER.info("wx notify response: {}",str);
			if(StringUtils.isEmpty(str)) {
				return null;
			}
			WxNotifyOutDto wxNotifyOutDto = Utils.toBean(str, WxNotifyOutDto.class);
			
			if(null != wxNotifyOutDto && PayConstant.WX_PAY_RETURN_CODE_SUCCESS.equals(wxNotifyOutDto.getResult_code())) {
				//校验签名
				String key = PropertyHolder.getProperty("wx.key");
				String mySign = Utils.md5Sign(wxNotifyOutDto.getParametersMap(), key);
				String wxSign = wxNotifyOutDto.getSign();
				if(!StringUtils.isEmpty(wxSign) && wxSign.equals(mySign)) {
					Date payDate = null;
					double fee = wxNotifyOutDto.getTotal_fee() / 100.0;
					if(StringUtils.isNotEmpty(wxNotifyOutDto.getTime_end())) {
						payDate = DateUtil.parseDate(wxNotifyOutDto.getTime_end(), DateUtil.NOCHAR_PATTERN);
					}
					//本地校验更新
					PayRequest payRequest = new PayRequest();
					payRequest.setOutTradeNo(wxNotifyOutDto.getTransaction_id());
					payRequest.setFee(new BigDecimal(String.valueOf(fee)));
					payRequest.setPayDate(payDate);
					payRequest.setTradeNo(wxNotifyOutDto.getOut_trade_no());
					
					boolean isTrue = payCommonService.verifyLocalOrder(payRequest);
					if(isTrue) {
						notifyDto.setReturn_code(PayConstant.WX_PAY_RETURN_CODE_SUCCESS);
						notifyDto.setReturn_msg(PayConstant.WX_PAY_RETURN_MSG_OK);
						
						//通知
						NotifyLog notifyLog = new NotifyLog();
						notifyLog.setTradeNo(wxNotifyOutDto.getOut_trade_no());
						notifyLog.setNotifyType(NotifyType.PAY);
						notifyLog.setNotifyContent(PayConstant.SUCCESS);
						notifyLog.setOutTradeNo(wxNotifyOutDto.getTransaction_id());
						notifyLog.setTradeDate(payDate);
						notifyLog.setFee(new BigDecimal(String.valueOf((double)wxNotifyOutDto.getTotal_fee() / 100)));
						
						notifyLog.setFee(new BigDecimal(String.valueOf(fee)));
						notifyService.notifyRequester(notifyLog);
					}
				} else {
					payErrorLogService.savePayErrorLog(wxNotifyOutDto.getOut_trade_no(), wxNotifyOutDto.getTransaction_id(),
							PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0009));
				}
				//保存成功回调日志
				if(!StringUtils.isEmpty(wxNotifyOutDto.getAttach())) {
					Integer id = Integer.parseInt(wxNotifyOutDto.getAttach());
					WxPayResponse wxPayResponse = convertWxPayResponse(wxNotifyOutDto);
					wxPayResponse.setId(id);
					wxPayResponseService.insert(wxPayResponse);
				}
			}
			return Utils.toXml(notifyDto);
		} catch(Exception e) {
			LOGGER.error("wx notify hanle error:" , e);
			return null;
		}
	}
	
	private WxPayResponse convertWxPayResponse(WxNotifyOutDto wxNotifyOutDto) {
		WxPayResponse wxPayResponse = new WxPayResponse();
		if(null != wxNotifyOutDto) {
			wxPayResponse.setType(WxResponseType.NOTIFY_RETURN);
			wxPayResponse.setNonceStr(wxNotifyOutDto.getNonce_str());
			wxPayResponse.setSign(wxNotifyOutDto.getSign());
			wxPayResponse.setResultCode(wxNotifyOutDto.getResult_code());
			wxPayResponse.setReturnCode(wxNotifyOutDto.getReturn_code());
			wxPayResponse.setReturnMsg(wxNotifyOutDto.getReturn_msg());
			wxPayResponse.setErrCode(wxNotifyOutDto.getErr_code());
			wxPayResponse.setErrCodeDes(wxNotifyOutDto.getErr_code_des());
			wxPayResponse.setOpenid(wxNotifyOutDto.getOpenid());
			wxPayResponse.setIsSubscribe(wxNotifyOutDto.getIs_subscribe());
			wxPayResponse.setTradeType(wxNotifyOutDto.getTrade_type());
			wxPayResponse.setBankType(wxNotifyOutDto.getBank_type());
			wxPayResponse.setTotalFee(new BigDecimal(String.valueOf((double)wxNotifyOutDto.getTotal_fee() / 100)));
			wxPayResponse.setFeeType(wxNotifyOutDto.getFee_type());
			if(null != wxNotifyOutDto.getCash_fee()) {
				wxPayResponse.setCashFee(new BigDecimal(String.valueOf((double)wxNotifyOutDto.getCash_fee() / 100)));
			}
			wxPayResponse.setCashFeeType(wxNotifyOutDto.getCash_fee_type());
			if(null != wxNotifyOutDto.getCoupon_fee()) {
				wxPayResponse.setCouponFee(new BigDecimal(String.valueOf((double)wxNotifyOutDto.getCoupon_fee() / 100)));
			}
			wxPayResponse.setCouponCount(wxNotifyOutDto.getCoupon_count());
			wxPayResponse.setCouponIds(wxNotifyOutDto.getCoupon_id_$n());
			wxPayResponse.setTransactionId(wxNotifyOutDto.getTransaction_id());
			wxPayResponse.setTradeNo(wxNotifyOutDto.getOut_trade_no());
			wxPayResponse.setCreateTime(new Date());
			if(StringUtils.isEmpty(wxNotifyOutDto.getTime_end())) {
				wxPayResponse.setTimeEnd(DateUtil.parseDate(wxNotifyOutDto.getTime_end(), DateUtil.TIMESTAMP_PATTERN));
			}
		}
		return wxPayResponse;
	}
	
}