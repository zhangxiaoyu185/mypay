package com.xiaoyu.lingdian.entity;

import java.util.Date;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class AliPayResponse extends BaseEntity {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 通知时间
	 */
	private Date notifyDate;

	/**
	 * 通知类型
	 */
	private String notifyType;

	/**
	 * 通知校验ID
	 */
	private String notifyId;

	/**
	 * 签名方式
	 */
	private String signType;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 交易流水号
	 */
	private String tradeNo;

	/**
	 * 商品名称
	 */
	private String subject;

	/**
	 * 支付类型
	 */
	private Integer paymentType;

	/**
	 * 支付宝交易号
	 */
	private String outTradeNo;

	/**
	 * 交易状态
	 */
	private String tradeStatus;

	/**
	 * 交易创建时间
	 */
	private Date gmtCreate;

	/**
	 * 交易付款时间
	 */
	private Date gmtPayment;

	/**
	 * 交易关闭时间
	 */
	private Date gmtClose;

	/**
	 * 退款状态
	 */
	private String refundStatus;

	/**
	 * 退款时间
	 */
	private Date gmtRefund;

	/**
	 * 买家支付宝账号
	 */
	private String buyerEmail;

	/**
	 * 买家支付宝账户号
	 */
	private String buyerId;

	/**
	 * 交易金额
	 */
	private BigDecimal totalFee;

	/**
	 * 商品描述
	 */
	private String body;

	/**
	 * 实际支付渠道
	 */
	private String outChannelInst;

	/**
	 * 是否扫码支付
	 */
	private String businessScene;

	/**
	 * 卖家支付宝账号
	 */
	private String sellerEmail;

	/**
	 * 卖家支付宝账户号
	 */
	private String sellerId;

	/**
	 * 折扣
	 */
	private Integer discount;

	/**
	 * 是否调整总价
	 */
	private String isTotalFeeAdjust;

	/**
	 * 是否使用红包买家
	 */
	private String useCoupon;

	/**
	 * 返回时间
	 */
	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtPayment() {
		return gmtPayment;
	}

	public void setGmtPayment(Date gmtPayment) {
		this.gmtPayment = gmtPayment;
	}

	public Date getGmtClose() {
		return gmtClose;
	}

	public void setGmtClose(Date gmtClose) {
		this.gmtClose = gmtClose;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Date getGmtRefund() {
		return gmtRefund;
	}

	public void setGmtRefund(Date gmtRefund) {
		this.gmtRefund = gmtRefund;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOutChannelInst() {
		return outChannelInst;
	}

	public void setOutChannelInst(String outChannelInst) {
		this.outChannelInst = outChannelInst;
	}

	public String getBusinessScene() {
		return businessScene;
	}

	public void setBusinessScene(String businessScene) {
		this.businessScene = businessScene;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getIsTotalFeeAdjust() {
		return isTotalFeeAdjust;
	}

	public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
		this.isTotalFeeAdjust = isTotalFeeAdjust;
	}

	public String getUseCoupon() {
		return useCoupon;
	}

	public void setUseCoupon(String useCoupon) {
		this.useCoupon = useCoupon;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}