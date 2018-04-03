package com.xiaoyu.lingdian.enums;

public enum WxRefundType {
	
	/**
	 * 查询退款
	 */
	REFUND_QUERY("QUERY"),
	/**
	 * 申请退款
	 */
	REFUND_APPLY("REFUND");
	
	private String name;
	
	WxRefundType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}