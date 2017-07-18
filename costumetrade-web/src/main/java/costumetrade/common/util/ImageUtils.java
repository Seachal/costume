package costumetrade.common.util;

import java.io.File;
import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class ImageUtils {

	// 设置好账号的ACCESS_KEY和SECRET_KEY
	public static String ACCESS_KEY = "Ujpjl2zfpUryVypY9H5pTKHOn94n3skAh0EG7TTL"; // 这两个登录七牛 账号里面可以找到
	public static String SECRET_KEY = "383o2ElTRPeY-ULuvJUJYFUQSNC4WcQBafkqAWsP";
	
	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public static String getUpToken(String bucketname) {
		// 密钥配置
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		return auth.uploadToken(bucketname);
	}	
	
	// 普通上传
	public static Response upload(File file,String fileName,String bucketname) throws IOException {
		// 创建上传对象
		UploadManager uploadManager = new UploadManager();
		Response res = null;
		try {
			// 调用put方法上传
			res = uploadManager.put(file, fileName, getUpToken(bucketname));
			// 打印返回的信息
			System.out.println(res.isOK());
			System.out.println(res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				System.out.println(r.bodyString());// 响应的文本信息
			} catch (QiniuException e1) {
				// ignore
			}
		}
		
		return res;
	}	
}
