package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.PayErrorLog;

public interface PayErrorLogService {

	void insert(PayErrorLog payErrorLog);

	void insertSelective(PayErrorLog payErrorLog);

	void update(PayErrorLog payErrorLog);

	void updateNullable(PayErrorLog payErrorLog);

	PayErrorLog selectByPk(Integer pk);

	void deleteByPk(Integer pk);
	
	void savePayErrorLog(String tradeNo, String reTradeNo, String outTradeNo, String errorMsg);

	void savePayErrorLog(String tradeNo, String outTradeNo, String errorMsg);
	
	PayErrorLog selectByTradeNo(String tradeNo);
	
}