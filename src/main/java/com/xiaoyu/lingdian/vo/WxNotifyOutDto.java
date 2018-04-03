package com.xiaoyu.lingdian.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAnyElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author  xiqf
 * @date 创建时间：2015年12月4日 下午7:46:56 
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxNotifyOutDto implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = -1726050948450812815L;
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
	 * 用户标识
	 */
	@XmlAnyElement
	private String openid;
	/**
	 * 是否关注公众账号
	 */
	@XmlAnyElement
	private String is_subscribe;
	/**
	 * 交易类型
	 */
	@XmlAnyElement
	private String trade_type;
	/**
	 * 付款银行
	 */
	@XmlAnyElement
	private String bank_type;
	/**
	 * 总金额
	 */
	@XmlAnyElement
	private Integer total_fee;
	/**
	 * 货币种类
	 */
	@XmlAnyElement
	private String fee_type;
	/**
	 * 现金支付金额
	 */
	@XmlAnyElement
	private Integer cash_fee;
	/**
	 * 现金支付货币类型
	 */
	@XmlAnyElement
	private String cash_fee_type;
	/**
	 * 代金券或立减优惠金额
	 */
	@XmlAnyElement
	private Integer coupon_fee;
	/**
	 * 代金券或立减优惠使用数量
	 */
	@XmlAnyElement
	private Integer coupon_count;
	/**
	 * 代金券或立减优惠ID
	 */
	@XmlAnyElement
	private String coupon_id_$n;
	/**
	 * 单个代金券或立减优惠支付金
	 */
	@XmlAnyElement
	private Integer coupon_fee_$n;
	/**
	 * 微信支付订单号
	 */
	@XmlAnyElement
	private String transaction_id;
	/**
	 * 商户订单号
	 */
	@XmlAnyElement
	private String out_trade_no;
	/**
	 * 商家数据包
	 */
	@XmlAnyElement
	private String attach;
	/**
	 * 支付完成时间
	 */
	@XmlAnyElement
	private String time_end;
	
	public Map<String, String> getParametersMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("return_code", return_code);
		map.put("return_msg", return_msg);
		map.put("appid", appid);
		map.put("mch_id", mch_id);
		map.put("device_info", device_info);
		map.put("nonce_str", nonce_str);
		map.put("result_code", result_code);
		map.put("err_code", err_code);
		map.put("err_code_des", err_code_des);
		map.put("openid", openid);
		map.put("is_subscribe", is_subscribe);
		map.put("trade_type", trade_type);
		map.put("bank_type", bank_type);
		if(null != total_fee) {
			map.put("total_fee", Integer.toString(total_fee));
		}
		map.put("fee_type", fee_type);
		if(null != cash_fee) {
			map.put("cash_fee", Integer.toString(cash_fee));
		}
		map.put("cash_fee_type", cash_fee_type);
		if(null != coupon_fee) {
			map.put("coupon_fee", Integer.toString(coupon_fee));
		}
		map.put("transaction_id", transaction_id);
		map.put("out_trade_no", out_trade_no);
		map.put("attach", attach);
		map.put("time_end", time_end);
		
		return map;
	}

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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
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

	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}

	public Integer getCoupon_fee() {
		return coupon_fee;
	}

	public void setCoupon_fee(Integer coupon_fee) {
		this.coupon_fee = coupon_fee;
	}

	public Integer getCoupon_count() {
		return coupon_count;
	}

	public void setCoupon_count(Integer coupon_count) {
		this.coupon_count = coupon_count;
	}

	public String getCoupon_id_$n() {
		return coupon_id_$n;
	}

	public void setCoupon_id_$n(String coupon_id_$n) {
		this.coupon_id_$n = coupon_id_$n;
	}

	public Integer getCoupon_fee_$n() {
		return coupon_fee_$n;
	}

	public void setCoupon_fee_$n(Integer coupon_fee_$n) {
		this.coupon_fee_$n = coupon_fee_$n;
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

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
}
