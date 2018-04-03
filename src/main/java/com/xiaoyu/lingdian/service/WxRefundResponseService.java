package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.WxRefundResponse;

public interface WxRefundResponseService {

	void insert(WxRefundResponse wxRefundResponse);

	void insertSelective(WxRefundResponse wxRefundResponse);

	void update(WxRefundResponse wxRefundResponse);

	void updateNullable(WxRefundResponse wxRefundResponse);

	WxRefundResponse selectByPk(Integer pk);

	void deleteByPk(Integer pk);

}