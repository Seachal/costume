package costumetrade.user.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	
	
}
