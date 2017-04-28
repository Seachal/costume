/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年3月10日
 */
package costumetrade.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 字符脱敏工具类
 * @author liyb
 * @version EncryptUtil.java,2016年3月10日 下午4:44:34
 */
public class SensitiveInfoUtils {
    
    private static String foramt="yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
        //张三脱敏成张*，张三四脱敏成张*四，张三四五脱敏成张三*五
        String a="李华";
        String b="李海峰";
        String c="欧阳震华";
        System.err.println(format(a));
        System.err.println(format(b));
        System.err.println(format(c));
        //1453946104
        System.err.println(timeStamp2Date(1453946104));
        System.err.println(date2TimeStamp("2016-01-28 09:55:04", foramt));
        System.err.println(deptName("浙江腾铭汽车销售服务有限公司"));
        System.err.println("2012.09.23-2016.01.04".replaceAll("\\.", ""));
    }
    
    /**
     * 字符脱敏
     * @param text
     * @return
     */
    public static String format(String text){
        String data="";
        if (StringUtils.isBlank(text)) {
            return "";
        }  
        if(text.length()==2){
            String name = StringUtils.left(text, 1);
            data = StringUtils.rightPad(name, 2, "*");
        }else if(text.length()==3){
            data = StringUtils.left(text,1).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(text, 1), 3, "*"), "*"));
        }else if(text.length()==4){
            data = StringUtils.left(text,2).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(text, 1), 3, "*"), "*"));
        }else{
            data = text;
        }
        return data;
    }
    
    /** 
     * 日期格式字符串转换成时间戳 
     * @param date 字符串日期 
     * @param format 如：yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * 时间戳转成日期字符串
     * @param time
     * @return
     */
    public static String timeStamp2Date(long time){
        SimpleDateFormat sdf = new SimpleDateFormat(foramt);
        String date = sdf.format(new Date(time*1000L));
        return date;
    }
    
    /**
     * 机构名称脱敏 (浙江*方担保投资有限公司=浙江南方担保投资有限公司)
     * @param deptName
     * @return
     */
    public static String deptName(String deptName) {
        if (StringUtils.isBlank(deptName)) {
            return "";
        }
        String dn = StringUtils.left(deptName, 2);
        String data = StringUtils.rightPad(dn, 3, "*");
        String right = StringUtils.right(deptName, deptName.length()-3);
        data=data+right;
        return data;
    }
}
