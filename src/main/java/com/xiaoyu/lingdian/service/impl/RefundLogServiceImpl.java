package com.xiaoyu.lingdian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.RefundLog;
import com.xiaoyu.lingdian.service.RefundLogService;

@Service("refundLogService")
@Transactional
public class RefundLogServiceImpl implements RefundLogService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(RefundLog refundLog) {
		myBatisDAO.insert("insertRefundLog", refundLog);
	}

	@Override
	public void insertSelective(RefundLog refundLog) {
		myBatisDAO.insert("insertRefundLogSelective", refundLog);
	}

	@Override
	public void update(RefundLog refundLog) {
		myBatisDAO.update("updateRefundLog", refundLog);
	}

	@Override
	public void updateNullable(RefundLog refundLog) {
		myBatisDAO.update("updateRefundLogNullable", refundLog);
	}

	@Override
	@Transactional(readOnly = true)
	public RefundLog selectByPk(Integer pk) {
		return (RefundLog) myBatisDAO.findForObject("selectRefundLogByPk", pk);
	}

	@Override
	public void deleteByPk(Integer pk) {
		myBatisDAO.delete("deleteRefundLogByPk", pk);
	}
	
	@Override
	public Integer selectLastId() {
		return (Integer) myBatisDAO.findForObject("selectRefundLogLastId");
	}
	
	@Override
	@Transactional(readOnly = true)
	public RefundLog selectByOutTradeNo(String outTradeNo) {
		return (RefundLog) myBatisDAO.findForObject("selectRefundLogByOutTradeNo", outTradeNo);
	}
	
}