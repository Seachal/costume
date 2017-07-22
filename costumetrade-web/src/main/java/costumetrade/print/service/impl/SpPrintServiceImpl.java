package costumetrade.print.service.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

import org.apache.poi.sl.draw.BitmapImageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import sun.misc.BASE64Encoder;
import costumetrade.common.util.Base64Util;
import costumetrade.common.util.StringUtil;
import costumetrade.print.service.SpPrintService;
import costumetrade.user.domain.ScPrinterInfo;
import costumetrade.user.domain.SpStore;
import costumetrade.user.mapper.ScPrinterInfoMapper;
import costumetrade.user.mapper.SpStoreMapper;

@Transactional
@Service
public class SpPrintServiceImpl implements SpPrintService{
	@Autowired
	private ScPrinterInfoMapper scPrinterInfoMapper;
	@Autowired
	private SpStoreMapper spStoreMapper;
	@Override
	public String gbkImage(String storeId) {
		String data = null;
		
		String weUrl = null;
		if(StringUtil.isNotBlank(storeId)){
			SpStore store = spStoreMapper.selectByPrimaryKey(storeId);
			if(store!=null){
				weUrl = store.getWeUrl();
			}
		}
		if(StringUtil.isNotBlank(weUrl)){
			
		}
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public String gbkText(String text) {
		BASE64Encoder encoder = new BASE64Encoder();  
		// 通过base64来转化图片
		String gbk ="";
		try {
//			String utf8 = new String(text.getBytes( "UTF-8"));  
//    	    System.out.println(utf8);  
//    	    String unicode = new String(utf8.getBytes(),"UTF-8");   
//    	    System.out.println(unicode);  
    	    gbk  = new String(text.getBytes("GBK"),"GBK");  
			//text = new String(text.getBytes("GB2312"),"GBK");
			//getEncoding(text);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data = encoder.encode(gbk.getBytes());
//		getEncoding(text);
//		String data = null;
//		try {
////			byte[] binBuffer = text.getBytes("GBK");
//			data = Base64Util.getImageBase64(text);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
		
  
		return data;
	}

	@Override
	public String getUserIdPrinter(ScPrinterInfo info) {
		//先判断打印机是否存在
		info.setPrintEnable(1+"");
		ScPrinterInfo print = scPrinterInfoMapper.select(info);
		if(print==null){
			return "1";//现在设置中保存打印机WiFi
		}else{
			return print.getPrintUserId();

		}
		 

	}
	
    public static String getEncoding(String str) { 
    	
        String encode = "GBK";        
       try {        
           if (str.equals(new String(str.getBytes(encode), encode))) {        
                String s = encode;        
               return s;        
            }        
        } catch (Exception exception) {        
        }        
        encode = "ISO-8859-1";        
       try {        
           if (str.equals(new String(str.getBytes(encode), encode))) {        
                String s1 = encode;        
               return s1;        
            }        
        } catch (Exception exception1) {        
        }        
        encode = "UTF-8";        
       try {        
           if (str.equals(new String(str.getBytes(encode), encode))) {        
                String s2 = encode;        
               return s2;        
            }        
        } catch (Exception exception2) {        
        }        
        encode = "GBK";        
       try {        
           if (str.equals(new String(str.getBytes(encode), encode))) {        
                String s3 = encode;        
               return s3;        
            }        
        } catch (Exception exception3) {        
        }        
       return "";        
    }   
    public static void main(String[] args) {
    	String text ="女粉丝的快感和反思呢市供热女";
    	getEncoding(text);
    	try {
//    		String utf8 = new String(text.getBytes( "GBK")); 
//    		
//			String s = new String(text.getBytes("gbk"),"gbk");
//			
//			String unicode = new String(utf8.getBytes("GBK"),"GBK"); 
//			getEncoding(unicode);
//			System.out.println(unicode); 
//			
//			String gbk1=URLEncoder.encode(text,"GBK");
//			getEncoding(gbk1);
//			
//			String gbk = new String(unicode.getBytes("GBK"));  
    		


    	    String utf8 = new String(text.getBytes( "GBK"),"GBK");  
    	    System.out.println(utf8);  
    	    String unicode = new String(utf8.getBytes(),"UTF-8");   
    	    System.out.println(unicode);  
    	    String gbk = new String(unicode.getBytes("GBK"));  
    	      
    	    System.out.println(gbk);  
			getEncoding(gbk);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
	}
	
}


