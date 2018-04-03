package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.PayRequest;
import com.xiaoyu.lingdian.vo.RefundDto;
import com.xiaoyu.lingdian.vo.RefundOutDto;
import com.xiaoyu.lingdian.vo.UnifiedOrderPayOutDto;

public interface AlipayOrderService {
	
	/**
	 * @Description: 组织支付宝即时支付参数
	 * @param  payRequest
	 * @return UnifiedPayResponse
	 */
	UnifiedOrderPayOutDto aliPayOrderHandle(PayRequest payRequest);
	
	/**
	 * @Description: 这里用一句话描述这个方法的作用
	 * @param  refundDto
	 * @return RefundOutDto
	 */
	RefundOutDto aliPayRefund(RefundDto refundDto);
	
	/**
	 * @Description: 支付宝退款参数组织、并记录请求返回日志
	 * @param  refundRequestLog
	 * @return RefundOutDto
	 */
	RefundOutDto aliPayOrderRefundHandle(RefundDto refundDto);
	
}