package costumetrade.user.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import costumetrade.common.service.GeneratorBaseTable;
import costumetrade.order.service.WeChatService;
import costumetrade.user.domain.PriceJson;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpCustProdPrice;
import costumetrade.user.domain.SpPrivilege;
import costumetrade.user.domain.SpStore;
import costumetrade.user.domain.SpUser;
import costumetrade.user.domain.SsDataDictionary;
import costumetrade.user.mapper.ScWeChatMapper;
import costumetrade.user.mapper.SpCustProdPriceMapper;
import costumetrade.user.mapper.SpPrivilegeMapper;
import costumetrade.user.mapper.SpStoreMapper;
import costumetrade.user.mapper.SpUserMapper;
import costumetrade.user.mapper.SsDataDictionaryMapper;
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
	@Autowired
	private SpCustProdPriceMapper spCustProdPriceMapper;
	@Autowired
	private SpPrivilegeMapper spPrivilegeMapper;
	@Autowired
	private SsDataDictionaryMapper ssDataDictionaryMapper;
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
		    	GeneratorBaseTable.generatorTable(store.getId()+"");
		    	if(store.getId()!=null){
		    		//保存高级设置中的客户类型
		    		insertCustPrice(store.getId());
		    		//保存数据字段信息
		    		insertDictionarys(store.getId());
		    	}
		    	return store.getId();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	// 默认取 店铺1的初始数据
	public int insertDictionarys(Integer storeId){
		List<SsDataDictionary> dicts = new ArrayList<SsDataDictionary>();
		List<SsDataDictionary> dictList = new ArrayList<SsDataDictionary>();
		SsDataDictionary dict = new SsDataDictionary();
		dict.setStoreId(-1);
		dicts = ssDataDictionaryMapper.select(dict);
		if(dicts.size()>0){
			for(SsDataDictionary d:dicts){
				d.setStoreId(storeId);
				dictList.add(d);
			}
		}
		int save =0;
		if(dictList.size()>0){
			save = ssDataDictionaryMapper.insertDatas(dictList);
		}
		return save;
	}
	/**
	 * 插入客户种类，等级 毛利  折扣  默认取 店铺1的初始数据
	 * */
	public int insertCustPrice(Integer storeId){
		List<SpCustProdPrice> prices = new ArrayList<SpCustProdPrice>();
		List<SpCustProdPrice> priceList = new ArrayList<SpCustProdPrice>();
		SpCustProdPrice price = new SpCustProdPrice();
		price.setStoreid(-1);
		prices = spCustProdPriceMapper.select(price);
		if(prices.size()>0){
			for(SpCustProdPrice p : prices){
				p.setStoreid(storeId);
				priceList.add(p);
			}
		}
		int save = 0;
		if(priceList.size()>0){
			save = spCustProdPriceMapper.saveTypeOrGradeRates(priceList);
		}
		return save;
//		List<String> customType = new ArrayList<String>();//客户类型
//		customType.add("普通");
//		customType.add("金卡");
//		customType.add("银卡");
//		customType.add("白金");
//		customType.add("钻石");
//		
//		List<String> gradeType = new ArrayList<String>();//客户类型
//		gradeType.add("处理");
//		gradeType.add("活动");
//		gradeType.add("在销");
//		gradeType.add("新品");
//		gradeType.add("专供");
//		
//		List<PriceJson> custPriceJson = new ArrayList<PriceJson>();
//		for(String custom : customType){
//			PriceJson price = new PriceJson();
//			price.setName(custom);
//			price.setValue(new BigDecimal(100));
//			custPriceJson.add(price);
//		}
//		List<PriceJson> gradePriceJson = new ArrayList<PriceJson>();
//		for(String grade : gradeType){
//			PriceJson price = new PriceJson();
//			price.setName(grade);
//			price.setValue(new BigDecimal(100));
//			gradePriceJson.add(price);
//		}
//		for(int i= 0;i<customType.size();i++){
//			SpCustProdPrice price = new SpCustProdPrice();
//			price.setCreatetime(new Date());
//			price.setCustpricejson(JSONArray.toJSONString(custPriceJson));
//			price.setDiscpricejson(JSONArray.toJSONString(custPriceJson));
//			price.setCustTypeCode(i+"");
//			price.setCusttypename(customType.get(0));
//			price.setType(1+"");
//			price.setProdgrade(i+"");
//			prices.add(price);
//		}
//		
//		for(int i= 0;i<gradeType.size();i++){
//			SpCustProdPrice price = new SpCustProdPrice();
//			price.setCreatetime(new Date());
//			price.setCustpricejson(JSONArray.toJSONString(gradePriceJson));
//			price.setDiscpricejson(JSONArray.toJSONString(gradePriceJson));
//			price.setCustTypeCode(i+"");
//			price.setCusttypename(gradeType.get(0));
//			price.setType(2+"");
//			price.setProdgrade(i+"");
//			prices.add(price);
//		}
		
		
	}
	
}
