package com.xiaoyu.lingdian.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class PrePayDto implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = -7504440017427484467L;

	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 商品名称
	 */
	private String productName;

	/**
	 * 商品详情
	 */
	private String productDetail;

	/**
	 * 需付金额
	 */
	private BigDecimal fee;

	/**
	 * 商品编号
	 */
	private String productId;

	/**
	 * 用户标识:微信JSAPI支付必传
	 */
	private String openid;

	/**
	 * 买家ID
	 */
	private Integer buyerId;

	/**
	 * 买家账号
	 */
	private String buyerName;

	/**
	 * 异步回调地址
	 */
	private String notifyUrl;

	/**
	 * 同步通知地址
	 */
	private String returnUrl;

	/**
	 * 客户端地址
	 */
	private String clientIp;

	/**
	 * 自定义属性:通知时原样返回
	 */
	private String property;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
