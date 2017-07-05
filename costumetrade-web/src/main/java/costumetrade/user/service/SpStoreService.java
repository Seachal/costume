package costumetrade.user.service;

import java.util.List;

import costumetrade.user.domain.SpStore;


public interface SpStoreService {
	/**
	 * 查询分店
	 * */
	public List<SpStore> getChainStore(String storeId);
	/**
	 * 保存分店
	 * */
	public int saveChainStore(SpStore spStore);
	/**
	 * 查询当前店铺信息
	 * */
	public SpStore getStore(String storeId);
	/**
	 * 删除店铺信息
	 * */
	public int deleteChainStore(String storeId);
	
	
	public String insertStore(String openid,String name,String image);
}
