package com.xiaoyu.lingdian.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.xiaoyu.lingdian.enums.NotifyType;

@SuppressWarnings("serial")
public class NotifyLog extends BaseEntity {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 平台单号
	 */
	private String payNo;

	/**
	 * 支付流水号
	 */
	private String tradeNo;

	/**
	 * 通知地址
	 */
	private String notifyUrl;

	/**
	 * 最近一次通知时间
	 */
	private Date notifyDate;

	/**
	 * 下一次通知时间
	 */
	private Date nextNotifyDate;

	/**
	 * 通知次数
	 */
	private Integer notifyTimes;

	/**
	 * 支付完成时间
	 */
	private Date tradeDate;

	/**
	 * 第三方交易单号
	 */
	private String outTradeNo;

	/**
	 * 订单金额
	 */
	private BigDecimal fee;

	/**
	 * 通知内容
	 */
	private String notifyContent;

	/**
	 * 通知结果
	 */
	private String notifyResult;

	/**
	 * 通知类型
	 */
	private NotifyType notifyType;

	/**
	 * 退款单号
	 */
	private String refundNo;

	/**
	 * 退款金额
	 */
	private BigDecimal refundFee;

	/**
	 * 退款时间
	 */
	private Date refundDate;

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

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public Date getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}

	public Date getNextNotifyDate() {
		return nextNotifyDate;
	}

	public void setNextNotifyDate(Date nextNotifyDate) {
		this.nextNotifyDate = nextNotifyDate;
	}

	public Integer getNotifyTimes() {
		return notifyTimes;
	}

	public void setNotifyTimes(Integer notifyTimes) {
		this.notifyTimes = notifyTimes;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getNotifyContent() {
		return notifyContent;
	}

	public void setNotifyContent(String notifyContent) {
		this.notifyContent = notifyContent;
	}

	public String getNotifyResult() {
		return notifyResult;
	}

	public void setNotifyResult(String notifyResult) {
		this.notifyResult = notifyResult;
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