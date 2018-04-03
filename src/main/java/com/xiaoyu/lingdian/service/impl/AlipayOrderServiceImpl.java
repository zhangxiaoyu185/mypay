package com.xiaoyu.lingdian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.xiaoyu.lingdian.core.spring.adapter.PropertyHolder;
import com.xiaoyu.lingdian.entity.AliPayRequest;
import com.xiaoyu.lingdian.entity.AliRefundRequest;
import com.xiaoyu.lingdian.entity.PayConstant;
import com.xiaoyu.lingdian.entity.PayParams;
import com.xiaoyu.lingdian.entity.PayRecord;
import com.xiaoyu.lingdian.entity.PayRequest;
import com.xiaoyu.lingdian.entity.RefundLog;
import com.xiaoyu.lingdian.entity.RefundRequestDetail;
import com.xiaoyu.lingdian.service.AliPayRequestService;
import com.xiaoyu.lingdian.service.AliRefundRequestService;
import com.xiaoyu.lingdian.service.AlipayOrderService;
import com.xiaoyu.lingdian.service.NotifyLogService;
import com.xiaoyu.lingdian.service.PayRecordService;
import com.xiaoyu.lingdian.service.PayRequestService;
import com.xiaoyu.lingdian.service.RefundLogService;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import com.xiaoyu.lingdian.tool.Utils;
import com.xiaoyu.lingdian.tool.encrypt.MD5;
import com.xiaoyu.lingdian.tool.encrypt.RSA;
import com.xiaoyu.lingdian.vo.AlipayDirectOrderDto;
import com.xiaoyu.lingdian.vo.AlipayRefundDto;
import com.xiaoyu.lingdian.vo.RefundDto;
import com.xiaoyu.lingdian.vo.RefundOutDto;
import com.xiaoyu.lingdian.vo.RefundResponseDetail;
import com.xiaoyu.lingdian.vo.UnifiedOrderPayOutDto;

@Service("alipayOrderService")
public class AlipayOrderServiceImpl implements AlipayOrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("BASE_LOGGER");
	
	@Autowired
	private AliPayRequestService aliPayRequestService;
	
	@Autowired
	private NotifyLogService notifyLogService;
	
	@Autowired
	private AliRefundRequestService aliRefundRequestService;
	
	@Autowired
	private PayRecordService payRecordService;
	
	@Autowired
	private PayRequestService payRequestService;
	
	@Autowired
	private RefundLogService refundLogService;
	
	@Override
	public UnifiedOrderPayOutDto aliPayOrderHandle(PayRequest payRequest) {
		UnifiedOrderPayOutDto orderPayOutDto = new UnifiedOrderPayOutDto();
		try {
			PayRecord payRecord = payRecordService.selectByPayNo(payRequest.getPayNo());
			if(null != payRecord) {
				if(StringUtils.isNotEmpty(payRecord.getPayResult()) && PayConstant.SUCCESS.equals(payRecord.getPayResult())) {//平台单号已经支付成功
					orderPayOutDto.setResultCode(PayConstant.FAIL);
					orderPayOutDto.setErrorCode(PayConstant.ERROR_0008);
					orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0008));
				} else {
					aliPayOrder(payRecord,payRequest,orderPayOutDto);
				}
			} else {//平台单号不存在
				orderPayOutDto.setResultCode(PayConstant.FAIL);
				orderPayOutDto.setErrorCode(PayConstant.ERROR_0006);
				orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0006));
			}
		} catch (Exception e) {
			orderPayOutDto.setResultCode(PayConstant.FAIL);
			orderPayOutDto.setErrorCode(PayConstant.ERROR_0002);
			orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0002));
			LOGGER.error("handle alipay order log error:",e);
		}
		//更新支付请求记录
		if(null != orderPayOutDto.getPayParams()) {
			payRequest.setPayParams(JSON.toJSONString(orderPayOutDto.getPayParams()));
		}
		payRequest.setResultCode(orderPayOutDto.getResultCode());
		payRequest.setErrorCode(orderPayOutDto.getErrorCode());
		payRequest.setErrorMsg(orderPayOutDto.getErrorMsg());
		payRequest.setUpdateDate(new Date());
		payRequestService.update(payRequest);
		
		return orderPayOutDto;
	}
	
	@Override
	public RefundOutDto aliPayOrderRefundHandle(RefundDto refundDto) {
		RefundOutDto refundOutDto = new RefundOutDto();
		try {
			refundOutDto = aliPayRefund(refundDto);
		} catch(Exception e) {
			LOGGER.error("handle ali refund log error :" ,e);
		}
		return refundOutDto;
	}
	
	private UnifiedOrderPayOutDto aliPayOrder(PayRecord payRecord,PayRequest payRequest,UnifiedOrderPayOutDto orderPayOutDto) {
		orderPayOutDto.setResultCode(PayConstant.FAIL);
		orderPayOutDto.setErrorCode(PayConstant.ERROR_0002);
		orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0002));
		try {
			if(null == payRecord || StringUtil.isEmpty(payRecord.getProductName())
					|| StringUtil.isEmpty(payRecord.getOrderNo()) || null == payRecord.getFee()) {
				orderPayOutDto.setResultCode(PayConstant.FAIL);
				orderPayOutDto.setErrorCode(PayConstant.ERROR_0001);
				orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0001));
				return orderPayOutDto;
			} else {
				String notifyUrl = PropertyHolder.getProperty("alipay.payNotifyUrl");
				
				AlipayDirectOrderDto alipayDirectOrder = new AlipayDirectOrderDto();
				if(PayConstant.ALIPAY_WAP_DIRECT.equals(payRequest.getTradeType().getName())) {
					alipayDirectOrder.setService(PayConstant.ALIPAY_WAP_DIRECT_SERVICE);
				} else if(PayConstant.ALIPAY_APP_DIRECT.equals(payRequest.getTradeType().getName())) {
					alipayDirectOrder.setService(PayConstant.ALIPAY_APP_DIRECT_SERVICE);
				} else {
					alipayDirectOrder.setService(PayConstant.ALIPAY_DIRECT_SERVICE);
				}
				
				String partner = PropertyHolder.getProperty("alipay.partner");
				String sellerEmail = PropertyHolder.getProperty("alipay.sellerEmail");
				
				alipayDirectOrder.setPartner(partner);
				alipayDirectOrder.set_input_charset(PayConstant.CHARSET_UTF8);
				alipayDirectOrder.setNotify_url(notifyUrl);
				alipayDirectOrder.setReturn_url(payRecord.getReturnUrl());
				//支付宝支付直接使用平台单号作为支付流水号
				alipayDirectOrder.setOut_trade_no(payRecord.getPayNo());
				alipayDirectOrder.setSubject(payRecord.getProductName());
				alipayDirectOrder.setPayment_type(PayConstant.ALIPAY_PAYMENT_TYPE_1);
				if(null == payRecord.getAdjustFee()) {
					alipayDirectOrder.setTotal_fee(payRecord.getFee());
					payRequest.setFee(payRecord.getFee());
				} else {
					alipayDirectOrder.setTotal_fee(payRecord.getAdjustFee());
					payRequest.setFee(payRecord.getAdjustFee());
				}
				alipayDirectOrder.setSeller_id(partner);
				alipayDirectOrder.setSeller_email(sellerEmail);
				alipayDirectOrder.setBody(payRecord.getProductDetail());
				if(null != payRequest.getPayTimeout()) {
					if(payRequest.getPayTimeout().intValue() < PayConstant.PAY_MIN_TIME_OUT) {
						alipayDirectOrder.setIt_b_pay(PayConstant.PAY_MIN_TIME_OUT + "m");//分钟
					} else {
						alipayDirectOrder.setIt_b_pay(payRequest.getPayTimeout() + "m");
					}
				}
				
				String sign = "";
				if(PayConstant.ALIPAY_APP_DIRECT.equals(payRequest.getTradeType().getName())) {
					String privateKey = PropertyHolder.getProperty("alipay.privateKey");
					sign = RSA.sign(Utils.mapToStr(alipayDirectOrder.getParameters()), privateKey);

					alipayDirectOrder.setSign_type(PayConstant.ALI_PAY_SIGN_TYPE_RSA);
				} else {
					String key = PropertyHolder.getProperty("alipay.key");
					sign = MD5.sign(Utils.mapToStr(alipayDirectOrder.getParameters()), key, PayConstant.CHARSET_UTF8);
					
					alipayDirectOrder.setSign_type(PayConstant.ALI_PAY_SIGN_TYPE);
				}
				alipayDirectOrder.setSign(sign);
				
				String htmlStr = Utils.buildRequest(alipayDirectOrder.getParameters(),PayConstant.METHOD_POST,PayConstant.SUBMIT_BT_NAME);
				PayParams payParams = new PayParams();
				if(PayConstant.ALIPAY_APP_DIRECT.equals(payRequest.getTradeType().getName())) {
					payParams.setPayInfo(Utils.mapToStr(alipayDirectOrder.getParameters()));
				} else {
					payParams.setJspText(htmlStr);
				}
				orderPayOutDto.setPayParams(payParams);
				orderPayOutDto.setResultCode(PayConstant.SUCCESS);
				orderPayOutDto.setErrorCode(null);
				orderPayOutDto.setErrorMsg(null);
				//保存请求日志
				AliPayRequest aliPayRequest = convertToAliPayRequest(alipayDirectOrder);
				aliPayRequestService.insert(aliPayRequest);
				
				payRequest.setTradeNo(payRecord.getPayNo());
			}
		} catch (Exception e) {
			orderPayOutDto.setResultCode(PayConstant.FAIL);
			orderPayOutDto.setErrorCode(PayConstant.ERROR_0002);
			orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0002));
			LOGGER.error("handle alipay order params error:",e);
		}
		return orderPayOutDto;
	}
	
	@Override
	public RefundOutDto aliPayRefund(RefundDto refundDto) {
		RefundOutDto refundOutDto = new RefundOutDto();
		List<RefundResponseDetail> responseDetails = new ArrayList<RefundResponseDetail>();
		RefundLog refundLog = new RefundLog();
		try {
			String notifyUrl = PropertyHolder.getProperty("alipay.refundNotifyUrl");
			String partner = PropertyHolder.getProperty("alipay.partner");
			String sellerEmail = PropertyHolder.getProperty("alipay.sellerEmail");
			
			Integer lastId = refundLogService.selectLastId();
			String refunNo = Utils.createRefundNo(lastId);
			
			AlipayRefundDto alipayRefundDto = new AlipayRefundDto();
			alipayRefundDto.setService(PayConstant.ALIPAY_REFUND_SERVICE);
			alipayRefundDto.setPartner(partner);
			alipayRefundDto.set_input_charset(PayConstant.CHARSET_UTF8);
			alipayRefundDto.setNotify_url(notifyUrl);
			alipayRefundDto.setSeller_email(sellerEmail);
			alipayRefundDto.setSeller_id(partner);
			alipayRefundDto.setRefund_date(DateUtil.formatDate(DateUtil.TIMESTAMP_PATTERN, new Date()));
			alipayRefundDto.setBatch_no(refunNo);
			alipayRefundDto.setBatch_num(String.valueOf(refundDto.getDetails().size()));
			
			StringBuilder sb = new StringBuilder();
			for(RefundRequestDetail detail : refundDto.getDetails()) {
				//查询第三方交易单号
				PayRecord payRecord = payRecordService.selectByPayNo(detail.getPayNo());
				//保存请求退款日志记录
				refundLog = convertRefundLog(detail, payRecord, refundDto.getNotifyUrl(), refunNo);
				refundLogService.insert(refundLog);
				
				RefundResponseDetail responseDetail = new RefundResponseDetail();
				if(null != payRecord && !StringUtil.isEmpty(payRecord.getOutTradeNo())) {
					sb.append(payRecord.getOutTradeNo()).append("^");
					sb.append(detail.getRefundFee()).append("^");
					sb.append(detail.getRefundCause()).append("#");
					responseDetail.setResultCode(PayConstant.SUCCESS);
					responseDetail.setPayNo(detail.getPayNo());
				} else {
					responseDetail.setResultCode(PayConstant.FAIL);
					responseDetail.setErrorCode(PayConstant.ERROR_0005);
					responseDetail.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0005));
				}
				responseDetails.add(responseDetail);
				
				//更新请求日志
				refundLog.setResultCode(responseDetail.getResultCode());
				refundLog.setErrorCode(responseDetail.getErrorCode());
				refundLog.setErrorMsg(responseDetail.getErrorMsg());
				refundLog.setUpdateDate(new Date());
				refundLogService.update(refundLog);
			}
			String data = sb.toString();
			alipayRefundDto.setDetail_data(data.substring(0, data.length() - 1));
			
			String key = PropertyHolder.getProperty("alipay.key");
			String sign = MD5.sign(Utils.mapToStr(alipayRefundDto.getParameters()), key, PayConstant.CHARSET_UTF8);
			
			alipayRefundDto.setSign_type(PayConstant.ALI_PAY_SIGN_TYPE);
			alipayRefundDto.setSign(sign);
			
			String htmlStr = Utils.buildRequest(alipayRefundDto.getParameters(),PayConstant.METHOD_GET,PayConstant.SUBMIT_BT_NAME);
			refundOutDto.setRefundParams(htmlStr);
			refundOutDto.setDetails(responseDetails);
			refundOutDto.setResultCode(PayConstant.SUCCESS);
			refundOutDto.setErrorCode(null);
			refundOutDto.setErrorMsg(null);
			
			//保存请求日志
			AliRefundRequest aliRefundRequest = convertAliPayResponse(alipayRefundDto);
			aliRefundRequestService.insert(aliRefundRequest);
		} catch (Exception e) {
			refundOutDto.setResultCode(PayConstant.FAIL);
			refundOutDto.setErrorCode(PayConstant.ERROR_0002);
			refundOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0002));
			LOGGER.error("handle alipay refund params error:",e);
		}
		return refundOutDto;
	}
	
	private AliRefundRequest convertAliPayResponse(AlipayRefundDto alipayRefundDto) {
		AliRefundRequest aliRefundRequest = new AliRefundRequest();
		if(null != alipayRefundDto) {
			aliRefundRequest.setService(alipayRefundDto.getService());
			aliRefundRequest.setPartner(alipayRefundDto.getPartner());
			aliRefundRequest.setCharset(alipayRefundDto.get_input_charset());
			aliRefundRequest.setSignType(alipayRefundDto.getSign_type());
			aliRefundRequest.setSign(alipayRefundDto.getSign());
			aliRefundRequest.setNotifyUrl(alipayRefundDto.getNotify_url());
			aliRefundRequest.setSellerEmail(alipayRefundDto.getSeller_email());
			aliRefundRequest.setSellerUserId(alipayRefundDto.getSeller_id());
			aliRefundRequest.setRefundDate(DateUtil.parseDate(alipayRefundDto.getRefund_date(),DateUtil.TIMESTAMP_PATTERN));
			aliRefundRequest.setBatchNo(alipayRefundDto.getBatch_no());
			aliRefundRequest.setBatchNum(Integer.parseInt(alipayRefundDto.getBatch_num()));
			aliRefundRequest.setDetailData(alipayRefundDto.getDetail_data());
			aliRefundRequest.setCreateDate(new Date());
		}
		return aliRefundRequest;
	}
	
	private AliPayRequest convertToAliPayRequest(AlipayDirectOrderDto alipayDirectOrder) {
		AliPayRequest aliPayRequest = new AliPayRequest();
		aliPayRequest.setServiceName(alipayDirectOrder.getService());
		aliPayRequest.setPartner(alipayDirectOrder.getPartner());
		aliPayRequest.setCharset(alipayDirectOrder.get_input_charset());
		aliPayRequest.setSignType(alipayDirectOrder.getSign_type());
		aliPayRequest.setSign(alipayDirectOrder.getSign_type());
		aliPayRequest.setNotifyUrl(alipayDirectOrder.getNotify_url());
		aliPayRequest.setReturnUrl(alipayDirectOrder.getReturn_url());
		aliPayRequest.setErrorNotifyUrl(alipayDirectOrder.getError_notify_url());
		aliPayRequest.setTradeNo(alipayDirectOrder.getOut_trade_no());
		aliPayRequest.setSubject(alipayDirectOrder.getSubject());
		aliPayRequest.setPaymentType(Integer.parseInt(alipayDirectOrder.getPayment_type()));
		aliPayRequest.setFee(alipayDirectOrder.getTotal_fee());
		aliPayRequest.setSellerId(alipayDirectOrder.getSeller_id());
		aliPayRequest.setSellerEmail(alipayDirectOrder.getSeller_email());
		aliPayRequest.setCreateDate(new Date());
		
		return aliPayRequest;
	}
	private RefundLog convertRefundLog(RefundRequestDetail detail,PayRecord payRecord,String notifyUrl,String refundNo) {
		RefundLog refundLog = new RefundLog();
		refundLog.setPayNo(detail.getPayNo());
		refundLog.setRefundCause(detail.getRefundCause());
		refundLog.setApplyRefundFee(detail.getRefundFee());
		refundLog.setTradeNo(payRecord.getTradeNo());
		refundLog.setOutTradeNo(payRecord.getOutTradeNo());
		refundLog.setNotifyUrl(notifyUrl);
		refundLog.setRefundNo(refundNo);
		refundLog.setCreateDate(new Date());
		return refundLog;
	}
	
}