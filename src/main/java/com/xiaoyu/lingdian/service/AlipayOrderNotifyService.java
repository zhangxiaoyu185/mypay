package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.vo.AlipayOrderNotifyOutDto;
import com.xiaoyu.lingdian.vo.AlipayRefundOutDto;

public interface AlipayOrderNotifyService {
	
	/**
	 * @Description: 支付通知
	 * @param  alipayNotifyOutDto
	 * @return boolean
	 */
	boolean payNotifyHandle(AlipayOrderNotifyOutDto alipayNotifyOutDto);
	
	/**
	 * @Description: 退款通知
	 * @param  alipayRefundOutDto
	 * @return boolean
	 */
	boolean refundNotifyHandle(AlipayRefundOutDto alipayRefundOutDto);
	
}