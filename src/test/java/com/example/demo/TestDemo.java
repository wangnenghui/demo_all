package com.example.demo;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class TestDemo {
	private static  Map<String,byte[]> map_t=new HashMap<>();
	public static void main (String[] args) throws Exception {
		 String data = "abcasfasdf";
		 jdkDSA(data);
		 System.out.println(verfiy(map_t,data));
	}
	
	 public static void jdkDSA(String data) {
		    try {
		      //1.初始化密钥
		      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
		      keyPairGenerator.initialize(512);
		      KeyPair keyPair = keyPairGenerator.generateKeyPair();
		      DSAPublicKey dsaPublicKey = (DSAPublicKey) keyPair.getPublic();  //生成公钥
		      map_t.put("dsaPublicKey", dsaPublicKey.getEncoded());
		      DSAPrivateKey dsaPrivateKey = (DSAPrivateKey)keyPair.getPrivate(); //生成私钥
		      //2.执行签名
		      PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(dsaPrivateKey.getEncoded());
		      KeyFactory keyFactory = KeyFactory.getInstance("DSA");
		      PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		      Signature signature = Signature.getInstance("DSA");
		      signature.initSign(privateKey);
		      signature.update(data.getBytes()); //通过私钥加密文件
		      byte[] result = signature.sign();//生成签名
		      map_t.put("sign", result);
		      System.out.println("jdk dsa sign : " + new String(result));
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		  }
	 public static boolean verfiy(Map<String,byte[]> map,String data) throws Exception {
		//3.验证签名
		 byte publickey[]=map.get("dsaPublicKey");
		 byte sign[]=map.get("sign");
	      X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publickey);
	      KeyFactory keyFactory = KeyFactory.getInstance("DSA");
	      keyFactory = KeyFactory.getInstance("DSA");
	      Signature signature = Signature.getInstance("DSA");
	      PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
	      signature.initVerify(publicKey);
	      signature.update((data+"1").getBytes());
	      System.out.println("######"+new String(data));
	      boolean bool = signature.verify(sign);
	      System.out.println("jdk dsa verify : " + bool);
	      return bool;
	 }
}
