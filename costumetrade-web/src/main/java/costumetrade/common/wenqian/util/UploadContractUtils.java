/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年10月19日
 */
package costumetrade.common.wenqian.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

import costumetrade.common.wenqian.constant.WQAPIConstants;

/**
 * 上传合同到文签
 * @author liyb
 * @version UploadContractUtils.java,2016年10月19日 下午6:12:05
 */
public class UploadContractUtils {
    
    /**
     * 上传文件
     * @param apiUrl 请求接口
     * @param file 文件
     * @param shareId 用户唯一标识
     * @param docName 文件名
     * @return
     */
    public static String uploadFile(String apiUrl,String file,String shareId,String docName){
        String responseContent = "";
        JSONObject result = new JSONObject();
        HttpPost httppost = null;
        try {
            httppost = new HttpPost(apiUrl);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("client_id", WQAPIConstants.CLIENT_ID);
            builder.addTextBody("share_id", shareId);
            ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
            StringBody stringBody = new StringBody(docName,contentType);
            builder.addPart("doc_name", stringBody);
            builder.addBinaryBody("document", new File(file));
            httppost.setEntity(builder.build());
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpResponse response = httpclient.execute(httppost);
            //如果成功
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                responseContent = EntityUtils.toString(entity);
            }else {
                //如果失败
                result.put("error_code", -999);
                result.put("message", "上传失败");
                responseContent = result.toString();
            }
        } catch (ClientProtocolException e) {
            result.put("error_code", -999);
            result.put("message", "上传失败");
            responseContent = result.toString();
        } catch (IOException e) {
            result.put("error_code", -999);
            result.put("message", "上传失败");
            responseContent = result.toString();
        } finally{
            if(httppost!=null){
                httppost.releaseConnection();
            }
        }
        return responseContent;
    }
    
    
    
    /**
     * 上传文件
     * @param apiUrl 请求接口
     * @param file 文件路径
     * @param partName 文件域名称
     * @param params map参数
     * @return json字符串
     */
    public static String doUploadFile(String apiUrl,File file,String partName,Map<String,Object> params){
        String responseContent = "";
        JSONObject result = new JSONObject();
        org.apache.http.client.HttpClient client = null;
        HttpPost httpPost = null;
        try {
            client = new DefaultHttpClient();
            httpPost = postForm(apiUrl,partName,params, file);
            //发送请求获得返回结果
            HttpResponse response = client.execute(httpPost);
            //如果成功
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                responseContent = EntityUtils.toString(entity);
            }else {
                //如果失败
                result.put("error_code", -999);
                result.put("message", "上传失败");
                responseContent = result.toString();
            }
        } catch (Exception e) {
            result.put("error_code", -999);
            result.put("message", "上传失败");
            responseContent = result.toString();
        } finally{
            if(httpPost!=null){
                httpPost.releaseConnection();
            }
            if(client!=null){
                client.getConnectionManager().shutdown();
            }
        }
        return responseContent;
    }
    
    /**
     * 构造 POST文件请求
     * @param url
     * @param partName
     * @param params
     * @param file
     * @return
     */
    private static HttpPost postForm(String url, String partName,Map<String, Object> params,File file){
        HttpPost httpost = new HttpPost(url);
        try {
            FileBody f = new FileBody(file);
            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart(partName, f);
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
                StringBody comment = new StringBody(params.get(key)+"",contentType);
                reqEntity.addPart(key, comment);
            }
            httpost.setEntity(reqEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpost;
    }
}
