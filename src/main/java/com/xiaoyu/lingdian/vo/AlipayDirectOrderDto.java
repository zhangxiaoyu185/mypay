package com.xiaoyu.lingdian.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AlipayDirectOrderDto implements Serializable {
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = 6819857366882675477L;

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
	 * 页面跳转同步通知页面路径
	 */
	private String return_url;
	/**
	 * 请求出错时的通知页面路径
	 */
	private String error_notify_url;
	/**
	 * 商户网站唯一订单号
	 */
	private String out_trade_no;
	/**
	 * 商品名称
	 */
	private String subject;
	/**
	 * 支付类型
	 */
	private String payment_type;
	/**
	 * 交易金额
	 */
	private BigDecimal total_fee;
	/**
	 * 卖家支付宝用户号
	 */
	private String seller_id;
	/**
	 * 卖家支付宝账号
	 */
	private String seller_email;
	/**
	 * 卖家别名支付宝账号
	 */
	private String seller_account_name;
	/**
	 * 客户端IP
	 */
	private String exter_invoke_ip;
	/**
	 * 超时时间
	 */
	private String it_b_pay;
	/**
	 * 客户端号
	 */
	private String app_id;
	/**
	 * 客户端来源
	 */
	private String appenv;
	/**
	 * 商品详情
	 */
	private String body;
	/**
	 * 商品类型
	 */
	private String goods_type;
	/**
	 * 是否发起实名校验
	 */
	private String rn_check;
	/**
	 * 授权令牌
	 */
	private String extern_token;
	/**
	 * 商户业务扩展参数
	 */
	private String out_context;
	
	public Map<String, String> getParameters() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("service", service);
		map.put("partner", partner);
		map.put("sign_type", sign_type);
		map.put("sign", sign);
		map.put("_input_charset", _input_charset);
		map.put("notify_url", notify_url);
		map.put("return_url", return_url);
		map.put("error_notify_url", error_notify_url);
		map.put("out_trade_no", out_trade_no);
		map.put("subject", subject);
		map.put("payment_type", payment_type);
		map.put("total_fee", String.valueOf(total_fee));
		map.put("seller_id", seller_id);
		map.put("seller_email", seller_email);
		map.put("seller_account_name", seller_account_name);
		map.put("exter_invoke_ip", exter_invoke_ip);
		map.put("it_b_pay", it_b_pay);
		map.put("app_id", app_id);
		map.put("appenv", appenv);
		map.put("body", body);
		map.put("goods_type", goods_type);
		map.put("rn_check", rn_check);
		map.put("extern_token", extern_token);
		map.put("out_context", out_context);
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

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getError_notify_url() {
		return error_notify_url;
	}

	public void setError_notify_url(String error_notify_url) {
		this.error_notify_url = error_notify_url;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public BigDecimal getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(BigDecimal total_fee) {
		this.total_fee = total_fee;
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

	public String getSeller_account_name() {
		return seller_account_name;
	}

	public void setSeller_account_name(String seller_account_name) {
		this.seller_account_name = seller_account_name;
	}

	public String getExter_invoke_ip() {
		return exter_invoke_ip;
	}

	public void setExter_invoke_ip(String exter_invoke_ip) {
		this.exter_invoke_ip = exter_invoke_ip;
	}

	public String getIt_b_pay() {
		return it_b_pay;
	}

	public void setIt_b_pay(String it_b_pay) {
		this.it_b_pay = it_b_pay;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getAppenv() {
		return appenv;
	}

	public void setAppenv(String appenv) {
		this.appenv = appenv;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}

	public String getRn_check() {
		return rn_check;
	}

	public void setRn_check(String rn_check) {
		this.rn_check = rn_check;
	}

	public String getExtern_token() {
		return extern_token;
	}

	public void setExtern_token(String extern_token) {
		this.extern_token = extern_token;
	}

	public String getOut_context() {
		return out_context;
	}

	public void setOut_context(String out_context) {
		this.out_context = out_context;
	}
}
