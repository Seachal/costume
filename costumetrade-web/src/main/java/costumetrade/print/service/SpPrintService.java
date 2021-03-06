package costumetrade.print.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import costumetrade.user.domain.ScPrinterInfo;


public interface SpPrintService {

	public String gbkImage(String storeId);
	
	public String gbkText(String text);
	
	public String getUserIdPrinter(ScPrinterInfo info);
}
