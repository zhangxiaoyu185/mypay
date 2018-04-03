package com.xiaoyu.lingdian.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author  xiaqf
 * @date 创建时间：2016年5月24日 上午9:35:38 
 * @version 1.0
 */
public class TransformOrderDto implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = -717089385591252085L;
	/**
	 * 支付账户编号
	 */
	private Integer payAccountId;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 海关编号
	 */
	private String customsNo;
	/**
	 * 电商企业代码
	 */
	private String companyCode;
	/**
	 * 电商企业名称
	 */
	private String companyName;
	/**
	 * 用户编号
	 */
	private Integer userId;
	/**
	 * 用户账号
	 */
	private String userAccount;
	/**
	 * 支付总额
	 */
	private BigDecimal totalPayFee;
	/**
	 * 商品总额
	 */
	private BigDecimal goodsTotalFee;
	/**
	 * 税费总额
	 */
	private BigDecimal taxTotalFee;
	/**
	 * 运费总额
	 */
	private BigDecimal ShippingFee;
	/**
	 * 调整金额
	 */
	private BigDecimal adjustFee;
	/**
	 * 下单时间
	 */
	private Date orderDate;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 身份证号
	 */
	private String cardNo;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 备注
	 */
	private String memo;

	public Integer getPayAccountId() {
		return payAccountId;
	}

	public void setPayAccountId(Integer payAccountId) {
		this.payAccountId = payAccountId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustomsNo() {
		return customsNo;
	}

	public void setCustomsNo(String customsNo) {
		this.customsNo = customsNo;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	
	public BigDecimal getTotalPayFee() {
		return totalPayFee;
	}

	public void setTotalPayFee(BigDecimal totalPayFee) {
		this.totalPayFee = totalPayFee;
	}

	public BigDecimal getGoodsTotalFee() {
		return goodsTotalFee;
	}

	public void setGoodsTotalFee(BigDecimal goodsTotalFee) {
		this.goodsTotalFee = goodsTotalFee;
	}

	public BigDecimal getTaxTotalFee() {
		return taxTotalFee;
	}

	public void setTaxTotalFee(BigDecimal taxTotalFee) {
		this.taxTotalFee = taxTotalFee;
	}

	public BigDecimal getShippingFee() {
		return ShippingFee;
	}

	public void setShippingFee(BigDecimal shippingFee) {
		ShippingFee = shippingFee;
	}

	public BigDecimal getAdjustFee() {
		return adjustFee;
	}

	public void setAdjustFee(BigDecimal adjustFee) {
		this.adjustFee = adjustFee;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
