package costumetrade.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.logging.Logger;

/**
 * @类功能说明：文件服务类
 * @公司名称：点艺信息有限公司
 * @作者：陈友敬
 * @创建时间：2017-9-6 下午04:36:56
 * @版本：V1.0
 *
 */
public class FileHelper {

    public static Logger logger = Logger.getLogger(FTPClientUtils.class);
    /*
     * (non-Javadoc)
     * @see com.qqwz.bank.business.service#createFile()
     * 创建新文件
     */
	public static File createFile(String filePath){
		
		File writefile = new File(filePath);
		try {
        // 如果文本文件不存在则创建它
        if (writefile.exists() == false) {
			writefile.createNewFile();
            writefile = new File(filePath); // 重新实例化
        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("文件创建异常");
		}
		return writefile;
	}
	
    /*
     * (non-Javadoc)
     * @see com.qqwz.bank.business.service#getBufferReader()
     * 获得BufferedReader
     */
	public static BufferedReader getBufferReader(String filePath){
		
		File readFile = new File(filePath);
		BufferedReader reader = null;
		
		try {
	        if (readFile.exists()) {
	    		FileInputStream input = new  FileInputStream(readFile);
	    		reader = new BufferedReader(new InputStreamReader(input));
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("文件读取异常:"+e);
		}
		return reader;
	}
	
    /*
     * (non-Javadoc)
     * @see com.qqwz.bank.business.service#isFileExists()
     * 判断文件是否存在
     */
	public static boolean isFileExists(String filePath){
			File writefile = new File(filePath);
			return writefile.exists();
	}
	
    /*
     * (non-Javadoc)
     * @see com.qqwz.bank.business.service#createPath()
     * 创建文件夹
     */
	public  static void createPath(String prefilePath){
		File file = new File(prefilePath);
		if(!file.exists()){
			file.mkdirs();
		}	
	}
	
    /*
     * (non-Javadoc)
     * @see com.qqwz.bank.business.service#deleteFile()
     * 删除文件
     */
	public static void deleteFile(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			 file.delete();
		}
	}
	
    /*
     * (non-Javadoc)
     * @see com.qqwz.bank.business.service#appendChannelContent()
     * 新增文件，并添加内容
     */
	 public static long appendChannelContent(String file, String content,boolean append)
	    {
	        long begin = System.currentTimeMillis(); 
	        if(null == content){
	        	return 0;
	        }
	        try {
		        File out = new File(file);
		        FileOutputStream fout = new FileOutputStream(out,append);
		        FileChannel outc = fout.getChannel();
		        
		        int bufferLen = content.length()*2;
		        ByteBuffer buffer = ByteBuffer.allocateDirect(bufferLen);
		        ByteBuffer contentBuf  = ByteBuffer.wrap(content.getBytes());
		        buffer.clear();
		        outc.write(contentBuf);
		      
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				logger.error("找不到文件异常:"+e); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("输入输出异常:"+e); 
			}
	        long end = System.currentTimeMillis();
	        long runtime = 0;
	        if(end >  begin)
	            runtime = end - begin;
	        return runtime;

	 }
	    /*
	     * (non-Javadoc)
	     * @see com.qqwz.bank.business.service#appendChannelContent()
	     * 新增文件，并添加内容
	     */
	 public static long appendChannelContent(String file, byte[] content,boolean append)
	    {
	        long begin = System.currentTimeMillis(); 
	        if(null == content){
	        	return 0;
	        }
	        try {
		        File out = new File(file);
		        FileOutputStream fout = new FileOutputStream(out,append);
		        FileChannel outc = fout.getChannel();
		        
		        int bufferLen = content.length*2;
		        ByteBuffer buffer = ByteBuffer.allocateDirect(bufferLen);
		        ByteBuffer contentBuf  = ByteBuffer.wrap(content);
		        buffer.clear();
		        outc.write(contentBuf);
		      
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				logger.error("找不到文件异常:"+e); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("输入输出异常:"+e); 
			}
	        long end = System.currentTimeMillis();
	        long runtime = 0;
	        if(end >  begin)
	            runtime = end - begin;
	        return runtime;

	 }
	 
	    /*
	     * (non-Javadoc)
	     * @see com.qqwz.bank.business.service#appendChannelContent()
	     * 新增文件，并添加内容
	     */
	public static void appendContent(String file, String content,boolean append){
       BufferedWriter out = null ;  
       try{  
             out = new  BufferedWriter(new OutputStreamWriter(new  FileOutputStream(file,  append)));  
             out.write(content); 
             out.flush();
         }catch(Exception e){  
        	 logger.error("文件创建异常");  
         }finally{ 
        	 
            try{  
                  out.close();  
             } catch  (IOException e) {  
            	 logger.error("文件创建异常"); 
             }  
        }    
	}
    /*
     * (non-Javadoc)
     * @see com.qqwz.bank.business.service#getNowDateFold()
     * 获得日期文件夹
     */
	public static String getNowDateFold(){
	     SimpleDateFormat  format= new SimpleDateFormat("yyyyMMdd");	  
	     String  dateFold = format.format(new Date());
	     return dateFold;
	}
	
    /*
     * (non-Javadoc)
     * @see com.qqwz.bank.business.service#getTomorrowDateFold()
     * 获得日期文件夹
     */
	public static String getLastDateFold(){
	     SimpleDateFormat  format= new SimpleDateFormat("yyyyMMdd");	  
	     String  dateFold = format.format(new Date());
	     String dateToString = Integer.toString(Integer.parseInt(dateFold)-1);
	     return dateToString;
	}
	
    /*
     * (non-Javadoc)
     * @see com.qqwz.bank.business.service#getNowDate()
     * 获得日期文件夹
     */
	public static String getNowDate(){
	     SimpleDateFormat  format= new SimpleDateFormat("yyyy-MM-dd");	  
	     String  dateFold = format.format(new Date());
	     return dateFold;
	}
	
   
	//获得项目路径
	public static String getProjectPath(){
		String projectName = "DCI";
		String nowPath = System.getProperty("user.dir");
		String tempDir = nowPath.replace("bin", "webapps");
		tempDir += "\\"+projectName; 
		return tempDir;
	}
	public static String getFoldPrefilePath(){
		String projectName = "DCI";
		String nowPath = System.getProperty("user.dir");
		String tempDir = nowPath.replace("bin", "webapps");
		tempDir += "\\"+projectName; 
		return tempDir;
	}
	
}
