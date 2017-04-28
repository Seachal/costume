/*
 * huirong Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * Author     :liyb
 * Create Date:2017年4月14日
 */
package costumetrade.common.des;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import costumetrade.common.util.MD5Utils;

/**
 * DES模式加密解密类
 * @author liyb
 * @version DESUtil.java,2017年4月14日 上午11:05:23
 */
public class DESCoder {
    private static final Logger logger    = LoggerFactory.getLogger(DESCoder.class);
    /** 加密算法,可用 DES,DESede,Blowfish. */
    private final static String ALGORITHM = "DES";
    
    public static void main(String[] args) {
        String key = "1234567890";
        key = MD5Utils.MD5(key);
        System.out.println("商户KEY："+key+"    "+key.length());
        
        String data = "";
        JSONObject obj = new JSONObject();
        obj.put("platno", "32");
        obj.put("userName", "张三");
        data = obj.toString();
        System.out.println("原文："+data);
        
        String encryData = encrypt(data,key);
        System.err.println("加密后: " + encryData);
        
        String decryData = decrypt(encryData,key);
        System.out.println("解密后: " + decryData);
    }
    
    /**
     * 对用DES加密过的数据进行解密
     * @param data DES加密数据
     * @param key 商户解密key
     * @return 返回解密后的数据
     */
    public static String decrypt(String data,String key){
        String decryptData = "";
        try {
            byte[] dt = hex2byte(data.getBytes());
            byte[] b = decrypt(dt,key.getBytes());
            decryptData = new String(b);
        } catch (Exception e) {
            decryptData = "";
            logger.error("数据解密异常,", e);
        }
        return decryptData;
    }
    
    /**
     * 对数据进行DES加密
     * @param data 待进行DES加密的数据
     * @param key 商户加密key
     * @return 返回经过DES加密后的数据
     */
    public static String encrypt(String data,String key){
        String encryptedData = null;
        try {
            byte[] b = encrypt(data.getBytes(), key.getBytes());
            encryptedData = byte2hex(b);
        } catch (Exception e) {
            encryptedData = "";
            logger.error("数据加密异常,", e);
        }
        return encryptedData;
    }
    
    /**
     * 用指定的key对数据进行DES加密
     * @param data 待加密的数据
     * @param key DES加密的key
     * @return 返回DES加密后的数据
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(data);
    }
    /**
     * 用指定的key对数据进行DES解密.
     * @param data 待解密的数据
     * @param key DES解密的key
     * @return 返回DES解密后的数据
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        // 现在，获取数据并解密
        // 正式执行解密操作
        return cipher.doFinal(data);
    }
    
    private static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }
    
    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

}
