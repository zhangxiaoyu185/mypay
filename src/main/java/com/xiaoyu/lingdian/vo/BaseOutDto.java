package com.xiaoyu.lingdian.vo;

import java.io.Serializable;

/**
 * @author  xiqf
 * @date 创建时间：2016年1月13日 下午3:36:39 
 * @version 1.0
 */
public class BaseOutDto implements Serializable {

	private static final long serialVersionUID = 2619217162810190354L;

	/**
	 * 业务结果
	 */
	private String resultCode;

	/**
	 * 错误代码
	 */
	private String errorCode;

	/**
	 * 错误描述
	 */
	private String errorMsg;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
