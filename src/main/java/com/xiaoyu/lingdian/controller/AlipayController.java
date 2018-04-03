package com.xiaoyu.lingdian.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
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
import com.xiaoyu.lingdian.service.AlipayOrderService;
import com.xiaoyu.lingdian.tool.StringUtil;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import com.xiaoyu.lingdian.vo.RefundDto;
import com.xiaoyu.lingdian.vo.RefundOutDto;
import com.xiaoyu.lingdian.vo.UnifiedOrderPayOutDto;

@Controller
@RequestMapping("/aliPay")
public class AlipayController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("BASE_LOGGER");
	
	@Autowired
	private AlipayOrderService alipayOrderService;
	
	@RequestMapping(value = "/directPay")
	public void directPay(@RequestBody PayRequest payRequest, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("AlipayController.directPay start");
		try {
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding(PayConstant.CHARSET_UTF8);
			response.setContentType("text/html");
			UnifiedOrderPayOutDto orderPayOutDto = alipayOrderService.aliPayOrderHandle(payRequest);
			if(null != orderPayOutDto && !StringUtil.isEmpty(orderPayOutDto.getPayParams().getJspText())) {
				request.setAttribute("resultStr", orderPayOutDto.getPayParams().getJspText());
				out.print(orderPayOutDto.getPayParams().getJspText());
				LOGGER.info("AlipayController.directPay end success");
				return;
			} else {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "支付失败"), response);
				LOGGER.info("AlipayController.directPay end fail>>支付失败");
				return;
			}
		}  catch (Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "支付失败"), response);
			LOGGER.info("AlipayController.directPay end fail>>支付失败");
			return;
		}
	}
	
	@RequestMapping(value = "/refund")
	public void refund(String params, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("AlipayController.refund start");
		try {
			RefundDto refundDto = JSON.parseObject(params, RefundDto.class);
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding(PayConstant.CHARSET_UTF8);
			RefundOutDto refundOutDto = alipayOrderService.aliPayOrderRefundHandle(refundDto);
			if(null != refundOutDto && !StringUtil.isEmpty(refundOutDto.getRefundParams())) {
				out.print(refundOutDto.getRefundParams());
				LOGGER.info("AlipayController.refund end success");
				return;
			} else {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "支付失败"), response);
				LOGGER.info("AlipayController.refund end fail>>支付失败");
				return;
			}
		}  catch (Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "支付失败"), response);
			LOGGER.info("AlipayController.refund end fail>>支付失败");
			return;
		}
	}
	
}