package costumetrade.common.word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang.SystemUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class AuditTableWordUtil {

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

    public AuditTableWordUtil(){
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
    public File createWord(Map<String,Object> dataMap,String templatePath,String fileName, String ftlName){
    	Template t=null;
        File outFile = null;
        try {
            configuration.setDirectoryForTemplateLoading(new File(templatePath));
            t = configuration.getTemplate(ftlName); //文件名
        } catch (IOException e) {
            e.printStackTrace();
        }
        outFile = new File(path+"/tmp_"+fileName);
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
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outFile;
    }
    
}
