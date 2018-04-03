package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.WxPayResponse;

public interface WxPayResponseService {

	void insert(WxPayResponse wxPayResponse);

	void insertSelective(WxPayResponse wxPayResponse);

	void update(WxPayResponse wxPayResponse);

	void updateNullable(WxPayResponse wxPayResponse);

}