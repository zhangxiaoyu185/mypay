package com.xiaoyu.lingdian.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.PayErrorLog;
import com.xiaoyu.lingdian.service.PayErrorLogService;

@Service("payErrorLogService")
@Transactional
public class PayErrorLogServiceImpl implements PayErrorLogService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(PayErrorLog payErrorLog) {
		myBatisDAO.insert("insertPayErrorLog", payErrorLog);
	}

	@Override
	public void insertSelective(PayErrorLog payErrorLog) {
		myBatisDAO.insert("insertPayErrorLogSelective", payErrorLog);
	}

	@Override
	public void update(PayErrorLog payErrorLog) {
		myBatisDAO.update("updatePayErrorLog", payErrorLog);
	}

	@Override
	public void updateNullable(PayErrorLog payErrorLog) {
		myBatisDAO.update("updatePayErrorLogNullable", payErrorLog);
	}

	@Override
	@Transactional(readOnly = true)
	public PayErrorLog selectByPk(Integer pk) {
		return (PayErrorLog) myBatisDAO.findForObject("selectPayErrorLogByPk", pk);
	}
	
	@Override
	public PayErrorLog selectByTradeNo(String tradeNo) {
		return (PayErrorLog) myBatisDAO.findForObject("selectPayErrorLogByTradeNo", tradeNo);
	}

	@Override
	public void deleteByPk(Integer pk) {
		myBatisDAO.delete("deletePayErrorLogByPk", pk);
	}
	
	@Override
	public void savePayErrorLog(
			String tradeNo,
			String reTradeNo,
			String outTradeNo,
			String errorMsg) {
		PayErrorLog errorLog = selectByTradeNo(tradeNo);
		if(null != errorLog) {
			return;
		}
		PayErrorLog payErrorLog = new PayErrorLog();
		payErrorLog.setCreateDate(new Date());
		payErrorLog.setTradeNo(tradeNo);
		payErrorLog.setReTradeNo(reTradeNo);
		payErrorLog.setOutTradeNo(outTradeNo);
		payErrorLog.setErrorMsg(errorMsg);
		insertSelective(payErrorLog);
	}
	
	@Override
	public void savePayErrorLog(
			String tradeNo,
			String outTradeNo,
			String errorMsg) {
		PayErrorLog payErrorLog = new PayErrorLog();
		payErrorLog.setCreateDate(new Date());
		payErrorLog.setTradeNo(tradeNo);
		payErrorLog.setOutTradeNo(outTradeNo);
		payErrorLog.setErrorMsg(errorMsg);
		insertSelective(payErrorLog);
	}
	
}