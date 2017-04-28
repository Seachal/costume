/*
 * huirong Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * Author     :liyb
 * Create Date:2017年4月14日
 */
package costumetrade.common.des;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import costumetrade.common.util.MD5Utils;

/**
 * DES+Base64模式对称加密工具类
 * @author liyb
 * @version DESBase64Coder.java,2017年4月14日 上午9:51:53
 */
public class DESBase64Coder {
    private static final Logger logger    = LoggerFactory.getLogger(DESBase64Coder.class);
    private static final String ALGORITHM = "DES";
    
    /**
     * 加密
     * @param data 待加密数据
     * @param key 商户加密key
     * @return 返回加密后的密文
     */
    public static String encrypt(String data, String key) {
        String encryptedData = null;
        try {
            key = DESBase64Coder.initKey(key);
            byte[] inputData = data.getBytes();
            inputData = encrypt(inputData, key);
            encryptedData = encryptBASE64(inputData);
        } catch (Exception e) {
            encryptedData = "";
            logger.error("数据加密异常,", e);
        }
        return encryptedData;
    }
    
    /**
     * 解密
     * @param data 待解密数据
     * @param key 商户解密key
     * @return 返回解密后的明文
     */
    public static String decrypt(String data, String key) {
        String decryptData = "";
        try {
            key = DESBase64Coder.initKey(key);
            byte[] inputData = decryptBASE64(data);
            byte[] outputData = decrypt(inputData, key);
            decryptData = new String(outputData);
        } catch (Exception e) {
            decryptData = "";
            logger.error("数据解密异常,", e);
        }
        return decryptData;
    }
    
    /**
     * 对数据进行签名
     * @param data 待签名数据
     * @param key 商户key
     * @return 返回签名后的数据
     */
    public static String signature(String data,String key){
        String sign="";
        try {
            sign = MD5Utils.MD5(data+"&"+key);
        } catch (Exception e) {
            sign="";
            logger.error("数据签名异常,", e);
        }
        return sign;
    }
    
    /**
     * 签名校验
     * @param data 解密后的数据
     * @param sign 原签名数据
     * @param key 商户key
     * @return true:验签成功  false:验签失败
     */
    public static boolean verfiySign(String data,String sign,String key){
        boolean bool = true;
        try {
            //1、对解密后的源数据进行签名
            String signData = MD5Utils.MD5(data+"&"+key);
            //2、签名校验
            if(!signData.equals(sign)){
                bool = false;
            }
        } catch (Exception e) {
            bool = false;
        }
        return bool;
    }

    /** 
     * 转换密钥<br> 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    private static Key toKey(byte[] key) throws Exception {  
        DESKeySpec dks = new DESKeySpec(key);  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);  
        SecretKey secretKey = keyFactory.generateSecret(dks);  
        // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码  
        // SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);  
        return secretKey;  
    }
    
    /** 
     * 加密 
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    private static byte[] encrypt(byte[] data, String key) throws Exception {  
        Key k = toKey(decryptBASE64(key));  
        Cipher cipher = Cipher.getInstance(ALGORITHM);  
        cipher.init(Cipher.ENCRYPT_MODE, k);  
        return cipher.doFinal(data);  
    }
    
    /** 
     * 解密 
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    private static byte[] decrypt(byte[] data, String key) throws Exception {  
        Key k = toKey(decryptBASE64(key));  
        Cipher cipher = Cipher.getInstance(ALGORITHM); 
        cipher.init(Cipher.DECRYPT_MODE, k);  
        return cipher.doFinal(data);  
    }
    
    /** 
     * 生成密钥 
     * @param seed 
     * @return 
     * @throws Exception 
     */  
    private static String initKey(String seed) throws Exception {
        SecureRandom secureRandom = null;
        if (seed != null) {
            secureRandom = new SecureRandom(decryptBASE64(seed));
        } else {
            secureRandom = new SecureRandom();
        }
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
        kg.init(secureRandom);
        SecretKey secretKey = kg.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }
    
    /** 
     * BASE64解密
     * @param key
     * @return
     * @throws Exception
     */  
    private static byte[] decryptBASE64(String key) throws Exception {  
        return Base64.decodeBase64(key);
    }
      
    /** 
     * BASE64加密 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    private static String encryptBASE64(byte[] key) throws Exception {  
        return Base64.encodeBase64String(key);
    }
}
