package costumetrade.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang.SystemUtils;

/**
 * 
 * @ClassName: InternetResourceDownloadUtil
 * @Description: 下载网络资源
 * @author: caopc
 * @date: 2016年10月27日 上午10:33:41
 */
public class InternetResourceDownloadUtil {

	static String path = "";
	static {
		if (SystemUtils.IS_OS_WINDOWS) {
			path = "C:/cardry/video";
		} else {
			path = "/home/cardry/video";
		}
		File file = new File(path);
        if(!file.exists()){
           file.mkdirs();
        }
	}
	
	
	/**
	 * 网上获取文件
	 * 
	 * @param savepath
	 *            保存路径
	 * @param resurl
	 *            资源路径
	 * @param fileName
	 *            自定义资源名
	 */
	public static File getInternetRes(String resurl, String fileName) {
		URL url = null;
		HttpURLConnection con = null;
		InputStream in = null;
		FileOutputStream out = null;
		File res = null;
		try {
			url = new URL(resurl);
			// 建立http连接，得到连接对象
			con = (HttpURLConnection) url.openConnection();
			in = con.getInputStream();
			byte[] data = getByteData(in);// 转化为byte数组

			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}

			res = new File(file + File.separator + fileName);
			out = new FileOutputStream(res);
			out.write(data);
			System.out.println("downloaded successfully!");
		} catch (MalformedURLException e) {
			res = null;
			e.printStackTrace();
		} catch (IOException e) {
			res = null;
			e.printStackTrace();
		} finally {
			try {
				if (null != out)
					out.close();
				if (null != in)
					in.close();
			} catch (IOException e) {
				res = null;
				e.printStackTrace();
			}
		}
		return res;
	}

	/**
     * 从输入流中获取字节数组
     * 
     * @param in
     * @return
     * @throws IOException
     */
	private static byte[] getByteData(InputStream in) throws IOException {
		byte[] b = new byte[1024 * 10];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int len = 0;
		while ((len = in.read(b)) != -1) {
			bos.write(b, 0, len);
		}
		if (null != bos) {
			bos.close();
		}
		return bos.toByteArray();
	}

	public static void main(String[] args) {
		File f = getInternetRes("http://hrfax.oss-cn-hangzhou.aliyuncs.com/video/karongyi/zjzr2016102609165461/20161026/52nF884tCj.mp4", "bbbb.mp4");
		System.out.println(f);
	}
}
