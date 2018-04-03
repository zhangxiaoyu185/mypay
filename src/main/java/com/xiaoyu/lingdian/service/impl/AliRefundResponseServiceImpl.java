package com.xiaoyu.lingdian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.AliRefundResponse;
import com.xiaoyu.lingdian.service.AliRefundResponseService;

@Service("aliRefundResponseService")
@Transactional
public class AliRefundResponseServiceImpl implements AliRefundResponseService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(AliRefundResponse aliRefundResponse) {
		myBatisDAO.insert("insertAliRefundResponse", aliRefundResponse);
	}

	@Override
	public void insertSelective(AliRefundResponse aliRefundResponse) {
		myBatisDAO.insert("insertAliRefundResponseSelective", aliRefundResponse);
	}

	@Override
	public void update(AliRefundResponse aliRefundResponse) {
		myBatisDAO.update("updateAliRefundResponse", aliRefundResponse) ; 
	}

	@Override
	public void updateNullable(AliRefundResponse aliRefundResponse) {
		myBatisDAO.update("updateAliRefundResponseNullable", aliRefundResponse) ; 
	}

	@Override
	@Transactional(readOnly = true)
	public AliRefundResponse selectByPk(Integer pk) {
		return (AliRefundResponse) myBatisDAO.findForObject("selectAliRefundResponseByPk", pk);
	}

	@Override
	public void deleteByPk(Integer pk) {
		myBatisDAO.delete("deleteAliRefundResponseByPk", pk);
	}

}