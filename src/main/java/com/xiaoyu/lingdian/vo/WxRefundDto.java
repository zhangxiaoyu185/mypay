package com.xiaoyu.lingdian.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAnyElement;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author  xiqf
 * @date 创建时间：2015年12月15日 下午3:57:28 
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxRefundDto implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = -3497081878772250632L;
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
	 * 总金额
	 */
	@XmlAnyElement
	private Integer total_fee;
	/**
	 * 退款金额
	 */
	@XmlAnyElement
	private Integer refund_fee;
	/**
	 * 货币种类
	 */
	@XmlAnyElement
	private String refund_fee_type;
	/**
	 * 操作员
	 */
	@XmlAnyElement
	private String op_user_id;
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public Map<String, String> getParametersMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", appid);
		map.put("mch_id", mch_id);
		map.put("device_info", device_info);
		map.put("nonce_str", nonce_str);
		map.put("transaction_id", transaction_id);
		map.put("out_trade_no", out_trade_no);
		map.put("out_refund_no", out_refund_no);
		if(null != total_fee) {
			map.put("total_fee", Integer.toString(total_fee));
		}
		if(null != refund_fee) {
			map.put("refund_fee", Integer.toString(refund_fee));
		}
		map.put("refund_fee_type", refund_fee_type);
		map.put("op_user_id", op_user_id);
		
		return map;
	}
	
	public Map<String, String> getRefundParametersMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", appid);
		map.put("mch_id", mch_id);
		map.put("device_info", device_info);
		map.put("nonce_str", nonce_str);
		map.put("out_trade_no", out_trade_no);
		
		return map;
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

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public Integer getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(Integer refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getRefund_fee_type() {
		return refund_fee_type;
	}

	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}

	public String getOp_user_id() {
		return op_user_id;
	}

	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}
}
