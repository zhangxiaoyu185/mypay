package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.AliPayResponse;

public interface AliPayResponseService {

	void insert(AliPayResponse aliPayResponse);

	void insertSelective(AliPayResponse aliPayResponse);

	void update(AliPayResponse aliPayResponse);

	void updateNullable(AliPayResponse aliPayResponse);

	AliPayResponse selectByPk(Integer pk);

	void deleteByPk(Integer pk);

}