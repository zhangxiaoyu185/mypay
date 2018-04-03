package com.xiaoyu.lingdian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.WxCloseorder;
import com.xiaoyu.lingdian.service.WxCloseorderService;

@Service("wxCloseorderService")
@Transactional
public class WxCloseorderServiceImpl implements WxCloseorderService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(WxCloseorder wxCloseorder) {
		myBatisDAO.insert("insertWxCloseorder", wxCloseorder);
	}

	@Override
	public void insertSelective(WxCloseorder wxCloseorder) {
		myBatisDAO.insert("insertWxCloseorderSelective", wxCloseorder);
	}

	@Override
	public void update(WxCloseorder wxCloseorder) {
		myBatisDAO.update("updateWxCloseorder", wxCloseorder) ; 
	}

	@Override
	public void updateNullable(WxCloseorder wxCloseorder) {
		myBatisDAO.update("updateWxCloseorderNullable", wxCloseorder) ; 
	}

	@Override
	@Transactional(readOnly = true)
	public WxCloseorder selectByPk(Integer pk) {
		return (WxCloseorder) myBatisDAO.findForObject("selectWxCloseorderByPk", pk);
	}

	@Override
	public void deleteByPk(Integer pk) {
		myBatisDAO.delete("deleteWxCloseorderByPk", pk);
	}

}