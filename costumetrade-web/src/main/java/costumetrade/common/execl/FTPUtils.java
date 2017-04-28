/*
 * huirong Inc.
 * Copyright (c) 2015 All Rights Reserved.
 * Author     :liyb
 * Create Date:2015年8月21日
 */
package costumetrade.common.execl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * FTP文件操作帮助类
 * @author liyb
 * @version FTPUtils.java,2015年8月21日 下午5:12:45
 */
public class FTPUtils implements Serializable {
    private static final long serialVersionUID = -7680819051854328768L;

    private FTPClient ftpClient;
    private String host;//服务地址
    private String port; //服务器端口
    private String username; //用户登录名
    private String password; //用户登录密码
    private String remotePath;
    
    public FTPUtils(String host,String port,String username,String password,String remotePath){
        if(ftpClient==null){
            ftpClient = new FTPClient();
        }
        this.host=host;
        this.port=port;
        this.username=username;
        this.password=password;
        this.remotePath=remotePath;
    }
    
    /**
     * 打开服务器连接
     * @param remotePath 当前访问目录
     * @return
     * <b>true</b>：连接成功 <br/> 
     * <b>false</b>：连接失败
     */
    public boolean connectToServer(){
        boolean result = false; 
        try {
            //连接至服务器
            ftpClient.connect(host, Integer.parseInt(port));
            //登录服务器
            ftpClient.login(username, password);
            //判断返回码是否合法  
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {  
                //不合法时断开连接  
                ftpClient.disconnect();  
                //结束程序  
                return result;  
            }
            //设置文件操作目录  
            result = ftpClient.changeWorkingDirectory(remotePath);  
            //设置文件类型，二进制  
            result = ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
            //设置缓冲区大小  
            ftpClient.setBufferSize(3072);  
            //设置字符编码  
            ftpClient.setControlEncoding("UTF-8");  
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 上传文件至FTP服务器
     * @param storePath 上传文件存储路径
     * @param fileName 上传文件存储名称
     * @param is 上传文件输入流
     * @param orederNo 订单号
     * @return
     * <b>true</b>：连接成功 <br/> 
     * <b>false</b>：连接失败
     */
    public boolean storeFile(String storePath,String fileName, InputStream is,String orederNo){
        boolean result = false;
        result = connectToServer();
        try {
            //判断服务器是否连接成功  
            if (result) {
                result = false;
                boolean bool=true;
                if(!isDirExist(storePath)){
                    createDir(storePath);
                    bool = ftpClient.changeWorkingDirectory(storePath);
                }
                if(StringUtils.isNotBlank(orederNo)){
                	if(!isDirExist(orederNo)){
                        createDir(orederNo);
                        bool = ftpClient.changeWorkingDirectory(orederNo);
                    }
                }
                
                //上传文件 
                if(bool){
                    result = ftpClient.storeFile(fileName, is);
                }
            }  
            //关闭输入流  
            is.close();  
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //判断输入流是否存在  
            if (null != is) {  
                try {  
                    //关闭输入流  
                    is.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }
            logout();
        }
        
        return result;
    }
    
    /**
     * 下载FTP服务器文件至本地
     * @param storePath 远程文件的存储路径
     * @param fileName 需下载的文件名
     * @param orederNo
     * @return 
     */
    public File retrieveFile(String storePath,String fileName,String orederNo){
        String localPath="";
        if(SystemUtils.IS_OS_WINDOWS){
            localPath = "C:/cardry/tmp";
        }else{
            localPath = "/home/cardry/tmp";
        }
        File file = new File(localPath);
        if(!file.exists()){
            file.mkdirs();
        }
        try {
            boolean result = false;
            boolean data = false;
            result = connectToServer();
            //判断服务器是否连接成功 
            if (result) {
                File localFile = new File(localPath + "/" + fileName);
                OutputStream is = new FileOutputStream(localFile);
                data = ftpClient.retrieveFile("/"+storePath+"/"+orederNo+"/"+fileName, is);
                is.close();
                if(data){
                    return localFile;
                }
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            logout();
        }
        return null;
    }
    
    /**
     * 检测FTP服务器文件是否存在
     * @param fileName 检测文件存储名称
     * @return
     * <b>true</b>：文件存在 <br/> 
     * <b>false</b>：文件不存在 
     */
    public boolean checkFile(String fileName){
        boolean result = false;
        try {
            result = connectToServer();
            //判断服务器是否连接成功 
            if (result) {
                //默认文件不存在  
                result = false;
                //获取文件操作目录下所有文件名称  
                String[] remoteNames = ftpClient.listNames();  
                // 循环比对文件名称，判断是否含有当前要下载的文件名  
                for (String remoteName: remoteNames) {  
                    if (fileName.equals(remoteName)) {  
                        result = true;  
                    }  
                }  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            logout();
        }
        return result;
    }
    
    /**
     * 删除FTP服务器文件 
     * @param fileName 文件存储名称 
     * @return 
     * <b>true</b>：删除成功 <br/> 
     * <b>false</b>：删除失败 
     */  
    public boolean deleteFile (String fileName) {  
        boolean result = false;  
        //连接至服务器  
        result = connectToServer();  
        //判断服务器是否连接成功  
        if (result) {  
            try {  
                //删除文件  
                result = ftpClient.deleteFile(fileName);  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                //登出服务器并断开连接  
                logout();  
            }  
        }  
        return result;  
    }
    
    /**
     * 检查文件夹是否存在
     * @param dir
     * @return
     */
    public boolean isDirExist(String dir){
        boolean result= false;
        try {
            result=ftpClient.changeWorkingDirectory(dir);
        } catch (Exception e) {
            return false;
        }
        return result;
    }
    
    /**
     * 在ftp上创建文件夹
     * @param dir
     * @return
     */
    public boolean createDir(String dir){
        boolean b=false;
        try {
            b=ftpClient.makeDirectory(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
    
    /** 
     * 登出服务器并断开连接 
     * @param ftp FTPClient对象实例 
     * @return 
     * <b>true</b>：操作成功 <br/> 
     * <b>false</b>：操作失败 
     */  
    public boolean logout() {
        boolean result = false; 
        if (null != ftpClient) {  
            try {  
                //登出服务器  
                result = ftpClient.logout();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                //判断连接是否存在  
                if (ftpClient.isConnected()) {  
                    try {  
                        //断开连接  
                        ftpClient.disconnect();  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }  
        return result;  
    }
    
    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
