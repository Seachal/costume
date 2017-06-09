package costumetrade.user.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import costumetrade.order.service.WeChatService;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpStore;
import costumetrade.user.domain.SpUser;
import costumetrade.user.mapper.ScWeChatMapper;
import costumetrade.user.mapper.SpStoreMapper;
import costumetrade.user.mapper.SpUserMapper;
import costumetrade.user.service.SpStoreService;

@Transactional
@Service
public class SpStoreServiceImpl implements SpStoreService{
	@Autowired 
	private ScWeChatMapper scWeChatMapper;
	@Autowired 
	private SpUserMapper spUserMapper;
	@Autowired
	private SpStoreMapper spStoreMapper;
	@Autowired
	private WeChatService WeChatService;
	@Override
	public List<SpStore> getChainStore(Integer storeId) {
		SpStore store = new SpStore();
		store.setParentid(storeId);
		List<SpStore> stores = spStoreMapper.selectStores(store, null);
		return stores;
	}
	@Override
	public int saveChainStore(SpStore spStore) {
		List<SpStore> stores = null;
		int update = 0;
		if(spStore.getId()==null){//新增保存
			stores = spStoreMapper.selectStores(spStore, null); 
		}else{
			update = spStoreMapper.updateByPrimaryKeySelective(spStore);
		}
		if(stores !=null && stores.size()>0){
			update = 2;//该分店已经存在
		}else{
			update = spStoreMapper.insertSelective(spStore);
		}
		return update;
	}
	@Override
	public SpStore getStore(Integer storeId) {
		return spStoreMapper.selectByPrimaryKey(storeId);
	}
	@Override
	public int deleteChainStore(Integer storeId) {
		return spStoreMapper.deleteByPrimaryKey(storeId);
	}
	@Override
	public int insertStore(String openid) {
		String  userInfo=null;
		try {
			userInfo =WeChatService.getWeChatUserInfo(openid);
			if(userInfo !=null){
				JSONObject json = JSON.parseObject(userInfo);
		    	String nickName = json.getString("nickname"); 
		    	String headimgurl = json.getString("headimgurl");
		    	SpStore store = new SpStore();
		    	store.setCreateTime(new Date());
		    	store.setName(nickName);
		    	store.setStorephoto(headimgurl);
		    	store.setStatus(0);
		    	spStoreMapper.insertSelective(store);
		    	
		    	//初始化高级设置数据
		    	if(store.getId()!=null){
		    		
		    	}
		    	return store.getId();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
}
