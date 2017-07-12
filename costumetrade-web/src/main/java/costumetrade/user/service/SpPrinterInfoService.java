package costumetrade.user.service;

import costumetrade.user.domain.ScPrinterInfo;




public interface SpPrinterInfoService {
	/*
	 * 获取该分店下的打印机
	 * 
	 * */
	public ScPrinterInfo getSpPrinterInfos(String storeId);
	/**
	 * 保存打印机信息
	 * 
	 * */
	public ScPrinterInfo saveSpPrinterInfo(ScPrinterInfo spPrinterInfo);

}
