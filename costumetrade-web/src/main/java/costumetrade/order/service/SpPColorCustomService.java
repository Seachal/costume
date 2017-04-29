package costumetrade.order.service;

import java.util.List;

import costumetrade.order.domain.SpPColorCustom;


public interface SpPColorCustomService {
	/*
	 * 获取所有颜色组
	 * 
	 * */
	public List<SpPColorCustom> getSpPColorCustoms(int cropId);
	/**
	 * 保存颜色组信息
	 * 
	 * */
	public int saveSpPColorCustom(SpPColorCustom spPColorCustom);
	
	/**
	 * 删除颜色组信息
	 * 
	 * */
	public int deleteSpPColorCustom(int id);
}
