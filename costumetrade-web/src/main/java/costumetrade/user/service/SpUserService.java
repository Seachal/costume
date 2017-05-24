package costumetrade.user.service;

import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpStore;


public interface SpUserService {
	/**
	 * 小程序加载页面登录
	 * */
	public ScWeChat login(String openId);
	/**
	 * 保存个人中心信息
	 * */
	public Object saveUserOrStore(SpStore spStore);
}
