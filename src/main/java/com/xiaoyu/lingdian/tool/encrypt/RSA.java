package com.xiaoyu.lingdian.tool.encrypt;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;


public class RSA {

	private static final String ALGORITHM = "RSA";

	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	private static final String DEFAULT_CHARSET = "UTF-8";

	public static String sign(String content, String privateKey) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey.getBytes(DEFAULT_CHARSET)));
			KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initSign(priKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));

			byte[] signed = signature.sign();

			return Base64.encodeBase64String(signed);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean verify(String content, String publicKey, String sign) {
		String mysign = sign(content, publicKey);
		if(sign.equals(mysign)){
			return true;
		} else {
			return false;
		}
	}
}
