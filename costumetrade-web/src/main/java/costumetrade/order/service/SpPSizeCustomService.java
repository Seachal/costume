package costumetrade.order.service;

import java.util.List;

import costumetrade.order.domain.SpPSizeCustom;


public interface SpPSizeCustomService {
	/**
	 * 获取所有尺码组
	 * 
	 * */
	public List<SpPSizeCustom> getSpPSizeCustoms(String cropId);
	/**
	 * 保存尺码组信息
	 * 
	 * */
	public int saveSpPSizeCustom(SpPSizeCustom spPSizeCustom);
	
	/**
	 * 删除尺码组信息
	 * 
	 * */
	public int deleteSpPSizeCustom(List<Integer> ids);
}
