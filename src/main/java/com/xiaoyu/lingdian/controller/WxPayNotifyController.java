package com.xiaoyu.lingdian.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xiaoyu.lingdian.entity.PayConstant;
import com.xiaoyu.lingdian.service.WxNotifyService;

@Controller
@RequestMapping("/wxOrder")
public class WxPayNotifyController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("BASE_LOGGER");
	
	@Autowired
	WxNotifyService wxNotifyService;
	
	@RequestMapping(value = "/wxOrderPayNotify")
	public void wxPayNotify(@RequestBody String str, HttpServletResponse response) {
		LOGGER.info("WxPayNotifyController.wxPayNotify start");
		try {
			response.setCharacterEncoding(PayConstant.CHARSET_UTF8);
			response.setContentType("text/xml");
			PrintWriter out = response.getWriter();
			String result = wxNotifyService.notifyHandle(str);
			if(StringUtils.isNotEmpty(result)) {
				out.write(result);//告诉微信我已经成功接收通知，不必再次通知
				out.flush();
				LOGGER.info("WxPayNotifyController.wxPayNotify end success");
				return;
			}
			LOGGER.error("WxPayNotifyController.wxPayNotify end fail");
		} catch (IOException e) {
			LOGGER.error("WxPayNotifyController.wxPayNotify end fail>>处理微信异步通知异常", e);
			return;
		}
	}
	
}