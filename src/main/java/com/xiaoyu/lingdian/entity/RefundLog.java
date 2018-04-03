package com.xiaoyu.lingdian.entity;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class RefundLog extends BaseEntity {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 商户单号
	 */
	private String payNo;

	/**
	 * 支付流水号
	 */
	private String tradeNo;

	/**
	 * 第三方交易单号
	 */
	private String outTradeNo;

	/**
	 * 退款单号
	 */
	private String refundNo;

	/**
	 * 退款金额
	 */
	private BigDecimal applyRefundFee;

	/**
	 * 退款理由
	 */
	private String refundCause;

	/**
	 * 异步通知地址
	 */
	private String notifyUrl;

	/**
	 * 业务结果
	 */
	private String resultCode;

	/**
	 * 错误代码
	 */
	private String errorCode;

	/**
	 * 错误描述
	 */
	private String errorMsg;

	/**
	 * 实际退款金额
	 */
	private BigDecimal refundFee;

	/**
	 * 退款时间
	 */
	private Date refundDate;

	/**
	 * 退款结果
	 */
	private String refundResult;

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

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
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

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public BigDecimal getApplyRefundFee() {
		return applyRefundFee;
	}

	public void setApplyRefundFee(BigDecimal applyRefundFee) {
		this.applyRefundFee = applyRefundFee;
	}

	public String getRefundCause() {
		return refundCause;
	}

	public void setRefundCause(String refundCause) {
		this.refundCause = refundCause;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public String getRefundResult() {
		return refundResult;
	}

	public void setRefundResult(String refundResult) {
		this.refundResult = refundResult;
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