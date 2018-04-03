package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.PayRequest;

public interface PayCommonService {
	
	/**
	 * @Description: 接到微信、支付宝通知后和本地数据校验并更新
	 */
	boolean verifyLocalOrder(PayRequest payRequest);
	
}