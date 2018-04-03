package com.xiaoyu.lingdian.entity;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class RefundRequestDetail extends BaseEntity {

	/**
	 * 平台单号
	 */
	private String payNo;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 退款金额
	 */
	private BigDecimal refundFee;
	/**
	 * 退款理由
	 */
	private String refundCause;
	/**
	 * 退款单号
	 */
	private Integer refundNo;

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public String getRefundCause() {
		return refundCause;
	}

	public void setRefundCause(String refundCause) {
		this.refundCause = refundCause;
	}

	public Integer getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(Integer refundNo) {
		this.refundNo = refundNo;
	}
	
}