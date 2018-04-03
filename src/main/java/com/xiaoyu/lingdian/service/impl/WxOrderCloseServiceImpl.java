package com.xiaoyu.lingdian.service.impl;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.spring.adapter.PropertyHolder;
import com.xiaoyu.lingdian.entity.PayConstant;
import com.xiaoyu.lingdian.entity.WxCloseorder;
import com.xiaoyu.lingdian.entity.WxOrderCloseResponseDetail;
import com.xiaoyu.lingdian.service.WxCloseorderService;
import com.xiaoyu.lingdian.service.WxOrderCloseService;
import com.xiaoyu.lingdian.tool.StringUtil;
import com.xiaoyu.lingdian.tool.Utils;
import com.xiaoyu.lingdian.tool.http.HttpsUtils;
import com.xiaoyu.lingdian.vo.WxOrderCloseDto;
import com.xiaoyu.lingdian.vo.WxOrderCloseOutDto;

@Service("wxOrderCloseService")
public class WxOrderCloseServiceImpl implements WxOrderCloseService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("BASE_LOGGER");
	
	@Autowired
	private WxCloseorderService closeorderService;
	
	@Override
	public WxOrderCloseResponseDetail closeOrder(String orderNo) {
		WxOrderCloseResponseDetail detail = new WxOrderCloseResponseDetail();
		detail.setOrderNo(orderNo);
		detail.setResultCode(PayConstant.ERROR_0002);
		detail.setErrorCode(PayConstant.ERROR_0002);
		detail.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0002));
		try {
			if(StringUtil.isEmpty(orderNo)) {
				detail.setResultCode(PayConstant.ERROR_0001);
				detail.setErrorCode(PayConstant.ERROR_0001);
				detail.setErrorMsg(PayConstant.ERROR_MAPS.get(PayConstant.ERROR_0001));
				return detail;
			}
			String appId = PropertyHolder.getProperty("wx.appId");
			String mchId = PropertyHolder.getProperty("wx.matchId");
			
			WxOrderCloseDto wxOrderCloseDto = new WxOrderCloseDto();
			wxOrderCloseDto.setAppid(appId);
			wxOrderCloseDto.setMch_id(mchId);
			wxOrderCloseDto.setOut_trade_no(orderNo);
			wxOrderCloseDto.setNonce_str(Utils.getRandomString(PayConstant.WX_PAY_NONCE_STR_LENGTH));
			//签名
			String key = PropertyHolder.getProperty("wx.key");
			String sign = Utils.md5Sign(wxOrderCloseDto.getParametersMap(), key);
			wxOrderCloseDto.setSign(sign);
			//保存请求记录
			WxCloseorder wxCloseorder = convertWxCloseorder(wxOrderCloseDto);
			closeorderService.insert(wxCloseorder);
			
			String url = PropertyHolder.getProperty("wx.closeorder");
			
			LOGGER.info("call wx closeorder request param:{}",wxOrderCloseDto.toString());
			String result = HttpsUtils.post(url, Utils.toXml(wxOrderCloseDto));
			LOGGER.info("call wx closeorder response:{}",result);
			
			if(StringUtils.isEmpty(result)) {
				return detail;
			}
			WxOrderCloseOutDto wxOrderCloseOutDto = Utils.toBean(result, WxOrderCloseOutDto.class);
			if(null == wxOrderCloseOutDto) {
				return detail;
			}
			if(PayConstant.SUCCESS.equals(wxOrderCloseOutDto.getReturn_code())) {
				detail.setResultCode(PayConstant.SUCCESS);
				detail.setErrorCode(null);
				detail.setErrorMsg(null);
			} else {
				detail.setResultCode(PayConstant.FAIL);
				detail.setErrorCode(wxOrderCloseOutDto.getErr_code());
				detail.setErrorMsg(wxOrderCloseOutDto.getErr_code_des());
			}
			
			wxCloseorder.setReNonceStr(wxOrderCloseOutDto.getNonce_str());
			wxCloseorder.setReSign(wxOrderCloseOutDto.getSign());
			wxCloseorder.setReturnCode(wxOrderCloseOutDto.getReturn_code());
			wxCloseorder.setReturnMsg(wxOrderCloseOutDto.getReturn_msg());
			wxCloseorder.setErrCode(wxOrderCloseOutDto.getErr_code());
			wxCloseorder.setReturnMsg(wxOrderCloseOutDto.getErr_code_des());
			wxCloseorder.setUpdateDate(new Date());
			//更新关闭订单返回结果
			closeorderService.update(wxCloseorder);
		} catch(Exception e) {
			LOGGER.error("wx close order error:", e);
		}
		return detail;
	}
	
	private WxCloseorder convertWxCloseorder(WxOrderCloseDto wxOrderCloseDto) {
		WxCloseorder wxCloseorder = new WxCloseorder();
		if(null == wxOrderCloseDto) {
			return wxCloseorder;
		}
		wxCloseorder.setAppid(wxOrderCloseDto.getAppid());
		wxCloseorder.setMchId(wxOrderCloseDto.getMch_id());
		wxCloseorder.setNonceStr(wxOrderCloseDto.getNonce_str());
		wxCloseorder.setTradeNo(wxOrderCloseDto.getOut_trade_no());
		wxCloseorder.setSign(wxOrderCloseDto.getSign());
		wxCloseorder.setCreateDate(new Date());
		return wxCloseorder;
	}
	
}