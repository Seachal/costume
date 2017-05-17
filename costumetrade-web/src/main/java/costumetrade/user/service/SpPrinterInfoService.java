package costumetrade.user.service;

import costumetrade.user.domain.ScPrinterInfo;




public interface SpPrinterInfoService {
	/*
	 * 获取该分店下的打印机
	 * 
	 * */
	public ScPrinterInfo getSpPrinterInfos(Integer storeId);
	/**
	 * 保存打印机信息
	 * 
	 * */
	public int saveSpPrinterInfo(ScPrinterInfo spPrinterInfo);

}
