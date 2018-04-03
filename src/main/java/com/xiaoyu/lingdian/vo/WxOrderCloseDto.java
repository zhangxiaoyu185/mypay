package com.xiaoyu.lingdian.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAnyElement;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author  xiqf
 * @date 创建时间：2015年12月18日 下午1:55:21 
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxOrderCloseDto implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = 6808568784409663862L;
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
	 * 商户订单号
	 */
	@XmlAnyElement
	private String out_trade_no;
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
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public Map<String, String> getParametersMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", appid);
		map.put("mch_id", mch_id);
		map.put("out_trade_no", out_trade_no);
		map.put("nonce_str", nonce_str);
		
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

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
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
	
}
