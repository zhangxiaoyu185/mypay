package com.xiaoyu.lingdian.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xiaoyu.lingdian.entity.PayRequest;
import com.xiaoyu.lingdian.service.WxOrderService;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import com.xiaoyu.lingdian.vo.UnifiedOrderPayOutDto;

@Controller
@RequestMapping("/wxPay")
public class WxPayController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("BASE_LOGGER");
	
	@Autowired
	private WxOrderService wxOrderService;
	
	@RequestMapping(value = "/jsApiPay")
	public void jsApiPay(PayRequest payRequest, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("WxPayController.jsApiPay start");
		UnifiedOrderPayOutDto orderPayOutDto = wxOrderService.wxOrderPayHandle(payRequest);		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "success", orderPayOutDto), response);
		LOGGER.info("WxPayController.jsApiPay end success");
		return;
	}
	
	@RequestMapping(value = "/nativePay")
	public void nativePay(PayRequest payRequest, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("WxPayController.nativePay start");
		UnifiedOrderPayOutDto orderPayOutDto = wxOrderService.wxOrderPayHandle(payRequest);		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "success", orderPayOutDto), response);
		LOGGER.info("WxPayController.nativePay end success");
		return;
	}
	
}