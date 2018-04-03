package com.xiaoyu.lingdian.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * @author  xiqf
 * @date 创建时间：2015年12月30日 下午4:59:35 
 * @version 1.0
 */
public class AlipayRefundOutDto implements Serializable {
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = 7328502372970128959L;
	
	/**
	 * 通知时间 
	 */
	private String notify_time;
	/**
	 * notify_type
	 */
	private String notify_type;
	/**
	 * notify_id
	 */
	private String notify_id;
	/**
	 * sign_type
	 */
	private String sign_type;
	/**
	 * sign 
	 */
	private String sign;
	/**
	 * batch_no 
	 */
	private String batch_no;
	/**
	 * success_num
	 */
	private String success_num;
	/**
	 * result_details
	 */
	private String result_details;

	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public Map<String, String> getParametersMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("notify_time",notify_time);
		map.put("notify_type",notify_type);
		map.put("notify_id",notify_id);
//		map.put("sign_type",sign_type);
//		map.put("sign",sign);
		map.put("batch_no",batch_no);
		map.put("success_num",success_num);
		map.put("result_details",result_details);
		return map;
	}
	
	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}

	public String getNotify_type() {
		return notify_type;
	}

	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
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

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getSuccess_num() {
		return success_num;
	}

	public void setSuccess_num(String success_num) {
		this.success_num = success_num;
	}

	public String getResult_details() {
		return result_details;
	}

	public void setResult_details(String result_details) {
		this.result_details = result_details;
	}
	
}
