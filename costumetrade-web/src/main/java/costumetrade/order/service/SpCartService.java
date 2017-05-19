package costumetrade.order.service;

import java.util.List;

import costumetrade.order.domain.SpCart;


public interface SpCartService {
	/**
	 * 获取所有尺码
	 * 
	 * */
	public List<SpCart> getSpCarts(int cropId);
	/**
	 * 保存尺码信息
	 * 
	 * */
	public int saveSpCart(SpCart spCart);
	
	/**
	 * 删除尺码信息
	 * 
	 * */
	public int deleteSpCart(int id);
}
