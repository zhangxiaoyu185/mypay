package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.WxPayRequest;

public interface WxPayRequestService {

	void insert(WxPayRequest wxPayRequest);

	void insertSelective(WxPayRequest wxPayRequest);

	void update(WxPayRequest wxPayRequest);

	void updateNullable(WxPayRequest wxPayRequest);

	WxPayRequest selectByPk(Integer pk);

	void deleteByPk(Integer pk);

}