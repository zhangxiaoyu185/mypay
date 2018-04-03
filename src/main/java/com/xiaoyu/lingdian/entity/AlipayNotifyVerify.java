package com.xiaoyu.lingdian.entity;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class AlipayNotifyVerify extends BaseEntity {
	
	private String service;
	
	private String partner;
	
	private String notify_id;
	
	public Map<String, String> getParametersMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("service", service);
		map.put("partner", partner);
		map.put("notify_id", notify_id);
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

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}
	
}