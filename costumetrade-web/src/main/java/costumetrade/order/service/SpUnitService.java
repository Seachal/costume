package costumetrade.order.service;

import java.util.List;

import costumetrade.order.domain.SpUnit;

public interface SpUnitService {
	
	/**
	 * 获取所有单位
	 * 
	 * */
	public List<SpUnit> getSpUnits(SpUnit spUnit);
	/**
	 * 保存单位
	 * 
	 * */
	public int saveSpUnit(SpUnit spUnit);
	
	/**
	 * 删除单位
	 * 
	 * */
	public int deleteSpUnit(List<Integer> ids);
}
