package com.xiaoyu.lingdian.service;

import java.util.Date;
import java.util.List;
import com.xiaoyu.lingdian.entity.WxRefundQuery;

public interface WxRefundQueryService {

	void insert(WxRefundQuery wxRefundQuery);

	void insertSelective(WxRefundQuery wxRefundQuery);

	void update(WxRefundQuery wxRefundQuery);

	void updateNullable(WxRefundQuery wxRefundQuery);

	WxRefundQuery selectByPk(Integer pk);

	void deleteByPk(Integer pk);
	
	List<WxRefundQuery> selectByQueryDate(Date queryDate);

}