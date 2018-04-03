package com.xiaoyu.lingdian.service;

import java.util.Date;
import java.util.List;
import com.xiaoyu.lingdian.entity.NotifyLog;

public interface NotifyLogService {

	void insert(NotifyLog notifyLog);

	void insertSelective(NotifyLog notifyLog);

	void update(NotifyLog notifyLog);

	void updateNullable(NotifyLog notifyLog);

	NotifyLog selectByPk(Integer pk);

	void deleteByPk(Integer pk);
	
	NotifyLog selectNotifyLogByTradeNo(String tradeNo);
	
	NotifyLog selectByRefundNo(NotifyLog notifyLog);
	
	NotifyLog selectByTradeNoAndOrderNo(NotifyLog notifyLog);
	
	List<NotifyLog> selectByQueryDate(Date queryDate);

}