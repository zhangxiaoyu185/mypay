package com.xiaoyu.lingdian.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.xiaoyu.lingdian.core.spring.adapter.PropertyHolder;
import com.xiaoyu.lingdian.entity.PayConstant;
import com.xiaoyu.lingdian.entity.PayParams;
import com.xiaoyu.lingdian.entity.PayRecord;
import com.xiaoyu.lingdian.entity.PayRequest;
import com.xiaoyu.lingdian.entity.RefundLog;
import com.xiaoyu.lingdian.entity.RefundRequestDetail;
import com.xiaoyu.lingdian.entity.WxPayRequest;
import com.xiaoyu.lingdian.entity.WxPayResponse;
import com.xiaoyu.lingdian.entity.WxRefundQuery;
import com.xiaoyu.lingdian.entity.WxRefundRequest;
import com.xiaoyu.lingdian.entity.WxRefundResponse;
import com.xiaoyu.lingdian.enums.TradeType;
import com.xiaoyu.lingdian.enums.WxRefundType;
import com.xiaoyu.lingdian.enums.WxResponseType;
import com.xiaoyu.lingdian.service.NotifyLogService;
import com.xiaoyu.lingdian.service.PayRecordService;
import com.xiaoyu.lingdian.service.PayRequestService;
import com.xiaoyu.lingdian.service.RefundLogService;
import com.xiaoyu.lingdian.service.WxOrderService;
import com.xiaoyu.lingdian.service.WxPayRequestService;
import com.xiaoyu.lingdian.service.WxPayResponseService;
import com.xiaoyu.lingdian.service.WxRefundQueryService;
import com.xiaoyu.lingdian.service.WxRefundRequestService;
import com.xiaoyu.lingdian.service.WxRefundResponseService;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import com.xiaoyu.lingdian.tool.Utils;
import com.xiaoyu.lingdian.tool.http.HttpsUtils;
import com.xiaoyu.lingdian.vo.RefundDto;
import com.xiaoyu.lingdian.vo.RefundResponseDetail;
import com.xiaoyu.lingdian.vo.UnifiedOrderPayOutDto;
import com.xiaoyu.lingdian.vo.WxOrderDto;
import com.xiaoyu.lingdian.vo.WxOrderOutDto;
import com.xiaoyu.lingdian.vo.WxRefundDto;
import com.xiaoyu.lingdian.vo.WxRefundOutDto;

@Service("wxOrderService")
@Transactional
public class WxOrderServiceImpl implements WxOrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("BASE_LOGGER");
	
	@Autowired
	private WxPayRequestService wxPayRequestService;
	
	@Autowired
	private WxPayResponseService wxPayResponseService;
	
	@Autowired
	private WxRefundRequestService wxRefundRequestService;
	
	@Autowired
	private WxRefundResponseService wxRefundResponseService;
	
	@Autowired
	private NotifyLogService notifyLogService;
	
	@Autowired
	private WxRefundQueryService wxRefundQueryService;
	
	@Autowired
	private PayRecordService payRecordService;
	
	@Autowired
	private PayRequestService payRequestService;
	
	@Autowired
	private RefundLogService refundLogService;
	
	@Override
	public UnifiedOrderPayOutDto wxOrderPayHandle(PayRequest payRequest) {
		UnifiedOrderPayOutDto orderPayOutDto = new UnifiedOrderPayOutDto();
		try {
			PayRecord payRecord = payRecordService.selectByPayNo(payRequest.getPayNo());
			if(null == payRecord) {
				orderPayOutDto.setResultCode(PayConstant.FAIL);
				orderPayOutDto.setErrorCode(PayConstant.ERROR_0006);
				orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0006));
				return orderPayOutDto;
			}
			if(StringUtils.isNotEmpty(payRecord.getPayResult()) && PayConstant.SUCCESS.equals(payRecord.getPayResult())) {//平台单号已经支付成功
				orderPayOutDto.setResultCode(PayConstant.FAIL);
				orderPayOutDto.setErrorCode(PayConstant.ERROR_0008);
				orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0008));
			} else {
				Integer count = payRequestService.countByPayNoAndPayChannel(payRequest);
				if(null == count) {
					count = 0;
				}
				//微信交易流水号格式:payNo_x(x代表该平台号使用该渠道发起的支付次数)
				String tradeNo = payRequest.getPayNo() + "_" + count;
				payRequest.setTradeNo(tradeNo);
				if(null != payRecord.getAdjustFee()) {
					payRequest.setFee(payRecord.getAdjustFee());
				} else {
					payRequest.setFee(payRecord.getFee());
				}
				if(TradeType.WX_NATIVE.equals(payRequest.getTradeType())) {
					orderPayOutDto = wxNativePay(payRequest,payRecord,orderPayOutDto);
				} else if(TradeType.WX_JSAPI.equals(payRequest.getTradeType())) {
					orderPayOutDto = wxJsapiPay(payRequest,payRecord,orderPayOutDto);
				} else if(TradeType.WX_APP.equals(payRequest.getTradeType())) {
					orderPayOutDto = wxAppPay(payRequest,payRecord,orderPayOutDto);
				} else {
					orderPayOutDto.setResultCode(PayConstant.FAIL);
					orderPayOutDto.setErrorCode(PayConstant.ERROR_0004);
					orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0004));
				}
			}
		} catch (Exception e) {
			orderPayOutDto.setResultCode(PayConstant.FAIL);
			orderPayOutDto.setErrorCode(PayConstant.ERROR_0002);
			orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0002));
			LOGGER.error("handle wx pay log error :" ,e);
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
	public List<RefundResponseDetail> wxOrderRefundHandle(RefundDto refundDto) {
		List<RefundResponseDetail> detailList = new ArrayList<RefundResponseDetail>();
		try {
			List<RefundRequestDetail> list = refundDto.getDetails();
			for(RefundRequestDetail detail : list) {
				if(null != detail) {
					RefundResponseDetail responseDetail = wxRefund(detail,refundDto.getNotifyUrl());
					detailList.add(responseDetail);
				}
			}
		} catch(Exception e) {
			LOGGER.error("handle wx pay log error :" ,e);
		}
		return detailList;
	}
	
	private UnifiedOrderPayOutDto wxNativePay(PayRequest payRequest,PayRecord payRecord,UnifiedOrderPayOutDto orderPayOutDto) {
		orderPayOutDto.setResultCode(PayConstant.FAIL);
		orderPayOutDto.setErrorCode(PayConstant.ERROR_0002);
		orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0002));
		try {
			if(null == payRecord || null == payRequest || StringUtils.isEmpty(payRequest.getTradeNo())) {
				return orderPayOutDto;
			}
			WxOrderOutDto wxOrderOutDto = unifiedorder(payRequest,payRecord,TradeType.WX_NATIVE);
			if(null != wxOrderOutDto && !StringUtil.isEmpty(wxOrderOutDto.getCode_url())) {
				PayParams payParams = new PayParams();
				payParams.setCodeUrl(wxOrderOutDto.getCode_url());
				orderPayOutDto.setPayParams(payParams);
				orderPayOutDto.setResultCode(PayConstant.SUCCESS);
				orderPayOutDto.setErrorCode(null);
				orderPayOutDto.setErrorMsg(null);
			}
		} catch(Exception e) {
			LOGGER.error("wx native pay get code_url error :" ,e);
		}
		return orderPayOutDto;
	}
	
	private UnifiedOrderPayOutDto wxJsapiPay(PayRequest payRequest,PayRecord payRecord,UnifiedOrderPayOutDto orderPayOutDto) {
		orderPayOutDto.setResultCode(PayConstant.FAIL);
		orderPayOutDto.setErrorCode(PayConstant.ERROR_0002);
		orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0002));
		try {
			if(null == payRecord || null == payRequest || StringUtils.isEmpty(payRequest.getTradeNo())) {
				return orderPayOutDto;
			}
			WxOrderOutDto wxOrderOutDto = unifiedorder(payRequest,payRecord,TradeType.WX_JSAPI);
			if(null != wxOrderOutDto && !StringUtil.isEmpty(wxOrderOutDto.getPrepay_id())) {
				PayParams payParams = new PayParams();
				payParams.setAppid(wxOrderOutDto.getAppid());
				//生成用于jsapi支付的参数
				payParams.setNonceStr(Utils.getRandomString(PayConstant.WX_PAY_NONCE_STR_LENGTH));
				payParams.setSignType(PayConstant.WX_PAY_SIGN);
				//取当前秒数
				payParams.setTimestampStr(Long.toString(new Date().getTime() / 1000));
				payParams.setPackageStr(PayConstant.WX_PAY_JSAPI_PACKAGE_PREFIX + wxOrderOutDto.getPrepay_id());
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("appId", payParams.getAppid());
				map.put("timeStamp", payParams.getTimestampStr());
				map.put("nonceStr", payParams.getNonceStr());
				map.put("package", payParams.getPackageStr());
				map.put("signType", payParams.getSignType());
				
				String key = PropertyHolder.getProperty("wx.key");
				String sign = Utils.md5Sign(map, key);
				payParams.setPaySign(sign);
				
				orderPayOutDto.setPayParams(payParams);
				orderPayOutDto.setResultCode(PayConstant.SUCCESS);
				orderPayOutDto.setErrorCode(null);
				orderPayOutDto.setErrorMsg(null);
			}
		} catch (Exception e) {
			LOGGER.error("wx jsapi pay get code_url error :" ,e);
		}
		return orderPayOutDto;
	}
	
	private UnifiedOrderPayOutDto wxAppPay(PayRequest payRequest,PayRecord payRecord,UnifiedOrderPayOutDto orderPayOutDto) {
		orderPayOutDto.setResultCode(PayConstant.FAIL);
		orderPayOutDto.setErrorCode(PayConstant.ERROR_0002);
		orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0002));
		try {
			if(null == payRecord || null == payRequest || StringUtils.isEmpty(payRequest.getTradeNo())) {
				return orderPayOutDto;
			}
			WxOrderOutDto wxOrderOutDto = unifiedorder(payRequest,payRecord,TradeType.WX_APP);
			if(null != wxOrderOutDto && !StringUtil.isEmpty(wxOrderOutDto.getPrepay_id())) {
				PayParams payParams = new PayParams();
				payParams.setAppid(wxOrderOutDto.getAppid());
				payParams.setPartnerid(wxOrderOutDto.getMch_id());
				payParams.setPrepayid(wxOrderOutDto.getPrepay_id());
				//生成用于jsapi支付的参数
				payParams.setNonceStr(Utils.getRandomString(PayConstant.WX_PAY_NONCE_STR_LENGTH));
				//取当前秒数
				payParams.setTimestampStr(Long.toString(new Date().getTime() / 1000));
				payParams.setPackageStr("Sign=WXPay");
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("appId", payParams.getAppid());
				map.put("partnerid", payParams.getPartnerid());
				map.put("prepayid", payParams.getPrepayid());
				map.put("timestamp", payParams.getTimestampStr());
				map.put("noncestr", payParams.getNonceStr());
				map.put("package", payParams.getPackageStr());
				
				String key = PropertyHolder.getProperty("wx.key");
				String sign = Utils.md5Sign(map, key);
				payParams.setPaySign(sign);
				
				orderPayOutDto.setPayParams(payParams);
				orderPayOutDto.setResultCode(PayConstant.SUCCESS);
				orderPayOutDto.setErrorCode(null);
				orderPayOutDto.setErrorMsg(null);
			}
		} catch (Exception e) {
			LOGGER.error("wx jsapi pay get code_url error :" ,e);
		}
		return orderPayOutDto;
	}
	
	private WxOrderOutDto unifiedorder(PayRequest payRequest,PayRecord payRecord,TradeType tradeType) {
		WxOrderOutDto wxOrderOutDto = new WxOrderOutDto();
		try {
			WxOrderDto wxOrder = convertOrderParams(payRequest,payRecord,tradeType);
			//保存微信请求日志
			WxPayRequest wxPayRequest = convertWxPayRequest(wxOrder);
			wxPayRequest.setSign(PayConstant.WX_PAY_SIGN);
			wxPayRequestService.insert(wxPayRequest);
			if(null != wxPayRequest.getId()) {
				wxOrder.setAttach(wxPayRequest.getId().toString());
			}
			String key = "";
			if(TradeType.WX_APP.equals(payRequest.getTradeType())) {
				key = PropertyHolder.getProperty("wx.ourselfKey");
			} else {
				key = PropertyHolder.getProperty("wx.key");
			}
			String signStr = Utils.md5Sign(wxOrder.getParametersMap(), key);
			wxOrder.setSign(signStr);
			
			String url = PropertyHolder.getProperty("wx.unifiedorder");
			
			LOGGER.info("wx unified order request param:{}",wxOrder.toString());
			String result = HttpsUtils.post(url, Utils.toXml(wxOrder));
			LOGGER.info("wx unified order response:{}",result);
			
			if(StringUtils.isEmpty(result)) {
				return null;
			}
			
			wxOrderOutDto = Utils.toBean(result, WxOrderOutDto.class);
			//保存返回日志
			if(null != wxPayRequest.getId()) {
				wxPayResponseService.insert(convertWxPayResponse(wxPayRequest.getId(), wxOrderOutDto));
			} 
		}  catch (Exception e) {
			LOGGER.error(" call wx unified order error:" ,e);
		}
		return wxOrderOutDto;
	}
	
	@Override
	public RefundResponseDetail wxRefund(RefundRequestDetail requestDetail,String notifyUrl) {
		RefundResponseDetail refundResponseDetail = new RefundResponseDetail();
		refundResponseDetail.setPayNo(requestDetail.getPayNo());
		RefundLog refundLog = new RefundLog();
		
		refundResponseDetail.setResultCode(PayConstant.FAIL);
		refundResponseDetail.setErrorCode(PayConstant.ERROR_0002);
		refundResponseDetail.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0002));
		try {
			if(StringUtil.isEmpty(requestDetail.getPayNo()) || null == requestDetail.getRefundFee()
					|| StringUtil.isEmpty(requestDetail.getRefundCause())) {
				refundResponseDetail.setResultCode(PayConstant.FAIL);
				refundResponseDetail.setErrorCode(PayConstant.ERROR_0001);
				refundResponseDetail.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0001));
				return refundResponseDetail;
			} 
			PayRecord payRecord = payRecordService.selectByPayNo(requestDetail.getPayNo());
			if(null != payRecord && PayConstant.SUCCESS.equals(payRecord.getPayResult())) {
				Integer lastId = refundLogService.selectLastId();
				String refunNo = Utils.createRefundNo(lastId);
				//保存请求退款日志记录
				refundLog = convertRefundLog(requestDetail, payRecord, notifyUrl, refunNo);
				refundLogService.insert(refundLog);
				
				BigDecimal totalFee = null == payRecord.getAdjustFee() ? payRecord.getFee() : payRecord.getAdjustFee();
				
				WxRefundDto wxRefundDto = convertWxRefundDto(payRecord, refunNo, requestDetail.getRefundFee(), totalFee);
				String key = "";
				if(TradeType.WX_APP.equals(payRecord.getTradeType())) {
					key = PropertyHolder.getProperty("wx.ourselfKey");
				} else {
					key = PropertyHolder.getProperty("wx.key");
				}
				String signStr = Utils.md5Sign(wxRefundDto.getParametersMap(), key);
				wxRefundDto.setSign(signStr);
				
				//保存请求记录
				WxRefundRequest wxRefundRequest = convertWxRefundRequest(wxRefundDto);
				wxRefundRequest.setTransactionId(payRecord.getOutTradeNo());
				wxRefundRequestService.insert(wxRefundRequest);
				
				String certPwd = PropertyHolder.getProperty("wx.certPwd");
				String certPath = PropertyHolder.getProperty("wx.certPath");
				
				String url = PropertyHolder.getProperty("wx.refund");
				LOGGER.info("call wx refund request param:{}",wxRefundDto.toString());
				String result = HttpsUtils.sendHttpsWithCert(url, Utils.toXml(wxRefundDto), certPwd, certPath);
				LOGGER.info("call wx refund response:{}",result);
				
				if(StringUtils.isEmpty(result)) {
					return refundResponseDetail;
				}
				
				WxRefundOutDto wxRefundOutDto = Utils.toBean(result, WxRefundOutDto.class);
				if(PayConstant.WX_PAY_RETURN_CODE_SUCCESS.equals(wxRefundOutDto.getReturn_code()) && PayConstant.WX_PAY_RETURN_CODE_SUCCESS.equals(wxRefundOutDto.getResult_code())) {
					refundResponseDetail.setResultCode(PayConstant.SUCCESS);
					refundResponseDetail.setErrorCode(null);
					refundResponseDetail.setErrorMsg(null);
					//保存退款记录至查询表，供查询退款状态使用
					saveRefundQuery(wxRefundOutDto,payRecord.getOutTradeNo());
				}
				//保存返回日志
				WxRefundResponse wxRefundResponse = convertWxRefundResponse(wxRefundOutDto);
				wxRefundResponseService.insert(wxRefundResponse);
			} else {
				refundResponseDetail.setResultCode(PayConstant.FAIL);
				refundResponseDetail.setErrorCode(PayConstant.ERROR_0005);
				refundResponseDetail.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0005));
				LOGGER.warn(" call wx refund error:no pay successed payNo info");
			}
		} catch(Exception e) {
			refundResponseDetail.setResultCode(PayConstant.FAIL);
			refundResponseDetail.setErrorCode(PayConstant.ERROR_0002);
			refundResponseDetail.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0002));
			LOGGER.error(" call wx refund error:" ,e);
		}
		//更新请求日志
		refundLog.setResultCode(refundResponseDetail.getResultCode());
		refundLog.setErrorCode(refundResponseDetail.getErrorCode());
		refundLog.setErrorMsg(refundResponseDetail.getErrorMsg());
		refundLog.setUpdateDate(new Date());
		refundLogService.update(refundLog);
		
		return refundResponseDetail;
	}
	
	private void saveRefundQuery(WxRefundOutDto wxRefundOutDto,String tradeNo) {
		WxRefundQuery wxRefundQuery = new WxRefundQuery();
		if(null != wxRefundOutDto) {
			wxRefundQuery.setAppid(wxRefundOutDto.getAppid());
			wxRefundQuery.setMchId(wxRefundOutDto.getMch_id());
			wxRefundQuery.setTradeNo(wxRefundOutDto.getOut_trade_no());
			wxRefundQuery.setRefundNo(wxRefundOutDto.getOut_refund_no());
			wxRefundQuery.setTransactionId(tradeNo);
			wxRefundQuery.setQueryTimes(1);//设置第一次查询
			//设置第一次查询时间
			Date now = new Date();
			long nextNotifyMills = now.getTime() + PayConstant.REFUND_QUERY_MAPS.get(1);
			wxRefundQuery.setNextDate(new Date(nextNotifyMills));
			
			wxRefundQuery.setCreateDate(new Date());
			wxRefundQuery.setUpdateDate(new Date());
			
			wxRefundQueryService.insert(wxRefundQuery);
		}
	}
	
	private WxRefundRequest convertWxRefundRequest(WxRefundDto wxRefundDto) {
		WxRefundRequest wxRefundRequest = new WxRefundRequest();
		if(null != wxRefundDto) {
			wxRefundRequest.setAppid(wxRefundDto.getAppid());
			wxRefundRequest.setMchId(wxRefundDto.getMch_id());
			wxRefundRequest.setDeviceInfo(PayConstant.WX_PAY_DEVICE_INFO);
			wxRefundRequest.setRefundRequestType(WxRefundType.REFUND_APPLY);
			wxRefundRequest.setNonceStr(wxRefundDto.getNonce_str());
			wxRefundRequest.setSign(wxRefundDto.getSign());
			wxRefundRequest.setTransactionId(wxRefundDto.getTransaction_id());
			wxRefundRequest.setTradeNo(wxRefundDto.getOut_trade_no());
			wxRefundRequest.setRefundNo(wxRefundDto.getOut_refund_no());
			wxRefundRequest.setTotalFee(new BigDecimal(String.valueOf((double)wxRefundDto.getTotal_fee() / 100)));
			wxRefundRequest.setRefundFee(new BigDecimal(String.valueOf((double)wxRefundDto.getRefund_fee() / 100)));
			wxRefundRequest.setOpUserId(wxRefundDto.getOp_user_id());
			wxRefundRequest.setCreateDate(new Date());
		}
		return wxRefundRequest;
	}
	
	private WxRefundResponse convertWxRefundResponse(WxRefundOutDto wxRefundOutDto) {
		WxRefundResponse wxRefundResponse = new WxRefundResponse();
		if(null != wxRefundOutDto) {
			wxRefundResponse.setRefundResponseType(WxRefundType.REFUND_APPLY);
			wxRefundResponse.setResultCode(wxRefundOutDto.getResult_code());
			wxRefundResponse.setReturnMsg(wxRefundOutDto.getReturn_msg());
			wxRefundResponse.setReturnCode(wxRefundOutDto.getReturn_code());
			wxRefundResponse.setErrCode(wxRefundOutDto.getErr_code());
			wxRefundResponse.setErrCodeDes(wxRefundOutDto.getErr_code_des());
			wxRefundResponse.setNonceStr(wxRefundOutDto.getNonce_str());
			wxRefundResponse.setSign(wxRefundOutDto.getSign());
			wxRefundResponse.setTransactionId(wxRefundOutDto.getTransaction_id());
			wxRefundResponse.setTradeNo(wxRefundOutDto.getOut_trade_no());
			wxRefundResponse.setRefundNo(wxRefundOutDto.getOut_refund_no());
			wxRefundResponse.setWxRefundNo(wxRefundOutDto.getRefund_id());
			wxRefundResponse.setRefundChannel(wxRefundOutDto.getRefund_channel());
			if(null != wxRefundOutDto.getRefund_fee()) {
				wxRefundResponse.setRefundFee(new BigDecimal(String.valueOf((double)wxRefundOutDto.getRefund_fee() / 100)));
			}
			if(null != wxRefundOutDto.getTotal_fee()) {
				wxRefundResponse.setTotalFee(new BigDecimal(String.valueOf((double)wxRefundOutDto.getTotal_fee() / 100)));
			}
			if(null != wxRefundOutDto.getCash_fee()) {
				wxRefundResponse.setCashFee(new BigDecimal(String.valueOf((double)wxRefundOutDto.getCash_fee() / 100)));
			}
			if(null != wxRefundOutDto.getCash_refund_fee()) {
				wxRefundResponse.setCashRefundFee(new BigDecimal(String.valueOf((double)wxRefundOutDto.getCash_refund_fee() / 100)));
			}
			wxRefundResponse.setFeeType(wxRefundOutDto.getFee_type());
			if(null != wxRefundOutDto.getCoupon_refund_fee()) {
				wxRefundResponse.setCouponRefundFee(new BigDecimal(String.valueOf((double)wxRefundOutDto.getCoupon_refund_fee() / 100)));
			}
			wxRefundResponse.setCouponRefundCount(wxRefundOutDto.getCoupon_refund_count());
			wxRefundResponse.setCouponRefundId(wxRefundOutDto.getCoupon_refund_id());
			wxRefundResponse.setCreateDate(new Date());
		}
		return wxRefundResponse;
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
	
	private WxRefundDto convertWxRefundDto(PayRecord payRecord, String refundNo,BigDecimal refundFee,BigDecimal totalFee) {
		String appId = "";
		String mchId = "";
		if(TradeType.WX_APP.equals(payRecord.getTradeType())) {
			appId = PropertyHolder.getProperty("wx.ourselfAppId");
			mchId = PropertyHolder.getProperty("wx.ourselfMatchId");
		} else {
			appId = PropertyHolder.getProperty("wx.appId");
			mchId = PropertyHolder.getProperty("wx.matchId");
		}
		
		WxRefundDto wxRefundDto = new WxRefundDto();
		wxRefundDto.setAppid(appId);
		wxRefundDto.setMch_id(mchId);
		wxRefundDto.setDevice_info(PayConstant.WX_PAY_DEVICE_INFO);
		wxRefundDto.setNonce_str(Utils.getRandomString(PayConstant.WX_PAY_NONCE_STR_LENGTH));
		wxRefundDto.setOut_trade_no(payRecord.getTradeNo());
		wxRefundDto.setOut_refund_no(refundNo);
		
		//把BigDecimal转成整数分
		int unit = 100;
		BigDecimal bd = new BigDecimal(String.valueOf(unit));
		wxRefundDto.setRefund_fee(refundFee.multiply(bd).intValue());
		wxRefundDto.setTotal_fee(totalFee.multiply(bd).intValue());
		wxRefundDto.setOp_user_id(mchId);
		
		return wxRefundDto;
	}
	
	private WxOrderDto convertOrderParams(PayRequest payRequest,PayRecord payRecord,TradeType tradeType) {
		String appId = "";
		String mchId = "";
		if(TradeType.WX_APP.equals(tradeType)) {
			appId = PropertyHolder.getProperty("wx.ourselfAppId");
			mchId = PropertyHolder.getProperty("wx.ourselfMatchId");
		} else {
			appId = PropertyHolder.getProperty("wx.appId");
			mchId = PropertyHolder.getProperty("wx.matchId");
		}
		String notify_url = PropertyHolder.getProperty("wx.notifyUrl");
		
		WxOrderDto wxOrder = new WxOrderDto();
		wxOrder.setAppid(appId);
		wxOrder.setMch_id(mchId);
		wxOrder.setDevice_info(PayConstant.WX_PAY_DEVICE_INFO);
		wxOrder.setNonce_str(Utils.getRandomString(PayConstant.WX_PAY_NONCE_STR_LENGTH));
		wxOrder.setBody(StringUtil.filterUnsafeUnicode(payRecord.getProductName()));
		wxOrder.setDetail(StringUtil.filterUnsafeUnicode(payRecord.getProductDetail()));
		wxOrder.setOut_trade_no(payRequest.getTradeNo());
		//把BigDecimal转成整数分
		int unit = 100;
		BigDecimal bd = new BigDecimal(String.valueOf(unit));
		if(null != payRecord.getAdjustFee()) {
			wxOrder.setTotal_fee(payRecord.getAdjustFee().multiply(bd).intValue());
		} else {
			wxOrder.setTotal_fee(payRecord.getFee().multiply(bd).intValue());
		}
		wxOrder.setSpbill_create_ip(payRecord.getClientIp());
		wxOrder.setNotify_url(notify_url);
		wxOrder.setTrade_type(tradeType.getName());
		wxOrder.setProduct_id(payRecord.getProductId());
		wxOrder.setOpenid(payRecord.getOpenid());
		Date currDate = new Date();
		wxOrder.setTime_start(DateUtil.formatDate(DateUtil.NOCHAR_PATTERN, currDate));
		if(null != payRequest.getPayTimeout()) {
			int payTimeOut = payRequest.getPayTimeout();
			if(payTimeOut < PayConstant.PAY_MIN_TIME_OUT) {
				payTimeOut = PayConstant.PAY_MIN_TIME_OUT;
			}
			long expireDate = currDate.getTime() + payTimeOut * 60 * 1000;
			wxOrder.setTime_expire(DateUtil.formatDate(DateUtil.NOCHAR_PATTERN, new Date(expireDate)));
		}
		return wxOrder;
	}
	
	private WxPayResponse convertWxPayResponse(Integer id, WxOrderOutDto wxOrderOutDto) {
		WxPayResponse wxPayResponse = new WxPayResponse();
		wxPayResponse.setId(id);
		wxPayResponse.setType(WxResponseType.ORDER_RETURN);
		wxPayResponse.setResultCode(wxOrderOutDto.getResult_code());
		wxPayResponse.setReturnMsg(wxOrderOutDto.getReturn_msg());
		wxPayResponse.setReturnCode(wxOrderOutDto.getReturn_code());
		wxPayResponse.setNonceStr(wxOrderOutDto.getNonce_str());
		wxPayResponse.setSign(wxOrderOutDto.getSign());
		wxPayResponse.setErrCode(wxOrderOutDto.getErr_code());
		wxPayResponse.setErrCodeDes(wxOrderOutDto.getErr_code_des());
		wxPayResponse.setTradeType(wxOrderOutDto.getTrade_type());
		wxPayResponse.setPrepayId(wxOrderOutDto.getPrepay_id());
		wxPayResponse.setCodeUrl(wxOrderOutDto.getCode_url());
		wxPayResponse.setCreateTime(new Date());
		return wxPayResponse;
	}
	
	private WxPayRequest convertWxPayRequest(WxOrderDto wxOrder) {
		WxPayRequest wxPayRequest = new WxPayRequest();
		wxPayRequest.setAppid(wxOrder.getAppid());
		wxPayRequest.setMchId(wxOrder.getMch_id());
		wxPayRequest.setDeviceInfo(wxOrder.getDevice_info());
		wxPayRequest.setNonceStr(wxOrder.getNonce_str());
		wxPayRequest.setSign(wxOrder.getSign());
		wxPayRequest.setBody(wxOrder.getBody());
		wxPayRequest.setDetail(wxOrder.getDetail());
		wxPayRequest.setAttach(wxOrder.getAttach());
		wxPayRequest.setTradeNo(wxOrder.getOut_trade_no());
		wxPayRequest.setFeeType(wxOrder.getFee_type());
		wxPayRequest.setTotalFee(new BigDecimal(String.valueOf((double)wxOrder.getTotal_fee() / 100)));
		wxPayRequest.setSpbillCreateIp(wxOrder.getSpbill_create_ip());
		if(!StringUtil.isEmpty(wxOrder.getTime_start())) {
			wxPayRequest.setTimeStart(DateUtil.parseDate(wxOrder.getTime_start(), DateUtil.NOCHAR_PATTERN));
		}
		if(!StringUtil.isEmpty(wxOrder.getTime_expire())) {
			wxPayRequest.setTimeExpire(DateUtil.parseDate(wxOrder.getTime_expire(), DateUtil.NOCHAR_PATTERN));
		}
		wxPayRequest.setGoodsTag(wxOrder.getGoods_tag());
		wxPayRequest.setNotifyUrl(wxOrder.getNotify_url());
		wxPayRequest.setTradeType(wxOrder.getTrade_type());
		wxPayRequest.setProductId(wxOrder.getProduct_id());
		wxPayRequest.setLimitPay(wxOrder.getLimit_pay());
		wxPayRequest.setOpenid(wxOrder.getOpenid());
		wxPayRequest.setCreateDate(new Date());
		return wxPayRequest;
	}
	
}