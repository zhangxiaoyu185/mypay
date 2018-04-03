package com.xiaoyu.lingdian.vo;

import java.io.Serializable;

import com.xiaoyu.lingdian.enums.PayChannel;
import com.xiaoyu.lingdian.enums.TradeType;


/**
 * @author  xiqf
 * @date 创建时间：2016年1月12日 下午2:46:20 
 * @version 1.0
 */
public class UnifiedOrderPayDto implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = -5642055632589682567L;
	/**
	 * 平台单号
	 */
	private String payNo;
	/**
	 * 支付渠道
	 */
	private PayChannel payChannel;
	/**
	 * 交易类型
	 */
	private TradeType tradeType;
	/**
	 * 支付超时时间
	 */
	private Integer payTimeOut;
	/**
	 * 支付账户编号
	 */
	private Integer payAccountId;
	/**
	 * 海关编号
	 */
	private Integer customsId;
	
	
	public Integer getPayTimeOut() {
		return payTimeOut;
	}
	public void setPayTimeOut(Integer payTimeOut) {
		this.payTimeOut = payTimeOut;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public PayChannel getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(PayChannel payChannel) {
		this.payChannel = payChannel;
	}
	public TradeType getTradeType() {
		return tradeType;
	}
	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}
	public Integer getPayAccountId() {
		return payAccountId;
	}
	public void setPayAccountId(Integer payAccountId) {
		this.payAccountId = payAccountId;
	}
	public Integer getCustomsId() {
		return customsId;
	}
	public void setCustomsId(Integer customsId) {
		this.customsId = customsId;
	}
	
}
