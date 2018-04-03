package com.xiaoyu.lingdian.vo;



/**
 * @author  xiqf
 * @date 创建时间：2016年1月16日 下午4:41:36 
 * @version 1.0
 */
public class RefundResponseDetail extends BaseOutDto {
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = 989355636961742527L;
	
	private String payNo;

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
}
