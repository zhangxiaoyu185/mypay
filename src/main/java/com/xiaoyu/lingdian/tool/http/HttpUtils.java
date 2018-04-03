package com.xiaoyu.lingdian.tool.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xiaoyu.lingdian.entity.PayConstant;

public class HttpUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);
    private static final String CRLF = "\r\n";
    
    /**
     * @Title: sendPost
     * @Description: TODO(发送post请求)
     * @param  url
     * @param  content
     * @param  headerMap
     * @param  connectTimeout
     * @param  readTimeout
     * @return String    返回类型
     * @throws
     */
    public static String sendPost(String url, byte[] content, Map<String,String> headerMap, int connectTimeout, int readTimeout) {
    	return sendHttpRequest(url, content, PayConstant.HTTP_METHOD_POST, headerMap, connectTimeout, readTimeout);
    }
    /**
     * @Title: sendPost
     * @Description: TODO(发送post请求)
     * @param  url
     * @param  contetent
     * @return String    返回类型
     * @throws
     */
    public static String sendPost(String url, String content, Map<String,String> headerMap) {
    	if(null != content) {
    		return sendPost(url, content.getBytes(),headerMap, PayConstant.CONNECTIONTIMEOUT, PayConstant.READTIMEOUT);
    	} else {
    		return sendPost(url, null,headerMap, PayConstant.CONNECTIONTIMEOUT, PayConstant.READTIMEOUT);
    	}
    }
    /**
     * @Title: sendGet
     * @Description: TODO(发送get请求)
     * @param  url
     * @param  headerMap
     * @param  connectTimeout
     * @param  readTimeout
     * @throws
     */
    public static String sendGet(String url, Map<String,String> headerMap, int connectTimeout, int readTimeout) {
    	return sendHttpRequest(url,null, PayConstant.HTTP_METHOD_GET, headerMap, connectTimeout, readTimeout);
    }
    
    public static String sendHttpRequest(String url, byte[] content,String method, Map<String,String> headerMap, int connectTimeout, int readTimeout) {
    	OutputStream out = null;
        BufferedReader in = null;
        HttpURLConnection conn = null;
        StringBuilder result = new StringBuilder();

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn = (HttpURLConnection) realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(method);
            conn.setUseCaches(false);
            if(null != headerMap) {
                Set<String> keySet = headerMap.keySet();
                for(String key : keySet) {
                	conn.setRequestProperty(key, headerMap.get(key));
                }
            }

            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.connect();
            //获取URLConnection对象对应的输出流
            out = conn.getOutputStream();
            if(null != content) {
//                out = conn.getOutputStream();
                //发送请求参数
                out.write(content);
//                flush输出流的缓冲
                out.flush();
            }
            //定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),PayConstant.CHARSET_UTF8));
            String line;

            while ((line = in.readLine()) != null) {
            	result.append(line).append(CRLF);
            }

        } catch (Exception e) {
        	LOGGER.error("[发送http请求]异常：",e);
        } finally { 
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (conn != null) {
    				conn.disconnect();
    			}
            } catch (IOException ex) {
            	LOGGER.error("[发送http请求]异常：",ex);
            }
        }
        return result.toString();
    }

}
