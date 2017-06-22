package costumetrade.order.service;

import java.util.List;

import costumetrade.order.domain.SpPSize;


public interface SpPSizeService {
	/**
	 * 获取所有尺码
	 * 
	 * */
	public List<SpPSize> getSpPSizes(int storeId,String productId);
	/**
	 * 保存尺码信息
	 * 
	 * */
	public int saveSpPSize(SpPSize spPSize);
	
	/**
	 * 删除尺码信息
	 * 
	 * */
	public int deleteSpPSize(List<Integer> ids);
}
