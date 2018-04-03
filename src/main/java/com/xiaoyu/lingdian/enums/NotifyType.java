package com.xiaoyu.lingdian.enums;

public enum NotifyType {
	
	/**
	 * 支付
	 */
	PAY("PAY"),
	/**
	 * 退款
	 */
	REFUND("REFUND");
	
	private String name;
	
	NotifyType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}