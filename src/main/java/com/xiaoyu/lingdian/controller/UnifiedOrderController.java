package com.xiaoyu.lingdian.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.lingdian.entity.PayConstant;
import com.xiaoyu.lingdian.entity.PayRequest;
import com.xiaoyu.lingdian.entity.WxOrderCloseResponse;
import com.xiaoyu.lingdian.entity.WxOrderCloseResponseDetail;
import com.xiaoyu.lingdian.enums.PayChannel;
import com.xiaoyu.lingdian.service.AlipayOrderService;
import com.xiaoyu.lingdian.service.PayRecordService;
import com.xiaoyu.lingdian.service.PayRequestService;
import com.xiaoyu.lingdian.service.UnifiedPrePayService;
import com.xiaoyu.lingdian.service.WxOrderCloseService;
import com.xiaoyu.lingdian.service.WxOrderService;
import com.xiaoyu.lingdian.tool.StringUtil;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import com.xiaoyu.lingdian.vo.PrePayOutDto;
import com.xiaoyu.lingdian.vo.RefundDto;
import com.xiaoyu.lingdian.vo.RefundOutDto;
import com.xiaoyu.lingdian.vo.RefundResponseDetail;
import com.xiaoyu.lingdian.vo.UnifiedOrderPayDto;
import com.xiaoyu.lingdian.vo.UnifiedOrderPayOutDto;

@Controller
@RequestMapping("unifiedOrder")
public class UnifiedOrderController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("BASE_LOGGER");
	
	@Autowired
	private WxOrderService wxOrderService; 
	
	@Autowired
	private AlipayOrderService alipayOrderService;
	
	@Autowired
	private WxOrderCloseService wxOrderCloseService;

	@Autowired
	private PayRequestService payRequestService;
	
	@Autowired
	private UnifiedPrePayService unifiedPrePayService;
	
	@Autowired
	private PayRecordService payRecordService;
	
	@RequestMapping("createOrder")
	public void createOrder(@RequestBody String params, HttpServletResponse response) {
		LOGGER.info("UnifiedOrderController.createOrder start");
		PrePayOutDto prePayOutDto = unifiedPrePayService.createPrePayOrder(params);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "success", prePayOutDto), response);
		LOGGER.info("UnifiedOrderController.createOrder end success");
	}
	
	@RequestMapping("updateOrder")
	public void prePay(@RequestBody String params, HttpServletResponse response) {
		LOGGER.info("UnifiedOrderController.prePay start");
		PrePayOutDto prePayOutDto = unifiedPrePayService.updatePrePayOrder(params);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "success", prePayOutDto), response);
		LOGGER.info("UnifiedOrderController.prePay end success");
	}
	
	@RequestMapping("pay")
	public void pay(@RequestBody String params, HttpServletResponse response) {
		LOGGER.info("UnifiedOrderController.pay start");
		UnifiedOrderPayDto unifiedOrderPayDto = JSON.parseObject(params, UnifiedOrderPayDto.class);
		LOGGER.info("unifiedorder pay rquest:{}",params);
		UnifiedOrderPayOutDto orderPayOutDto = new UnifiedOrderPayOutDto();
		
		if(null == unifiedOrderPayDto || StringUtil.isEmpty(unifiedOrderPayDto.getPayNo()) || null == unifiedOrderPayDto.getPayChannel() 
				|| null == unifiedOrderPayDto.getTradeType()) {
			orderPayOutDto.setResultCode(PayConstant.FAIL);
			orderPayOutDto.setErrorCode(PayConstant.ERROR_0001);
			orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0001));
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "success", orderPayOutDto), response);
			LOGGER.info("UnifiedOrderController.pay end success");
			return;
		} 
		//保存支付请求日志
		PayRequest pay = convertPayRequest(unifiedOrderPayDto);
		payRequestService.insert(pay);
		
		if(PayChannel.WX.equals(unifiedOrderPayDto.getPayChannel())) {
			orderPayOutDto = wxOrderService.wxOrderPayHandle(pay);
		} else if(PayChannel.ALIPAY.equals(unifiedOrderPayDto.getPayChannel())) {
			orderPayOutDto = alipayOrderService.aliPayOrderHandle(pay);
		} else {
			orderPayOutDto.setResultCode(PayConstant.FAIL);
			orderPayOutDto.setErrorCode(PayConstant.ERROR_0003);
			orderPayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0003));
		}

		LOGGER.info("unifiedorder pay response:{}",JSON.toJSONString(orderPayOutDto));
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "success", orderPayOutDto), response);
		LOGGER.info("UnifiedOrderController.pay end success");
	}
	
	@RequestMapping("refund")
	public void refund(@RequestBody String params, HttpServletResponse response) {
		LOGGER.info("UnifiedOrderController.refund start");
		LOGGER.info("unifiedorder refund rquest:{}",params);
		
		RefundDto refundDto = JSON.parseObject(params, RefundDto.class);
		RefundOutDto refundOutDto = new  RefundOutDto();
		if(null == refundDto || StringUtil.isEmpty(refundDto.getNotifyUrl()) || refundDto.getDetails().isEmpty() || null == refundDto.getPayChannel()) {
			refundOutDto.setResultCode(PayConstant.FAIL);
			refundOutDto.setErrorCode(PayConstant.ERROR_0001);
			refundOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0001));
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "success", refundOutDto), response);
			LOGGER.info("UnifiedOrderController.refund end success");
			return;
		}
		if(PayChannel.WX.equals(refundDto.getPayChannel())) {
			List<RefundResponseDetail> detailList = new ArrayList<RefundResponseDetail>(); // NOSONAR
			detailList = wxOrderService.wxOrderRefundHandle(refundDto);
			refundOutDto.setDetails(detailList);
		} else {
			refundOutDto = alipayOrderService.aliPayOrderRefundHandle(refundDto);
		}
		LOGGER.info("unifiedorder refund response:{}",JSON.toJSONString(refundOutDto));
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "success", refundOutDto), response);
		LOGGER.info("UnifiedOrderController.refund end success");
	}
	
	@RequestMapping("close")
	public void closeOrder(String orderNos, HttpServletResponse response) {
		LOGGER.info("UnifiedOrderController.closeOrder start");
		LOGGER.info("unifiedorder close rquest:{}",orderNos);
		WxOrderCloseResponse wxOrderCloseResponse = new WxOrderCloseResponse();
		List<WxOrderCloseResponseDetail> list = new ArrayList<WxOrderCloseResponseDetail>();
		if(StringUtil.isEmpty(orderNos)) {
			WxOrderCloseResponseDetail detail = new WxOrderCloseResponseDetail();
			
			detail.setResultCode(PayConstant.FAIL);
			detail.setErrorCode(PayConstant.ERROR_0001);
			detail.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0001));
			list.add(detail);
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "success", wxOrderCloseResponse), response);
			LOGGER.info("UnifiedOrderController.closeOrder end success");
			return;
		}
		String[] orderNoAarray = orderNos.split(",");
		for(String orderNo : orderNoAarray) {
			WxOrderCloseResponseDetail detail = wxOrderCloseService.closeOrder(orderNo);
			list.add(detail);
		}
		wxOrderCloseResponse.setDetails(list);
		LOGGER.info("unifiedorder close rquest:{}",JSON.toJSONString(wxOrderCloseResponse));
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "success", wxOrderCloseResponse), response);
		LOGGER.info("UnifiedOrderController.closeOrder end success");
		return;
	}
	
	private PayRequest convertPayRequest(UnifiedOrderPayDto unifiedOrderPayDto) {
		//支付请求日志记录
		PayRequest payRequest = new PayRequest();
		payRequest.setPayNo(unifiedOrderPayDto.getPayNo());
		payRequest.setPayChannel(unifiedOrderPayDto.getPayChannel());
		payRequest.setTradeType(unifiedOrderPayDto.getTradeType());
		payRequest.setPayTimeout(unifiedOrderPayDto.getPayTimeOut());
		payRequest.setCreateDate(new Date());
		return payRequest;
	}
	
}