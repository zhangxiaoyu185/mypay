package com.xiaoyu.lingdian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.WxPayResponse;
import com.xiaoyu.lingdian.service.WxPayResponseService;

@Service("wxPayResponseService")
@Transactional
public class WxPayResponseServiceImpl implements WxPayResponseService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(WxPayResponse wxPayResponse) {
		myBatisDAO.insert("insertWxPayResponse", wxPayResponse);
	}

	@Override
	public void insertSelective(WxPayResponse wxPayResponse) {
		myBatisDAO.insert("insertWxPayResponseSelective", wxPayResponse);
	}

	@Override
	public void update(WxPayResponse wxPayResponse) {
		myBatisDAO.update("updateWxPayResponse", wxPayResponse) ; 
	}

	@Override
	public void updateNullable(WxPayResponse wxPayResponse) {
		myBatisDAO.update("updateWxPayResponseNullable", wxPayResponse) ; 
	}

}