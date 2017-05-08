package costumetrade.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.jboss.logging.Logger;



/**
 * @类功能说明：文件服务类
 * @公司名称：点艺信息有限公司
 * @作者：陈友敬
 * @创建时间：2017-9-6 下午04:36:56
 * @版本：V1.0
 *
 */
public class FTPClientUtils {

    public static Logger logger = Logger.getLogger(FTPClientUtils.class);
	
	private ThreadLocal<FTPClient> ftpClientThreadLocal = new ThreadLocal<FTPClient>(); 

	private String url;
	private Integer port  = 21;

	private String username;

	private String password;
	private Integer clientTimeout = 20000;
	private boolean passiveMode = false;
	private String encoding = "gbk";
	
	private static FTPClientUtils ftpClientUtils = null;

	 //---------------------------------------------------------------------  
    // private method  
    //---------------------------------------------------------------------  
    /** 
     * 返回一个FTPClient实例 
     *  
     * @throws FTPClientException 
     */  
    private FTPClient getFTPClient() {  
        if (ftpClientThreadLocal.get() != null && ftpClientThreadLocal.get().isConnected()) {  
            return ftpClientThreadLocal.get();  
        } else {  
            FTPClient ftpClient = new FTPClient(); //构造一个FtpClient实例  
            //ftpClient.setControlEncoding(encoding); //设置字符集  
            ftpClient.setControlEncoding("gbk");
           
            
            try {
                //设置为passive模式  
                if (passiveMode) {  
                    ftpClient.enterLocalPassiveMode();  
                }
                
				connect(ftpClient);//连接到ftp服务器  
				ftpClient.setSoTimeout(clientTimeout);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("connect FTP  异常:"+e);
			}
 
            
            //setFileType(ftpClient); //设置文件传输类型  
            ftpClientThreadLocal.set(ftpClient);  
            return ftpClient;  
        }  
    }  
    public static FTPClientUtils getInstance(){
    	if(null==ftpClientUtils){
    		ftpClientUtils = new FTPClientUtils();
    	}
    	return ftpClientUtils;
    }
	  /**
	    * 上传文件至FTP
	    * @param path
	    * @param fileName
	    * param input
	    * @return
	    */
	public  boolean uploadFileToFTP(String remoteRelativePath, String localfilename, String localAbsoluteFile) {  
	    boolean success = false;  
//	    File file = new File(localAbsoluteFile);
//	    if(!file.exists()){
//	    	return success;
//	    }
//	    
	    
	    try {  
	    	FileInputStream input =new  FileInputStream(new File(localAbsoluteFile));
	    	createDir(remoteRelativePath);
	        getFTPClient().changeWorkingDirectory(remoteRelativePath);  
	        //String  name=gbktoiso8859(localfilename);
	        success = getFTPClient().storeFile(localfilename, input);           
	        input.close();  
	        getFTPClient().logout();   
	    } catch (IOException e) {    
	        logger.error("输入输出异常:"+e);
	    } finally {  
	        if (getFTPClient().isConnected()) {  
	            try {  
	               getFTPClient().disconnect();  
	           } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	   return success;  
  }
	
	
	
    /**
     * 创建目录
     * 
     * @param remote
     * @return
     * @throws IOException
     */
    public  boolean createDir(String remote) 
    {
      try{
        	if(getFTPClient().changeWorkingDirectory(remote)){
        		return true;
            }
        	getFTPClient().makeDirectory(remote);
    	}catch (IOException e) {
        	logger.error("创建FTP目录输入输出异常:"+e);
        	return false;
	    }
      
        return true;
      }
    
        /**
        *不同的平台需要不同的转码
	    * @param obj
	    * @return
	    */
	/* private  String iso8859togbk(Object obj) {
	    try {
	     if (obj == null)
	      return "";
	     else
	      return new String(obj.toString().getBytes("iso-8859-1"), "GBK");
	    } catch (Exception e) {
	     return "";
	    }
	  }*/
	
	  /**
	    * 转码[GBK ->  ISO-8859-1]
	    *不同的平台需要不同的转码
	    * @param obj
	    * @return
	    */
	/* private  String gbktoiso8859(Object obj) {
	    try {
	     if (obj == null)
	      return "";
	     else
	      return new String(obj.toString().getBytes("GBK"), "iso-8859-1");
	    } catch (Exception e) {
	     return "";
	    }
	 }*/
	  /**
	    * 新建连接
	    * @param ftpClient
	    * @return
	    */
	 public  boolean connect(FTPClient ftpClient) throws Exception{
		 
		this.url=ConfigProperties.getProperty("ftp.ip");
		this.username=ConfigProperties.getProperty("ftp.username");
		this.password=ConfigProperties.getProperty("ftp.password");
		
		if(null == ftpClient)
			return false;
		
		 try {  
		        int reply;  
		        ftpClient.connect(url, port);  
		        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器   
		        reply = ftpClient.getReplyCode();  
		        if (FTPReply.isPositiveCompletion(reply)) {  
		        	if (ftpClient.login(username, password)) {  
	                    return true;  
	                }    
		        }else {  
	                ftpClient.disconnect();  
	                throw new Exception("FTP server refused connection.");  
	            }  
		    } catch (IOException e) {  
		    	 if (ftpClient.isConnected()) {  
		                try {  
		                    ftpClient.disconnect(); //断开连接  
		                } catch (IOException e1) {  
		                    throw new Exception("Could not disconnect from server.", e1);  
		                }  
		  
		        logger.error("输入输出异常:"+e);
		      }
		    } 
           return false;
	 }
	  /**
	    * 从FTP获得最新的文件
	    * @param remotePath
	    * @param fileName
	    * @return
	    */
    public  boolean getlatestFile(String remotePath,String fileName){ 
	    String topFold = remotePath;
	    boolean success = false;
	    if(null == fileName || "".equalsIgnoreCase(fileName)){
	    	return success;
	    }
	    
	    if(null == remotePath || "".equalsIgnoreCase(remotePath)){
	    	topFold = "/";
	    }
	    try {  

	        getFTPClient().changeWorkingDirectory(topFold);//转移到FTP服务器目录   
	        String[] fileNames = getFTPClient().listNames();
	        List<String> ListFiles = new ArrayList<String>();
	        Collections.addAll(ListFiles, fileNames);
	        Collections.sort(ListFiles,Collections.reverseOrder());
	        
	        for(String folderName:ListFiles){
	        	topFold = "/"+folderName+"/";
		        getFTPClient().changeWorkingDirectory(topFold);//转移到FTP服务器目录   
		        FTPFile[] fs = getFTPClient().listFiles(); 
		        for(FTPFile ftpFile:fs){  
		           String ftpFileName = ftpFile.getName();
		        	
                   if(ftpFileName.indexOf(fileName) >= 0){
                	   
		                String subDate = ftpFileName.substring(fileName.length()+1, fileName.length()+1+8);
		                String localFileName = ftpFileName.replace(subDate, FileHelper.getNowDateFold());
		                File localFile = new File(FileHelper.getFoldPrefilePath()+"/"+localFileName);      
		                OutputStream out = new FileOutputStream(localFile);  

		                getFTPClient().retrieveFile(ftpFileName, out);  
		                out.close();
		                //FileInputStream input = new FileInputStream(localFile);
		                //String ftpFileName = ff.getName();
		                //uploadFileToFtp(ConstantPFInterface.FTP_TARGET_PATH,ftpFileName,input);
		                success = true;
		                getFTPClient().logout();
		                return success;
                   }
	            }
	        }
	       
	          
	        
	    } catch (IOException e) {  
	        logger.error("输入输出异常:"+e);
	    } finally {  
	        if (getFTPClient().isConnected()) {  
	            try {  
	                getFTPClient().disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    } 
	    return success ;
    }
    
	  /**
	    * 从指定的FTP目录获得多个文件
	    * @param remotePath
	    * @param fileName
	    * @return
	    */
public  boolean getFileFromFold(String remotePath,String[] fileNames){ 
	    String topFold = remotePath;
	    boolean success = false;
	    if(null == fileNames || fileNames.length <=0 ){
	    	return success;
	    }
	    
	    if(null == remotePath || "".equalsIgnoreCase(remotePath)){
	    	topFold = "/";
	    }
	    List<String> downedFiles = new ArrayList<String>();
	    
	    try {  
	        	if(downedFiles.size() == fileNames.length){
	        		return success;
	        	}
	        	
		        getFTPClient().changeWorkingDirectory(topFold);//转移到FTP服务器目录   
		        FTPFile[] ftpFiles = getFTPClient().listFiles(); 
		        
		        Label:for(String fileName:fileNames){
			       for(FTPFile ftpFile:ftpFiles){  
			    	    String ftpFileName = ftpFile.getName();
		                if(ftpFileName.indexOf(fileName) >= 0){
				                String subDate = ftpFileName.substring(fileName.length()+1, fileName.length()+1+8);
				                String localFileName = ftpFileName.replace(subDate, FileHelper.getNowDateFold());
				                File localFile = new File(FileHelper.getFoldPrefilePath()+"/"+localFileName);      
				                OutputStream out = new FileOutputStream(localFile);  
				                getFTPClient().retrieveFile(ftpFileName, out);  
				                out.close();
				                success = true;
				                downedFiles.add(fileName);
				                continue Label;
		                }
		            }
	        }  
	    } catch (IOException e) {  
	    	success = false;
	        logger.error("输入输出异常:"+e);
	    } finally {  
	        if (getFTPClient().isConnected()) {  
	            try {  
	                getFTPClient().disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    } 
	    return success ;
      }

	  /**
	    * 从FTP获得多个最新的文件
	    * @param remotePath
	    * @param fileName
	    * @return
	    */
   public  boolean getlatestFiles(String remotePath,String[] fileNames){ 
	    String topFold = remotePath;
	    boolean success = false;
	    if(null == fileNames || fileNames.length <=0 ){
	    	return success;
	    }
	    
	    if(null == remotePath || "".equalsIgnoreCase(remotePath)){
	    	topFold = "/";
	    }
	    List<String> downedFiles = new ArrayList<String>();
	    
	    try {  

	        getFTPClient().changeWorkingDirectory(topFold);//转移到FTP服务器目录   
	        String[] folderNames = getFTPClient().listNames();
	        List<String> ListFiles = new ArrayList<String>();
	        Collections.addAll(ListFiles, folderNames);
	        Collections.sort(ListFiles,Collections.reverseOrder());
	        
	        for(String folderName:ListFiles){
	        	if(downedFiles.size() == fileNames.length){
	        		break;
	        	}
	        	
	        	topFold = "/"+folderName+"/";
		        getFTPClient().changeWorkingDirectory(topFold);//转移到FTP服务器目录   
		        FTPFile[] ftpFiles = getFTPClient().listFiles(); 
		        
		        Label:for(String fileName:fileNames){
			       for(FTPFile ftpFile:ftpFiles){  
			    	    String ftpFileName = ftpFile.getName();
			    	    
		                if(ftpFileName.indexOf(fileName) >= 0){
		             	   
				                String subDate = ftpFileName.substring(fileName.length()+1, fileName.length()+1+8);
				                String localFileName = ftpFileName.replace(subDate, FileHelper.getNowDateFold());
				                File localFile = new File(FileHelper.getFoldPrefilePath()+"/"+localFileName);      
				                OutputStream out = new FileOutputStream(localFile);  
		
				                getFTPClient().retrieveFile(ftpFileName, out);  
				                out.close();
				                //FileInputStream input = new FileInputStream(localFile);
				                //String ftpFileName = ff.getName();
				                //uploadFileToFtp(ConstantPFInterface.FTP_TARGET_PATH,ftpFileName,input);
				                success = true;
				                downedFiles.add(fileName);
				                continue Label;
		                }
		            }
	        }
	     }
	       
	          
	        
	    } catch (IOException e) {  
	    	success = false;
	        logger.error("输入输出异常:"+e);
	    } finally {  
	        if (getFTPClient().isConnected()) {  
	            try {  
	                getFTPClient().disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    } 
	    return success ;
   }
    
	  /**
	    * 上传文件至FTP
	    * @param path
	    * @param fileName
	    * param input
	    * @return
	    */
    public  boolean uploadFileToFtp(String path,String fileName,InputStream input){
    	
    	    createDir(path);
	       
	        boolean success = false;
			try {
				getFTPClient().changeWorkingDirectory(path);
				success = getFTPClient().storeFile(fileName, input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("输入输出异常:"+e);
			}
	        return success;
    }
    
    public boolean uploadFile(String path,String ftpFileName){
    	boolean success = false;
    	//getFTPClient();
    	createDir(path);
    	File localFile = new File(ftpFileName);      
    	InputStream in =null;  

        try {
        	in = new FileInputStream(localFile); 
			getFTPClient().storeFile(path, in);
		
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         
         return success;
    }
    
    /** 
     * 断开ftp连接 
     *  
     * @throws FTPClientException 
     */  
    public void disconnect() throws IOException {  
        try {  
            FTPClient ftpClient = getFTPClient();  
            ftpClient.logout();  
            if (ftpClient.isConnected()) {  
                ftpClient.disconnect();  
                ftpClient = null;  
            }  
        } catch (IOException e) {  
            throw new IOException("Could not disconnect from server:", e);  
        }  
    }  
    /** 
	 * Description: 从FTP服务器下载文件
	 * @param remotePath FTP服务器上的相对路径 
	 * @param fileNames 要下载的文件名 
	 * @return 
	 */  
	public  boolean downFileFromFtp(String remotePath,String[] fileNames) {
/*		 boolean success = false;
		 for(String file:fileNames){
			   getlatestFile(remotePath,file);
		 }*/
		 boolean success = getFileFromFold(remotePath,fileNames);
		 //success = true;
		 return success;
	}
	
    /** 
	 * Description: 从FTP服务器下载文件
	 * @param fileNames 要下载的文件名 
	 * @param remotePath FTP根路径 
	 * @return 
	 */  
	/*public  boolean downFileFromFtp(String remotePath,String[] fileNames) {  
		 boolean success = false;
		 
		 if(null == fileNames || fileNames.length <=0){
			 return success;
		 }

	    List<String> downFiles = new ArrayList<String>();
	    List<String> uploadFiles = new ArrayList<String>();
	    try { 
		  String targetPath = ConstantPFInterface.FTP_TARGET_PATH; 
		     if(getFTPClient().changeWorkingDirectory(targetPath)){
			 getFTPClient().changeWorkingDirectory(targetPath);//转移到FTP服务器目录   
		     FTPFile[] fs = getFTPClient().listFiles();  
		       
	         for(String fileName:fileNames){
	        	boolean addFlag = true;
	        	FTPFile ftpFile = null;
	        	for(FTPFile ff:fs){ 
		            if(ff.getName().indexOf(fileName) > 0){  
		                File localFile = new File(PFFileHelper.getFoldPrefilePath()+"/"+ff.getName());      
		                OutputStream is = new FileOutputStream(localFile);   
		                getFTPClient().retrieveFile(ff.getName(), is);  
		                is.close(); 
		                success = true;  
		                addFlag = false;
		                ftpFile = ff;
		            }  
	        	}
	        	if(addFlag){
	        		downFiles.add(fileName);
	        	}else{
	        		uploadFiles.add(ftpFile.getName());
	        	}
	         }  
		        getFTPClient().logout(); 
		     }
		    } catch (IOException e) {  
		        logger.error("输入输出异常:"+e);
		    } finally {  
		        if (getFTPClient().isConnected()) {  
		            try {  
		                getFTPClient().disconnect();  
		            } catch (IOException ioe) {  
		            }  
		        }  
		  }   
		 if(success) { 
			 for(String file:downFiles){
			   getlatestFile(remotePath,file);
			 }
			 
			 for(String upFile:uploadFiles){
				 //File localFile = new File(PFFileHelper.getFoldPrefilePath()+"/"+iso8859togbk(upFile));  
				 String localAbsoluteFile = PFFileHelper.getFoldPrefilePath()+"/"+iso8859togbk(upFile);
				 this.uploadFileToFTP(PFFileHelper.getTargetPath(), iso8859togbk(upFile), localAbsoluteFile);
			 }
		 }else{
			 for(String file:fileNames){
				   getlatestFile(remotePath,file);
				 }			 
		 }
		 return success; 
	}
	*/
	 /** 
     * 从ftp服务器上删除一个文件 
     *  
     * @param delFile 
     * @param autoClose 是否自动关闭当前连接 
     *  
     * @return 
     * @throws FTPClientException 
     */  
    public boolean delete(String delFile, boolean autoClose) throws IOException {  
        try {  
            getFTPClient().deleteFile(delFile);  
            return true;  
        } catch (IOException e) {  
            throw new IOException("Couldn't delete file from server:", e);  
        } finally {  
            if (autoClose) {  
                disconnect(); //关闭链接  
            }  
        }  
    }  
      
	/** 
	 * Description: 从FTP服务器下载文件
	 * @param remotePath FTP服务器上的相对路径 
	 * @param fileNames 要下载的文件名 
	 * @param localPath 下载后保存到本地的路径 
	 * @return 
	 */  
	public  boolean downFileFromFtp(String remotePath,String[] fileNames,String localPath) {  
	    boolean success = false;  
	 
	    try {  

	        getFTPClient().changeWorkingDirectory(remotePath);//转移到FTP服务器目录   
	        FTPFile[] fs = getFTPClient().listFiles();  
	        for(FTPFile ff:fs){ 
	        	for(String fileName:fileNames){
	        		String ftpFileName = ff.getName();
		            if(ftpFileName.equals(fileName)){  
		                File localFile = new File(localPath+"/"+ftpFileName);      
		                OutputStream is = new FileOutputStream(localFile);   
		                getFTPClient().retrieveFile(ftpFileName, is);  
		                is.close(); 
		                success = true;  
		            }  
	        	}
	        }  
	          
	        getFTPClient().logout();  
	        
	    } catch (IOException e) {  
	        logger.error("输入输出异常:"+e);
	    } finally {  
	        if (getFTPClient().isConnected()) {  
	            try {  
	                getFTPClient().disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return success;  

	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}


	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}



	/**
	 * @return the clientTimeout
	 */
	public Integer getClientTimeout() {
		return clientTimeout;
	}

	/**
	 * @param clientTimeout the clientTimeout to set
	 */
	public void setClientTimeout(Integer clientTimeout) {
		this.clientTimeout = clientTimeout;
	}

	/**
	 * @return the passiveMode
	 */
	public boolean isPassiveMode() {
		return passiveMode;
	}

	/**
	 * @param passiveMode the passiveMode to set
	 */
	public void setPassiveMode(boolean passiveMode) {
		this.passiveMode = passiveMode;
	}

	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	
}
