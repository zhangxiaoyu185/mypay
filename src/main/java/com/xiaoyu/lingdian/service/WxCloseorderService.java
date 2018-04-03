package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.WxCloseorder;

public interface WxCloseorderService {

	void insert(WxCloseorder wxCloseorder);

	void insertSelective(WxCloseorder wxCloseorder);

	void update(WxCloseorder wxCloseorder);

	void updateNullable(WxCloseorder wxCloseorder);

	WxCloseorder selectByPk(Integer pk);

	void deleteByPk(Integer pk);

}