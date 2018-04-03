package com.xiaoyu.lingdian.tool.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author  xiqf
 * @date 创建时间：2015年12月5日 上午10:33:24 
 * @version 1.0
 */
public class HttpsUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpsUtils.class);

	private static final String CRLF = "\r\n";
	
	private static final String METHOD_POST = "POST";
	
	private static final String METHOD_GET = "GET";
	
	private static final String CHARSET_UTF8 = "UTF-8";
	
	private static class TrustAnyTrustManager implements X509TrustManager {
		 
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
 
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
 
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
	}
	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
	}
	
	public static String post(String url, String content) {
		return send(url, content, CHARSET_UTF8, METHOD_POST);
	}
	
	public static String get(String url) {
		return send(url, null, CHARSET_UTF8, METHOD_GET);
	}
	
	public static String sendHttpsWithCert(String url, String content, String certPwd,String certPath) {
		HttpsURLConnection conn = null;
		OutputStream out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
		try {
			KeyStore keyStore  = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(certPath));
			keyStore.load(instream, certPwd.toCharArray());
			
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");  
	        kmf.init(keyStore,certPwd.toCharArray());  
			
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(kmf.getKeyManagers(), new TrustManager[] { new TrustAnyTrustManager() },
			        new java.security.SecureRandom());
			URL realUrl = new URL(url);
			conn = (HttpsURLConnection) realUrl.openConnection();
			conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.connect();
			
			out = conn.getOutputStream();
			if(null != content) {
				out.write(content.trim().getBytes(CHARSET_UTF8));
				// 刷新、关闭
				out.flush();
			}
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), CHARSET_UTF8));
            String line;
            while ((line = in.readLine()) != null) {
            	result.append(line).append(CRLF);
            }
            return result.toString();
		} catch (Exception e) {
			LOGGER.error("发送https请求异常", e);
			return null;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if (conn != null) {
					conn.disconnect();;
				}
			} catch (IOException e) {
				LOGGER.error("Uncaught exception", e);
			}
		}
	}
	
	public static String send(String url, String content, String charset,String method) {
		HttpsURLConnection conn = null;
		OutputStream out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
			        new java.security.SecureRandom());
 
			URL realUrl = new URL(url);
			conn = (HttpsURLConnection) realUrl.openConnection();
			conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(method);
            conn.setUseCaches(false);
            conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.connect();
			
			out = conn.getOutputStream();
			if(null != content) {
				out.write(content.trim().getBytes(charset));
				// 刷新、关闭
				out.flush();
			}
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), CHARSET_UTF8));
            String line;
            while ((line = in.readLine()) != null) {
            	result.append(line).append(CRLF);
            }
            return result.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("发送https请求异常", e);
			return null;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if (conn != null) {
					conn.disconnect();;
				}
			} catch (IOException e) {
				LOGGER.error("Uncaught exception", e);
			}
		}
	}
	
	public static String sendHttpsPost(String url, String content, Map<String, String> headerMap) {
		return sendHttps(url, content, CHARSET_UTF8, METHOD_POST, headerMap);
	}
	
	public static String sendHttps(String url, String content, String charset, String method, Map<String, String> headerMap) {
		HttpsURLConnection conn = null;
		OutputStream out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
			        new java.security.SecureRandom());
 
			URL realUrl = new URL(url);
			conn = (HttpsURLConnection) realUrl.openConnection();
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
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.connect();
			
			out = conn.getOutputStream();
			if(null != content) {
				out.write(content.trim().getBytes(charset));
				// 刷新、关闭
				out.flush();
			}
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), CHARSET_UTF8));
            String line;
            while ((line = in.readLine()) != null) {
            	result.append(line).append(CRLF);
            }
            return result.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("发送https请求异常", e);
			return null;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if (conn != null) {
					conn.disconnect();;
				}
			} catch (IOException e) {
				LOGGER.error("Uncaught exception", e);
			}
		}
	}
}
