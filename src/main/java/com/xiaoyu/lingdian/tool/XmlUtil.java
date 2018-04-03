package com.xiaoyu.lingdian.tool;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * @author  xiqf
 * @date 创建时间：2015年12月4日 下午8:33:08 
 * @version 1.0
 */
public class XmlUtil {

	public static String toXml(Object obj) {
		XStream xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
		xstream.processAnnotations(obj.getClass());
		return xstream.toXML(obj);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T toBean(String xmlStr, Class<T> cls) {
		XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(cls);
		xstream.ignoreUnknownElements(); //忽略位置节点
        T t = (T) xstream.fromXML(xmlStr);
        return t;
	}

}
