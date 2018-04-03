package com.xiaoyu.lingdian.vo;

import java.io.Serializable;
import java.util.List;

import com.xiaoyu.lingdian.entity.RefundRequestDetail;
import com.xiaoyu.lingdian.enums.PayChannel;

/**
 * @author  xiqf
 * @date 创建时间：2015年12月18日 上午9:54:09 
 * @version 1.0
 */
public class RefundDto implements Serializable {

	private static final long serialVersionUID = 4393875441795242277L;

	/**
	 * 退款详细列表
	 */
	private List<RefundRequestDetail> details;
	/**
	 * 退款结果异步通知地址
	 */
	private String notifyUrl;
	/**
	 * 支付渠道
	 */
	private PayChannel payChannel;
	
	public List<RefundRequestDetail> getDetails() {
		return details;
	}

	public void setDetails(List<RefundRequestDetail> details) {
		this.details = details;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public PayChannel getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(PayChannel payChannel) {
		this.payChannel = payChannel;
	}
	
}
