package com.xiaoyu.lingdian.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.NotifyLog;
import com.xiaoyu.lingdian.service.NotifyLogService;

@Service("notifyLogService")
@Transactional
public class NotifyLogServiceImpl implements NotifyLogService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(NotifyLog notifyLog) {
		myBatisDAO.insert("insertNotifyLog", notifyLog);
	}

	@Override
	public void insertSelective(NotifyLog notifyLog) {
		myBatisDAO.insert("insertNotifyLogSelective", notifyLog);
	}

	@Override
	public void update(NotifyLog notifyLog) {
		myBatisDAO.update("updateNotifyLog", notifyLog) ; 
	}

	@Override
	public void updateNullable(NotifyLog notifyLog) {
		myBatisDAO.update("updateNotifyLogNullable", notifyLog) ; 
	}

	@Override
	@Transactional(readOnly = true)
	public NotifyLog selectByPk(Integer pk) {
		return (NotifyLog) myBatisDAO.findForObject("selectNotifyLogByPk", pk);
	}

	@Override
	public void deleteByPk(Integer pk) {
		myBatisDAO.delete("deleteNotifyLogByPk", pk);
	}

	@Override
	public NotifyLog selectNotifyLogByTradeNo(String tradeNo) {
		return (NotifyLog) myBatisDAO.findForObject("selectNotifyLogByTradeNo", tradeNo);
	}
	
	@Override
	public NotifyLog selectByTradeNoAndOrderNo(NotifyLog notifyLog) {
		return (NotifyLog) myBatisDAO.findForObject("selectNotifyLogByTradeNoAndOrderNo", notifyLog);
	}
	
	@Override
	public NotifyLog selectByRefundNo(NotifyLog notifyLog) {
		return (NotifyLog) myBatisDAO.findForObject("selectNotifyLogRefundNo", notifyLog);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NotifyLog> selectByQueryDate(Date queryDate) {
		return myBatisDAO.findForList("selectNotifyLogByNotifyDate", queryDate);
	}
	
}