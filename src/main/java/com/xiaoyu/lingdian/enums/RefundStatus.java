package com.xiaoyu.lingdian.enums;

public enum RefundStatus {
	
	/**
	 * 退款成功
	 */
	REFUND_SUCCESS("SUCCESS"),
	/**
	 * 退款失败
	 */
	REFUND_FAIL("FAIL"),
	/**
	 * 退款处理中
	 */
	REFUND_PROCESSING("PROCESSING"),
	/**
	 * 需要重新发起退款
	 */
	REFUND_NOTSURE("NOTSURE"),
	
	/**
	 * 退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
	 */
	REFUND_CHANGE("CHANGE");
	
	private String name;
	
	RefundStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}