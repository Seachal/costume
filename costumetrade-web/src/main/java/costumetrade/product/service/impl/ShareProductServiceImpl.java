package costumetrade.product.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;

import costumetrade.common.page.Page;
import costumetrade.common.util.StringUtil;
import costumetrade.order.domain.SpClient;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.mapper.SpClientMapper;
import costumetrade.order.mapper.SpProductMapper;
import costumetrade.order.query.ProductQuery;
import costumetrade.order.service.SpClientService;
import costumetrade.order.service.SpProductService;
import costumetrade.product.domain.ScPromotionalProduct;
import costumetrade.product.mapper.ScPromotionalProductMapper;
import costumetrade.product.mapper.ScUpgradeProductMapper;
import costumetrade.product.service.ShareProductService;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpStore;
import costumetrade.user.mapper.ScWeChatMapper;
import costumetrade.user.mapper.SpStoreMapper;

@Transactional
@Service
public class ShareProductServiceImpl implements ShareProductService {
	@Autowired
	private SpClientService spClientService;
	@Autowired
	private ScUpgradeProductMapper upgradeProductMapper;
	@Autowired
	private ScPromotionalProductMapper scPromotionalProductMapper;
	@Autowired
	private SpStoreMapper spStoreMapper;
	@Autowired 
	private SpProductMapper spProductMapper;
	@Autowired
	private SpClientMapper spClientMapper;
	@Autowired 
	private ScWeChatMapper scWeChatMapper;
	@Autowired
	private SpProductService spProductService;
	@Override
	public List<ScPromotionalProduct> getAllPromotionalProduct(String storeId,String openid,Integer pageNum) {
	
		ScWeChat wechat = new ScWeChat();
		if(StringUtil.isNotBlank(openid)){
			wechat = scWeChatMapper.selectByOpenId(openid);
		}
		ScPromotionalProduct product = new ScPromotionalProduct();
		product.setPromoterStoreid(storeId);
		if(wechat!=null){
			if(StringUtil.isNotBlank(wechat.getStoreid())){
				product.setRecommendedId(wechat.getStoreid());
			}else{
				product.setRecommendedId(wechat.getUserid());
			}
		}
		Page page = new Page();
		page.setPageNum(pageNum);
		List<ScPromotionalProduct> products =  scPromotionalProductMapper.getAllPromotionals(product,page);
		List<ScPromotionalProduct> result = new ArrayList<ScPromotionalProduct>();
		
		Integer custTypeCode = 1;
		SpClient client = new SpClient();
		client.setOpenid(openid);
		client.setType(1+"");
		client.setStoreId(storeId);
		//查找当前客户所在等级
		List<SpClient> clients =  spClientMapper.select(client,null );
		if(clients!=null&&clients.size()>0){
			client = clients.get(0);
			custTypeCode = Integer.parseInt(client.getCate());
		}
		if(products!=null&&products.size()>0){
			for(ScPromotionalProduct pro : products){
				ProductQuery productQuery = new ProductQuery();
				if(StringUtil.isNotBlank(pro.getProductIds())){
					String demoArray[] = pro.getProductIds().split(",");
					List<String> demoList = Arrays.asList(demoArray);
					productQuery.setIdArray(demoList);
				}
				productQuery.setStoreId(storeId);
				productQuery.setCustTypeCode(custTypeCode);
				//获取货品
				List<Map<String, Object>> maps = spProductMapper.selectProducts(productQuery ,null);
				if(maps!=null && maps.size()>0){
					List<Map<String, Object>> maps1 = new ArrayList<Map<String,Object>>();
					for(int i=0;i< maps.size();i++){
						
						Map<String,Object> map = maps.get(i);
						
						ProductQuery p = new ProductQuery();
						p.setThirdPrice(new BigDecimal(map.get("thirdPrice").toString()));
						p.setFifthPrice(new BigDecimal(map.get("fifthPrice").toString()));
						p.setSecondPrice(new BigDecimal(map.get("secondPrice").toString()));
						p.setFourthPrice(new BigDecimal(map.get("fourthPrice").toString()));
						p.setFirsthPrice(new BigDecimal(map.get("firsthPrice").toString()));
						if(custTypeCode >0){
							BigDecimal salePrice = spProductService.setPrice(p, custTypeCode);
							map.put("salePrice", salePrice.toString());
						}
						maps1.add(map);
					}
					JSONArray jsonArray = (JSONArray) JSONArray.toJSON(maps1);
					List<ProductQuery> productList = JSONArray.parseArray(jsonArray.toJSONString(), ProductQuery.class);
				
					pro.setProducts(productList);
				}
				result.add(pro);
			}
		}
		return result;
	}
	@Override
	public ScPromotionalProduct getPromotionalProduct(String id) {
		ScPromotionalProduct pro = new ScPromotionalProduct();
		if(StringUtil.isNotBlank(id)){
			pro = scPromotionalProductMapper.selectByPrimaryKey(id);
		}
		return pro;
	}
	@Override
	public int saveShareInfoToCustomers(ScPromotionalProduct product) {
		int save = 0;
		if(!product.getCheckAllTag()&&(product.getClientIds()==null||product.getClientIds().size()<0)){
			return -1;
		}
		//获取需分享的客户
		List<String> clientIds = new ArrayList<String>();
		if(product.getCheckAllTag()){
			SpClient spClient = new SpClient();
			spClient.setCheckAllTag(product.getCheckAllTag());
			spClient.setIdArray(product.getClientIds());
			spClient.setStoreId(product.getPromoterStoreid());
			spClient.setType(1+"");
			clientIds = spClientService.getIds(spClient );
		}else{
			clientIds = product.getClientIds();
		}
		if(product.getProductIdArray()!=null&&product.getProductIdArray().size()>0){
			product.setProductIds(StringUtils.join(product.getProductIdArray().toArray(),","));
		}
		SpStore store = spStoreMapper.selectByPrimaryKey(product.getPromoterStoreid());
		if(store !=null && StringUtil.isNotBlank(store.getId())){
			product.setPromoterPhoto(store.getStorephoto());
			product.setPromoterName(store.getName());
			product.setPromoterAddress(store.getAddress());
		}
		product.setShareType(2+"");
		product.setCreateTime(new Date());
		
		ProductQuery productQuery = new ProductQuery();
		productQuery.setIdArray(product.getProductIdArray());
		productQuery.setStoreId(product.getPromoterStoreid());
		//获取默认图片
		List<Map<String, Object>> maps = spProductMapper.selectProducts(productQuery ,null);
		if(maps!=null && maps.size()>0){
			List<String> images = new ArrayList<String>();
			JSONArray jsonArray = (JSONArray) JSONArray.toJSON(maps);
			List<ProductQuery> products = JSONArray.parseArray(jsonArray.toJSONString(), ProductQuery.class);
			if(products!=null&&products.size()>0){
				for(int i=0 ;i < products.size() ; i++){
					if(i<8){
						images.add(products.get(i).getImage());
					}
				}
			}
			product.setProductImages(StringUtils.join(images.toArray(),","));
		}
		
		List<ScPromotionalProduct> products = new ArrayList<ScPromotionalProduct>();
		if(clientIds!=null&&clientIds.size()>0){
			int count =0;
			for(String clientId : clientIds){
				SpClient client = spClientMapper.selectByPrimaryKey(clientId);
				ScWeChat wechat = new ScWeChat();
				if(client!=null){
					wechat = scWeChatMapper.selectByOpenId(client.getOpenid());
				}
				ScPromotionalProduct pro = new ScPromotionalProduct();
				pro.setCreateBy(product.getCreateBy());
				pro.setProductImages(product.getProductImages());
				pro.setProductIds(product.getProductIds());
				pro.setNoReadCount(product.getNoReadCount());
				pro.setPromoterAddress(product.getPromoterAddress());
				pro.setPromoterName(product.getPromoterName());
				pro.setPromoterPhoto(product.getPromoterPhoto());
				pro.setCreateTime(new Date());
				pro.setPromoterStoreid(product.getPromoterStoreid());
				pro.setShareType(product.getShareType());
				pro.setTitle(product.getTitle());
				if(wechat!=null){
					if(StringUtil.isNotBlank(wechat.getStoreid())){
						pro.setRecommendedId(wechat.getStoreid());
					}else{
						pro.setRecommendedId(wechat.getUserid());
					}
					
				}
//				UUID uuid=UUID.randomUUID();
//		        String str = uuid.toString().replaceAll("\\-", ""); 
				pro.setId(new Date().getTime()+""+(++count));
				products.add(pro);
			}
		}
		if(products!=null&&products.size()>0){
			save = scPromotionalProductMapper.inserts(products);
		}
		return save;
	}
	@Override
	public List<ScPromotionalProduct> getGroupPromotionalProduct(String openid,
			Integer pageNum) {
		
		ScWeChat wechat = new ScWeChat();
		if(StringUtil.isNotBlank(openid)){
			wechat = scWeChatMapper.selectByOpenId(openid);
		}
		
		ScPromotionalProduct product = new ScPromotionalProduct();
		if(wechat!=null){
			if(StringUtil.isNotBlank(wechat.getStoreid())){
				product.setRecommendedId(wechat.getStoreid());
			}else{
				product.setRecommendedId(wechat.getUserid());
			}
		}
		List<ScPromotionalProduct> products = new ArrayList<ScPromotionalProduct>();
		if(StringUtil.isNotBlank(product.getRecommendedId())){
			Page page = new Page();
			page.setPageNum(pageNum);
			products = scPromotionalProductMapper.getGroupPromotionals(product , page );
		}
		
		return products;
	}
	

}
