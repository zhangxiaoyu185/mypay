package com.xiaoyu.lingdian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.WxPayRequest;
import com.xiaoyu.lingdian.service.WxPayRequestService;

@Service("wxPayRequestService")
@Transactional
public class WxPayRequestServiceImpl implements WxPayRequestService {

	@Autowired
 	private MyBatisDAO myBatisDAO;

	@Override
	public void insert(WxPayRequest wxPayRequest) {
		myBatisDAO.insert("insertWxPayRequest", wxPayRequest);
	}

	@Override
	public void insertSelective(WxPayRequest wxPayRequest) {
		myBatisDAO.insert("insertWxPayRequestSelective", wxPayRequest);
	}

	@Override
	public void update(WxPayRequest wxPayRequest) {
		myBatisDAO.update("updateWxPayRequest", wxPayRequest) ; 
	}

	@Override
	public void updateNullable(WxPayRequest wxPayRequest) {
		myBatisDAO.update("updateWxPayRequestNullable", wxPayRequest) ; 
	}

	@Override
	@Transactional(readOnly = true)
	public WxPayRequest selectByPk(Integer pk) {
		return (WxPayRequest) myBatisDAO.findForObject("selectWxPayRequestByPk", pk);
	}

	@Override
	public void deleteByPk(Integer pk) {
		myBatisDAO.delete("deleteWxPayRequestByPk", pk);
	}

}