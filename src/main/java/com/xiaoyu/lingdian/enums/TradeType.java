package com.xiaoyu.lingdian.enums;

public enum TradeType {
	
	/**
	 * 微信公众号支付(手机)
	 */
	WX_JSAPI("JSAPI"),
	/**
	 * 微信扫码支付(PC)
	 */
	WX_NATIVE("NATIVE"),
	/**
	 * 微信app支付
	 */
	WX_APP("APP"),
	/**
	 * 支付宝即时支付(PC)
	 */
	ALI_PC_DIRECT("PC"),
	/**
	 * 支付宝即时支付(手机)
	 */
	ALI_WAP_DIRECT("WAP"),
	/**
	 * 支付宝app支付
	 */
	ALI_APP_DIRECT("ALI_APP");
	
	private String name;

	public String getName() {
		return name;
	}

	TradeType(String name) {
		this.name = name;
	}
	
}