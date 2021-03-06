package com.example.demo.secret;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


public abstract class DSACoder{

    public static final String ALGORITHM = "DSA";

   
    private static final int KEY_SIZE = 1024;

   
    private static final String DEFAULT_SEED = "7d8a3082122966e3";

    private static final String PUBLIC_KEY = "DSAPublicKey";
    private static final String PRIVATE_KEY = "DSAPrivateKey";

   
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = Base64Util.decodeBASE64(privateKey).getBytes();

        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
        signature.initSign(priKey);
        signature.update(data);

        return Base64Util.encodeBASE64(new String(signature.sign()));
    }

   
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {

        // 解密由base64编码的公钥
        byte[] keyBytes = Base64Util.decodeBASE64(new String(publicKey)).getBytes();

        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        // ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
        signature.initVerify(pubKey);
        signature.update(data);

        // 验证签名是否正常
        return signature.verify(Base64Util.decodeBASE64(sign).getBytes());
    }

   
    public static Map initKey(String seed) throws Exception {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(ALGORITHM);
        // 初始化随机产生器
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(seed.getBytes());
        keygen.initialize(KEY_SIZE, secureRandom);

        KeyPair keys = keygen.genKeyPair();

        DSAPublicKey publicKey = (DSAPublicKey) keys.getPublic();
        DSAPrivateKey privateKey = (DSAPrivateKey) keys.getPrivate();

        Map map = new HashMap(2);
        map.put(PUBLIC_KEY, publicKey);
        map.put(PRIVATE_KEY, privateKey);

        return map;
    }

   
    public static Map initKey() throws Exception {
        return initKey(DEFAULT_SEED);
    }

   
    public static String getPrivateKey(Map keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);

        return Base64Util.encodeBASE64(new String(key.getEncoded()));
    }

   
    public static String getPublicKey(Map keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);

        return Base64Util.encodeBASE64(new String(key.getEncoded()));
    }
}