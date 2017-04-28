/*
 * huirong Inc.
 * Copyright (c) 2015 All Rights Reserved.
 * Author     :liyb
 * Create Date:2015年11月19日
 */
package costumetrade.common.icbc.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import costumetrade.common.icbc.constant.ICBCConstants;

/**
 * 通过HttpURLConnection模拟post表单提交
 * @author liyb
 * @version HttpRequestUtils.java,2015年11月19日 下午3:48:40
 */
public class HttpRequestUtils {
    
    private static final Log log = LogFactory.getLog(HttpRequestUtils.class);
    
    private static final String param            = "packets=";
    private static final String CHAR_SET         = "UTF-8";

    /**
     * 请求接口
     * @param requestUrl 接口请求地址
     * @param json json格式数据
     * @return
     */
/*    public static JSONObject sendPostRequestJSON(String requestUrl, JSONObject json) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(requestUrl);
        JSONObject response = null;
        try {
         // 将JSON进行UTF-8编码,以便传输中文
            String encoderJson = URLEncoder.encode(json.toString(), HTTP.UTF_8);
            StringEntity string = new StringEntity(encoderJson);
            string.setContentEncoding("UTF-8");
            string.setContentType("application/json");
            post.setEntity(string);
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                String charset = EntityUtils.getContentCharSet(entity);
                System.err.println("HttpEntity response charset="+charset);
                response = new JSONObject(new JSONTokener(new InputStreamReader(entity.getContent(), CHAR_SET)));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }*/

    /**
     * 请求接口需要参数名+报文体、不需要编码
     * @param requestUrl 接口请求地址
     * @param params 参数:packets=xml报文体
     * @return
     */
    public static String sendPostRequestByForm(String requestUrl, String params) {
        String result = "";
        HttpURLConnection conn = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");//提交模式
            //Post请求不能使用缓存  
            conn.setUseCaches(false);
            conn.setConnectTimeout(ICBCConstants.TIME_OUT);//连接超时 单位毫秒
            conn.setReadTimeout(ICBCConstants.TIME_OUT);//读取超时 单位毫秒
            conn.setDoInput(true);//是否输入参数
            conn.setDoOutput(true);//是否输出参数
            //进行编码  
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            byte[] bypes = params.toString().getBytes();
            conn.getOutputStream().write(bypes);// 输入参数
            InputStream inStream = conn.getInputStream();
            //第一种方式
            result = IOUtils.toString(inStream);
            //第二种方式
            //            byte[] buffer = new byte[1024];
            //            buffer = StreamTool.readInputStream(inStream);
            //            result = IOUtils.toString(buffer);
            //第三种方式
            //            new String(StreamTool.readInputStream(inStream), "gbk");
        } catch (MalformedURLException e) {
            result = ICBCConstants.URLException;
            e.printStackTrace();
        } catch (IOException e) {
            result = ICBCConstants.ERROR;
            e.printStackTrace();
        } catch (Exception e) {
            result = ICBCConstants.ERROR;
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return result;
    }

    /**
     * 请求接口不需要参数名、不需要编码
     * @param requestUrl 接口请求地址
     * @param params 参数：xml报文体
     * @return
     */
    public static String sendPostRequestForm(String requestUrl, String params) {
        String result = "";
        HttpURLConnection conn = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");// 提交模式
            // Post 请求不能使用缓存  
            conn.setUseCaches(false);
            conn.setConnectTimeout(ICBCConstants.TIME_OUT);//连接超时 单位毫秒
            conn.setReadTimeout(ICBCConstants.TIME_OUT);//读取超时 单位毫秒
            conn.setDoInput(true);//是否输入参数
            conn.setDoOutput(true);//是否输出参数
            //进行编码  
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(param + URLEncoder.encode(params, ICBCConstants.CHARSET_ENCODING));
            out.flush();
            out.close();
            InputStream inStream = conn.getInputStream();
            result = IOUtils.toString(inStream);
        } catch (MalformedURLException e) {
            result = ICBCConstants.URLException;
            e.printStackTrace();
        } catch (IOException e) {
            result = ICBCConstants.ERROR;
            e.printStackTrace();
        } catch (Exception e) {
            result = ICBCConstants.ERROR;
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
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
        String result = IOUtils.toString(request.getInputStream(),"UTF-8");
        return URLDecoder.decode(result, "UTF-8");
    }
}
