package com.xiaoyu.lingdian.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAnyElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author  xiqf
 * @date 创建时间：2015年12月18日 下午2:28:04 
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxOrderCloseOutDto implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = -3556911216808183953L;
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
	
	@XmlAnyElement
	private String sub_mch_id;
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
	 * 业务结果
	 */
	@XmlAnyElement
	private String result_code;

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

	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
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

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	
}
