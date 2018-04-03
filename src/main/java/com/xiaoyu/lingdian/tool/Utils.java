package com.xiaoyu.lingdian.tool;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.xiaoyu.lingdian.core.spring.adapter.PropertyHolder;
import com.xiaoyu.lingdian.entity.PayConstant;
import com.xiaoyu.lingdian.tool.encrypt.MD5;

/**
 * @author  xiqf
 * @date 创建时间：2015年12月4日 下午8:33:08 
 * @version 1.0
 */
public class Utils {
	private static final String YYYYMMDD = "yyyyMMdd";
	
	public static String createRefundNo(Integer lastId) {
		String dateStr = DateUtil.formatDate(YYYYMMDD, new Date());
		if(null == lastId) {
			return dateStr + "9999";
		} else {
			String id = lastId.toString();
			int length = id.length();
			if(length < 4) {
				int count = 4 - length;
				StringBuilder sb = new StringBuilder();
				for(int i = 0;i < count ;i++) {
					sb.append("0");
				}
				sb.append(id);
				return dateStr + sb.toString();
			} else {
				return dateStr + id.substring(length - 4,length);
			}
		}
	}
	
	public static String toXml(Object obj) {
		XStream xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
		xstream.processAnnotations(obj.getClass());
		return xstream.toXML(obj);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T toBean(String xmlStr, Class<T> cls) {
		XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(cls);
        T t = (T) xstream.fromXML(xmlStr);
        return t;
	}
	
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";     
	    SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 } 
	
	public static String md5Sign(Map<String,String> map,String secretKey) {
		Set<String> keySet = map.keySet();
		Map<String,String> newMap = new TreeMap<String, String>(new StringComparator());
		for(String key : keySet) {
			String value = map.get(key);
			if(!StringUtil.isEmpty(key) && !StringUtil.isEmpty(value)) {
				newMap.put(key, value);
			}
		}
		MapJoiner joiner =  Joiner.on("&").withKeyValueSeparator("=");
		String params = joiner.join(newMap);
		params += "&key=" + secretKey;
		return MD5.sign(params).toUpperCase();
	}
	
	public static String mapToStr(Map<String,String> map) {
		if(null != map && map.size() > 0) {
			Set<String> keySet = map.keySet();
			Map<String,String> newMap = new TreeMap<String, String>(new StringComparator());
			for(String key : keySet) {
				String value = map.get(key);
				if(!StringUtil.isEmpty(key) && !StringUtil.isEmpty(value)) {
					newMap.put(key, value);
				}
			}
			MapJoiner joiner =  Joiner.on("&").withKeyValueSeparator("=");
			return joiner.join(newMap);
		} else {
			return null;
		}
	}

	/**
	 * FIXME Do not call InetAddress.getLocalHost() on multihomed servers.
	 * FIXME On a multihomed server, InetAddress.getLocalHost() simply returns the IP address associated with the server's internal hostname.
	 * @return
	 */
	public static String getLocalHostIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return null;
		}
	}
	
	public static String getRemoteIp(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	    if(!StringUtil.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
	        //多次反向代理后会有多个ip值，第一个ip才是真实ip
	        int index = ip.indexOf(",");
	        if(index != -1){
	            return ip.substring(0,index);
	        }else{
	            return ip;
	        }
	    }
	    ip = request.getHeader("X-Real-IP");
	    if(!StringUtil.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
	        return ip;
	    }
	    return request.getRemoteAddr();
	}
	
	 /**
	  * FIXME 用StringBuilder#append 替换 直接字符串连接, 另外, 需要对组成HTML的参数HTML 编码(至少保证对双引号编码)
     * 建立请求，以表单HTML形式构造（默认）
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName) {
        //待请求参数数组
        List<String> keys = new ArrayList<String>(sParaTemp.keySet());

        String getWay = PropertyHolder.getProperty("alipay.getway");

		StringBuilder sbHtml = new StringBuilder();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + getWay
                      + "_input_charset=" + PayConstant.CHARSET_UTF8 + "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
        	String name = keys.get(i);
        	String value = sParaTemp.get(name);
        	if(!StringUtil.isEmpty(name) && !StringUtil.isEmpty(value)) {
        		sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        	}
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

        return sbHtml.toString();
    }
    
	/**
	 * 包装通联支付表单
	 *
	 * @param sParaTemp 请求参数数组
	 * @param strMethod 提交方式。两个值可选：post、get
	 * @param getWay 请求URL
	 * @return 提交表单HTML文本
	 */
	public static String buildAllinPayRequest(Map<String, String> sParaTemp, String strMethod, String getWay) {
		// 待请求参数数组
		List<String> keys = new ArrayList<String>(sParaTemp.keySet());

		StringBuilder sbHtml = new StringBuilder();

		sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\""
				+ getWay + "\" method=\"" + strMethod + "\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = keys.get(i);
			String value = sParaTemp.get(name) == null ? "" : sParaTemp.get(name);
			if (!StringUtil.isEmpty(name)) {
				sbHtml.append("<input type=\"hidden\" name=\"" + name
						+ "\" value=\"" + value + "\"/>");
			}
		}

		// submit按钮控件请不要含有name属性
		sbHtml.append("<input type=\"submit\" value=\"确认付款\" style=\"display:none;\"></form>");
		sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

		return sbHtml.toString();
	}
   
}
