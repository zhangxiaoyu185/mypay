package com.xiaoyu.lingdian.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.WxRefundQuery;
import com.xiaoyu.lingdian.service.WxRefundQueryService;

@Service("wxRefundQueryService")
@Transactional
public class WxRefundQueryServiceImpl implements WxRefundQueryService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(WxRefundQuery wxRefundQuery) {
		myBatisDAO.insert("insertWxRefundQuery", wxRefundQuery);
	}

	@Override
	public void insertSelective(WxRefundQuery wxRefundQuery) {
		myBatisDAO.insert("insertWxRefundQuerySelective", wxRefundQuery);
	}

	@Override
	public void update(WxRefundQuery wxRefundQuery) {
		myBatisDAO.update("updateWxRefundQuery", wxRefundQuery) ; 
	}

	@Override
	public void updateNullable(WxRefundQuery wxRefundQuery) {
		myBatisDAO.update("updateWxRefundQueryNullable", wxRefundQuery) ; 
	}

	@Override
	@Transactional(readOnly = true)
	public WxRefundQuery selectByPk(Integer pk) {
		return (WxRefundQuery) myBatisDAO.findForObject("selectWxRefundQueryByPk", pk);
	}

	@Override
	public void deleteByPk(Integer pk) {
		myBatisDAO.delete("deleteWxRefundQueryByPk", pk);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WxRefundQuery> selectByQueryDate(Date queryDate) {
		return myBatisDAO.findForList("selectWxRefundQueryByQueryDate", queryDate);
	}

}