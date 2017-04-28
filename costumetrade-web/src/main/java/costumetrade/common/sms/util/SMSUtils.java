/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年2月25日
 
package costumetrade.common.sms.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;

import costumetrade.common.context.DomainContext;
import costumetrade.common.sms.constant.SMSApiConfig;

*//**
 * 短信工具类
 * @author liyb
 * @version SMSUtils.java,2016年2月25日 下午5:29:04
 *//*
public class SMSUtils {
    
    private static  final Log log = LogFactory.getLog(SMSUtils.class);
	
	// 提供角色ID
	public static final String GET_MESSAGE_TEMP_INFO = DomainContext.getAppServer()+"/msgMessage/getMessageTemplateInfo.html";
	
	// 获取电话后发送短信
	public static final String SEND_MESSAGE =  DomainContext.getAppServer()+"/msgMessage/sendMessage.html";
	
	//将手机号码和已经替换好的短信内容发送过来
	public static final String HTTP_SEND_MESSAGE =  DomainContext.getAppServer()+"/msgMessage/httpSendMessage.html";
    
    public static String sendSMS(String mobile, String content){
        JSONObject json = new JSONObject();
        try {
            StringBuilder params = new StringBuilder();
            params.append("action="+SMSApiConfig.action)
            .append("&userid="+SMSApiConfig.userid)
            .append("&account="+SMSApiConfig.account)
            .append("&password="+SMSApiConfig.password)
            .append("&mobile="+mobile)
            .append("&content="+URLEncoder.encode(content,SMSApiConfig.charset));
            byte[] response = sendPostRequestByForm(SMSApiConfig.url, params.toString());
            String xml = new String(response,"UTF-8");
            Map<String,Object> returnMap = xmlToMap(xml);
            String returnstatus = returnMap.get("returnstatus")+"";
            if(returnstatus.equals(SMSApiConfig.success)){//成功
                json.put("code", 0);
                json.put("msg", "短信发送成功");
            }else{
                json.put("code", -1);
                json.put("msg", "短信发送失败");
            }
        } catch (Exception e) {
            log.error("短信发送失败",e);
            json.put("code", -1);
            json.put("msg", "短信发送失败");
            
        }
        return json.toString();
    }

    *//**
     * 生成六位随机数字验证码
     * @return
     *//*
    public static int generateSmsCode(){
        Random random = new Random();
        int x = random.nextInt(899999);
        int y = x+100000;
        return y;
    }
    
    *//**
     * 通过HttpURLConnection模拟post表单提交
     * @param path
     * @param params 例如"name=zhangsan&age=21"
     * @return
     *//*
    private static byte[] sendPostRequestByForm(String path, String params){
        HttpURLConnection conn = null;
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");//提交模式
            conn.setDoOutput(true);//是否输入参数
            conn.setDoInput(true);
            byte[] bypes = params.toString().getBytes();
            conn.getOutputStream().write(bypes);//输入参数
            InputStream inStream=conn.getInputStream();
            return StreamTool.readInputStream(inStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            if(conn!=null){
                conn.disconnect();
            }
        }
    }
    
    *//**
     * xml字符串转成map
     * @param xml
     * @return
     *//*
    public static Map<String,Object> xmlToMap(String xml){
        Map<String,Object> returnMap = new HashMap<String, Object>();
        try {
            Document doc = DocumentHelper.parseText(xml);
            Element root = doc.getRootElement();//获取根节点
            String returnstatus = root.elementTextTrim("returnstatus");
            returnMap.put("returnstatus", returnstatus);
            String message = root.elementTextTrim("message");
            returnMap.put("message", message);
            String remainpoint = root.elementTextTrim("remainpoint");
            returnMap.put("remainpoint", remainpoint);
            String taskID = root.elementTextTrim("taskID");
            returnMap.put("taskID", taskID);
            String successCounts = root.elementTextTrim("successCounts");
            returnMap.put("successCounts", successCounts);
        } catch (Exception e) {
            log.error("短信发送结果解析出错",e);
        }
        return returnMap;
    }
}
*/