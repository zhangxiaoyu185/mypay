package com.xiaoyu.lingdian.entity;

import java.util.Date;

@SuppressWarnings("serial")
public class WxRefundQuery extends BaseEntity {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 公众账号ID
	 */
	private String appid;

	/**
	 * 商户号
	 */
	private String mchId;

	/**
	 * 支付流水号
	 */
	private String tradeNo;

	/**
	 * 退款单号
	 */
	private String refundNo;

	/**
	 * 微信订单号
	 */
	private String transactionId;

	/**
	 * 查询次数
	 */
	private Integer queryTimes;

	/**
	 * 下一次查询时间
	 */
	private Date nextDate;

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

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getQueryTimes() {
		return queryTimes;
	}

	public void setQueryTimes(Integer queryTimes) {
		this.queryTimes = queryTimes;
	}

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
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