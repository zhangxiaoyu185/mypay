package com.xiaoyu.lingdian.entity;

import java.util.Date;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class AliRefundResponse extends BaseEntity {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 通知时间
	 */
	private Date notifyTime;

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
	 * 退款批次号
	 */
	private String batchNo;

	/**
	 * 退款成功总数
	 */
	private Integer successNum;

	/**
	 * 交易号
	 */
	private String outTradeNo;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 退款金额
	 */
	private BigDecimal refundFee;

	/**
	 * 退款处理结果
	 */
	private String resultCode;

	/**
	 * 退款结果明细
	 */
	private String resultDetails;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
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

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(Integer successNum) {
		this.successNum = successNum;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDetails() {
		return resultDetails;
	}

	public void setResultDetails(String resultDetails) {
		this.resultDetails = resultDetails;
	}

}