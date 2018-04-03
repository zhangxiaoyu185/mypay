package com.xiaoyu.lingdian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.AliPayResponse;
import com.xiaoyu.lingdian.service.AliPayResponseService;

@Service("aliPayResponseService")
@Transactional
public class AliPayResponseServiceImpl implements AliPayResponseService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(AliPayResponse aliPayResponse) {
		myBatisDAO.insert("insertAliPayResponse", aliPayResponse);
	}

	@Override
	public void insertSelective(AliPayResponse aliPayResponse) {
		myBatisDAO.insert("insertAliPayResponseSelective", aliPayResponse);
	}

	@Override
	public void update(AliPayResponse aliPayResponse) {
		myBatisDAO.update("updateAliPayResponse", aliPayResponse) ; 
	}

	@Override
	public void updateNullable(AliPayResponse aliPayResponse) {
		myBatisDAO.update("updateAliPayResponseNullable", aliPayResponse) ; 
	}

	@Override
	@Transactional(readOnly = true)
	public AliPayResponse selectByPk(Integer pk) {
		return (AliPayResponse) myBatisDAO.findForObject("selectAliPayResponseByPk", pk);
	}

	@Override
	public void deleteByPk(Integer pk) {
		myBatisDAO.delete("deleteAliPayResponseByPk", pk);
	}

}