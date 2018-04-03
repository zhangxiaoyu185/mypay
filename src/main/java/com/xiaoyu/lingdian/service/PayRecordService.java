package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.PayRecord;

public interface PayRecordService {

	void insert(PayRecord payRecord);

	void insertSelective(PayRecord payRecord);

	void update(PayRecord payRecord);

	void updateNullable(PayRecord payRecord);

	PayRecord selectByPk(Integer pk);

	void deleteByPk(Integer pk);
	
	PayRecord selectByPayNo(String payNo);
	
	PayRecord selectByOrderNo(String orderNo);
	
	PayRecord selectByTradeNo(String tradeNo);
	
	PayRecord selectByOutTradeNo(String outTradeNo);
	
}