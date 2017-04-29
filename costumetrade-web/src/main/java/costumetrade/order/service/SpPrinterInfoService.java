package costumetrade.order.service;

import costumetrade.order.domain.SpPrinterInfo;


public interface SpPrinterInfoService {
	/*
	 * 获取该分店下的打印机
	 * 
	 * */
	public SpPrinterInfo getSpPrinterInfos(int id);
	/**
	 * 保存打印机信息
	 * 
	 * */
	public int saveSpPrinterInfo(SpPrinterInfo spPrinterInfo);
	
	/**
	 * 删除打印机信息
	 * 
	 * */
	public int deleteSpPrinterInfo(int id);
}
