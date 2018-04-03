package com.xiaoyu.lingdian.entity;

import java.util.Date;

@SuppressWarnings("serial")
public class PayErrorLog extends BaseEntity {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 交易号
	 */
	private String tradeNo;

	/**
	 * 第三方交易单号
	 */
	private String outTradeNo;

	/**
	 * 重复交易号
	 */
	private String reTradeNo;

	/**
	 * 错误描述
	 */
	private String errorMsg;

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

	public String getReTradeNo() {
		return reTradeNo;
	}

	public void setReTradeNo(String reTradeNo) {
		this.reTradeNo = reTradeNo;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}