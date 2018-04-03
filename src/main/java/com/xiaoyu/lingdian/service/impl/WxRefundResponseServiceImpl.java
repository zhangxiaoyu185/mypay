package com.xiaoyu.lingdian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.WxRefundResponse;
import com.xiaoyu.lingdian.service.WxRefundResponseService;

@Service("wxRefundResponseService")
@Transactional
public class WxRefundResponseServiceImpl implements WxRefundResponseService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(WxRefundResponse wxRefundResponse) {
		myBatisDAO.insert("insertWxRefundResponse", wxRefundResponse);
	}

	@Override
	public void insertSelective(WxRefundResponse wxRefundResponse) {
		myBatisDAO.insert("insertWxRefundResponseSelective", wxRefundResponse);
	}

	@Override
	public void update(WxRefundResponse wxRefundResponse) {
		myBatisDAO.update("updateWxRefundResponse", wxRefundResponse) ; 
	}

	@Override
	public void updateNullable(WxRefundResponse wxRefundResponse) {
		myBatisDAO.update("updateWxRefundResponseNullable", wxRefundResponse) ; 
	}

	@Override
	@Transactional(readOnly = true)
	public WxRefundResponse selectByPk(Integer pk) {
		return (WxRefundResponse) myBatisDAO.findForObject("selectWxRefundResponseByPk", pk);
	}

	@Override
	public void deleteByPk(Integer pk) {
		myBatisDAO.delete("deleteWxRefundResponseByPk", pk);
	}

}