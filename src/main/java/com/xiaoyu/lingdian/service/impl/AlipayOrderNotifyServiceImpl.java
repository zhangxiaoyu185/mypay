package com.xiaoyu.lingdian.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.xiaoyu.lingdian.core.spring.adapter.PropertyHolder;
import com.xiaoyu.lingdian.entity.AliPayResponse;
import com.xiaoyu.lingdian.entity.AliRefundResponse;
import com.xiaoyu.lingdian.entity.AlipayNotifyVerify;
import com.xiaoyu.lingdian.entity.NotifyLog;
import com.xiaoyu.lingdian.entity.PayConstant;
import com.xiaoyu.lingdian.entity.PayRecord;
import com.xiaoyu.lingdian.entity.PayRequest;
import com.xiaoyu.lingdian.entity.RefundLog;
import com.xiaoyu.lingdian.enums.NotifyType;
import com.xiaoyu.lingdian.service.AliPayResponseService;
import com.xiaoyu.lingdian.service.AliRefundResponseService;
import com.xiaoyu.lingdian.service.AlipayOrderNotifyService;
import com.xiaoyu.lingdian.service.NotifyLogService;
import com.xiaoyu.lingdian.service.NotifyService;
import com.xiaoyu.lingdian.service.PayCommonService;
import com.xiaoyu.lingdian.service.PayErrorLogService;
import com.xiaoyu.lingdian.service.PayRecordService;
import com.xiaoyu.lingdian.service.RefundLogService;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import com.xiaoyu.lingdian.tool.Utils;
import com.xiaoyu.lingdian.tool.encrypt.MD5;
import com.xiaoyu.lingdian.tool.encrypt.RSA;
import com.xiaoyu.lingdian.tool.http.HttpsUtils;
import com.xiaoyu.lingdian.vo.AlipayOrderNotifyOutDto;
import com.xiaoyu.lingdian.vo.AlipayRefundOutDto;

@Service("alipayOrderNotifyService")
public class AlipayOrderNotifyServiceImpl implements AlipayOrderNotifyService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("BASE_LOGGER");
	
	@Autowired
	private NotifyService notifyService;

	@Autowired
	private AliPayResponseService aliPayResponseService;
	
	@Autowired
	private AliRefundResponseService aliRefundResponseService;
	
	@Autowired
	private NotifyLogService notifyLogService;
	
	@Autowired
	private PayCommonService payCommonService;
	
	@Autowired
	private PayRecordService payRecordService;
	
	@Autowired
	private RefundLogService refundLogService;
	
	@Autowired
	private PayErrorLogService payErrorLogService;
	
	@Override
	public boolean payNotifyHandle(AlipayOrderNotifyOutDto alipayNotifyOutDto) {
		boolean flag = false;
		try {
			if(null != alipayNotifyOutDto && null != alipayNotifyOutDto.getParametersMap()) {
				LOGGER.info("ali pay notify content:{}",alipayNotifyOutDto.toString());
				String partner = PropertyHolder.getProperty("alipay.partner");
				//校验支付宝返回签名
				boolean isSignTrue = verifySign(alipayNotifyOutDto);
				if(isSignTrue) {//签名验证通过
					//校验notifyid
					AlipayNotifyVerify verify = new AlipayNotifyVerify();
					verify.setService(PayConstant.ALIPAY_NOTIFY_VERIFY);
					verify.setPartner(partner);
					verify.setNotify_id(alipayNotifyOutDto.getNotify_id());
					
					MapJoiner joiner =  Joiner.on("&").withKeyValueSeparator("=");
					String params = joiner.join(verify.getParametersMap());
					String getWay = PropertyHolder.getProperty("alipay.getway");
					
					StringBuilder url = new StringBuilder();
					url.append(getWay);
					url.append(params);
					
					String result = HttpsUtils.get(url.toString());
					if(!StringUtil.isEmpty(result) && PayConstant.ALIPAY_NOTIFY_VERIFY_TRUE.equals(result.trim())) {//签名和notify_id均验证通过
						Date payDate = null;
						if(StringUtils.isNotEmpty(alipayNotifyOutDto.getGmt_payment())) {
							payDate = DateUtil.parseDate(alipayNotifyOutDto.getGmt_payment(), DateUtil.TIMESTAMP_PATTERN);
						}
						//本地校验更新
						PayRequest payRequest = new PayRequest();
						payRequest.setOutTradeNo(alipayNotifyOutDto.getTrade_no());
						payRequest.setFee(new BigDecimal(alipayNotifyOutDto.getTotal_fee()));
						payRequest.setPayDate(payDate);
						payRequest.setTradeNo(alipayNotifyOutDto.getOut_trade_no());
						
						boolean isTrue = payCommonService.verifyLocalOrder(payRequest);
						if(isTrue) {
							flag = true;
							
							NotifyLog notifyLog = new NotifyLog();
							notifyLog.setTradeNo(alipayNotifyOutDto.getOut_trade_no());
							notifyLog.setNotifyType(NotifyType.PAY);
							notifyLog.setNotifyContent(PayConstant.SUCCESS);
							notifyLog.setOutTradeNo(alipayNotifyOutDto.getTrade_no());
							notifyLog.setTradeDate(payDate);
							notifyLog.setFee(new BigDecimal(alipayNotifyOutDto.getTotal_fee()));
							notifyService.notifyRequester(notifyLog);
						}
					} else {
						payErrorLogService.savePayErrorLog(alipayNotifyOutDto.getOut_trade_no(), alipayNotifyOutDto.getTrade_no(),
								PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0010));
					}
				} else {
					payErrorLogService.savePayErrorLog(alipayNotifyOutDto.getOut_trade_no(), alipayNotifyOutDto.getTrade_no(),
							PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0009));
				}
				//保存支付宝回调记录
				AliPayResponse aliPayResponse = convertAliPayResponse(alipayNotifyOutDto);
				aliPayResponseService.insert(aliPayResponse);
			}
		} catch(Exception e) {
			LOGGER.error("ali pay notify error:",e);
		}
		return flag;
	}
	
	@Override
	public boolean refundNotifyHandle(AlipayRefundOutDto alipayRefundOutDto) {
		boolean flag = false;
		try {
			if(null != alipayRefundOutDto && null != alipayRefundOutDto.getParametersMap()) {
				LOGGER.info("ali pay notify content:{}",alipayRefundOutDto.toString());
				String key = PropertyHolder.getProperty("alipay.key");
				String partner = PropertyHolder.getProperty("alipay.partner");
				//校验支付宝返回签名
				boolean isSignTrue = MD5.verify(Utils.mapToStr(alipayRefundOutDto.getParametersMap()), alipayRefundOutDto.getSign(), key, PayConstant.CHARSET_UTF8);
				if(isSignTrue) {//签名验证通过
					//校验notifyid
					AlipayNotifyVerify verify = new AlipayNotifyVerify();
					verify.setService(PayConstant.ALIPAY_NOTIFY_VERIFY);
					verify.setPartner(partner);
					verify.setNotify_id(alipayRefundOutDto.getNotify_id());
					
					MapJoiner joiner =  Joiner.on("&").withKeyValueSeparator("=");
					String params = joiner.join(verify.getParametersMap());
					String getWay = PropertyHolder.getProperty("alipay.getway");
					
					StringBuilder url = new StringBuilder();
					url.append(getWay);
					url.append(params);
					
					String result = HttpsUtils.get(url.toString());
					if(!StringUtil.isEmpty(result) && PayConstant.ALIPAY_NOTIFY_VERIFY_TRUE.equals(result.trim())) {
						//签名和notify_id均验证通过
						flag = true;
						
						if(!StringUtil.isEmpty(alipayRefundOutDto.getResult_details())) {
							String[] details = alipayRefundOutDto.getResult_details().split("#");
							for(String detail : details) {
								String[] detailArray = detail.split("\\^");
								String outTradeNo = detailArray[0];
								BigDecimal refundFee = new BigDecimal(detailArray[1]);
								String notifyResult = PayConstant.FAIL;
								if(detailArray.length == 6) {//退手续费结果返回格式：交易号^退款金额^处理结果$退费账号^退费账户ID^退费金额^处理结果
									//取详细信息
									notifyResult = detailArray[5];
								}  else if(detailArray.length == 3) {//不退手续费结果返回格式：交易号^退款金额^处理结果。
									//取详细信息
									notifyResult = detailArray[2];
								} else {
								}
								
								PayRecord payRecord = payRecordService.selectByOutTradeNo(outTradeNo);
								RefundLog refundLog = refundLogService.selectByOutTradeNo(outTradeNo);
								if(null != refundLog) {
									refundLog.setRefundResult(notifyResult);
									refundLog.setRefundFee(refundFee);
									refundLog.setUpdateDate(new Date());
									refundLog.setRefundDate(new Date());
									refundLogService.update(refundLog);
								}
								
								NotifyLog notifyLog = new NotifyLog();
								if(null != payRecord) {
									notifyLog.setOrderNo(payRecord.getOrderNo());
									notifyLog.setOutTradeNo(payRecord.getOutTradeNo());
									notifyLog.setPayNo(payRecord.getPayNo());
								}
								notifyLog.setNotifyType(NotifyType.REFUND);
								notifyLog.setNotifyContent(notifyResult);
								notifyLog.setRefundFee(refundFee);
								notifyLog.setRefundDate(new Date());
								notifyLog.setRefundNo(alipayRefundOutDto.getBatch_no());
								notifyService.notifyRequester(notifyLog);
							}
						}
					}
				}
				//保存支付宝回调记录
				AliRefundResponse aliRefundResponse = convertAliRefundResponse(alipayRefundOutDto);
				aliRefundResponseService.insert(aliRefundResponse);
			}
		} catch(Exception e) {
			LOGGER.error("ali pay notify error:",e);
		}
		return flag;
	}
	
	private boolean verifySign(AlipayOrderNotifyOutDto alipayNotifyOutDto) {
		String signType = alipayNotifyOutDto.getSign_type();
		boolean isSignTrue = false;
		if(PayConstant.ALI_PAY_SIGN_TYPE.equals(signType)) {
			String key = PropertyHolder.getProperty("alipay.key");
			isSignTrue = MD5.verify(Utils.mapToStr(alipayNotifyOutDto.getParametersMap()), alipayNotifyOutDto.getSign(), key, PayConstant.CHARSET_UTF8);
		} else {
			String publicKey = PropertyHolder.getProperty("alipay.publicKey");
			isSignTrue = RSA.verify(Utils.mapToStr(alipayNotifyOutDto.getParametersMap()), publicKey, alipayNotifyOutDto.getSign());
		}
		return isSignTrue;
	}
	
	private AliRefundResponse convertAliRefundResponse(AlipayRefundOutDto alipayRefundOutDto) {
		AliRefundResponse aliRefundResponse = new AliRefundResponse();
		if(null != alipayRefundOutDto.getNotify_time()) {
			aliRefundResponse.setNotifyTime(DateUtil.parseDate(alipayRefundOutDto.getNotify_time(), DateUtil.TIMESTAMP_PATTERN));
		}
		aliRefundResponse.setNotifyType(alipayRefundOutDto.getNotify_type());
		aliRefundResponse.setNotifyId(alipayRefundOutDto.getNotify_id());
		aliRefundResponse.setSignType(alipayRefundOutDto.getSign_type());
		aliRefundResponse.setSign(alipayRefundOutDto.getSign());
		aliRefundResponse.setBatchNo(alipayRefundOutDto.getBatch_no());
		aliRefundResponse.setResultDetails(alipayRefundOutDto.getResult_details());
		aliRefundResponse.setSuccessNum(null == alipayRefundOutDto.getSuccess_num() ? null : Integer.valueOf(alipayRefundOutDto.getSuccess_num()));
		aliRefundResponse.setCreateDate(new Date());
		
		return aliRefundResponse;
	}
	
	private AliPayResponse convertAliPayResponse(AlipayOrderNotifyOutDto alipayNotifyOutDto) {
		AliPayResponse aliPayResponse = new AliPayResponse();
		if(null != alipayNotifyOutDto) {
			if(!StringUtil.isEmpty(alipayNotifyOutDto.getNotify_time())) {
				aliPayResponse.setNotifyDate(DateUtil.parseDate(alipayNotifyOutDto.getNotify_time(), DateUtil.TIMESTAMP_PATTERN));
			}
			aliPayResponse.setNotifyType(alipayNotifyOutDto.getNotify_type());
			aliPayResponse.setNotifyId(alipayNotifyOutDto.getNotify_id());
			aliPayResponse.setSignType(alipayNotifyOutDto.getSign_type());
			aliPayResponse.setSign(alipayNotifyOutDto.getSign());
			aliPayResponse.setTradeNo(alipayNotifyOutDto.getOut_trade_no());
			aliPayResponse.setSubject(alipayNotifyOutDto.getSubject());
			if(!StringUtil.isEmpty(alipayNotifyOutDto.getPayment_type())) {
				aliPayResponse.setPaymentType(Integer.parseInt(alipayNotifyOutDto.getPayment_type()));
			}
			aliPayResponse.setOutTradeNo(alipayNotifyOutDto.getTrade_no());
			aliPayResponse.setTradeStatus(alipayNotifyOutDto.getTrade_status());
			if(!StringUtil.isEmpty(alipayNotifyOutDto.getGmt_create())) {
				aliPayResponse.setGmtCreate(DateUtil.parseDate(alipayNotifyOutDto.getGmt_create(), DateUtil.TIMESTAMP_PATTERN));
			}
			if(!StringUtil.isEmpty(alipayNotifyOutDto.getGmt_payment())) {
				aliPayResponse.setGmtPayment(DateUtil.parseDate(alipayNotifyOutDto.getGmt_payment(), DateUtil.TIMESTAMP_PATTERN));
			}
			if(!StringUtil.isEmpty(alipayNotifyOutDto.getGmt_close())) {
				aliPayResponse.setGmtClose(DateUtil.parseDate(alipayNotifyOutDto.getGmt_close(), DateUtil.TIMESTAMP_PATTERN));
			}
			aliPayResponse.setRefundStatus(alipayNotifyOutDto.getTrade_status());
			if(!StringUtil.isEmpty(alipayNotifyOutDto.getGmt_refund())) {
				aliPayResponse.setGmtRefund(DateUtil.parseDate(alipayNotifyOutDto.getGmt_refund(), DateUtil.TIMESTAMP_PATTERN));
			}
			aliPayResponse.setSellerId(alipayNotifyOutDto.getSeller_id());
			aliPayResponse.setBuyerId(alipayNotifyOutDto.getBuyer_id());
			aliPayResponse.setTotalFee(new BigDecimal(alipayNotifyOutDto.getTotal_fee()));
			aliPayResponse.setBody(alipayNotifyOutDto.getBody());
			aliPayResponse.setOutChannelInst(alipayNotifyOutDto.getOut_channel_inst());
			aliPayResponse.setBusinessScene(alipayNotifyOutDto.getBusiness_scene());
			aliPayResponse.setSellerId(alipayNotifyOutDto.getSeller_id());
			aliPayResponse.setSellerEmail(alipayNotifyOutDto.getSeller_email());
			aliPayResponse.setUseCoupon(alipayNotifyOutDto.getUse_coupon());
			aliPayResponse.setCreateDate(new Date());
		}
		return aliPayResponse;
	}
	
}