package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.PayRequest;

public interface PayRequestService {

	void insert(PayRequest payRequest);

	void insertSelective(PayRequest payRequest);

	void update(PayRequest payRequest);

	void updateNullable(PayRequest payRequest);

	PayRequest selectByPk(Integer pk);

	void deleteByPk(Integer pk);
	
	PayRequest selectByTradeNo(String tradeNo);
	
	Integer countByPayNoAndPayChannel(PayRequest payRequest);
	
}