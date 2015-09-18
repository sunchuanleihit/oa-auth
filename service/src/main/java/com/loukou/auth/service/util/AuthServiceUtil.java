package com.loukou.auth.service.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.loukou.auth.exception.AuthRuntimeException;

public final class AuthServiceUtil {

	private static final String BASE64_TAG = "==";

	private static byte[] operate(byte[] content, String password,
			boolean isEncrypt) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(isEncrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE,
					securekey, random);
			return cipher.doFinal(content);
		} catch (NoSuchAlgorithmException e) {
			throw new AuthRuntimeException("Failed in decrpty", e);
		} catch (NoSuchPaddingException e) {
			throw new AuthRuntimeException("Failed in decrpty", e);
		} catch (InvalidKeyException e) {
			throw new AuthRuntimeException("Failed in decrpty", e);
		} catch (IllegalBlockSizeException e) {
			throw new AuthRuntimeException("Failed in decrpty", e);
		} catch (BadPaddingException e) {
			throw new AuthRuntimeException("Failed in decrpty", e);
		} catch (InvalidKeySpecException e) {
			throw new AuthRuntimeException("Failed in decrpty", e);
		}
	}

	public static String encrypt(String content, String key) {
		byte[] bytes = operate(content.getBytes(), key, true);
		String base64Str = Base64.encodeBase64String(bytes);
		String result = base64Str.substring(0,
				base64Str.length() - BASE64_TAG.length());
		return result;
	}

	public static String decrypt(String content, String key) {
		byte[] rawContent = Base64.decodeBase64(content + BASE64_TAG);
		byte[] result = operate(rawContent, key, false);
		return new String(result);
	}

}