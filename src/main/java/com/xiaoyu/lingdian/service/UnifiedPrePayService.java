package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.vo.PrePayOutDto;

public interface UnifiedPrePayService {
	
	/**
	 * @Description: TODO(统一支付预订单，返回支付流水号)
	 * @return PrePayOutDto    返回类型
	 * @throws
	 */
	PrePayOutDto createPrePayOrder(String params);
	
	/**
	 * 
	 * @Description: TODO(更新预支付订单价格)
	 * @param  params
	 * @return PrePayOutDto    返回类型
	 * @throws
	 */
	PrePayOutDto updatePrePayOrder(String params);
	
}