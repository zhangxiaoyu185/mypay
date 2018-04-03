package com.xiaoyu.lingdian.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.xiaoyu.lingdian.enums.PayChannel;
import com.xiaoyu.lingdian.enums.TradeType;

@SuppressWarnings("serial")
public class PayRecord extends BaseEntity {
	
	/**
	 * id
	 */
	private Integer id;

	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 
	 */
	private String payNo;

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
	 * 调整后金额
	 */
	private BigDecimal adjustFee;

	/**
	 * 商品编号
	 */
	private String productId;

	/**
	 * 用户标识
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
	 * 自定义属性
	 */
	private String property;

	/**
	 * 支付流水号
	 */
	private String tradeNo;

	/**
	 * 第三方单号
	 */
	private String outTradeNo;

	/**
	 * 支付结果
	 */
	private String payResult;

	/**
	 * 支付渠道
	 */
	private PayChannel payChannel;

	/**
	 * 交易类型
	 */
	private TradeType tradeType;

	/**
	 * 支付时间
	 */
	private Date payDate;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
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

	public BigDecimal getAdjustFee() {
		return adjustFee;
	}

	public void setAdjustFee(BigDecimal adjustFee) {
		this.adjustFee = adjustFee;
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

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getPayResult() {
		return payResult;
	}

	public void setPayResult(String payResult) {
		this.payResult = payResult;
	}

	public PayChannel getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(PayChannel payChannel) {
		this.payChannel = payChannel;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}