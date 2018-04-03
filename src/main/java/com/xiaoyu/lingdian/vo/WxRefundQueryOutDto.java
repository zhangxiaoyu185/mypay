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
public class WxRefundQueryOutDto implements Serializable {
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
	private String out_refund_no_0;
	/**
	 * 退款渠道
	 */
	@XmlAnyElement
	private String refund_channel_0;
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
	 * 现金支付金额
	 */
	@XmlAnyElement
	private Integer cash_fee;
	/**
	 * 退款金额
	 */
	@XmlAnyElement
	private String refund_fee_0;
	/**
	 * 微信退款单号
	 */
	@XmlAnyElement
	private String refund_id_0;
	/**
	 * 退款入账账户
	 */
	@XmlAnyElement
	private String refund_recv_accout_0;
	/**
	 * 退款状态
	 */
	@XmlAnyElement
	private String refund_status_0;
	/**
	 * 退款笔数
	 */
	@XmlAnyElement
	private Integer refund_count;
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
	public String getOut_refund_no_0() {
		return out_refund_no_0;
	}
	public void setOut_refund_no_0(String out_refund_no_0) {
		this.out_refund_no_0 = out_refund_no_0;
	}
	public String getRefund_channel_0() {
		return refund_channel_0;
	}
	public void setRefund_channel_0(String refund_channel_0) {
		this.refund_channel_0 = refund_channel_0;
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
	public Integer getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(Integer cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getRefund_fee_0() {
		return refund_fee_0;
	}
	public void setRefund_fee_0(String refund_fee_0) {
		this.refund_fee_0 = refund_fee_0;
	}
	public String getRefund_id_0() {
		return refund_id_0;
	}
	public void setRefund_id_0(String refund_id_0) {
		this.refund_id_0 = refund_id_0;
	}
	public String getRefund_recv_accout_0() {
		return refund_recv_accout_0;
	}
	public void setRefund_recv_accout_0(String refund_recv_accout_0) {
		this.refund_recv_accout_0 = refund_recv_accout_0;
	}
	public String getRefund_status_0() {
		return refund_status_0;
	}
	public void setRefund_status_0(String refund_status_0) {
		this.refund_status_0 = refund_status_0;
	}
	public Integer getRefund_count() {
		return refund_count;
	}
	public void setRefund_count(Integer refund_count) {
		this.refund_count = refund_count;
	}
}
