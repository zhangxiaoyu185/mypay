package com.xiaoyu.lingdian.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.entity.PayConstant;
import com.xiaoyu.lingdian.service.AlipayOrderNotifyService;
import com.xiaoyu.lingdian.vo.AlipayOrderNotifyOutDto;
import com.xiaoyu.lingdian.vo.AlipayRefundOutDto;

@Controller
@RequestMapping("/aliPayOrder")
public class AlipayNotifyController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("BASE_LOGGER");
	
	@Autowired
	private AlipayOrderNotifyService alipayOrderNotifyService;
	
	@RequestMapping(value = "/payNotify", method = RequestMethod.POST)
	public void aliPayNotify(AlipayOrderNotifyOutDto alipayNotifyOutDto, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("AlipayNotifyController.aliPayNotify start");
		try {
			response.setCharacterEncoding(PayConstant.CHARSET_UTF8);
			PrintWriter out = response.getWriter();
			boolean flag = alipayOrderNotifyService.payNotifyHandle(alipayNotifyOutDto);
			if(flag) {
				out.write("success");//告诉支付宝我已经成功接收通知，不必再次通知
				out.flush();
				LOGGER.info("AlipayNotifyController.aliPayNotify end success");
				return;
			}
			LOGGER.error("AlipayNotifyController.aliPayNotify end fail");
		} catch (IOException e) {
			LOGGER.error("AlipayNotifyController.aliPayNotify end fail>>处理支付宝支付异步通知异常", e);
			return;
		}
	}
	
	@RequestMapping("/refundNotify")
	public void aliPayRefundNotify(AlipayRefundOutDto alipayRefundOutDto, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("AlipayNotifyController.aliPayRefundNotify start");
		try {
			response.setCharacterEncoding(PayConstant.CHARSET_UTF8);
			PrintWriter out = response.getWriter();
			boolean flag = alipayOrderNotifyService.refundNotifyHandle(alipayRefundOutDto);
			if(flag) {
				out.write("success");//告诉支付宝我已经成功接收通知，不必再次通知
				out.flush();
				LOGGER.info("AlipayNotifyController.aliPayRefundNotify end success");
				return;
			}
		} catch (IOException e) {
			LOGGER.error("AlipayNotifyController.aliPayRefundNotify end fail>>处理支付宝退款异步通知异常", e);
			return;
		}
	}
	
}