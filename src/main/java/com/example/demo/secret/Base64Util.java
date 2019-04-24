package com.example.demo.secret;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
	// 将 s 进行 BASE64 编码
	@SuppressWarnings({"restriction"})
	public static String encodeBASE64(String s) {
		if (s == null)
			return null;
		BASE64Encoder tt = new BASE64Encoder();
		return tt.encode(s.getBytes());
	}

	// 将 BASE64 编码的字符串 s 进行解码
	@SuppressWarnings("restriction")
	public static String decodeBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b,"UTF-8");
		} catch (Exception e) {
			return null;
		}
	}
}
