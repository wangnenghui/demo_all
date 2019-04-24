package com.example.demo.test;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {
	public static String encrypt(String strInput) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(strInput.getBytes("utf-8"));
			// 该函数返回值为存放哈希值结果的byte数组
			return ByteAndStrUtils.byteToString(md.digest());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			return null;
		}
	}
}
