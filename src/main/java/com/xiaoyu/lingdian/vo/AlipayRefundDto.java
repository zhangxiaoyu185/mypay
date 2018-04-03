package com.xiaoyu.lingdian.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  xiqf
 * @date 创建时间：2015年12月10日 下午4:06:57 
 * @version 1.0
 */
public class AlipayRefundDto implements Serializable {
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = -4543602716282133835L;
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
	private String _input_charset;
	/**
	 * 签名方式
	 */
	private String sign_type;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 服务器异步通知页面路径
	 */
	private String notify_url;
	/**
	 * 卖家支付宝用户号
	 */
	private String seller_id;
	/**
	 * 卖家支付宝账号
	 */
	private String seller_email;
	/**
	 * 退款请求时间 
	 */
	private String refund_date;
	/**
	 * 退款批次号 
	 */
	private String batch_no;
	/**
	 * 总笔数
	 */
	private String batch_num;
	/**
	 * 单笔数据集
	 */
	private String detail_data;
	
	public Map<String, String> getParameters() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("service", service);
		map.put("partner", partner);
		map.put("sign_type", sign_type);
		map.put("sign", sign);
		map.put("_input_charset", _input_charset);
		map.put("notify_url", notify_url);
		map.put("seller_id", seller_id);
		map.put("seller_email", seller_email);
		map.put("refund_date", refund_date);
		map.put("batch_no", batch_no);
		map.put("batch_num", batch_num);
		map.put("detail_data", detail_data);
		return map;
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

	public String get_input_charset() {
		return _input_charset;
	}

	public void set_input_charset(String _input_charset) {
		this._input_charset = _input_charset;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getRefund_date() {
		return refund_date;
	}

	public void setRefund_date(String refund_date) {
		this.refund_date = refund_date;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getBatch_num() {
		return batch_num;
	}

	public void setBatch_num(String batch_num) {
		this.batch_num = batch_num;
	}

	public String getDetail_data() {
		return detail_data;
	}

	public void setDetail_data(String detail_data) {
		this.detail_data = detail_data;
	}

}
