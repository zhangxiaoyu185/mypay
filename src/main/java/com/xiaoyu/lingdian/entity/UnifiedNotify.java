package com.xiaoyu.lingdian.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.xiaoyu.lingdian.enums.NotifyType;
import com.xiaoyu.lingdian.enums.PayChannel;
import com.xiaoyu.lingdian.enums.TradeType;

@SuppressWarnings("serial")
public class UnifiedNotify extends BaseEntity {
	
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 通知类型
	 */
	private NotifyType notifyType;
	/**
	 * 退款单号
	 */
	private String refundNo;
	/**
	 * 通知内容
	 */
	private String notifyContent;
	/**
	 * 支付流水号
	 */
	private String payNo;
	/**
	 * 交易完成时间
	 */
	private Date tradeTime;
	/**
	 * 第三方交易单号
	 */
	private String outTradeNo;
	/**
	 * 支付渠道
	 */
	private PayChannel payChannel;
	/**
	 * 交易类型
	 */
	private TradeType tradeType;
	/**
	 * 退款金额
	 */
	private BigDecimal refundFee;
	/**
	 * 退款时间
	 */
	private Date refundDate;
	/**
	 * 实付金额
	 */
	private BigDecimal fee;
	/**
	 * 通知时间
	 */
	private Date notifyDate;
	
	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public PayChannel getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(PayChannel payChannel) {
		this.payChannel = payChannel;
	}

	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public NotifyType getNotifyType() {
		return notifyType;
	}
	
	public void setNotifyType(NotifyType notifyType) {
		this.notifyType = notifyType;
	}
	
	public String getRefundNo() {
		return refundNo;
	}
	
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
	
	public String getNotifyContent() {
		return notifyContent;
	}
	
	public void setNotifyContent(String notifyContent) {
		this.notifyContent = notifyContent;
	}
	
	public String getPayNo() {
		return payNo;
	}
	
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	
	public Date getTradeTime() {
		return tradeTime;
	}
	
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	
	public String getOutTradeNo() {
		return outTradeNo;
	}
	
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
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

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Date getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}
	
}