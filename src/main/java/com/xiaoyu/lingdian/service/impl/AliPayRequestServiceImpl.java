package com.xiaoyu.lingdian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.AliPayRequest;
import com.xiaoyu.lingdian.service.AliPayRequestService;

@Service("aliPayRequestService")
@Transactional
public class AliPayRequestServiceImpl implements AliPayRequestService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(AliPayRequest aliPayRequest) {
		myBatisDAO.insert("insertAliPayRequest", aliPayRequest);
	}

	@Override
	public void insertSelective(AliPayRequest aliPayRequest) {
		myBatisDAO.insert("insertAliPayRequestSelective", aliPayRequest);
	}

	@Override
	public void update(AliPayRequest aliPayRequest) {
		myBatisDAO.update("updateAliPayRequest", aliPayRequest) ; 
	}

	@Override
	public void updateNullable(AliPayRequest aliPayRequest) {
		myBatisDAO.update("updateAliPayRequestNullable", aliPayRequest) ; 
	}

	@Override
	@Transactional(readOnly = true)
	public AliPayRequest selectByPk(Integer pk) {
		return (AliPayRequest) myBatisDAO.findForObject("selectAliPayRequestByPk", pk);
	}

	@Override
	public void deleteByPk(Integer pk) {
		myBatisDAO.delete("deleteAliPayRequestByPk", pk);
	}

}