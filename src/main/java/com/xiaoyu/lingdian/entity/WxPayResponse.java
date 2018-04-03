package com.xiaoyu.lingdian.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.xiaoyu.lingdian.enums.WxResponseType;

@SuppressWarnings("serial")
public class WxPayResponse extends BaseEntity {

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 下单请求，支付结果
	 */
	private WxResponseType type;

	/**
	 * 返回状态码
	 */
	private String returnCode;

	/**
	 * 返回信息
	 */
	private String returnMsg;

	/**
	 * 交易类型
	 */
	private String tradeType;

	/**
	 * 预支付交易会话标识
	 */
	private String prepayId;

	/**
	 * 二维码链接
	 */
	private String codeUrl;

	/**
	 * 随机字符串
	 */
	private String nonceStr;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 业务结果
	 */
	private String resultCode;

	/**
	 * 错误代码
	 */
	private String errCode;

	/**
	 * 错误代码描述
	 */
	private String errCodeDes;

	/**
	 * 用户标识
	 */
	private String openid;

	/**
	 * 是否关注公众账号
	 */
	private String isSubscribe;

	/**
	 * 付款银行
	 */
	private String bankType;

	/**
	 * 总金额
	 */
	private BigDecimal totalFee;

	/**
	 * 货币种类
	 */
	private String feeType;

	/**
	 * 现金支付金额
	 */
	private BigDecimal cashFee;

	/**
	 * 现金支付货币类型
	 */
	private String cashFeeType;

	/**
	 * 代金券或立减优惠金额
	 */
	private BigDecimal couponFee;

	/**
	 * 代金券或立减优惠使用数量
	 */
	private Integer couponCount;

	/**
	 * 代金券或立减优惠ID
	 */
	private String couponIds;

	/**
	 * 单个代金券或立减优惠支付金额
	 */
	private String couponFees;

	/**
	 * 微信支付订单号
	 */
	private String transactionId;

	/**
	 * 支付流水号
	 */
	private String tradeNo;

	/**
	 * 支付完成时间
	 */
	private Date timeEnd;

	/**
	 * 创建时间
	 */
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public WxResponseType getType() {
		return type;
	}

	public void setType(WxResponseType type) {
		this.type = type;
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

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
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

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public BigDecimal getCashFee() {
		return cashFee;
	}

	public void setCashFee(BigDecimal cashFee) {
		this.cashFee = cashFee;
	}

	public String getCashFeeType() {
		return cashFeeType;
	}

	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}

	public BigDecimal getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(BigDecimal couponFee) {
		this.couponFee = couponFee;
	}

	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}

	public String getCouponIds() {
		return couponIds;
	}

	public void setCouponIds(String couponIds) {
		this.couponIds = couponIds;
	}

	public String getCouponFees() {
		return couponFees;
	}

	public void setCouponFees(String couponFees) {
		this.couponFees = couponFees;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}