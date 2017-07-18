package costumetrade.user.service;

import java.util.List;

import costumetrade.order.domain.ScStoreAddr;
import costumetrade.order.domain.SpProduct;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpStore;
import costumetrade.user.query.ScUserQuery;
import costumetrade.user.query.StoreQuery;


public interface SpUserService {
	/**
	 * 小程序加载页面登录
	 * */
	public ScWeChat login(String openId,String unionid);
	/**
	 * 保存个人中心信息
	 * */
	public int saveUserOrStore(SpStore spStore);
	/**
	 * 加载页面
	 * */
	public StoreQuery getStores(StoreQuery query);
	/**
	 * 获取地址
	 * */
	public List<ScStoreAddr> getAddressList(String  openid);
	/**
	 * 保存收货地址
	 * */
	public int saveAddress(ScStoreAddr addr);
	
	public SpStore getStoreImage(SpStore spStore , List<SpProduct> products);
	
	public ScWeChat  getUnionId(String encryptedData,String iv,String sessionKey);
	
	public ScUserQuery getScUser(ScWeChat chat);
}
