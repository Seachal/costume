/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年2月29日
 */
package costumetrade.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * This is Class Description...
 * @author liyb
 * @version MessageFormatUtil.java,2016年2月29日 下午1:42:25
 */
public class MessageFormatUtil {

    public static void main(String[] args) {
//        String str1="我是%1$s,我来自%2$s,今年%3$s岁";
//        System.err.println(String.format(str1, "中国人", new Date(), new BigDecimal(123.5667)));

        String str = "我是{0},我来自{1},今年{2}岁";
//        String[] arr = { "中国人", "北京", "22" };
//        System.out.println(fillStringByArgs(str, arr));
        System.err.println(messageFormat(str, "中国人", new Date(), "123.5667"));
        
//        List<String> arr = new ArrayList<String>();
//        arr.add("中国人");
//        arr.add("北京");
//        arr.add("22");
//        System.out.println(fillStringByArgs(str, arr));
    }
    
    /**
     * 占位符方式格式化字符串
     * @param text 带占位符格式化内容
     * @param object 值
     * @return
     */
    public static String messageFormat(String text,Object... object){
        List<String> arr = new ArrayList<String>();
        for (int i = 0; i < object.length; i++) {
            if(object[i] instanceof BigDecimal){
                BigDecimal b = (BigDecimal)object[i];
                b=b.setScale(4, BigDecimal.ROUND_HALF_UP);
                arr.add(b.toString());
            }else if(object[i] instanceof Date){
                Date d = (Date) object[i];
                arr.add(DateFormatUtils.format(d, "yyyy-MM-dd"));
            }else if(object[i] instanceof String){
                arr.add((String) object[i]);
            }else if(object[i] instanceof Integer){
                Integer value = (Integer) object[i];
                arr.add(value.toString());
            }
        }
        text = fillStringByArgs(text, arr);
        return text;
    }
    
    private static String fillStringByArgs(String str, List<String> arr) {
        Matcher m = Pattern.compile("\\{(\\d)\\}").matcher(str);
        while (m.find()) {
            CharSequence replacement = arr.get(Integer.parseInt(m.group(1)));
            str = str.replace(m.group(), replacement);
        }
        return str;
    }
}
