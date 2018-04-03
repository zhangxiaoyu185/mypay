package com.xiaoyu.lingdian.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.entity.PayConstant;
import com.xiaoyu.lingdian.entity.PayRecord;
import com.xiaoyu.lingdian.entity.PayRequest;
import com.xiaoyu.lingdian.service.PayCommonService;
import com.xiaoyu.lingdian.service.PayErrorLogService;
import com.xiaoyu.lingdian.service.PayRecordService;
import com.xiaoyu.lingdian.service.PayRequestService;

@Service("payCommonService")
public class PayCommonServiceImpl implements PayCommonService {
	
	@Autowired
	private PayRecordService payRecordService;
	
	@Autowired
	private PayRequestService payRequestService;
	
	@Autowired
	private PayErrorLogService payErrorLogService;
	
	@Override
	public boolean verifyLocalOrder(PayRequest payRequest) {
		PayRequest request = payRequestService.selectByTradeNo(payRequest.getTradeNo());
		if(null == request) {
			return false;
		}
		PayRecord record =  payRecordService.selectByPayNo(request.getPayNo());
		if(null == record) {//不是我们系统发出的交易号
			return false;
		}
		if(payRequest.getFee().compareTo(request.getFee()) != 0) {//通知价格和实际支付价格不一样
			payErrorLogService.savePayErrorLog(payRequest.getTradeNo(), payRequest.getOutTradeNo(),
					PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0012));
			return false;
		}
		if(PayConstant.SUCCESS.equals(record.getPayResult())) {
			if(!payRequest.getTradeNo().equals(record.getTradeNo())) {//同一个订单成功支付了两次,计入异常订单log
				payErrorLogService.savePayErrorLog(record.getTradeNo(), payRequest.getTradeNo(), payRequest.getOutTradeNo(),
						PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0011));
				
				request.setPayResult(PayConstant.SUCCESS);
				request.setOutTradeNo(payRequest.getOutTradeNo());
				request.setPayDate(payRequest.getPayDate());
				payRequestService.update(request);
				return false;
			} else {
				return true;
			}
		} else {
			request.setPayResult(PayConstant.SUCCESS);
			request.setOutTradeNo(payRequest.getOutTradeNo());
			request.setPayDate(payRequest.getPayDate());
			payRequestService.update(request);
			
			record.setPayChannel(request.getPayChannel());
			record.setTradeType(request.getTradeType());
			record.setPayDate(payRequest.getPayDate());
			record.setOutTradeNo(payRequest.getOutTradeNo());
			record.setPayResult(PayConstant.SUCCESS);
			record.setTradeNo(payRequest.getTradeNo());
			record.setUpdateDate(new Date());
			payRecordService.update(record);
			
			return true;
		}
	}
	
}