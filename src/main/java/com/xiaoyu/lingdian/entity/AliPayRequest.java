package com.xiaoyu.lingdian.entity;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class AliPayRequest extends BaseEntity {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 接口名称
	 */
	private String serviceName;

	/**
	 * 合作者身份ID
	 */
	private String partner;

	/**
	 * 参数编码字符集
	 */
	private String charset;

	/**
	 * 签名方式
	 */
	private String signType;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 异步通知URL
	 */
	private String notifyUrl;

	/**
	 * 同步通知URL
	 */
	private String returnUrl;

	/**
	 * 出错通知URL
	 */
	private String errorNotifyUrl;

	/**
	 * 支付流水号
	 */
	private String tradeNo;

	/**
	 * 商品名称
	 */
	private String subject;

	/**
	 * 商品描述
	 */
	private String body;

	/**
	 * 支付类型
	 */
	private Integer paymentType;

	/**
	 * 支付金额
	 */
	private BigDecimal fee;

	/**
	 * 卖家支付宝用户号
	 */
	private String sellerId;

	/**
	 * 卖家支付宝账号
	 */
	private String sellerEmail;

	/**
	 * 客户端IP
	 */
	private String exterInvokeIp;

	/**
	 * 创建时间
	 */
	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
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

	public String getErrorNotifyUrl() {
		return errorNotifyUrl;
	}

	public void setErrorNotifyUrl(String errorNotifyUrl) {
		this.errorNotifyUrl = errorNotifyUrl;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getExterInvokeIp() {
		return exterInvokeIp;
	}

	public void setExterInvokeIp(String exterInvokeIp) {
		this.exterInvokeIp = exterInvokeIp;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}