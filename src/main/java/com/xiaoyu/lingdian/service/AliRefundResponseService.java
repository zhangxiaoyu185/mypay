package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.AliRefundResponse;

public interface AliRefundResponseService {

	void insert(AliRefundResponse aliRefundResponse);

	void insertSelective(AliRefundResponse aliRefundResponse);

	void update(AliRefundResponse aliRefundResponse);

	void updateNullable(AliRefundResponse aliRefundResponse);

	AliRefundResponse selectByPk(Integer pk);

	void deleteByPk(Integer pk);

}