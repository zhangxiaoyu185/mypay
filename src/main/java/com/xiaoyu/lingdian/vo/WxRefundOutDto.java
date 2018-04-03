package com.xiaoyu.lingdian.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAnyElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author  xiqf
 * @date 创建时间：2015年12月16日 下午6:58:38 
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxRefundOutDto implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = 3568343975709012335L;
	/**
	 * 返回状态码
	 */
	@XmlAnyElement
	private String return_code;
	/**
	 * 返回信息
	 */
	@XmlAnyElement
	private String return_msg;
	/**
	 * 业务结果
	 */
	@XmlAnyElement
	private String result_code;
	/**
	 * 错误代码
	 */
	@XmlAnyElement
	private String err_code;
	/**
	 * 错误代码描述
	 */
	@XmlAnyElement
	private String err_code_des;
	/**
	 * 公众账号ID
	 */
	@XmlAnyElement
	private String appid;
	/**
	 * 商户号
	 */
	@XmlAnyElement
	private String mch_id;
	/**
	 * 设备号
	 */
	@XmlAnyElement
	private String device_info;
	/**
	 * 随机字符串
	 */
	@XmlAnyElement
	private String nonce_str;
	/**
	 * 签名
	 */
	@XmlAnyElement
	private String sign;
	/**
	 * 微信订单号
	 */
	@XmlAnyElement
	private String transaction_id;
	/**
	 * 商户订单号
	 */
	@XmlAnyElement
	private String out_trade_no;
	/**
	 * 商户退款单号
	 */
	@XmlAnyElement
	private String out_refund_no;
	/**
	 * 微信退款单号
	 */
	@XmlAnyElement
	private String refund_id;
	/**
	 * 退款渠道
	 */
	@XmlAnyElement
	private String refund_channel;
	/**
	 * 退款金额
	 */
	@XmlAnyElement
	private Integer refund_fee;
	/**
	 * 订单总金额
	 */
	@XmlAnyElement
	private Integer total_fee;
	/**
	 * 订单金额货币种类
	 */
	@XmlAnyElement
	private String fee_type;
	/**
	 * 现金支付金额
	 */
	@XmlAnyElement
	private Integer cash_fee;
	/**
	 * 现金退款金额
	 */
	@XmlAnyElement
	private Integer cash_refund_fee;
	/**
	 * 代金券或立减优惠退款金额
	 */
	@XmlAnyElement
	private Integer coupon_refund_fee;
	/**
	 * 代金券或立减优惠使用数量
	 */
	@XmlAnyElement
	private Integer coupon_refund_count;
	/**
	 * 代金券或立减优惠ID
	 */
	@XmlAnyElement
	private String coupon_refund_id;
	
	/**
	 * 退款状态
	 */
	@XmlAnyElement
	private String refund_status;

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}

	public String getRefund_channel() {
		return refund_channel;
	}

	public void setRefund_channel(String refund_channel) {
		this.refund_channel = refund_channel;
	}

	public Integer getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(Integer refund_fee) {
		this.refund_fee = refund_fee;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public Integer getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(Integer cash_fee) {
		this.cash_fee = cash_fee;
	}

	public Integer getCash_refund_fee() {
		return cash_refund_fee;
	}

	public void setCash_refund_fee(Integer cash_refund_fee) {
		this.cash_refund_fee = cash_refund_fee;
	}

	public Integer getCoupon_refund_fee() {
		return coupon_refund_fee;
	}

	public void setCoupon_refund_fee(Integer coupon_refund_fee) {
		this.coupon_refund_fee = coupon_refund_fee;
	}

	public Integer getCoupon_refund_count() {
		return coupon_refund_count;
	}

	public void setCoupon_refund_count(Integer coupon_refund_count) {
		this.coupon_refund_count = coupon_refund_count;
	}

	public String getCoupon_refund_id() {
		return coupon_refund_id;
	}

	public void setCoupon_refund_id(String coupon_refund_id) {
		this.coupon_refund_id = coupon_refund_id;
	}

	public String getRefund_status() {
		return refund_status;
	}

	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}

}
