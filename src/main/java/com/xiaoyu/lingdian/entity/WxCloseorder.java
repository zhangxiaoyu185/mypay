package com.xiaoyu.lingdian.entity;

import java.util.Date;

@SuppressWarnings("serial")
public class WxCloseorder extends BaseEntity {

	/**
	 * ID
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
	 * 请求随机字符串
	 */
	private String nonceStr;

	/**
	 * 请求签名
	 */
	private String sign;

	/**
	 * 返回状态码
	 */
	private String returnCode;

	/**
	 * 返回信息
	 */
	private String returnMsg;

	/**
	 * 返回随机字符串
	 */
	private String reNonceStr;

	/**
	 * 返回签名
	 */
	private String reSign;

	/**
	 * 错误代码
	 */
	private String errCode;

	/**
	 * 错误代码描述
	 */
	private String errCodeDes;

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

	public String getReNonceStr() {
		return reNonceStr;
	}

	public void setReNonceStr(String reNonceStr) {
		this.reNonceStr = reNonceStr;
	}

	public String getReSign() {
		return reSign;
	}

	public void setReSign(String reSign) {
		this.reSign = reSign;
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