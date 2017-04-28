/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年8月22日
 */
package costumetrade.common.word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.SystemUtils;

import costumetrade.common.util.FileUtil;
import costumetrade.common.util.InternetResourceDownloadUtil;
import costumetrade.common.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 生成附带图片的word
 * @author liyb
 * @version WordImageUtil.java,2016年8月22日 下午2:55:59
 */
public class WordImageUtil {
    
    private static final String ftl = "bankCredit.ftl";//模版名
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
    }
    
    public WordImageUtil(){
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }
    
    /**
     * 根据模版创建word文件
     * @param sfzzm 身份证正面
     * @param sfzfm 身份证反面
     * @param sqs 征信授权书
     * @param sqsqzz 征信授权书签字照
     * @param xtzqtzs 系统知情通知书
     * @param templatePath 模版文件路径
     * @param fileName 文件名
     * @return 返回word临时文件
     */
    public File createWord(String sfzzm,String sfzfm,String sqs,String sqsqzz,String xtzqtzs,String templatePath,String fileName){
        Map<String,Object> dataMap=new HashMap<String,Object>();
        dataMap.put("imageSFZZM", getImageBase64(sfzzm));
        dataMap.put("imageSFZFM", getImageBase64(sfzfm));
        dataMap.put("imageSQS", getImageBase64(sqs));
        if(StringUtil.isNotEmpty(sqsqzz)){
        	dataMap.put("imageSQSQZZ", getImageBase64(sqsqzz));
        }
        if(StringUtil.isNotEmpty(xtzqtzs)){
        	dataMap.put("imageXTZQTZS", getImageBase64(xtzqtzs));
        }
        Template t=null;
        File outFile = null;
        try {
            configuration.setDirectoryForTemplateLoading(new File(templatePath));
            t = configuration.getTemplate(ftl); //文件名
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(path);
        if(!file.exists()){
        	file.mkdirs();
        }
        outFile = new File(path+"/tmp_"+fileName);
        try {
        	if(!outFile.exists()){
             	outFile.createNewFile();
             }
		} catch (Exception e) {
			e.printStackTrace();
		}
        Writer out = null;
        try {
        	 
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            t.process(dataMap, out);
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outFile;
    }
    
    /**
     * 把图片文件生成base64字符串
     * @param file
     * @return
     */
    private String getImageBase64(String file){
        InputStream in = null;
        byte[] data = null;
        try {
            file = imageProcess(file);
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(data);
    }
    
    /**
     * 处理图片再次压缩
     * @param file
     * @return
     */
    private String imageProcess(String file){
        String filePath=file;
        try {
            File f = new File(file);
            File tmpfile = ImageUtils.compressionFile(f, f.getName());
            filePath = tmpfile.getPath();
        } catch (Exception e) {
            filePath=file;
            e.printStackTrace();
        }
        return filePath;
    }
    
    public static void main(String[] args) {
        String sfzzm="C:\\cardry\\word\\s-sfzzm.jpg";
        String sfzfm="C:\\cardry\\word\\s-sfzfm.jpg";
        String sqs="C:\\cardry\\word\\s-sqs.jpg";
        WordImageUtil word = new WordImageUtil();
//        word.createWord(sfzzm, sfzfm, sqs);
       
       String filePath = "http://img02.tooopen.com/images/20140504/sy_60294738471.jpg";
       File file = InternetResourceDownloadUtil.getInternetRes(filePath, new File(filePath).getName());
//        File file = new File("http://img02.tooopen.com/images/20140504/sy_60294738471.jpg");
        if(file.exists()){
        	System.err.println("111");
        }
        
        System.err.println(file);
    }

}
