/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年4月15日
 */
package costumetrade.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 过滤html标签
 * @author liyb
 * @version HTMLSpirit.java,2016年4月15日 下午2:20:37
 */
public class HTMLSpirit {

    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式 

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签 

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签 

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签 

        return htmlStr.trim(); //返回文本字符串 
    }
    
    /**
     * 通过递归删除html标签
     * @param content - 包含HTML标签的内容 
     * @return 不带HTML标签的文本内容
     */
    public static String removeHtmlTag(String content) {
        Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>");
        Matcher m = p.matcher(content);
        if (m.find()) {
            content = content.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
            content = removeHtmlTag(content);
        }
        return content;
    }
}
