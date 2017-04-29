package costumetrade.order.service;

import java.util.List;

import costumetrade.order.domain.SpPBrand;


public interface SpPBrandService {
	/**
	 * 获取所有品牌
	 * 
	 * */
	public List<SpPBrand> getSpPBrands(int cropId);
	/**
	 * 保存品牌信息
	 * 
	 * */
	public int saveSpPBrand(SpPBrand spPBrand);
	
	/**
	 * 删除品牌信息
	 * 
	 * */
	public int deleteSpPBrand(int id);
}
