package com.xiaoyu.lingdian.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.xiaoyu.lingdian.enums.PayChannel;
import com.xiaoyu.lingdian.enums.TradeType;

@SuppressWarnings("serial")
public class PayRequest extends BaseEntity {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 平台单号
	 */
	private String payNo;

	/**
	 * 支付流水号
	 */
	private String tradeNo;

	/**
	 * 支付渠道
	 */
	private PayChannel payChannel;

	/**
	 * 交易类型
	 */
	private TradeType tradeType;

	/**
	 * 剩余支付时间
	 */
	private Integer payTimeout;

	/**
	 * 支付金额
	 */
	private BigDecimal fee;

	/**
	 * 支付参数
	 */
	private String payParams;

	/**
	 * 支付结果
	 */
	private String payResult;

	/**
	 * 支付时间
	 */
	private Date payDate;

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
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;

	/**
	 * 第三方交易单号
	 */
	private String outTradeNo;

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

	public PayChannel getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(PayChannel payChannel) {
		this.payChannel = payChannel;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public Integer getPayTimeout() {
		return payTimeout;
	}

	public void setPayTimeout(Integer payTimeout) {
		this.payTimeout = payTimeout;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getPayParams() {
		return payParams;
	}

	public void setPayParams(String payParams) {
		this.payParams = payParams;
	}

	public String getPayResult() {
		return payResult;
	}

	public void setPayResult(String payResult) {
		this.payResult = payResult;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
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

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

}