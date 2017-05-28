package costumetrade.user.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpStore;
import costumetrade.user.domain.SpUser;
import costumetrade.user.mapper.ScWeChatMapper;
import costumetrade.user.mapper.SpStoreMapper;
import costumetrade.user.mapper.SpUserMapper;
import costumetrade.user.service.SpUserService;

@Transactional
@Service
public class SpUserServiceImpl implements SpUserService{
	@Autowired 
	private ScWeChatMapper scWeChatMapper;
	@Autowired 
	private SpUserMapper spUserMapper;
	@Autowired
	private SpStoreMapper spStoreMapper;
	
	@Override
	public ScWeChat login(String openId) {
		ScWeChat wechat = scWeChatMapper.selectByOpenId(openId);
		//判断是否是平台的新用户，是新用户保存信息，不是，返回用户信息
		Integer userid =0;
		int weId = 0;
		SpUser user = new SpUser();
		if(wechat == null ){
			user.setCreateTime(new Date());
			userid = spUserMapper.insert(user);
		}
		if(userid > 0){
			wechat = new ScWeChat();
			wechat.setUserid(user.getId());
			wechat.setOpenid(openId);
			wechat.setCreateby(userid+"");
			wechat.setCreatetime(new Date());
			weId = scWeChatMapper.insertSelective(wechat);
			wechat.setId(weId);
		}
	
		return wechat;
	}
	@Override
	public int saveUserOrStore(SpStore spStore) {
		if(spStore.getStoreId() != null){
			return spStoreMapper.updateByPrimaryKeySelective(spStore);			
		}else if(spStore.getUserId() != null){
				SpUser spUser = new SpUser();
				spUser.setId(spStore.getUserId());
				spUser.setAddress(spStore.getAddress());
				spUser.setBirthday(spStore.getBirthday());
				spUser.setCphone(spStore.getCphone());
				spUser.setCreateBy(spStore.getUserId()+"");
				spUser.setCreateTime(new Date());
				spUser.setName(spStore.getName());
				spUser.setPhone(spStore.getPhone());
				spUser.setPhoto(spStore.getStorephoto());
				spUser.setRegion(spStore.getRegion());
				spUser.setRemark(spStore.getDescription());
				spUser.setWechatNo(spStore.getWechat());
				return spUserMapper.updateByPrimaryKeySelective(spUser);
		}
		
		return 0;
	}
	
}
