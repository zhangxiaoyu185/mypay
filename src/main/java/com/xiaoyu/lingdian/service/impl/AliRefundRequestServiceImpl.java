package com.xiaoyu.lingdian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.AliRefundRequest;
import com.xiaoyu.lingdian.service.AliRefundRequestService;

@Service("aliRefundRequestService")
@Transactional
public class AliRefundRequestServiceImpl implements AliRefundRequestService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(AliRefundRequest aliRefundRequest) {
		myBatisDAO.insert("insertAliRefundRequest", aliRefundRequest);
	}

	@Override
	public void insertSelective(AliRefundRequest aliRefundRequest) {
		myBatisDAO.insert("insertAliRefundRequestSelective", aliRefundRequest);
	}

	@Override
	public void update(AliRefundRequest aliRefundRequest) {
		myBatisDAO.update("updateAliRefundRequest", aliRefundRequest) ; 
	}

	@Override
	public void updateNullable(AliRefundRequest aliRefundRequest) {
		myBatisDAO.update("updateAliRefundRequestNullable", aliRefundRequest) ; 
	}

	@Override
	@Transactional(readOnly = true)
	public AliRefundRequest selectByPk(Integer pk) {
		return (AliRefundRequest) myBatisDAO.findForObject("selectAliRefundRequestByPk", pk);
	}

	@Override
	public void deleteByPk(Integer pk) {
		myBatisDAO.delete("deleteAliRefundRequestByPk", pk);
	}

}