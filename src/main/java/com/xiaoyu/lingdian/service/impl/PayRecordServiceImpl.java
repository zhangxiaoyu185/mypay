package com.xiaoyu.lingdian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.PayRecord;
import com.xiaoyu.lingdian.service.PayRecordService;

@Service("payRecordService")
@Transactional
public class PayRecordServiceImpl implements PayRecordService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(PayRecord payRecord) {
		myBatisDAO.insert("insertPayRecord", payRecord);
	}

	@Override
	public void insertSelective(PayRecord payRecord) {
		myBatisDAO.insert("insertPayRecordSelective", payRecord);
	}

	@Override
	public void update(PayRecord payRecord) {
		myBatisDAO.update("updatePayRecord", payRecord);
	}

	@Override
	public void updateNullable(PayRecord payRecord) {
		myBatisDAO.update("updatePayRecordNullable", payRecord);
	}

	@Override
	@Transactional(readOnly = true)
	public PayRecord selectByPk(Integer pk) {
		return (PayRecord) myBatisDAO.findForObject("selectPayRecordByPk", pk);
	}

	@Override
	public void deleteByPk(Integer pk) {
		myBatisDAO.delete("deletePayRecordByPk", pk);
	}
	
	@Override
	public PayRecord selectByPayNo(String payNo) {
		return (PayRecord) myBatisDAO.findForObject("selectPayRecordByPayNo", payNo);
	}
	
	@Override
	public PayRecord selectByOrderNo(String orderNo) {
		return (PayRecord) myBatisDAO.findForObject("selectPayRecordByOrderNo", orderNo);
	}
	
	@Override
	public PayRecord selectByTradeNo(String tradeNo) {
		return (PayRecord) myBatisDAO.findForObject("selectPayRecordByTradeNo", tradeNo);
	}
	
	@Override
	public PayRecord selectByOutTradeNo(String outTradeNo) {
		return (PayRecord) myBatisDAO.findForObject("selectPayRecordByOutTradeNo", outTradeNo);
	}
	
}