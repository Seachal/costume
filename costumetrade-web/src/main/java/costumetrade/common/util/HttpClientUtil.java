package costumetrade.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.cookie.params.CookieSpecPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;


/**
 *
 */
public class HttpClientUtil {
	public static Log log = LogFactory.getLog("HttpClientUtil.class");
	public static final String SunX509 = "SunX509";
	public static final String JKS = "JKS";
	public static final String PKCS12 = "PKCS12";
	public static final String TLS = "TLS";
	
	public static String sendGET(String url, String param) {
		String result = "";// 访问返回结果
		BufferedReader read = null;// 读取访问结果

		try {
			// 创建url
			URL realurl = new URL(url + "?" + param);
			// 打开连接
			URLConnection connection = realurl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立连接
			connection.connect();
			// 获取所有响应头字段
			// Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段，获取到cookies等
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应
			read = new BufferedReader(new InputStreamReader(connection.getInputStream(), "uft-8"));
			String line;// 循环读取
			while ((line = read.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (read != null) {// 关闭流
				try {
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
	
	/**
	 * get HttpURLConnection
	 * @param strUrl url��ַ
	 * @return HttpURLConnection
	 * @throws IOException
	 */
	public static HttpURLConnection getHttpURLConnection(String strUrl)
			throws IOException {
		URL url = new URL(strUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url
				.openConnection();
		return httpURLConnection;
	}
	
	/**
	 * get HttpsURLConnection
	 * @param strUrl url��ַ
	 * @return HttpsURLConnection
	 * @throws IOException
	 */
	public static HttpsURLConnection getHttpsURLConnection(String strUrl)
			throws IOException {
		URL url = new URL(strUrl);
		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url
				.openConnection();
		return httpsURLConnection;
	}
	
	/**
	 * url
	 * @param strUrl
	 * @return String
	 */
	public static String getURL(String strUrl) {

		if(null != strUrl) {
			int indexOf = strUrl.indexOf("?");
			if(-1 != indexOf) {
				return strUrl.substring(0, indexOf);
			} 
			
			return strUrl;
		}
		
		return strUrl;
		
	}
	
	/**
	 * ��ȡ��ѯ��
	 * @param strUrl
	 * @return String
	 */
	public static String getQueryString(String strUrl) {
		
		if(null != strUrl) {
			int indexOf = strUrl.indexOf("?");
			if(-1 != indexOf) {
				return strUrl.substring(indexOf+1, strUrl.length());
			} 
			
			return "";
		}
		
		return strUrl;
	}
	
	/**
	 * ��ѯ�ַ�ת����Map<br/>
	 * name1=key1&name2=key2&...
	 * @param queryString
	 * @return
	 */
	public static Map queryString2Map(String queryString) {
		if(null == queryString || "".equals(queryString)) {
			return null;
		}
		
		Map m = new HashMap();
		String[] strArray = queryString.split("&");
		for(int index = 0; index < strArray.length; index++) {
			String pair = strArray[index];
			HttpClientUtil.putMapByPair(pair, m);
		}
		
		return m;
		
	}
	
	/**
	 * �Ѽ�ֵ�����Map<br/>
	 * pair:name=value
	 * @param pair name=value
	 * @param m
	 */
	public static void putMapByPair(String pair, Map m) {
		
		if(null == pair || "".equals(pair)) {
			return;
		}
		
		int indexOf = pair.indexOf("=");
		if(-1 != indexOf) {
			String k = pair.substring(0, indexOf);
			String v = pair.substring(indexOf+1, pair.length());
			if(null != k && !"".equals(k)) {
				m.put(k, v);
			}
		} else {
			m.put(pair, "");
		}
	}
	
	/**
	 * BufferedReaderת����String<br/>
	 * ע��:���ر���Ҫ���д���
	 * @param reader
	 * @return String
	 * @throws IOException
	 */
	public static String bufferedReader2String(BufferedReader reader) throws IOException {
		StringBuffer buf = new StringBuffer();
		String line = null;
		while( (line = reader.readLine()) != null) {
			buf.append(line);
			buf.append("\r\n");
		}
				
		return buf.toString();
	}
	
	/**
	 * �������<br/>
	 * ע��:���ر���Ҫ���д���
	 * @param out
	 * @param data
	 * @param len
	 * @throws IOException
	 */
	public static void doOutput(OutputStream out, byte[] data, int len)
			throws IOException {
		int dataLen = data.length;
		int off = 0;
		while (off < data.length) {
			if (len >= dataLen) {
				out.write(data, off, dataLen);
				off += dataLen;
			} else {
				out.write(data, off, len);
				off += len;
				dataLen -= len;
			}

			// ˢ�»�����
			out.flush();
		}

	}
	
	/**
	 * ��ȡSSLContext
	 * @param trustFile 
	 * @param trustPasswd
	 * @param keyFile
	 * @param keyPasswd
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyStoreException 
	 * @throws IOException 
	 * @throws CertificateException 
	 * @throws UnrecoverableKeyException 
	 * @throws KeyManagementException 
	 */
	public static SSLContext getSSLContext(
			FileInputStream trustFileInputStream, String trustPasswd,
			FileInputStream keyFileInputStream, String keyPasswd)
			throws NoSuchAlgorithmException, KeyStoreException,
			CertificateException, IOException, UnrecoverableKeyException,
			KeyManagementException {

		// ca
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(HttpClientUtil.SunX509);
		KeyStore trustKeyStore = KeyStore.getInstance(HttpClientUtil.JKS);
		trustKeyStore.load(trustFileInputStream, HttpClientUtil
				.str2CharArray(trustPasswd));
		tmf.init(trustKeyStore);

		final char[] kp = HttpClientUtil.str2CharArray(keyPasswd);
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(HttpClientUtil.SunX509);
		KeyStore ks = KeyStore.getInstance(HttpClientUtil.PKCS12);
		ks.load(keyFileInputStream, kp);
		kmf.init(ks, kp);

		SecureRandom rand = new SecureRandom();
		SSLContext ctx = SSLContext.getInstance(HttpClientUtil.TLS);
		ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), rand);

		return ctx;
	}
	
	/**
	 * ��ȡCA֤����Ϣ
	 * @param cafile CA֤���ļ�
	 * @return Certificate
	 * @throws CertificateException
	 * @throws IOException
	 */
	public static Certificate getCertificate(File cafile)
			throws CertificateException, IOException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		FileInputStream in = new FileInputStream(cafile);
		Certificate cert = cf.generateCertificate(in);
		in.close();
		return cert;
	}
	
	/**
	 * �ַ�ת����char����
	 * @param str
	 * @return char[]
	 */
	public static char[] str2CharArray(String str) {
		if(null == str) return null;
		
		return str.toCharArray();
	}
	
	/**
	 * �洢ca֤���JKS��ʽ
	 * @param cert
	 * @param alias
	 * @param password
	 * @param out
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 */
	public static void storeCACert(Certificate cert, String alias,
			String password, OutputStream out) throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore ks = KeyStore.getInstance("JKS");

		ks.load(null, null);

		ks.setCertificateEntry(alias, cert);

		// store keystore
		ks.store(out, HttpClientUtil.str2CharArray(password));

	}
	
	public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
	
	/**
	 * InputStreamת����Byte
	 * ע��:���ر���Ҫ���д���
	 * @param in
	 * @return byte
	 * @throws Exception
	 */
	public static byte[] InputStreamTOByte(InputStream in) throws IOException{  
		
		int BUFFER_SIZE = 4096;  
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(); 
        byte[] data = new byte[BUFFER_SIZE];  
        int count = -1;  
        
        while((count = in.read(data,0,BUFFER_SIZE)) != -1)  
            outStream.write(data, 0, count);  
          
        data = null;  
        byte[] outByte = outStream.toByteArray();
        outStream.close();
        
        return outByte;  
    } 
	
	/**
	 * InputStreamת����String
	 * ע��:���ر���Ҫ���д���
	 * @param in
	 * @param encoding ����
	 * @return String
	 * @throws Exception
	 */
	public static String InputStreamTOString(InputStream in,String encoding) throws IOException{  

        return new String(InputStreamTOByte(in),encoding);
        
    }
	public String send(String host, int port, String encoding, String ReqStr) {
//		ClientConnectionManager cm = new MyBasicClientConnectionManager();  
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://" + host + ":" + port);
		
		// 构造最简单的字符串数据
		String reqbody = "requestXml=" + ReqStr;

		log.error("reqbody=" + reqbody);
		StringEntity reqEntity;
		StringBuffer sb = new StringBuffer();
		HttpResponse response = null;
		HttpEntity entity = null;
		try {
			reqEntity = new StringEntity(reqbody, "GB180380");
			// 设置类型
			reqEntity.setContentType("application/x-www-form-urlencoded"); 
			// 设置请求的数据
			httppost.setEntity(reqEntity);
			httppost.getParams().setBooleanParameter(
					CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
			httppost.setHeader("Accept-Encoding", "gzip, deflate");
			HttpProtocolParams
					.setUserAgent(
							httppost.getParams(),
							"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; LEN2; InfoPath.2; CIBA)");
			httppost.setHeader(
					"Accept",
					"image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
			httppost.setHeader("Accept-Language", "zh-cn");

			// 执行
			httpclient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,true);
			response = httpclient.execute(httppost);
			entity = response.getEntity();

			if (entity != null) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(entity.getContent(), "GB18030"));
				String line = "";
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				if (entity != null) {
					httppost.abort();
					entity.consumeContent();
					httpclient.getConnectionManager().shutdown();
				}
			}
		} catch (Exception e) {
			log.error("调用httpclient出错", e);
			httpclient.getConnectionManager().shutdown();
			throw new RuntimeException(e);
		} finally {
			httpclient.getConnectionManager().shutdown();
			httppost.abort();
			if (entity != null) {
			}
		}
		log.error("return=" + sb.toString());
		return sb.toString();
	}

	static String inputStream2String(InputStream is) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
    public static String get(String url, String encoding) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			httpclient.getParams().setBooleanParameter(CookieSpecPNames.SINGLE_COOKIE_HEADER, true);
			HttpGet httpget = new HttpGet(url);
			httpget.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
			httpget.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
			httpget.addHeader(
					"User-Agent",
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022)");
			log.debug("开始正式请求外部短信网关["+url+"]");
			HttpResponse response = httpclient.execute(httpget);
			log.debug("得到短信网关响应:"+response.getStatusLine().getStatusCode());
			//log.info(response.getStatusLine().getStatusCode());
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, encoding);
		}
		finally {
			if(httpclient!=null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
	}

	public static String post(String url, Map<String, String> paramMap, String encode) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			for (String key : paramMap.keySet()) {
				params.add(new BasicNameValuePair(key,  paramMap.get(key)));
			}
			httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);//连接超时：connectionTimeout
			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);//读取数据超时：soTimeout
			HttpPost httppost = new HttpPost(url);
			httppost.addHeader(
					"User-Agent",
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022)");
			UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, encode);
	        httppost.setEntity(ent);
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity resEntity = response.getEntity();
	        String html = EntityUtils.toString(resEntity);
	        log.info("http响应码:"+response.getStatusLine().getStatusCode());
	        return html;
		}
		finally {
			if(httpclient!=null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
	}
	
	public static String post(String url, String ReqStr, String encode) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		
		// 构造最简单的字符串数据
		String reqbody = "requestXml=" + ReqStr;

		log.error("reqbody=" + reqbody);
		StringEntity reqEntity;
		StringBuffer sb = new StringBuffer();
		HttpResponse response = null;
		HttpEntity entity = null;
		try {
			reqEntity = new StringEntity(ReqStr, "utf-8");
			// 设置类型
			reqEntity.setContentType("application/x-www-form-urlencoded"); 
			// 设置请求的数据
			httppost.setEntity(reqEntity);
			httppost.getParams().setBooleanParameter(
					CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
			httppost.setHeader("Accept-Encoding", "gzip, deflate");
			HttpProtocolParams
					.setUserAgent(
							httppost.getParams(),
							"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; LEN2; InfoPath.2; CIBA)");
			httppost.setHeader(
					"Accept",
					"image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
			httppost.setHeader("Accept-Language", "zh-cn");

			// 执行
			httpclient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,true);
			response = httpclient.execute(httppost);
			entity = response.getEntity();

			if (entity != null) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(entity.getContent(), "utf-8"));
				String line = "";
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				if (entity != null) {
					httppost.abort();
					entity.consumeContent();
					httpclient.getConnectionManager().shutdown();
				}
			}
		} catch (Exception e) {
			log.error("调用httpclient出错", e);
			httpclient.getConnectionManager().shutdown();
			throw new RuntimeException(e);
		} finally {
			httpclient.getConnectionManager().shutdown();
			httppost.abort();
			if (entity != null) {
			}
		}
		log.error("return=" + sb.toString());
		return sb.toString();
	} 
	
	public  static String postSSL(String url, String ReqStr, String encode) throws Exception {
		log.error("start>>>>>>>>"+ReqStr);
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        //String password = Config.getConfigValue(SPConstents.CWX_MCH_ID);
        String password ="1225937302";
        FileInputStream instream = new FileInputStream(new File("D:/certs/apiclient_cert.p12"));//P12文件目录

        try {
            keyStore.load(instream,password.toCharArray());
        } finally {
            instream.close();
        }
		StringBuffer returnMsg = new StringBuffer();
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, password.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        
        try {

        	HttpPost httpPost = new HttpPost(url);
        	StringEntity reqEntity = new StringEntity(ReqStr, "utf-8");
			// 设置类型
			reqEntity.setContentType("application/x-www-form-urlencoded"); 
			// 设置请求的数据
			httpPost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String line = "";
    				while ((line = bufferedReader.readLine()) != null) {
    					returnMsg.append(line);
    				}
    				if (entity != null) {
    					httpPost.abort();
    					entity.consumeContent();
    					httpclient.getConnectionManager().shutdown();
    				}
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        log.error("start>>>>>>>>"+returnMsg);
        return returnMsg.toString();
    }
}
