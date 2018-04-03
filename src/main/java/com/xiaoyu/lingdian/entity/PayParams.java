package com.xiaoyu.lingdian.entity;

@SuppressWarnings("serial")
public class PayParams extends BaseEntity {

	/**
	 * 微信公众号ID
	 */
	private String appid;

	/**
	 * 时间戳
	 */
	private String timestampStr;

	/**
	 * 随机字符串
	 */
	private String nonceStr;

	/**
	 * 订单详情扩展字符串
	 */
	private String packageStr;

	/**
	 * 签名方式
	 */
	private String signType;

	/**
	 * 签名
	 */
	private String paySign;

	/**
	 * 二维码链接
	 */
	private String codeUrl;

	/**
	 * 支付宝JSP
	 */
	private String jspText;
	
	/**
	 * 商户号
	 */
	private String payInfo;
	
	/**
	 * 预支付交易会话ID
	 */
	private String partnerid;
	
	/**
	 * 预支付交易会话ID
	 */
	private String prepayid;
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getTimestampStr() {
		return timestampStr;
	}

	public void setTimestampStr(String timestampStr) {
		this.timestampStr = timestampStr;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageStr() {
		return packageStr;
	}

	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public String getJspText() {
		return jspText;
	}

	public void setJspText(String jspText) {
		this.jspText = jspText;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}
	
}