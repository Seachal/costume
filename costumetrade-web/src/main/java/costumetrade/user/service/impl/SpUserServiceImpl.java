package costumetrade.user.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import costumetrade.common.util.AES;
import costumetrade.common.util.StringUtil;
import costumetrade.common.util.WxPKCS7Encoder;
import costumetrade.order.domain.ScFocusShop;
import costumetrade.order.domain.ScStoreAddr;
import costumetrade.order.domain.SpClient;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.mapper.ScFocusShopMapper;
import costumetrade.order.mapper.ScStoreAddrMapper;
import costumetrade.order.mapper.SpClientMapper;
import costumetrade.order.mapper.SpProductMapper;
import costumetrade.order.query.OrderCountQuery;
import costumetrade.order.service.SpOrderService;
import costumetrade.order.service.WeChatService;
import costumetrade.product.service.ShareProductService;
import costumetrade.report.domain.ProductReportQuery;
import costumetrade.report.domain.ReportQuery;
import costumetrade.report.service.SpReportService;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpEmployee;
import costumetrade.user.domain.SpStore;
import costumetrade.user.domain.SpUser;
import costumetrade.user.domain.SsDataDictionary;
import costumetrade.user.mapper.ScWeChatMapper;
import costumetrade.user.mapper.SpEmployeeMapper;
import costumetrade.user.mapper.SpStoreMapper;
import costumetrade.user.mapper.SpUserMapper;
import costumetrade.user.mapper.SsDataDictionaryMapper;
import costumetrade.user.query.ScUserQuery;
import costumetrade.user.query.StoreQuery;
import costumetrade.user.service.SpEmployeeService;
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
	@Autowired
	private SpEmployeeMapper spEmployeeMapper;
	@Autowired
	private ScFocusShopMapper scFocusShopMapper;
	@Autowired
	private SpProductMapper spProductMapper;
	@Autowired
	private SpOrderService spOrderService;
	@Autowired
	private WeChatService weChatService;
	@Autowired
	private ScStoreAddrMapper scStoreAddrMapper;
	@Autowired
	private SsDataDictionaryMapper ssDataDictionaryMapper;
	@Autowired
	private SpReportService spReportService;
	@Autowired
	private SpEmployeeService spEmployeeService;
	@Autowired
	private ShareProductService shareProductService;
	@Autowired
	private SpClientMapper spClientMapper;
	
	@Override
	public ScWeChat login(String openId,String unionid) {//openid 值和unionID 互换
		ScWeChat scWeChat =new ScWeChat();
		ScWeChat wechat = scWeChatMapper.selectByUnionid(unionid);
		//判断是否是平台的新用户，是新用户保存信息，不是，返回用户信息  
		Integer userid =0;
		int weId = 0;
		ScWeChat we = new ScWeChat();
		SpUser user = new SpUser();
		SpEmployee employee = new SpEmployee();
		employee.setOpenid(openId);
		SpEmployee semployee = spEmployeeMapper.selectByPrimaryKey(employee);//查询该用户是否是店员，是店员就绑定empid
		if(wechat == null ){
			we.setOpenid(openId);
			we.setUnionid(unionid);
			we.setCreatetime(new Date());
			if(semployee == null){//不存在店员，就保存新增用户信息
//				String userInfo;
//				try {
//					userInfo = weChatService.getWeChatUserInfo(unionid);
//					if(userInfo !=null){
//						JSONObject json = JSON.parseObject(userInfo);
//				    	String nickName = json.getString("nickname"); 
//				    	String headimgurl = json.getString("headimgurl");
//				    	user.setName(nickName);
//				    	user.setPhoto(headimgurl);
//				    }
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				String userId = new Date().getTime()+"";
				user.setId(userId);
				user.setCreateTime(new Date());
				userid = spUserMapper.insertSelective(user);
				we.setUserid(userId);
				we.setCreateby(userid+"");
			}else{//绑定员工号
				we.setEmpid(semployee.getId()+"");
				we.setStoreid(semployee.getStoreId());
			}
			weId = scWeChatMapper.insertSelective(we);
			scWeChat = we;
		}else {
			if(StringUtils.isBlank(wechat.getEmpid()+"")&&semployee !=null){//绑定员工号
				wechat.setEmpid(semployee.getId());
				wechat.setStoreid(semployee.getStoreId());
				wechat.setUserid("");
				scWeChatMapper.updateByPrimaryKeySelective(wechat);
			}
			scWeChat = wechat;
		}
		
		return scWeChat;
	}
	
	public ScWeChat bindEmployee(String openId){
		SpEmployee employee = new SpEmployee();
		employee.setOpenid(openId);
		SpEmployee semployee = spEmployeeMapper.selectByPrimaryKey(employee);//查询该用户是否是店员，是店员就绑定empid
		
		ScWeChat wechat = scWeChatMapper.selectByOpenId(openId);
		if(wechat!=null&&semployee!=null){
			if(wechat.getId()!=null&&StringUtil.isNotBlank(semployee.getId())){
				wechat.setEmpid(semployee.getId());
				wechat.setUserid("");
				wechat.setStoreid(semployee.getStoreId());
				scWeChatMapper.updateByPrimaryKeySelective(wechat);
			}
		}
		return wechat;
	}
	
	@Override
	public int saveUserOrStore(SpStore spStore) {
		int save = 0;
		//保存我的中心的信息时候，设置默认的地址
		ScStoreAddr addr = new ScStoreAddr();
		addr.setIsdefault(1);
		if(StringUtil.isNotBlank(spStore.getStoreId())){
			spStore.setId(spStore.getStoreId());
			addr.setUserid(spStore.getStoreId());
			save = spStoreMapper.updateByPrimaryKeySelective(spStore);			
		}else if(spStore.getUserId() != null){
			addr.setUserid(spStore.getUserId());
			
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
			save = spUserMapper.updateByPrimaryKeySelective(spUser);
			
		}
		
		if(save>0){
			String [] s = new String[3];
			if(StringUtil.isNotBlank(spStore.getRegion())){
				s = spStore.getRegion().split(",");
			}
			if(s.length>0){
				addr.setProvince(s[0]);
				addr.setCity(s[1]);
				addr.setDistrict(s[2]);
			}
			addr.setUserid(spStore.getStoreId());
			List<ScStoreAddr> addrs  = scStoreAddrMapper.selectAddr(addr);
			if(addrs!=null&&addrs.size()>0){
				addr = addrs.get(0);
				if(StringUtils.isNotBlank(spStore.getAddress())){
					addr.setAddress(spStore.getAddress());
				} 
				if(StringUtils.isNotBlank(spStore.getPhone())){
					addr.setPhone(spStore.getPhone());
				}
				if(StringUtils.isNotBlank(spStore.getName())){
					addr.setContact(spStore.getName());
				}
				scStoreAddrMapper.updateByPrimaryKeySelective(addr);
			}else{
				addr.setAddress(spStore.getAddress());
				addr.setPhone(spStore.getPhone());
				addr.setContact(spStore.getName());
				scStoreAddrMapper.insertSelective(addr);
			}
		}
		return save;
	}
	@Override
	public StoreQuery getStores(StoreQuery query) {
		/**
		 * 1、查询已关注的店铺
		 * 2、根据店铺信息查询推广的商品图片   如果只有一个货品推广 则都是这个货品的图片，如果三个推广就每个货品挑选一张图片显示，
		 * 3、统计订单数量，统计消息数量 统计，统计公告数量
		 * */
		ScFocusShop shop = new ScFocusShop();
		shop.setOpenid(query.getOpenid());
		List<ScFocusShop> shops = scFocusShopMapper.select(shop);
		
		List<SpStore> stores = new ArrayList<SpStore>();
		SpStore sp = new SpStore();
		if(StringUtil.isNotBlank(query.getStoreId())){
			sp =spStoreMapper.selectByPrimaryKey(query.getStoreId());//商城第一个店铺显示自己的店铺
			if(StringUtil.isNotBlank(sp.getId())){
				stores.add(sp);
			}
		}
		
		List<String> idArray = new ArrayList<String>();
		if(shops != null && shops.size() > 0){
			for(ScFocusShop s :shops){
				idArray.add(s.getShopid());
			}
			
			
		}
		SpStore store = new SpStore();
		if(idArray.size()>0){
			store.setIdArray(idArray);
		}
		List<SpStore> storeList  = null;
		StoreQuery resultQuery = new StoreQuery();
		if(store.getIdArray()==null||store.getIdArray().size()<=0){
			storeList = null;
		}else{
			storeList = spStoreMapper.selectStores(store, null);
		}
		
		if(storeList!=null && storeList.size()>0){
			for(SpStore spStore :storeList){
				stores.add(spStore);
			}
		}
		
		List<SpStore> storelist = new ArrayList<SpStore>();
		if(stores !=null && stores.size()>0 ){
			for(SpStore spStore :stores){
				SpStore sStore = new SpStore();
				sStore.setId(spStore.getId());
				sStore.setStorephoto(spStore.getStorephoto());
				sStore.setName(spStore.getName());
				sStore.setAddress(spStore.getAddress());
				
				//查询推广商品图片
				List<String> list = new ArrayList<String>();
				list.add("IMAGE");
				List<SsDataDictionary> dict = ssDataDictionaryMapper.selectDictionarys(list,spStore.getId());
				if(dict!=null&& dict.size()>0){
					SsDataDictionary dic = dict.get(0);
					String iamge = dic.getDictValue();
					sStore.setImages(Arrays.asList(iamge.split(",")));
				}

				storelist.add(sStore);
			}
		}
		resultQuery.setStoreList(storelist);
		resultQuery.setOpenid(query.getOpenid());
		//汇总订单数量
		OrderCountQuery countQuery = spOrderService.countOrders(query.getOpenid());
		resultQuery.setOrderCount(countQuery.getOrdersCount());
		return resultQuery;
	}
	/**
	 * 获取店铺对于的货品推广图片
	 * */
	public SpStore getStoreImage(SpStore spStore , List<SpProduct> products){
		int size =0;
		if(products !=null && products.size()>0){
			size= products.size();
			
			if(size ==1){//只有一个货品加入推广
				spStore.setImages(getImages(products.get(0)));
			}else if(size ==2){
				List<String> image = new ArrayList<String>();
				if(getImages(products.get(0)).size()>0){
					image.add(getImages(products.get(0)).get(0));
					
					List<String> imageList = getImages(products.get(1));
					if(imageList.size()>0){
						for(String i:imageList){
							image.add(i);
						}
					}
					spStore.setImages(image);
				}else{
					spStore.setImages(getImages(products.get(1)));
				}
				
			}else if(size >3){
				List<String> image = new ArrayList<String>();
				for(int i=0 ; i< 3;i++){
					SpProduct product = products.get(i);
					List<String> imageList = getImages(product);
					if(imageList !=null && imageList.size()>0){
						image.add(imageList.get(0));
					}
				}
				spStore.setImages(image);
			}
		}
		
		return spStore;
	}
	
	/**
	 * 
	 * 过滤图片为空的字段
	 * */
	public List<String> getImages(SpProduct product){
		List<String>  imageList = new ArrayList<String>();
		
		Map<String,String> images = new HashMap<String, String>();
		if(product.getImage() !=null){
			images.put(product.getImage(), product.getImage());
		}
		if(product.getImage1() !=null){
			images.put(product.getImage1(), product.getImage1());
		}
		if(product.getImage2() !=null){
			images.put(product.getImage2(), product.getImage2());
		}
		if(product.getImage3() !=null){
			images.put(product.getImage3(), product.getImage3());
		}
		if(product.getImage4() !=null){
			images.put(product.getImage4(), product.getImage4());
		}
		
		for(String key:images.keySet()){
			imageList.add(key);
		}
		return imageList;
	}
	@Override
	public List<ScStoreAddr> getAddressList(String openid) {
		ScStoreAddr addr = new ScStoreAddr();
		ScWeChat wechat = scWeChatMapper.selectByOpenId(openid);
		if(wechat!=null){
			if(wechat.getStoreid()!=null){
				addr.setUserid(wechat.getStoreid());
			}else{
				addr.setUserid(wechat.getUserid());
			}
		}
		List<ScStoreAddr> addrs = new ArrayList<ScStoreAddr>();
		if(addr.getUserid()!=null){
			addrs = scStoreAddrMapper.selectAddr(addr);
		}
		return addrs;
	}
	@Override
	public int saveAddress(ScStoreAddr addr) {
		
		ScWeChat wechat = scWeChatMapper.selectByOpenId(addr.getOpenid());
		if(wechat!=null){
			if(wechat.getStoreid()!=null){
				addr.setUserid(wechat.getStoreid());
			}else{
				addr.setUserid(wechat.getUserid());
			}
		}
		int save = 0;
		if(addr.getId()!=null){
			save =scStoreAddrMapper.updateByPrimaryKeySelective(addr);
		}else{
			save =scStoreAddrMapper.insertSelective(addr);
		}
		
		return save;
	}
	@Override
	public ScWeChat  getUnionId(String encryptedData,String iv,String sessionKey) {
		String unionId ="";
		ScWeChat wechat = new ScWeChat();
        try {  
            AES aes = new AES();  
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv)); 
            String u = new String(resultByte, "UTF-8");
            System.out.println(u+"\n");
            if(null != resultByte && resultByte.length > 0){  
                String userInfo = new String(WxPKCS7Encoder.decode(u.getBytes()));  

                JSONObject json = JSON.parseObject(userInfo);
                unionId = json.getString("unionId"); 
		    	String openId = json.getString("openId");
		    	
		    	String nickName = json.getString("nickName");
		    	String avatarUrl = json.getString("avatarUrl");
		    	//把openid字段保存unionID
		    	wechat = login(unionId,openId);
		    	
		    	wechat.setName(nickName);
		    	wechat.setPhoto(avatarUrl);
		    	if(wechat !=null && wechat.getId()!=null){
	    			//把昵称 头像保存到店铺or 用户
	    			if(StringUtil.isNotBlank(wechat.getStoreid())){
			    		SpStore store = new SpStore();
			    		store.setName(nickName);
			    		store.setId(wechat.getStoreid());
			    		store.setStorephoto(avatarUrl);
			    		spStoreMapper.updateByPrimaryKeySelective(store);
			    	}
	    			//把昵称 头像保存到店铺or 用户
	    			if(StringUtil.isNotBlank(wechat.getUserid())){
			    		SpUser user = new SpUser();
			    		user.setName(nickName);
			    		user.setId(wechat.getUserid());
			    		user.setPhoto(avatarUrl);;
			    		spUserMapper.updateByPrimaryKeySelective(user);
			    	}
	    		}
		    	
		    	
            }  
        } catch (Exception e) {  
            e.printStackTrace();
        }
        return wechat;
	}
	@Override
	public ScUserQuery getScUser(ScWeChat wechat) {
		ScUserQuery resultQuery = new ScUserQuery();
		StoreQuery query = new StoreQuery();
		if(wechat != null && StringUtil.isNotBlank(wechat.getOpenid())){
			query.setOpenid(wechat.getOpenid());
			query.setStoreId(wechat.getStoreid());
			if(StringUtil.isNotBlank(wechat.getStoreid())){
				if(StringUtil.isNotBlank(wechat.getEmpid())){
					resultQuery.setUserIdentity(3);//员工身份
					resultQuery.setEmpId(wechat.getEmpid());
				}else{
					resultQuery.setUserIdentity(1);//店家身份
				}
				resultQuery.setStoreId(wechat.getStoreid());
				SpStore store = spStoreMapper.selectByPrimaryKey(wechat.getStoreid());
				if(store!=null){
					resultQuery.setName(store.getName());
					resultQuery.setPhoto(store.getStorephoto());
				}
				ProductReportQuery report = new ProductReportQuery();
				report.setOpenid(wechat.getOpenid());
				ReportQuery r = spReportService.saleReport(report);
				resultQuery.setReport(r);
				
				SpClient client = new SpClient();
				client.setStoreId(wechat.getStoreid());
				client.setType(1+"");
				//查询粉丝数
				List<SpClient> clients =  spClientMapper.select(client , null);
				if(clients!=null){
					resultQuery.setFansCount(clients.size());
				}else{
					resultQuery.setFansCount(0);
				}
			}else{
				resultQuery.setUserIdentity(2);//普通消费者
				resultQuery.setUserid(wechat.getUserid());
				SpUser user = spUserMapper.selectByPrimaryKey(wechat.getUserid());
				if(user!=null){
					resultQuery.setName(user.getName());
					resultQuery.setPhoto(user.getPhoto());
				}
				resultQuery.setProducts(shareProductService.getGroupPromotionalProduct(wechat.getOpenid(), 1));
			}
			//如果普通消费者或者店铺名称为空，则默认是微信昵称
			if(StringUtil.isBlank(resultQuery.getName())){
				resultQuery.setName(wechat.getName());
			}
			//resultQuery.setProducts(shareProductService.getAllPromotionalProduct(wechat.getOpenid(), 1));
		}
	
		resultQuery.setQuery(query);
		SpEmployee employee = spEmployeeService.getEmployeePrivilege(wechat.getOpenid());
		SpEmployee e = new SpEmployee();
		if(employee!=null){
			e.setPrivilegeEmployees(employee.getPrivilegeEmployees());
			e.setZeroPrice(employee.getZeroPrice());
			e.setDiscount(employee.getDiscount());
			e.setModifyPrice(employee.getModifyPrice());
		}
		resultQuery.setEmployee(e);
		return resultQuery;
	}
	
}
