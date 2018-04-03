package com.xiaoyu.lingdian.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.xiaoyu.lingdian.enums.WxRefundType;

@SuppressWarnings("serial")
public class WxRefundRequest extends BaseEntity {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 请求类型
	 */
	private WxRefundType refundRequestType;

	/**
	 * 公众账号ID
	 */
	private String appid;

	/**
	 * 商户号
	 */
	private String mchId;

	/**
	 * 设备号
	 */
	private String deviceInfo;

	/**
	 * 随机字符串
	 */
	private String nonceStr;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 微信订单号
	 */
	private String transactionId;

	/**
	 * 支付流水号
	 */
	private String tradeNo;

	/**
	 * 商户退款单号
	 */
	private String refundNo;

	/**
	 * 微信退款单号
	 */
	private String wxRefundNo;

	/**
	 * 总金额
	 */
	private BigDecimal totalFee;

	/**
	 * 退款金额
	 */
	private BigDecimal refundFee;

	/**
	 * 货币种类
	 */
	private String refundFeeType;

	/**
	 * 操作员
	 */
	private String opUserId;

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

	public WxRefundType getRefundRequestType() {
		return refundRequestType;
	}

	public void setRefundRequestType(WxRefundType refundRequestType) {
		this.refundRequestType = refundRequestType;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public String getWxRefundNo() {
		return wxRefundNo;
	}

	public void setWxRefundNo(String wxRefundNo) {
		this.wxRefundNo = wxRefundNo;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public String getRefundFeeType() {
		return refundFeeType;
	}

	public void setRefundFeeType(String refundFeeType) {
		this.refundFeeType = refundFeeType;
	}

	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}