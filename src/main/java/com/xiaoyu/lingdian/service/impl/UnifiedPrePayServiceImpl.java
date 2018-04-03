package com.xiaoyu.lingdian.service.impl;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.xiaoyu.lingdian.entity.PayConstant;
import com.xiaoyu.lingdian.entity.PayRecord;
import com.xiaoyu.lingdian.service.PayRecordService;
import com.xiaoyu.lingdian.service.UnifiedPrePayService;
import com.xiaoyu.lingdian.tool.UniqueSequenceGenerator;
import com.xiaoyu.lingdian.vo.PrePayDto;
import com.xiaoyu.lingdian.vo.PrePayOutDto;
import com.xiaoyu.lingdian.vo.PrePayUpdateDto;

@Service("unifiedPrePayService")
public class UnifiedPrePayServiceImpl implements UnifiedPrePayService {
	
	@Autowired
	private PayRecordService payRecordService;
	
	@Override
	public PrePayOutDto createPrePayOrder(String params) {
		PrePayOutDto prePayOutDto = new PrePayOutDto();
		if(StringUtils.isEmpty(params)) {
			prePayOutDto.setResultCode(PayConstant.FAIL);
			prePayOutDto.setErrorCode(PayConstant.ERROR_0001);
			prePayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0001));
			return prePayOutDto;
		}
		PrePayDto prePayDto = JSON.parseObject(params, PrePayDto.class);
		if(null == prePayDto || StringUtils.isEmpty(prePayDto.getOrderNo()) || StringUtils.isEmpty(prePayDto.getProductName())
			|| null == prePayDto.getFee() || StringUtils.isEmpty(prePayDto.getNotifyUrl())) {
			prePayOutDto.setResultCode(PayConstant.FAIL);
			prePayOutDto.setErrorCode(PayConstant.ERROR_0001);
			prePayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0001));
			return prePayOutDto;
		} 
		PayRecord record = payRecordService.selectByOrderNo(prePayDto.getOrderNo());
		if(null != record) {
			record.setOpenid(prePayDto.getOpenid());
			record.setReturnUrl(prePayDto.getReturnUrl());
			record.setUpdateDate(new Date());
			record.setClientIp(prePayDto.getClientIp());
			payRecordService.update(record);
			prePayOutDto.setPayNo(record.getPayNo());
			prePayOutDto.setResultCode(PayConstant.SUCCESS);
			prePayOutDto.setErrorCode(null);
			prePayOutDto.setErrorMsg(null);
		} else {
			PayRecord payRecord = convertToPayRecord(prePayDto);
			//生成唯一的平台单号
			String payNo = UniqueSequenceGenerator.getInstance().getUniqueSequence(UniqueSequenceGenerator.DEFAULT_TRADE_NO_CODE);
			payRecord.setPayNo(payNo);;
			//保存至数据库
			payRecordService.insert(payRecord);
			
			prePayOutDto.setPayNo(payNo);
			prePayOutDto.setResultCode(PayConstant.SUCCESS);
			prePayOutDto.setErrorCode(null);
			prePayOutDto.setErrorMsg(null);
		}
		return prePayOutDto;
	}
	
	@Override
	public PrePayOutDto updatePrePayOrder(String params) {
		PrePayOutDto prePayOutDto = new PrePayOutDto();
		if(StringUtils.isEmpty(params)) {
			prePayOutDto.setResultCode(PayConstant.FAIL);
			prePayOutDto.setErrorCode(PayConstant.ERROR_0001);
			prePayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0001));
			return prePayOutDto;
		}
		PrePayUpdateDto payUpdateDto = JSON.parseObject(params, PrePayUpdateDto.class);
		if(null == payUpdateDto || StringUtils.isEmpty(payUpdateDto.getPayNo()) || null == payUpdateDto.getAdjustFee()) {
			prePayOutDto.setResultCode(PayConstant.FAIL);
			prePayOutDto.setErrorCode(PayConstant.ERROR_0001);
			prePayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0001));
			return prePayOutDto;
		}
		PayRecord record = payRecordService.selectByPayNo(payUpdateDto.getPayNo());
		if(null == record) {
			prePayOutDto.setResultCode(PayConstant.FAIL);
			prePayOutDto.setErrorCode(PayConstant.ERROR_0006);
			prePayOutDto.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0006));
			return prePayOutDto;
		}
		
		record.setAdjustFee(payUpdateDto.getAdjustFee());
		payRecordService.update(record);
		
		prePayOutDto.setPayNo(payUpdateDto.getPayNo());
		prePayOutDto.setResultCode(PayConstant.SUCCESS);
		prePayOutDto.setErrorCode(null);
		prePayOutDto.setErrorMsg(null);
		
		return prePayOutDto;
	}
	
	private PayRecord convertToPayRecord(PrePayDto prePayDto) {
		PayRecord payRecord = new PayRecord();
		payRecord.setBuyerId(prePayDto.getBuyerId());
		payRecord.setBuyerName(prePayDto.getBuyerName());
		payRecord.setClientIp(prePayDto.getClientIp());
		payRecord.setFee(prePayDto.getFee());
		payRecord.setNotifyUrl(prePayDto.getNotifyUrl());
		payRecord.setOpenid(prePayDto.getOpenid());
		payRecord.setOrderNo(prePayDto.getOrderNo());
		payRecord.setProductDetail(prePayDto.getProductDetail());
		payRecord.setProductId(prePayDto.getProductId());
		payRecord.setProductName(prePayDto.getProductName());
		payRecord.setProperty(prePayDto.getProperty());
		payRecord.setReturnUrl(prePayDto.getReturnUrl());
		payRecord.setCreateDate(new Date());
		return payRecord;
	}
	
}