package com.xiaoyu.lingdian.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.xiaoyu.lingdian.enums.WxRefundType;

@SuppressWarnings("serial")
public class WxRefundResponse extends BaseEntity {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 退款操作类型
	 */
	private WxRefundType refundResponseType;

	/**
	 * 返回状态码
	 */
	private String returnCode;

	/**
	 * 返回信息
	 */
	private String returnMsg;

	/**
	 * 业务结果
	 */
	private String resultCode;

	/**
	 * 错误代码
	 */
	private String errCode;

	/**
	 * 错误代码描述
	 */
	private String errCodeDes;

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
	 * 退款渠道
	 */
	private String refundChannel;

	/**
	 * 退款金额
	 */
	private BigDecimal refundFee;

	/**
	 * 订单总金额
	 */
	private BigDecimal totalFee;

	/**
	 * 现金支付金额
	 */
	private BigDecimal cashFee;

	/**
	 * 现金退款金额
	 */
	private BigDecimal cashRefundFee;

	/**
	 * 订单金额货币种类
	 */
	private String feeType;

	/**
	 * 代金券或立减优惠退款金额
	 */
	private BigDecimal couponRefundFee;

	/**
	 * 代金券或立减优惠使用数量
	 */
	private Integer couponRefundCount;

	/**
	 * 代金券或立减优惠ID
	 */
	private String couponRefundId;

	/**
	 * 退款笔数
	 */
	private Integer refundCount;

	/**
	 * 退款状态
	 */
	private String refundStatus;

	/**
	 * 退款入账账户
	 */
	private String refundRecvAccout;

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

	public WxRefundType getRefundResponseType() {
		return refundResponseType;
	}

	public void setRefundResponseType(WxRefundType refundResponseType) {
		this.refundResponseType = refundResponseType;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
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

	public String getRefundChannel() {
		return refundChannel;
	}

	public void setRefundChannel(String refundChannel) {
		this.refundChannel = refundChannel;
	}

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public BigDecimal getCashFee() {
		return cashFee;
	}

	public void setCashFee(BigDecimal cashFee) {
		this.cashFee = cashFee;
	}

	public BigDecimal getCashRefundFee() {
		return cashRefundFee;
	}

	public void setCashRefundFee(BigDecimal cashRefundFee) {
		this.cashRefundFee = cashRefundFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public BigDecimal getCouponRefundFee() {
		return couponRefundFee;
	}

	public void setCouponRefundFee(BigDecimal couponRefundFee) {
		this.couponRefundFee = couponRefundFee;
	}

	public Integer getCouponRefundCount() {
		return couponRefundCount;
	}

	public void setCouponRefundCount(Integer couponRefundCount) {
		this.couponRefundCount = couponRefundCount;
	}

	public String getCouponRefundId() {
		return couponRefundId;
	}

	public void setCouponRefundId(String couponRefundId) {
		this.couponRefundId = couponRefundId;
	}

	public Integer getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(Integer refundCount) {
		this.refundCount = refundCount;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getRefundRecvAccout() {
		return refundRecvAccout;
	}

	public void setRefundRecvAccout(String refundRecvAccout) {
		this.refundRecvAccout = refundRecvAccout;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}