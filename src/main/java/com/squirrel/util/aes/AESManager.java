package com.squirrel.util.aes;

import java.io.Console;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AESManager {

	// static private AESManager myAes;
	// private AES256Util aes256Util;
	private HashMap<String, Object> aesMap;
//	static {
//		myAes = new AESManager("1q2w3e4r5t6y7u8i");
//	}

//	public AESManager(String key) {
//		// TODO Auto-generated constructor stub
//		try {
//			aes256Util = new AES256Util(key);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public String enCodeText(String aesname, String text) {

		String result = "";
		AES256Util aes256Util = null;
		if (!aesMap.containsKey(aesname))
			try {
				aesMap.put(aesname, new AES256Util(aesname));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		aes256Util = (AES256Util) aesMap.get(aesname);

		try {
			result = aes256Util.aesEncode(text);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("암호화 문자열 길이" + result.length());
		return result;
	}

	public HashMap<String, Object> getAesMap() {
		return aesMap;
	}

	public void setAesMap(HashMap<String, Object> aesMap) {
		this.aesMap = aesMap;
	}

	public String deCodeText(String aesname, String str) {
		String result = "";
		AES256Util aes256Util = null;
		if (!aesMap.containsKey(aesname))
			try {
				aesMap.put(aesname, new AES256Util(aesname));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		aes256Util = (AES256Util) aesMap.get(aesname);

		try {
			result = aes256Util.aesDecode(str);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("복호화 문자열 길이" + result.length());
		return result;
	}

	public String escapeHtml(String str) {

		return null;
	}

}
