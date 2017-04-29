package costumetrade.order.service;

import java.util.List;

import costumetrade.order.domain.SpPSize;
import costumetrade.order.domain.SpPSizeKey;


public interface SpPSizeService {
	/**
	 * 获取所有尺码
	 * 
	 * */
	public List<SpPSize> getSpPSizes(int cropId);
	/**
	 * 保存尺码信息
	 * 
	 * */
	public int saveSpPSize(SpPSize spPSize);
	
	/**
	 * 删除尺码信息
	 * 
	 * */
	public int deleteSpPSize(int id);
}
