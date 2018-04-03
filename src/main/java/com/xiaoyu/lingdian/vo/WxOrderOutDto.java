package com.xiaoyu.lingdian.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAnyElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author  xiqf
 * @date 创建时间：2015年12月7日 下午3:08:59 
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxOrderOutDto implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = -3753533151890486671L;
	
	@XmlAnyElement
	private String appid;
	@XmlAnyElement
	private String mch_id;
	@XmlAnyElement
	private String device_info;
	@XmlAnyElement
	private String nonce_str;
	@XmlAnyElement
	private String sign;
	@XmlAnyElement
	private String result_code;
	@XmlAnyElement
	private String err_code;
	@XmlAnyElement
	private String err_code_des;
	@XmlAnyElement
	private String trade_type;
	@XmlAnyElement
	private String prepay_id;
	@XmlAnyElement
	private String code_url;
	@XmlAnyElement
	private String return_code;
	@XmlAnyElement
	private String return_msg;

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

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
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
	
}
