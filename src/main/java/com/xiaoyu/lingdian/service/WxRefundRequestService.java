package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.WxRefundRequest;

public interface WxRefundRequestService {

	void insert(WxRefundRequest wxRefundRequest);

	void insertSelective(WxRefundRequest wxRefundRequest);

	void update(WxRefundRequest wxRefundRequest);

	void updateNullable(WxRefundRequest wxRefundRequest);

	WxRefundRequest selectByPk(Integer pk);

	void deleteByPk(Integer pk);

}