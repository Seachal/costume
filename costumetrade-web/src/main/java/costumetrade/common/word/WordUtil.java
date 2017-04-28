/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年4月1日
 */
package costumetrade.common.word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 生成第三方代付证明word
 * @author liyb
 * @version WordUtil.java,2016年4月1日 下午4:49:08
 */
public class WordUtil {
    
    private static final String ftl = "thirdPay.ftl";//模版名
    private static final String fileName = "thirdPay-";
    protected static String extension = ".doc";
    private Configuration configuration = null;
    
    static String path="";
    static {
        if(SystemUtils.IS_OS_WINDOWS){
            path = "C:/cardry/word";
        }else{
            path = "/home/cardry/word";
        }
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    public WordUtil(){
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }
    
    /**
     * 生成word文件
     * @param servletContext
     * @param dataMap
     * @param orderNo
     * @return
     */
    public File createWord(ServletContext servletContext,Map<String,Object> dataMap,String orderNo){
        Template t=null;
        Writer out = null;
        File outFile = null;
        try {
            outFile = new File(path+"/"+fileName+orderNo+extension);
            configuration.setServletContextForTemplateLoading(servletContext, "/ftl");
            t = configuration.getTemplate(ftl); //文件名  
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
            t.process(dataMap, out);  
        } catch (FileNotFoundException e1) {
            outFile = null;
            e1.printStackTrace();
        } catch (TemplateException e) {
            outFile = null;
            e.printStackTrace();
        } catch (IOException e) {
            outFile = null;
            e.printStackTrace();
        }finally{
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {}
            }
        }
        return outFile;
    }
    
}
