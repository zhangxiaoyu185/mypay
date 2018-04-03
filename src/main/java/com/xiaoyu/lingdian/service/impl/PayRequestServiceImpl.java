package com.xiaoyu.lingdian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.PayRequest;
import com.xiaoyu.lingdian.service.PayRequestService;

@Service("payRequestService")
@Transactional
public class PayRequestServiceImpl implements PayRequestService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(PayRequest payRequest) {
		myBatisDAO.insert("insertPayRequest", payRequest);
	}

	@Override
	public void insertSelective(PayRequest payRequest) {
		myBatisDAO.insert("insertPayRequestSelective", payRequest);
	}

	@Override
	public void update(PayRequest payRequest) {
		myBatisDAO.update("updatePayRequest", payRequest);
	}

	@Override
	public void updateNullable(PayRequest payRequest) {
		myBatisDAO.update("updatePayRequestNullable", payRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public PayRequest selectByPk(Integer pk) {
		return (PayRequest) myBatisDAO.findForObject("selectPayRequestByPk", pk);
	}

	@Override
	public void deleteByPk(Integer pk) {
		myBatisDAO.delete("deletePayRequestByPk", pk);
	}
	
	@Override
	public PayRequest selectByTradeNo(String tradeNo) {
		return (PayRequest) myBatisDAO.findForObject("selectPayRequestByTradeNo", tradeNo);
	}
	
	@Override
	public Integer countByPayNoAndPayChannel(PayRequest payRequest) {
		return (Integer) myBatisDAO.findForObject("countPayRequestByPayNoAndPayChannel", payRequest);
	}
	
}