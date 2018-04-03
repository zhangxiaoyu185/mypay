package com.xiaoyu.lingdian.entity;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class WxPayRequest extends BaseEntity {

	/**
	 * 主键
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
	 * 设备号
	 */
	private String deviceInfo;

	/**
	 * 随机字符串
	 */
	private String nonceStr;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 商品描述
	 */
	private String body;

	/**
	 * 商品详情
	 */
	private String detail;

	/**
	 * 附加数据
	 */
	private String attach;

	/**
	 * 支付流水号
	 */
	private String tradeNo;

	/**
	 * 货币类型
	 */
	private String feeType;

	/**
	 * 总金额
	 */
	private BigDecimal totalFee;

	/**
	 * 终端IP
	 */
	private String spbillCreateIp;

	/**
	 * 交易起始时间
	 */
	private Date timeStart;

	/**
	 * 交易结束时间
	 */
	private Date timeExpire;

	/**
	 * 商品标记
	 */
	private String goodsTag;

	/**
	 * 通知地址
	 */
	private String notifyUrl;

	/**
	 * 交易类型
	 */
	private String tradeType;

	/**
	 * 商品ID
	 */
	private String productId;

	/**
	 * 指定支付方式
	 */
	private String limitPay;

	/**
	 * 用户标识
	 */
	private String openid;

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

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	public Date getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(Date timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}