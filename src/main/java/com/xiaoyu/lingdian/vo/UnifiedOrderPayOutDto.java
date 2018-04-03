package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.PayParams;

/**
 * @author  xiqf
 * @date 创建时间：2016年1月12日 下午2:46:20 
 * @version 1.0
 */
public class UnifiedOrderPayOutDto extends BaseOutDto {

	private static final long serialVersionUID = -6816923497739809473L;

	/**
	 * 返回前端用于支付需要的参数（json格式）
	 */
	private PayParams payParams;
	

	public PayParams getPayParams() {
		return payParams;
	}

	public void setPayParams(PayParams payParams) {
		this.payParams = payParams;
	}
}
