package com.xiaoyu.lingdian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.WxRefundRequest;
import com.xiaoyu.lingdian.service.WxRefundRequestService;

@Service("wxRefundRequestService")
@Transactional
public class WxRefundRequestServiceImpl implements WxRefundRequestService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(WxRefundRequest wxRefundRequest) {
		myBatisDAO.insert("insertWxRefundRequest", wxRefundRequest);
	}

	@Override
	public void insertSelective(WxRefundRequest wxRefundRequest) {
		myBatisDAO.insert("insertWxRefundRequestSelective", wxRefundRequest);
	}

	@Override
	public void update(WxRefundRequest wxRefundRequest) {
		myBatisDAO.update("updateWxRefundRequest", wxRefundRequest) ; 
	}

	@Override
	public void updateNullable(WxRefundRequest wxRefundRequest) {
		myBatisDAO.update("updateWxRefundRequestNullable", wxRefundRequest) ; 
	}

	@Override
	@Transactional(readOnly = true)
	public WxRefundRequest selectByPk(Integer pk) {
		return (WxRefundRequest) myBatisDAO.findForObject("selectWxRefundRequestByPk", pk);
	}

	@Override
	public void deleteByPk(Integer pk) {
		myBatisDAO.delete("deleteWxRefundRequestByPk", pk);
	}

}