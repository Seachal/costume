package costumetrade.common.util;

import java.io.File;
import java.util.Random;
import java.io.ByteArrayOutputStream;   
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.net.HttpURLConnection;  
import java.net.URL; 
import org.apache.commons.lang.SystemUtils;

/**
 * 图片下载到本地，返回路径
 * @author luchunlong
 * @date 2017-03-22
 */
public class PicOperate {
	
	 private static final Random random = new Random();
	    
	    private static String FILE_PATH = "";

	    static {
	        if (SystemUtils.IS_OS_WINDOWS) {
	            FILE_PATH = "C:/cardry/pic/";
	        } else {
	            FILE_PATH = "/home/cardry/pic/";
	        }
	        File file = new File(FILE_PATH);
	        if(!file.exists()){
	            file.mkdirs();
	        }
	    }
	    
	    /**
	     * 根据url下载文件到本地，并返回路径
	     * @param fileUrl
	     * @return
	     */
	    public static String getFileURL(String fileUrl){
	    	String downFileUrl="";
	    	byte[] btImg =getImageFromNetByUrl(fileUrl);
	    	if(btImg!=null && btImg.length > 0){
	    		downFileUrl=writeImageToDisk(btImg);
	    	}
	    	
	    	return downFileUrl;
	    }
	    
	    /**
	     * 将图片写入到磁盘 
	     * @param img 图片数据流 
	     * @return
	     */
	    public static String writeImageToDisk(byte[] img){ 
	    	String imgFilePath ="";
	        try {  
	            imgFilePath = FILE_PATH+String.valueOf(System.currentTimeMillis())+ random.nextInt()+".jpg";
	            File file = new File(imgFilePath);  
	            FileOutputStream fops = new FileOutputStream(file);  
	            fops.write(img);  
	            fops.flush();  
	            fops.close(); 
	          
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return imgFilePath;
	    } 
	    
	    /** 
	     * 根据地址获得数据的字节流 
	     * @param strUrl 网络连接地址 
	     * @return 
	     */  
	    public static byte[] getImageFromNetByUrl(String strUrl){  
	        try {  
	            URL url = new URL(strUrl);  
	            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	            conn.setRequestMethod("GET");  
	            conn.setConnectTimeout(5 * 1000);  
	            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据  
	            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据  
	            return btImg;  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	    } 
	    
	    /** 
	     * 从输入流中获取数据 
	     * @param inStream 输入流 
	     * @return 
	     * @throws Exception 
	     */  
	    public static byte[] readInputStream(InputStream inStream) throws Exception{  
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	        byte[] buffer = new byte[1024];  
	        int len = 0;  
	        while( (len=inStream.read(buffer)) != -1 ){  
	            outStream.write(buffer, 0, len);  
	        }  
	        inStream.close();  
	        return outStream.toByteArray();  
	    }  
	
}