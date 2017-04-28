/*
 * huirong Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * Author     :liyb
 * Create Date:2017年4月14日
 */
package costumetrade.common.des;

import com.alibaba.fastjson.JSONObject;

import costumetrade.common.util.MD5Utils;

/**
 * This is Class Description...
 * @author liyb
 * @version Test.java,2017年4月14日 上午10:21:24
 */
public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String key = "123456";
        key = MD5Utils.MD5(key);
        System.out.println("商户KEY："+key+"    "+key.length());
        
        JSONObject obj = new JSONObject();
        obj.put("platno", "32");
        obj.put("userName", "张三");
        System.out.println("原文："+obj.toString());
        
        System.out.println("+++++++++++++++DES+Base64模式+++++++++++++++");
        String encryData = DESBase64Coder.encrypt(obj.toString(), key);
        System.err.println("加密后："+encryData);
        
        String decryData = DESBase64Coder.decrypt(encryData, key);
        System.err.println("解密后："+decryData);
        
        String sign = DESBase64Coder.signature(obj.toString(), key);
        System.err.println("签名："+sign);
        
        boolean bool = DESBase64Coder.verfiySign(obj.toString(), sign, key);
        System.err.println("验签结果："+bool);
        
        System.out.println("+++++++++++++++DES模式+++++++++++++++");
        encryData = DESCoder.encrypt(obj.toString(), key);
        System.err.println("加密后："+encryData);
        
        decryData = DESCoder.decrypt(encryData, key);
        System.err.println("解密后："+decryData);
    }

}
