package com.xiaoyu.lingdian.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAnyElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author  xiqf
 * @date 创建时间：2015年12月4日 下午8:09:35 
 * @version 1.0
 */
@XStreamAlias("xml")
public class WxNotifyDto implements Serializable {
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = -2350349048662690892L;

	@XmlAnyElement
	private String return_code;
	
	@XmlAnyElement
	private String return_msg;

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
}
