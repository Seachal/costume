/*
 * 系统名称: 得仕宝系统
 * 模块名称: 手机APP模块
 * 文件名称: TestHttpClientUtils.java
 * 软件版权: 恒生电子股份有限公司
 * 创建日期: 2014年6月3日 人员：jinxx<br>
 *
*/

package com.httpclient;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;


/**
 * 功能说明: 模拟httpclient请求
 * <p> 系统版本: v1.0<br>
 * 开发人员: email jinxx@hundsun.com <br>
 * 时间: 2014年6月3日 <br>
 */

public class TestColor {
  
    @Test
    public  void testSaveColor() throws ClientProtocolException, IOException{
        
        String url = "http://localhost:8080/color/saveColor";
        Map<String,String> paramMap  = new HashMap<String,String>();
        paramMap.put("barcode", "barcode");
        
        paramMap.put("corpid", "100001");
        String str = HttpClientUtils.post(url, paramMap,"utf-8");
        System.out.println(str);
    }
/*    public static void post() throws IOException{
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<Request>"
                + "<Ver>1.0</Ver>"
                + "<SerialNum>20130415170908</SerialNum>"
                + "<ServiceId >F100</ServiceId>"
                + "<ReqTime>2014-09-10 15:30:30</ReqTime>"
                + "<Data/>"
                + "</Request>";
        String encode = URLEncoder.encode(xml,"utf-8");
        System.out.print(encode);
        String url = "http://localhost:8080/app/service.html";
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("data", encode);
        paramMap.put("serviceid", "F100");
        String str = HttpClientUtils.post(url, paramMap,"utf-8");
        System.out.println(str);
    }*/
}
