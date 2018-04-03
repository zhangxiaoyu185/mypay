package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.WxOrderCloseResponseDetail;

public interface WxOrderCloseService {
	
	/**
	 * @Description: 关闭微信订单
	 * @param  orderNo
	 * @return WxOrderCloseResponseDetail
	 */
	WxOrderCloseResponseDetail closeOrder(String orderNo);
	
}