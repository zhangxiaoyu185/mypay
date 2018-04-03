package com.xiaoyu.lingdian.service;

import java.util.List;
import com.xiaoyu.lingdian.entity.PayRequest;
import com.xiaoyu.lingdian.entity.RefundRequestDetail;
import com.xiaoyu.lingdian.vo.RefundDto;
import com.xiaoyu.lingdian.vo.RefundResponseDetail;
import com.xiaoyu.lingdian.vo.UnifiedOrderPayOutDto;

public interface WxOrderService {
	
	/**
	 * @Description:微信统一支付
	 * @param  payRequest
	 * @return UnifiedOrderPayOutDto    返回类型
	 * @throws
	 */
	UnifiedOrderPayOutDto wxOrderPayHandle(PayRequest payRequest);
	
	/**
	 * @Description: 微信退款处理
	 * @param  refundRequestDetail
	 * @return RefundResponseDetail
	 */
	RefundResponseDetail wxRefund(RefundRequestDetail refundRequestDetail,String notifyUrl);
	
	/**
	 * @Description: 微信批量退款处理
	 * @param  refundDto
	 * @return RefundResponseDetail
	 */
	List<RefundResponseDetail> wxOrderRefundHandle(RefundDto refundDto);
	
}