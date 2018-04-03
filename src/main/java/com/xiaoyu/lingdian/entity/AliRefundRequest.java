package com.xiaoyu.lingdian.entity;

import java.util.Date;

@SuppressWarnings("serial")
public class AliRefundRequest extends BaseEntity {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 接口名称
	 */
	private String service;

	/**
	 * 合作者身份ID
	 */
	private String partner;

	/**
	 * 参数编码字符集
	 */
	private String charset;

	/**
	 * 签名方式
	 */
	private String signType;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 异步通知页面路径
	 */
	private String notifyUrl;

	/**
	 * 卖家支付宝账号
	 */
	private String sellerEmail;

	/**
	 * 卖家用户ID
	 */
	private String sellerUserId;

	/**
	 * 总笔数
	 */
	private Integer batchNum;

	/**
	 * 退款请求时间
	 */
	private Date refundDate;

	/**
	 * 退款数据集
	 */
	private String detailData;

	/**
	 * 退款批次号
	 */
	private String batchNo;

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

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
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

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getSellerUserId() {
		return sellerUserId;
	}

	public void setSellerUserId(String sellerUserId) {
		this.sellerUserId = sellerUserId;
	}

	public Integer getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(Integer batchNum) {
		this.batchNum = batchNum;
	}

	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public String getDetailData() {
		return detailData;
	}

	public void setDetailData(String detailData) {
		this.detailData = detailData;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}