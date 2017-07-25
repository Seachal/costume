package costumetrade.user.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import costumetrade.common.service.GeneratorBaseTable;
import costumetrade.common.util.StringUtil;
import costumetrade.order.domain.SpPBrand;
import costumetrade.order.domain.SpPCate;
import costumetrade.order.mapper.SpPBrandMapper;
import costumetrade.order.mapper.SpPCateMapper;
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
	@Autowired
	private SpPBrandMapper spPBrandMapper;
	@Autowired
	private SpPCateMapper spPCateMapper;
	@Autowired 
	private WeChatService weChatService;
	@Override
	public List<SpStore> getChainStore(String storeId) {
		SpStore store = new SpStore();
		store.setParentid(storeId);
		List<SpStore> stores = spStoreMapper.selectStores(store, null);
		return stores;
	}
	@Override
	public String saveChainStore(SpStore spStore) {
		List<SpStore> stores = null;
		int update = 0;
		if(StringUtil.isBlank(spStore.getId())){//新增保存
			stores = spStoreMapper.selectStores(spStore, null); 
			if(stores !=null && stores.size()>0){
				update = 2;//该分店已经存在
			}else{
				String storeId = new Date().getTime()+"";
				spStore.setId(storeId);
				update = spStoreMapper.insertSelective(spStore);
				//初始化高级设置数据
		    	//GeneratorBaseTable.generatorTable(spStore.getId()+"");
		    	if(StringUtil.isNotBlank(spStore.getId())&&update>0){
		    		SpPBrand spPBrand = new SpPBrand();
		    		spPBrand.setStoreId(spStore.getId());
		    		spPBrand.setBrandname(spStore.getName());
		    		spPBrand.setStatus(0);
		    	
					//保存品牌
		    		spPBrandMapper.insert(spPBrand ) ;
		    		
		    		SpPCate spPCate = new SpPCate();
		    		spPCate.setStoreId(spStore.getId());
		    		spPCate.setCatename("服装");
		    		spPCate.setStatus(0);
					//保存种类
		    		spPCateMapper.insert(spPCate ) ;
		    		//保存高级设置中的客户类型
		    		insertCustPrice(spStore.getId());
		    		//保存数据字段信息
		    		insertDictionarys(spStore.getId());
		    	}
			}
		}else{
			update = spStoreMapper.updateByPrimaryKeySelective(spStore);
		}
		
		return spStore.getId();
	}
	@Override
	public SpStore getStore(String storeId) {
		return spStoreMapper.selectByPrimaryKey(storeId);
	}
	@Override
	public int deleteChainStore(String storeId) {
		return spStoreMapper.deleteByPrimaryKey(storeId);
	}
	@Override
	public String insertStore(String openid,String name,String image) {
		SpStore store = new SpStore();
		String  userInfo=null;
		try {
			//userInfo =WeChatService.getWeChatUserInfo(openid);
			//if(userInfo !=null){
//				JSONObject json = JSON.parseObject(userInfo);
//		    	String nickName = json.getString("nickname"); 
//		    	String headimgurl = json.getString("headimgurl");
//		    	
		    	ScWeChat record = new ScWeChat();
	    		record = scWeChatMapper.selectByOpenId(openid);
	    		
	    		if(record !=null&&StringUtil.isBlank(record.getStoreid())){
			    	store.setId(record.getUserid());
			    	store.setCreateTime(new Date());
			    	store.setName(name);
			    	store.setStorephoto(image);
			    	store.setStatus(0);
			    	
			    	String qccode = weChatService.getLimitTwoCode(record.getUserid());
			    	store.setWeUrl(qccode);
			    	spStoreMapper.insertSelective(store);
	    		}
//		    	//初始化高级设置数据
//		    	GeneratorBaseTable.generatorTable(store.getId()+"");
		    	if(StringUtil.isNotBlank(store.getId())){
		    		if(record !=null){
		    			record.setUserid("");
		    			record.setStoreid(store.getId());
		    			scWeChatMapper.updateByPrimaryKeySelective(record);
		    		}
		    		SpPBrand spPBrand = new SpPBrand();
		    		spPBrand.setStoreId(store.getId());
		    		spPBrand.setBrandname(store.getName());
		    		spPBrand.setStatus(0);
		    	
					//保存品牌
		    		spPBrandMapper.insert(spPBrand ) ;
		    		
		    		SpPCate spPCate = new SpPCate();
		    		spPCate.setStoreId(store.getId());
		    		spPCate.setCatename("服装");
		    		spPCate.setStatus(0);
					//保存种类
		    		spPCateMapper.insert(spPCate ) ;
		    		//保存高级设置中的客户类型
		    		insertCustPrice(store.getId());
		    		//保存数据字段信息
		    		insertDictionarys(store.getId());
		    	}
		    	
			//}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return store.getId();
	}
	// 默认取 店铺1的初始数据
	public int insertDictionarys(String storeId){
		List<SsDataDictionary> dicts = new ArrayList<SsDataDictionary>();
		List<SsDataDictionary> dictList = new ArrayList<SsDataDictionary>();
		SsDataDictionary dict = new SsDataDictionary();
		dict.setStoreId(-1+"");
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
	public int insertCustPrice(String storeId){
		List<SpCustProdPrice> prices = new ArrayList<SpCustProdPrice>();
		List<SpCustProdPrice> priceList = new ArrayList<SpCustProdPrice>();
		SpCustProdPrice price = new SpCustProdPrice();
		price.setStoreid(-1+"");
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
