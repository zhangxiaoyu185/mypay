package com.xiaoyu.lingdian.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class PrePayUpdateDto implements Serializable {

	private static final long serialVersionUID = -3969933310501247135L;
	
	/**
	 * 平台单号
	 */
	private String payNo;
	/**
	 * 调整后价格
	 */
	private BigDecimal adjustFee;
	
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public BigDecimal getAdjustFee() {
		return adjustFee;
	}
	public void setAdjustFee(BigDecimal adjustFee) {
		this.adjustFee = adjustFee;
	}
}
