package com.xiaoyu.lingdian.entity;

import java.util.List;

@SuppressWarnings("serial")
public class WxOrderCloseResponse extends BaseEntity {
	
	private List<WxOrderCloseResponseDetail> details;

	public List<WxOrderCloseResponseDetail> getDetails() {
		return details;
	}

	public void setDetails(List<WxOrderCloseResponseDetail> details) {
		this.details = details;
	}
	
}