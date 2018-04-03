package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.AliRefundRequest;

public interface AliRefundRequestService {

	void insert(AliRefundRequest aliRefundRequest);

	void insertSelective(AliRefundRequest aliRefundRequest);

	void update(AliRefundRequest aliRefundRequest);

	void updateNullable(AliRefundRequest aliRefundRequest);

	AliRefundRequest selectByPk(Integer pk);

	void deleteByPk(Integer pk);

}