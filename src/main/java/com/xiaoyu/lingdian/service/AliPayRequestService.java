package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.AliPayRequest;

public interface AliPayRequestService {

	void insert(AliPayRequest aliPayRequest);

	void insertSelective(AliPayRequest aliPayRequest);

	void update(AliPayRequest aliPayRequest);

	void updateNullable(AliPayRequest aliPayRequest);

	AliPayRequest selectByPk(Integer pk);

	void deleteByPk(Integer pk);

}