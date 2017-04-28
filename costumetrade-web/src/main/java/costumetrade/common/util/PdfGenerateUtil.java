/*
 * huirong Inc.
 * Copyright (c) 2015 All Rights Reserved.
 * Author     :liyb
 * Create Date:2015年10月24日
 */
package costumetrade.common.util;

import java.awt.Insets;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.SystemUtils;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

/**
 * PDF生成工具类
 * @author liyb
 * @version PdfGenerateUtil.java,2015年10月24日 下午4:56:16
 */
public class PdfGenerateUtil {
    private static final Logger logger = Logger.getAnonymousLogger();
    protected static int userSpaceWidth = 1300;
    protected static String extension = ".pdf";
    
    static String path="";
    static {
        if(SystemUtils.IS_OS_WINDOWS){
            path = "C:/cardry/pdf";
        }else{
            path = "/home/cardry/pdf";
        }
       /// FileUtils.createDir(path);
        File file = new File(path);
        if(!file.exists()){
        	file.mkdirs();
        }
    }
    
    public static void main(String[] args) {
        String url="http://192.168.0.54:8080/busAccepted/generateBankPDF.html?shopOrderNo=gsyh4s2016072518100796&orderNo=gdgsyh2016072610513483&needLogin=none";
//        String url="http://192.168.0.40:8080/thirdPay/initPayConfirmBook.html?orderNo=nfdb2016032322165854&needLogin=none";
        generatePDF(url, "银行",0);
        
    }
    
    /**
     * 读取临时文件的文件
     * @param fileName 文件名
     * @return
     */
    public static File readPdfTempFile(String fileName){
        File outFile = new File(path+File.separator+fileName+extension);
        return outFile;
    }
    
    /**
     * pdf文件临时路径
     * @param fileName
     * @return
     */
    public static String getPdfTempPath(String fileName){
    	return path + File.separator + fileName + extension;
    }
    
    /**
     * PDF生成在临时目录下
     * @param url 需生成的pdf模版请求URL
     * @param fileName 生成的文件名
     * @param width html宽度 
     * @return true:成功  false:失败
     */
    public static boolean generatePDF(String url,String fileName,int width){
        FileOutputStream fos = null;
        FileInputStream fis = null;
        boolean bool=true;
        try {
            if(width==0){
                width = userSpaceWidth;
            }
            File output = new File(path+File.separator+fileName+extension); 
            fos = new FileOutputStream(output); 
            PD4ML pd4ml = new PD4ML();
            pd4ml.setPageInsets(new Insets(5, 20, 20, 20));
            pd4ml.setHtmlWidth(width);
            pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));
            pd4ml.addStyle("BODY {margin: 0}", true); 
            pd4ml.useTTF("java:fonts", true);
            pd4ml.setDefaultTTFs("SimHei", "Arial", "Courier New");
            pd4ml.enableDebugInfo();
            pd4ml.render(new URL(url), fos);
        } catch (Exception e) {
            bool = false;
            logger.info("生成PDF文件异常:"+e.getMessage());
        }finally{
            try {
                if(fis != null) fis.close();
                if(fos != null) fos.close();
            } catch (IOException e) {}
        }
        return bool;
    }

    /**
     * PDF生成并下载
     * @param url 需生成的pdf模版请求URL
     * @param fileName 生成的文件名
     */
    public static void pdfExport(String url,String fileName,HttpServletResponse resp){
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fileName = fileName+extension;
            File output = new File(path+File.separator+fileName); 
            fos = new FileOutputStream(output); 
            PD4ML pd4ml = new PD4ML();
            pd4ml.setPageInsets(new Insets(5, 20, 20, 20));
            pd4ml.setHtmlWidth(userSpaceWidth);
            pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));
            pd4ml.addStyle("BODY {margin: 0}", true); 
            pd4ml.useTTF("java:fonts", true);
            pd4ml.setDefaultTTFs("SimHei", "Arial", "Courier New");
            pd4ml.enableDebugInfo();
            pd4ml.render(new URL(url), fos);
            fos.close();
            //HttpServletResponse resp = ServletActionContext.getResponse();
            fis = new FileInputStream(output);//服务器文件路径
            resp.reset();//必须加，不然保存不了临时文件
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition","attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1"));
            resp.setCharacterEncoding("UTF-8");
            OutputStream out = resp.getOutputStream(); //得到向客户端输出二进制数据的对象
            BufferedInputStream bis = new BufferedInputStream(fis);//输入缓冲流
            BufferedOutputStream bos = new BufferedOutputStream(out);//输出缓冲流
            byte data[] = new byte[4096];//缓冲字节数
            int size = 0;
            size = bis.read(data);
            while (size != -1) {
                bos.write(data, 0, size);
                size = bis.read(data);
            }
            bis.close();
            bos.flush();//清空输出缓冲流
            bos.close();
            out.close();
            output.delete();
        } catch (Exception e) {
            logger.info("生成PDF文件异常:"+e.getMessage());
        }finally{
            try {
                if(fis != null) fis.close();
                if(fos != null) fos.close();
            } catch (IOException e) {}
        }
    }
}
