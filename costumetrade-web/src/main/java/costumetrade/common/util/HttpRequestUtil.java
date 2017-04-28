package costumetrade.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import costumetrade.common.execl.FTPUtils;

public class HttpRequestUtil {

	private static final String CHARSET = "UTF-8";

	static String path = "";
//	static {
//		if (SystemUtil.isWindows()) {
//			path = "C:/cardry/mailImage";
//		} else {
//			path = "/home/cardry/pdf";
//		}
//		FileUtil.createDir(path);
//	}

	/**
	 * 上传到应用服务器
	 * 
	 * @param fileInput
	 * @param fileName
	 */
//	public static boolean uploadFile(File fileInput, String filePath) {
//		try {
//			File f = new File(filePath);
//			if (!f.exists()) {// 文件不存在则创建
//				f.mkdirs();
//			}
//			// 将文件上传到服务器
//			File file = new File(filePath);
//			FileUtil.copyFile(fileInput, file);// 上传文件
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
	
	/**
	 * 文件上传
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @param storePath
	 * @param fileInput
	 * @param fileName
	 * @param remotePath
	 * @param orderNo
	 * @return
	 */
//	public static boolean uploadFileFTP(String host, String port, String username, String password, String storePath, File fileInput,
//			String fileName, String remotePath, String orderNo) {
//		boolean bool = false;
//		FileInputStream is = null;
//		try {
//			is = new FileInputStream(fileInput);
//			FTPUtils ftp = new FTPUtils(host, port, username, password,
//					remotePath);
//			bool = ftp.storeFile(storePath, fileName, is, orderNo);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			if (is != null) {
//				try {
//					is.close();
//				} catch (IOException e) {
//				}
//			}
//		}
//		return bool;
//	}


	/**
	 * 下载应用服务器上的文件
	 * 
	 * @param filePath
	 * @return
	 */
//	public static String downloadFile(String filePath, String newFileName) {
//		String result = "";
//		boolean bool = false;
//		HttpClient client = new HttpClient();
//		GetMethod httpGet = new GetMethod(filePath);
//		try {
//			client.executeMethod(httpGet);
//			InputStream in = httpGet.getResponseBodyAsStream();
//			FileOutputStream out = new FileOutputStream(new File(path + File.separator + newFileName));
//
//			byte[] b = new byte[1024];
//			int len = 0;
//			while ((len = in.read(b)) != -1) {
//				out.write(b, 0, len);
//			}
//			in.close();
//			out.close();
//			bool = true;
//			if(bool){
//				return path + File.separator + newFileName;
//			}
//			
//		} catch (HttpException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			httpGet.releaseConnection();
//		}
//		return result;
//	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
//	public static String sendGet(String url, String param) {
//		String result = "";
//		BufferedReader in = null;
//		try {
//			String urlNameString = url + "?" + param;
//			URL realUrl = new URL(urlNameString);
//			// 打开和URL之间的连接
//			URLConnection connection = realUrl.openConnection();
//			// 设置通用的请求属性
//			connection.setRequestProperty("accept", "*/*");
//			connection.setRequestProperty("connection", "Keep-Alive");
//			connection.setRequestProperty("user-agent",
//					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//			// 建立实际的连接
//			connection.connect();
//			// 获取所有响应头字段
//			Map<String, List<String>> map = connection.getHeaderFields();
//			// 遍历所有的响应头字段
//			for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
//			// 定义 BufferedReader输入流来读取URL的响应
//			in = new BufferedReader(new InputStreamReader(
//					connection.getInputStream()));
//			String line;
//			while ((line = in.readLine()) != null) {
//				result += line;
//			}
//		} catch (Exception e) {
//			System.out.println("发送GET请求出现异常！" + e);
//			e.printStackTrace();
//		}
//		// 使用finally块来关闭输入流
//		finally {
//			try {
//				if (in != null) {
//					in.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return result;
//	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * post 提交
	 * 
	 * @param urlString
	 * @param params
	 * @return
	 */
	public static String doPost(String urlString, Map<String, Object> params) {
		PostMethod method = new PostMethod(urlString);
		HttpClient client = null;
		try {
			Set<String> keys = params.keySet();
			NameValuePair[] values = new NameValuePair[keys.size()];
			int i = 0;
			for (String key : keys) {
				NameValuePair v = new NameValuePair();
				v.setName(key);
				v.setValue(params.get(key) == null ? "" : params.get(key)
						.toString());
				values[i] = v;
				i++;
			}
			client = new HttpClient();
			client.getHostConfiguration().setHost(urlString, 80, "http");
			client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 建立连接的超时时间
			client.getHttpConnectionManager().getParams().setSoTimeout(50000);// 等待请求结果的超时时间
			client.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, CHARSET);
			method.setRequestBody(values); // 使用 POST 方式提交数据
			int state = client.executeMethod(method); // 返回的状态
			if (state != HttpStatus.SC_OK) {
				throw new Exception("HttpStatus is " + state);
			}
			return inputStreamToString(method.getResponseBodyAsStream(),
					CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			//obj.put("result", costumetrade.common.constant.VerifyConst.REQUEST_ERROR);
			return obj.toString();
		} finally {
			// releaseConnection方法不能关闭socket连接
			// 使用SimpleHttpConnectionManager的shutdown方法强制关闭
			method.releaseConnection();
			if (client != null) {
				client.getHttpConnectionManager().closeIdleConnections(0); // 将idleTimeout设为0可以确保链接被关闭

				// HttpConnectionManager manager =
				// client.getHttpConnectionManager();
				// if (manager instanceof SimpleHttpConnectionManager) {
				// SimpleHttpConnectionManager tmp =
				// (SimpleHttpConnectionManager)manager;
				// tmp.shutdown();
				//
				// }
			}
		}
	}

	private static String inputStreamToString(InputStream is, String encode) {
		StringBuffer out = new StringBuffer();
		try {
			byte[] b = new byte[4096];
			for (int n; (n = is.read(b)) != -1;) {
				out.append(new String(b, 0, n,
						StringUtil.isBlank(encode) ? CHARSET : encode));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return out.toString();
	}
	
	/**
     * 请求接口
     * @param requestUrl 接口请求地址
     * @param json json格式数据
     * @return
     */
    @SuppressWarnings({ "deprecation", "resource" })
//    public static org.json.JSONObject sendPostRequestJSON(String requestUrl, org.json.JSONObject json) {
//        org.apache.http.client.HttpClient client = new DefaultHttpClient();
//        HttpPost post = new HttpPost(requestUrl);
//        org.json.JSONObject response = null;
//        try {
//            // 将JSON进行UTF-8编码,以便传输中文
//            String encoderJson = URLEncoder.encode(json.toString(), HTTP.UTF_8);
//            StringEntity string = new StringEntity(encoderJson);
//            string.setContentEncoding("UTF-8");
//            string.setContentType("application/json");
//            post.setEntity(string);
//            HttpResponse res = client.execute(post);
//            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                HttpEntity entity = res.getEntity();
////                String charset = EntityUtils.getContentCharSet(entity);
////                System.err.println("HttpEntity response charset="+charset);
//                response = new org.json.JSONObject(new JSONTokener(new InputStreamReader(entity.getContent(), CHARSET)));
//            }else{
//                response = new org.json.JSONObject();
//                try {
//                    response.put("retcode", ResponseCodes.OP_EXCEPTION.getCode());
//                    response.put("retmsg", ResponseCodes.OP_EXCEPTION.getName());
//                } catch (JSONException e1) {}
//            }
//        } catch (IOException e) {
//            response = new org.json.JSONObject();
//            try {
//                response.put("retcode", ResponseCodes.OP_EXCEPTION.getCode());
//                response.put("retmsg", ResponseCodes.OP_EXCEPTION.getName());
//            } catch (JSONException e1) {}
//            e.printStackTrace();
//        } catch (JSONException e) {
//            response = new org.json.JSONObject();
//            try {
//                response.put("retcode", ResponseCodes.OP_EXCEPTION.getCode());
//                response.put("retmsg", ResponseCodes.OP_EXCEPTION.getName());
//            } catch (JSONException e1) {}
//            e.printStackTrace();
//        }
//        return response;
//    }

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String smsSendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 上传文件
	 * @param apiUrl 请求接口
	 * @param file 文件路径
	 * @param params map参数
	 * @return json字符串
	 */
	public static String doUploadFile(String apiUrl,String file,Map<String,String> params){
	    String responseContent = "";
	    JSONObject result = new JSONObject();
	    org.apache.http.client.HttpClient client = null;
	    HttpPost httpPost = null;
	    try {
	        client = new DefaultHttpClient();
	        httpPost = postForm(apiUrl, params, file);
	        //发送请求获得返回结果
	        HttpResponse response = client.execute(httpPost);
	        //如果成功
	        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	            HttpEntity entity = response.getEntity();
	            responseContent = EntityUtils.toString(entity);
	        }else {
	            //如果失败
	            result.put("code", "-3");
	            result.put("msg", "上传失败");
	            responseContent = result.toString();
	        }
        } catch (Exception e) {
            result.put("code", "-3");
            result.put("msg", "上传失败");
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
     * @param params
     * @param file
     * @return
     */
    private static HttpPost postForm(String url, Map<String, String> params,String file){
        HttpPost httpost = new HttpPost(url);
        try {
            FileBody f = new FileBody(new File(file));
            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart("fileData", f);
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                StringBody comment = new StringBody(params.get(key));
                reqEntity.addPart(key, comment);
            }
            httpost.setEntity(reqEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return httpost;
    }
    
    /**
     * POST发送JSON数据
     * @param url
     * @param json
     * @return
     */
    @SuppressWarnings({ "deprecation", "resource" })
//    public static String sendPostRequestJSON(String url, String json) {
//        String result="";
//        org.apache.http.client.HttpClient client = new DefaultHttpClient();
//        HttpPost post = new HttpPost(url);
//        JSONObject error = new JSONObject();
//        try {
//            HttpParams params = client.getParams();
//            HttpConnectionParams.setConnectionTimeout(params, 30000);
//            HttpConnectionParams.setSoTimeout(params, 30000);
//            // 将JSON进行UTF-8编码,以便传输中文
//            String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
//            StringEntity string = new StringEntity(encoderJson);
//            string.setContentEncoding("UTF-8");
//            string.setContentType("application/json");
//            post.setEntity(string);
//            HttpResponse response = client.execute(post);
//            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                result = EntityUtils.toString(response.getEntity());
//            }else{
//                error.put("code", ResponseCodes.API_ERROR.getCode());
//                error.put("msg", ResponseCodes.API_ERROR.getName());
//                result = error.toString();
//            }
//        } catch(ConnectException con){
//            error.put("code", ResponseCodes.API_ERROR.getCode());
//            error.put("msg", ResponseCodes.API_ERROR.getName());
//            result = error.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//            error.put("code", ResponseCodes.OP_EXCEPTION.getCode());
//            error.put("msg", ResponseCodes.OP_EXCEPTION.getName());
//            result = error.toString();
//        }
//        return result;
//    }
    
    /**
     * 读取客户端发送的JSON数据
     * @param request
     * @return 返回接收到的json数据
     */
    public static String getRequestJSONData(HttpServletRequest request){
        String response="";
        try {
            String result = IOUtils.toString(request.getInputStream(),"UTF-8");
            response = URLDecoder.decode(result, "UTF-8");
        } catch (Exception e) {
            response="";
            e.printStackTrace();
        }
        return response;
    }

	public static void main(String[] args) {
		// String result =
		// HttpRequestUtil.sendGet("http://192.168.0.51:8089/bank-transport/image/test.html",
		// "orderNo=1&mch_id=2&key=3");
		// System.out.println(result);
//		uploadFile(new File("F:\\材料图片\\_GPNAHV3S()EMH@DD0TXC1A.png"), "aaa.jpg");
//		downloadFile("http://192.168.0.186:8082/image/20160613/hrjf2016061311383991/tmbf_1465789133282-704652880.jpg", "aa.jpg");
//		String imgPath = "F:\\材料图片\\购车发票.jpg";
//		File f = new File(imgPath);
//		uploadFileFTP("192.168.0.186", "21", "cardry", "abc#123","photo", f,"aaa.jpg","d:\\image",null);
//		String storePath, File fileInput,
//		String fileName, String remotePath, String orderNo
		
		String apiUrl="http://192.168.0.52:8080/bank-transport/file/fileupload.html";
		String file="E:/20160628.txt";
		Map<String, String> params = new HashMap<String, String>();
		params.put("filetype", "1");
		String response = doUploadFile(apiUrl, file,params);
		System.err.println(response);
	}
}
