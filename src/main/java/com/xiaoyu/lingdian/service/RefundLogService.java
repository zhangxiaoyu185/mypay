package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.RefundLog;

public interface RefundLogService {

	void insert(RefundLog refundLog);

	void insertSelective(RefundLog refundLog);

	void update(RefundLog refundLog);

	void updateNullable(RefundLog refundLog);

	RefundLog selectByPk(Integer pk);

	void deleteByPk(Integer pk);
	
	Integer selectLastId();
	
	RefundLog selectByOutTradeNo(String outTradeNo);

}