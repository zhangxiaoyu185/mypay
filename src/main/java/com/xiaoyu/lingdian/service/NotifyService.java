package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.NotifyLog;

public interface NotifyService {
	
	/**
	 * @Description: 微信、支付宝、通联支付异步通知统一处理
	 */
	void notifyRequester(NotifyLog notifyLog) throws Exception;
	
	/**
	 * @Description: 支付系统通知第三方计划任务
	 */
	void notifySchedule();
	
}