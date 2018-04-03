package com.xiaoyu.lingdian.vo;

import java.util.List;

/**
 * @author  xiqf
 * @date 创建时间：2016年1月16日 下午4:40:26 
 * @version 1.0
 */
public class RefundOutDto extends BaseOutDto {

	private static final long serialVersionUID = -3867959597701815614L;
	
	private String refundParams;
	
	private List<RefundResponseDetail> details;

	public String getRefundParams() {
		return refundParams;
	}

	public void setRefundParams(String refundParams) {
		this.refundParams = refundParams;
	}

	public List<RefundResponseDetail> getDetails() {
		return details;
	}

	public void setDetails(List<RefundResponseDetail> details) {
		this.details = details;
	}
	
}
