/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年5月5日
 */
package costumetrade.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

import costumetrade.common.word.ImageUtils;

/**
 * 图片Base64工具类
 * @author liyb
 * @version Base64Util.java,2016年5月5日 下午1:26:33
 */
public class Base64Util {
    
    private static final Random random = new Random();
    
    private static String FILE_PATH = "";

    static {
        if (SystemUtils.IS_OS_WINDOWS) {
            FILE_PATH = "C:/cardry/ocr/";
        } else {
            FILE_PATH = "/home/cardry/ocr/";
        }
        File file = new File(FILE_PATH);
        if(!file.exists()){
            file.mkdirs();
        }
    }
    
    /**
     * byte数字转换成base64字符串
     * @param b
     * @return
     */
    public static String byteToStringBase64(byte[] b){
        String base64Data="";
        try {
           
            base64Data = Base64.encodeBase64String(b);
            if(StringUtils.isNotEmpty(base64Data)){
                base64Data = base64Data.replaceAll("\\+", "*").replaceAll("\\/", "-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64Data;//返回Base64编码过的字节数组字符串
    }
    
    /**
     * base64字符串转换成byte数组
     * @param base64String
     * @return
     */
    public static byte[] base64StringToByte(String base64String){
        if (base64String == null) 
            return null;
        //替换字符
        base64String = base64String.replaceAll("\\*", "+").replaceAll("-", "/");
        //Base64解码
        byte[] b = null;
        b=Base64.decodeBase64(base64String);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {//调整异常数据
                b[i] += 256;
            }
        }
        return b;
    }
    
    /**
     * 字符串转化成base64字符串
     * @param file 文件对象
     * @return
     */
    public static String getImageBase64(String str) {
        //将字符串转化为字节数组字符串，并对其进行Base64编码处理
        //对字节数组Base64编码
        String base64Data="";
		try {
			base64Data = Base64.encodeBase64String(str.getBytes("utf-8"));
			if(StringUtils.isNotEmpty(base64Data)){
	            base64Data = base64Data.replaceAll("\\+", "*").replaceAll("\\/", "-");
	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return base64Data;//返回Base64编码过的字节数组字符串
    }

    /**
     * 图片转化成base64字符串
     * @param file 文件对象
     * @return
     */
    public static String getImageBase64(File file) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        //String imgFile = "E:/image/001.png";//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        String base64Data = Base64.encodeBase64String(data);
        if(StringUtils.isNotEmpty(base64Data)){
            base64Data = base64Data.replaceAll("\\+", "*").replaceAll("\\/", "-");
        }
        return base64Data;//返回Base64编码过的字节数组字符串
    }
    
    /**
     * base64字符串转化成图片
     * @param imageBase64Str 图片base64字符串
     * @return 返回生成的文件对象
     */
    public static File generateImage(String imageBase64Str) {
        File file=null;
        OutputStream out = null;
        //对字节数组字符串进行Base64解码并生成图片
        if (imageBase64Str == null) //图像数据为空
            return null;
        try {
            //替换字符
            imageBase64Str = imageBase64Str.replaceAll("\\*", "+").replaceAll("-", "/");
            //Base64解码
            byte[] b = Base64.decodeBase64(imageBase64Str);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            String imgFilePath = FILE_PATH+String.valueOf(System.currentTimeMillis())+ random.nextInt()+".jpg";
            out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            file = new File(imgFilePath);
            return file;
        } catch (Exception e) {
            return null;
        } finally {
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {}
            }
        }
    }
    
    /**
     * 把图片文件生成base64字符串
     * @param file
     * @return
     */
    public static String getImageBase64Str(String file){
        InputStream in = null;
        byte[] data = null;
        try {
            file = imageProcess(file);
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(data);
    }
    
    /**
     * 处理图片再次压缩
     * @param file
     * @return
     */
    public static String imageProcess(String file){
        String filePath=file;
        try {
            File f = new File(file);
            File tmpfile = ImageUtils.compressionFile(f, f.getName());
            filePath = tmpfile.getPath();
        } catch (Exception e) {
            filePath=file;
            e.printStackTrace();
        }
        return filePath;
    }
}
