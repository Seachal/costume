package costumetrade.print.service.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.poi.sl.draw.BitmapImageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import sun.misc.BASE64Encoder;
import costumetrade.common.util.Base64Util;
import costumetrade.print.service.SpPrintService;
import costumetrade.user.domain.ScPrinterInfo;
import costumetrade.user.mapper.ScPrinterInfoMapper;

@Transactional
@Service
public class SpPrintServiceImpl implements SpPrintService{
	@Autowired
	private ScPrinterInfoMapper scPrinterInfoMapper;
	@Override
	public String gbkImage(CommonsMultipartFile image) {
		String data = null;
		
		try {
			
			InputStream is=image.getInputStream();
			BufferedImage bi=ImageIO.read(is);
			Image im=(Image)bi; 
			
//			BitmapImageRenderer bit = new BitmapImageRenderer();
//			bit.
			BASE64Encoder encoder = new BASE64Encoder();  
			// 通过base64来转化图片
			data = encoder.encode(image.getBytes());
			data = new String(data.getBytes(), "GBK");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public String gbkText(String text) {
//		BASE64Encoder encoder = new BASE64Encoder();  
		// 通过base64来转化图片
		//String data = encoder.encode(text.getBytes());
		String data = null;
		try {
//			byte[] binBuffer = text.getBytes("GBK");
			data = Base64Util.getImageBase64(text);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
  
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
	
}


