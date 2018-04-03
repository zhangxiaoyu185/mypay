package com.xiaoyu.lingdian.vo;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlAnyElement;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xiaoyu.lingdian.tool.StringComparator;

/**
 * @author  xiqf
 * @date 创建时间：2015年12月4日 下午1:45:26 
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxOrderDto implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = 1L;
	
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
	 * 商品描述
	 */
	@XmlAnyElement
	private String body;
	/**
	 * 商品详情
	 */
	@XmlAnyElement
	private String detail;
	/**
	 * 附加数据
	 */
	@XmlAnyElement
	private String attach;
	/**
	 * 商户订单号
	 */
	@XmlAnyElement
	private String out_trade_no;
	/**
	 * 货币类型
	 */
	@XmlAnyElement
	private String fee_type;
	/**
	 * 总金额
	 */
	@XmlAnyElement
	private int total_fee;
	/**
	 * 终端IP
	 */
	@XmlAnyElement
	private String spbill_create_ip;
	/**
	 * 交易起始时间
	 */
	@XmlAnyElement
	private String time_start;
	/**
	 * 交易结束时间
	 */
	@XmlAnyElement
	private String time_expire;
	/**
	 * 商品标记
	 */
	@XmlAnyElement
	private String goods_tag;
	/**
	 * 通知地址
	 */
	@XmlAnyElement
	private String notify_url;
	/**
	 * 交易类型
	 */
	@XmlAnyElement
	private String trade_type;
	/**
	 * 商品ID
	 */
	@XmlAnyElement
	private String product_id;
	/**
	 * 指定支付方式
	 */
	@XmlAnyElement
	private String limit_pay;
	/**
	 * 用户标识
	 */
	@XmlAnyElement
	private String openid;

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

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public Map<String,String> getParametersMap() {
		Map<String, String> map = new TreeMap<String, String>(new StringComparator());
		map.put("appid", appid);
		map.put("mch_id", mch_id);
		map.put("device_info", device_info);
		map.put("nonce_str", nonce_str);
		map.put("body", body);
		map.put("detail", detail);
		map.put("attach", attach);
		map.put("out_trade_no", out_trade_no);
		map.put("fee_type", fee_type);
		map.put("total_fee", total_fee + "");
		map.put("spbill_create_ip", spbill_create_ip);
		map.put("time_start", time_start);
		map.put("time_expire", time_expire);
		map.put("goods_tag", goods_tag);
		map.put("notify_url", notify_url);
		map.put("trade_type", trade_type);
		map.put("product_id", product_id);
		map.put("limit_pay", limit_pay);
		map.put("openid", openid);
		return map;
	}
}
