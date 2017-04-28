/*
 * huirong Inc.
 * Copyright (c) 2015 All Rights Reserved.
 * Author     :liyb
 * Create Date:2015年11月19日
 */
package costumetrade.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import costumetrade.common.param.ResponseInfo;

/**
 * 通过HttpURLConnection模拟post表单提交
 * @author liyb
 * @version HttpPostUtil.java,2015年11月19日 下午3:48:40
 */
public class HttpPostUtil implements Serializable {

    private static final long   serialVersionUID = -292336323378065582L;
    private static final String CHAR_SET         = "UTF-8";
    private static final String CONTENT_TYPE     = "application/json";

    /**
     * 请求接口
     * @param requestUrl 接口请求地址
     * @param json json格式数据
     * @return
     */
    @SuppressWarnings({ "deprecation", "resource" })
    public static JSONObject sendPostRequestJSON(String requestUrl, JSONObject json) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(requestUrl);
        JSONObject response = null;
        try {
            // 将JSON进行UTF-8编码,以便传输中文
            String encoderJson = URLEncoder.encode(json.toString(), HTTP.UTF_8);
            StringEntity string = new StringEntity(encoderJson);
            string.setContentEncoding(CHAR_SET);
            string.setContentType(CONTENT_TYPE);
            post.setEntity(string);
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                String result = EntityUtils.toString(entity);
                response = JSONObject.fromObject(result);
            }else{
                response = new JSONObject();
                try {
                    response.put("code", ResponseInfo.EXCEPTION.code);
                    response.put("msg", ResponseInfo.EXCEPTION.msg);
                } catch (JSONException e1) {}
            }
        } catch (IOException e) {
            response = new JSONObject();
            try {
                response.put("code", ResponseInfo.EXCEPTION.code);
                response.put("msg", ResponseInfo.EXCEPTION.msg);
            } catch (JSONException e1) {}
            e.printStackTrace();
        } catch (JSONException e) {
            response = new JSONObject();
            try {
                response.put("code", ResponseInfo.EXCEPTION.code);
                response.put("msg", ResponseInfo.EXCEPTION.msg);
            } catch (JSONException e1) {}
            e.printStackTrace();
        }
        return response;
    }
    
    /**
     * 处理银行通过网关发送过来的数据
     */
    public static String getRequestMapData(HttpServletRequest request) throws IOException{
        String result="";
        try {
            Map<String, String[]> map = request.getParameterMap();
            Set<Entry<String, String[]>> set = map.entrySet();
            Iterator<Entry<String, String[]>> it = set.iterator();
            while (it.hasNext()) {
                Entry<String, String[]> entry = it.next();
                for (String value : entry.getValue()) {
                    result=value;
                }
            }
            if(StringUtil.isNotEmpty(result)){
                int index = result.indexOf("=");
                if(index!=-1){
                    result = result.substring(index+1,result.length());
                }
            }
        } catch (Exception e) {
            result="";
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 读取客户端发送的JSON数据
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestData(HttpServletRequest request) throws IOException{
        String result="";
        try {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine())!=null){
                sb.append(line);
            }
            //解码
            String reqBody = sb.toString();
            result=URLDecoder.decode(reqBody, HTTP.UTF_8);
        } catch (Exception e) {
            result="";
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 文件下载
     * @param imageNetwork 图片地址
     * @param targetPath 下载目录
     * @param fileName 文件名
     * @return true:成功  false:失败
     */
    public static boolean loaddown(String imageNetwork,String targetPath,String fileName){
          boolean bool = true;
          HttpClient client = new DefaultHttpClient();
          HttpResponse httpResponse = null;
          HttpGet httpGet = null;
          try {
             httpGet = new HttpGet(imageNetwork);
             httpResponse = client.execute(httpGet);
             if(httpResponse.getStatusLine().getStatusCode() == 200){
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();
                File file = new File(targetPath);
                if(!file.exists()){
                    file.mkdirs();
                }
//                String fileName = imageNetwork.substring(imageNetwork.lastIndexOf("=")+1, imageNetwork.length());
                String imgPath = targetPath + File.separator + fileName;
                OutputStream outStream = new FileOutputStream(imgPath);
                IOUtils.copy(inputStream, outStream);
                inputStream.close();
                outStream.close();
                bool = true;
              }else{
                bool = false;
              }
           } catch (Exception e) {
              e.printStackTrace();
              bool = false;
          }finally{
              if(httpGet!=null){
                  httpGet.releaseConnection();
              }
              client.getConnectionManager().shutdown();
              client = null;
          }
          return bool;
    }
}
