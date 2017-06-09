package costumetrade.user.service;

import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpStore;
import costumetrade.user.query.StoreQuery;


public interface SpUserService {
	/**
	 * 小程序加载页面登录
	 * */
	public ScWeChat login(String openId);
	/**
	 * 保存个人中心信息
	 * */
	public int saveUserOrStore(SpStore spStore);
	/**
	 * 加载页面
	 * */
	public StoreQuery getStores(StoreQuery query);
}
