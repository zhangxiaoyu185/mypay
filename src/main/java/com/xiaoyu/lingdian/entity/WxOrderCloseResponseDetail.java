package com.xiaoyu.lingdian.entity;

@SuppressWarnings("serial")
public class WxOrderCloseResponseDetail extends BaseEntity {

	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 返回结果
	 */
	private String resultCode;
	/**
	 * 错误code
	 */
	private String errorCode;
	/**
	 * 错误信息
	 */
	private String errorMsg;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

}