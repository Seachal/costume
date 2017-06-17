package costumetrade.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import me.chanjar.weixin.common.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aj.org.objectweb.asm.Type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import costumetrade.common.page.Page;
import costumetrade.common.util.StringUtil;
import costumetrade.order.domain.SpClient;
import costumetrade.order.domain.SpPBrand;
import costumetrade.order.domain.SpPCate;
import costumetrade.order.domain.SpPSizeCustom;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.domain.SpUnit;
import costumetrade.order.domain.SsPrice;
import costumetrade.order.domain.SsProductFile;
import costumetrade.order.domain.SsProductReview;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.domain.SsStock;
import costumetrade.order.mapper.SpClientMapper;
import costumetrade.order.mapper.SpPBrandMapper;
import costumetrade.order.mapper.SpPCateMapper;
import costumetrade.order.mapper.SpPColorCustomMapper;
import costumetrade.order.mapper.SpPSizeCustomMapper;
import costumetrade.order.mapper.SpProductMapper;
import costumetrade.order.mapper.SpUnitMapper;
import costumetrade.order.mapper.SsPriceMapper;
import costumetrade.order.mapper.SsProductFileMapper;
import costumetrade.order.mapper.SsProductReviewMapper;
import costumetrade.order.mapper.SsStoOrderMapper;
import costumetrade.order.mapper.SsStockMapper;
import costumetrade.order.query.ProductQuery;
import costumetrade.order.query.Rules;
import costumetrade.order.service.SpProductService;
import costumetrade.order.service.WeChatService;
import costumetrade.user.domain.PriceJson;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpCustProdPrice;
import costumetrade.user.domain.SpPrivilege;
import costumetrade.user.domain.SpStore;
import costumetrade.user.domain.SsDataDictionary;
import costumetrade.user.mapper.ScWeChatMapper;
import costumetrade.user.mapper.SpCustProdPriceMapper;
import costumetrade.user.mapper.SpPrivilegeMapper;
import costumetrade.user.mapper.SpStoreMapper;
import costumetrade.user.mapper.SsDataDictionaryMapper;
import costumetrade.user.service.SpUserService;

@Transactional
@Service
public class SpProductServiceImpl implements SpProductService{
	@Autowired
	private SpProductMapper spProductMapper;
	@Autowired
	private SpPBrandMapper spPBrandMapper;
	@Autowired
	private SpPSizeCustomMapper spPSizeCustomMapper;
	@Autowired
	private SpPColorCustomMapper spPColorCustomMapper;
	@Autowired
	private SpPCateMapper spPCateMapper;
	@Autowired
	private SsPriceMapper ssPriceMapper;
	@Autowired
	private SsProductFileMapper ssProductFileMapper;
	@Autowired
	private SsStockMapper ssStockMapper;
	@Autowired
	private SsDataDictionaryMapper ssDataDictionaryMapper;
	@Autowired
	private SpUnitMapper spUnitMapper;
	@Autowired
	private SsProductReviewMapper  ssProductReviewMapper;
	@Autowired
	private SpCustProdPriceMapper spCustProdPriceMapper;
	@Autowired
	private SpStoreMapper spStoreMapper;
	@Autowired
	private ScWeChatMapper scWeChatMapper;
	@Autowired
	private SpClientMapper spClientMapper;
	@Autowired
	private WeChatService weChatService;
	@Autowired
	private SpUserService spUserService;
	@Autowired
	private SsStoOrderMapper ssStoOrderMapper;
	@Autowired
	private SpPrivilegeMapper spPrivilegeMapper;
	
	@Override
	public List<ProductQuery> selectProducts(ProductQuery productQuery) {
		if(productQuery.getSort() != null){
			if("timeUpOp".equals(productQuery.getSort().getValue())){
				productQuery.setTimeUpOp(productQuery.getSort().getOp());
			}else if("priceOp".equals(productQuery.getSort().getValue())){
				productQuery.setPriceOp(productQuery.getSort().getOp());
			}else if("saleOp".equals(productQuery.getSort().getValue())){
				productQuery.setSaleOp(productQuery.getSort().getOp());
			}
		}
		List<Rules> rules = productQuery.getRules();
		if(rules != null && rules.size()>0){
			for(int i=0 ; i< rules.size() ;i++){
				if("productTypeArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					productQuery.setProductTypeArray(rules.get(i).getValue());
				}else if("productBrandArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					productQuery.setProductBrandArray(rules.get(i).getValue());
				}if("productSeasonArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					productQuery.setProductSeasonArray(rules.get(i).getValue());
				}if("status".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					productQuery.setStatus(Integer.parseInt(rules.get(i).getValue().get(0)));
				}
			}
		}
		
	
		/**
		 * 根据微信用户在这家店的客户类别，显示不同的销售价,显示对应的商品等级  针对商城商品列表 
		 * */
		
		SpClient client = null;
		List<String> grades = null;
		int custTypeCode =1;
		client = getClientByopenId(productQuery);
		if(client !=null&&client.getId()!=null){
			SpCustProdPrice spCustProdPrice = new SpCustProdPrice();
			spCustProdPrice.setCustTypeCode(client.getCate());
			spCustProdPrice.setStoreid(productQuery.getStoreId());
			List<SpCustProdPrice> custProdPrice = spCustProdPriceMapper.select(spCustProdPrice);
			
			if(custProdPrice.size() > 0 ){
				spCustProdPrice =custProdPrice.get(0);
				grades = setGrages(Integer.parseInt(spCustProdPrice.getProdgrade()));
				custTypeCode = Integer.parseInt(spCustProdPrice.getCustTypeCode());
			}
			productQuery.setGrades(grades);
			productQuery.setCustTypeCode(custTypeCode);
		}else{
			productQuery.setProductManagerQuery("1");//表示货品管理查询
			productQuery.setGrades(null);
			productQuery.setCustTypeCode(null);
		}
	
		Page page = new Page();
		page.setPageNum(productQuery.getPageNum());
		page.setPageSize(productQuery.getPageSize());
		
		List<Map<String,Object>> list = spProductMapper.selectProducts(productQuery,page);
		//商城商品列表设置销售价
		List<ProductQuery> productList = new ArrayList<ProductQuery>();
		
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> listMapFiled = new ArrayList<Map<String,Object>>();
		
		
		
		if(list!=null&&list.size() >0){
			int size = list.size();
			if(StringUtils.isNotBlank(productQuery.getFields())){
				String[] filed =productQuery.getFields().split(",");
				for(int i=0;i<size;i++){
					Map<String,Object> mapField = new HashMap<String, Object>();
					Map<String,Object> map = list.get(i);
					for(int j=0;j<filed.length;j++){
						
						ProductQuery product = new ProductQuery();
						product.setThirdPrice(new BigDecimal(map.get("thirdPrice").toString()));
						product.setFifthPrice(new BigDecimal(map.get("fifthPrice").toString()));
						product.setSecondPrice(new BigDecimal(map.get("secondPrice").toString()));
						product.setFourthPrice(new BigDecimal(map.get("fourthPrice").toString()));
						product.setFirsthPrice(new BigDecimal(map.get("firsthPrice").toString()));
						if(custTypeCode >0){
							BigDecimal salePrice = setPrice(product, custTypeCode);
							map.put("salePrice", salePrice.toString());
						}
						
						mapField.put(filed[j], map.get(filed[j]));
					}
					listMapFiled.add(mapField);
				}
				
			}else{
				for(int i=0;i<size;i++){
					Map<String,Object> map = list.get(i);
					ProductQuery product = new ProductQuery();
				
					product.setThirdPrice(new BigDecimal(map.get("thirdPrice").toString()));
					product.setFifthPrice(new BigDecimal(map.get("fifthPrice").toString()));
					product.setSecondPrice(new BigDecimal(map.get("secondPrice").toString()));
					product.setFourthPrice(new BigDecimal(map.get("fourthPrice").toString()));
					product.setFirsthPrice(new BigDecimal(map.get("firsthPrice").toString()));
					if(custTypeCode >0){
						BigDecimal salePrice = setPrice(product, custTypeCode);
						map.put("salePrice", salePrice.toString());
					}
					listMap.add(map);
				}
				
			}
			
		}
		JSONArray jsonArray = null;
		if(StringUtils.isNotBlank(productQuery.getFields())){
			jsonArray = (JSONArray) JSONArray.toJSON(listMapFiled);

		}else{
			jsonArray = (JSONArray) JSONArray.toJSON(listMap);
			
		}
		productList = JSONArray.parseArray(jsonArray.toJSONString(), ProductQuery.class);
	
		return productList;
	}

	/**
	 * 商城商品列表，设置客户可见商品等级控制
	 * */
	public List<String> setGrages(int grade){
		List<String> grades = new ArrayList<String>();
		switch (grade) {
		case 5:
			grades.add("5");
		case 4:
			grades.add("4");
		case 3:
			grades.add("3");
		case 2:
			grades.add("2");	
		case 1:
			grades.add("1");		
		}
		return grades;
	}
	/**
	 * 商城商品列表，设置客户销售价格
	 * */
	public BigDecimal setPrice(ProductQuery product,int custTypeCode){
		BigDecimal salePrice = new BigDecimal(0.0);
		switch (custTypeCode) {
		case 5:
			salePrice = product.getFifthPrice();
			break;
		case 4:
			salePrice = product.getFourthPrice();
			break;
		case 3:
			salePrice = product.getThirdPrice();
			break;
		case 2:
			salePrice = product.getSecondPrice();
			break;
		case 1:
			salePrice = product.getFirsthPrice();
			break;		
		}
		return salePrice;
	}
	
	public SpClient getClientByopenId(ProductQuery productQuery){
		ScWeChat weChat = scWeChatMapper.selectByOpenId(productQuery.getOpenid());
		SpClient client = new SpClient();
		List<SpClient> clients = null;
		boolean mallProList = false; //false 表示后端商品管理
		if(weChat!=null){
			//针对商城的商品查询，货品管理的商品查询不需要对价格显示设置和商品等级
			if(weChat.getStoreid()!=null&&(!weChat.getStoreid().equals(productQuery.getStoreId()))){
				client.setOtherStoreId(weChat.getStoreid());
				mallProList =true ;
			}else if(weChat.getUserid()!=null){
				client.setUserId(weChat.getUserid());
				mallProList =true ;
			}
			client.setStoreId(productQuery.getStoreId());
			client.setType(1+"");
			clients = spClientMapper.select(client,null);
		}else{
			return null;
		}
		if(clients !=null && clients.size() > 0 && mallProList){
			client = clients.get(0);
		}
		return client;
	}
	@Override
	public ProductQuery selectProduct(ProductQuery queryDetail) {
		ProductQuery query = spProductMapper.selectProduct(queryDetail);
		
		SpClient client = getClientByopenId(queryDetail);
		int custTypeCode = 0;
		if(client != null){
			custTypeCode = Integer.parseInt(client.getCate());
			if(custTypeCode>0){
				BigDecimal salePrice = setPrice(query, custTypeCode);
				query.setPrice(salePrice);
				query.setSalePrice(salePrice);
				query.setOriginalPrice(query.getTagprice());
			}
		}
		List<SsProductReview> productReviews = ssProductReviewMapper.selectReviews(queryDetail);
		query.setProductReviews(productReviews);
		return query;
	}

	@Override
	public ProductQuery productInit(ProductQuery query) {
		Integer storeId = query.getStoreId();
		String productId = query.getId();
		List<SpPBrand> brands = spPBrandMapper.getSpPBrands(storeId,null);
		List<SpPCate> productTypes = spPCateMapper.getSpPCates(storeId,null);
		List<SpPSizeCustom> sizes = spPSizeCustomMapper.getSpPSizeCustoms(storeId,null);
		List<SpUnit> units = spUnitMapper.getUnits(storeId,null) ;
		List<SpPrivilege> privileges= spPrivilegeMapper.getSpPrivilegeList();
		List<String> list = new ArrayList<String>();
		list.add("SELLING_METHOD");//售价方式
		List<SsDataDictionary> dict = ssDataDictionaryMapper.selectDictionarys(list,storeId);
		
		ProductQuery queryResult = new ProductQuery();
		
		queryResult.setStoreId(storeId);
		queryResult.setBrandList(brands);
		queryResult.setProductSize(sizes);
		queryResult.setProductTypeList(productTypes);
		queryResult.setUnitList(units);
		queryResult.setPrivileges(privileges);
		//获取售价生成方式，value=1表示按照毛利，value=2表示按照折扣
		//设置生成方式默认值：根据店铺类型
		SpStore spStore = spStoreMapper.selectByPrimaryKey(storeId);
		String custOrDiscTag = "1"; 
		if(spStore != null){
			custOrDiscTag = spStore.getStoreType()+"";
		}
		if(dict !=null && dict.size()>0){
			for(SsDataDictionary dictionary : dict){
				if("SELLING_METHOD".equals(dictionary.getDictGroup())){
					custOrDiscTag = dictionary.getDictValue();
				}
			}
		}
		//获取商品等级对应的折扣率和毛利率
		List<SpCustProdPrice> custProdPrice = new ArrayList<SpCustProdPrice>();
		SpCustProdPrice spCustProdPrice = new SpCustProdPrice();
		spCustProdPrice.setStoreid(storeId);
		custProdPrice = spCustProdPriceMapper.select(spCustProdPrice);
		
		List<SpCustProdPrice> gradeList = new ArrayList<SpCustProdPrice>();
		List<SpCustProdPrice> customTypeList = new ArrayList<SpCustProdPrice>();
		List<SpCustProdPrice> custProdList = new ArrayList<SpCustProdPrice>();
		if(custProdPrice.size()>0){
			for(SpCustProdPrice price : custProdPrice){
				price.setCustPriceJson((List<PriceJson>) JSONArray.parse(price.getCustpricejson()));
				price.setDiscPriceJson((List<PriceJson>) JSONArray.parse(price.getDiscpricejson()));
				
				SpCustProdPrice prodPrice = new SpCustProdPrice();
				prodPrice.setId(Integer.parseInt(price.getCustTypeCode()));
				prodPrice.setCusttypename(price.getCusttypename());
				if("1".equals(price.getType())){
					gradeList.add(prodPrice);
				}else if("2".equals(price.getType())){
					customTypeList.add(prodPrice);
				}
				
				price.setCustpricejson(null);
				price.setDiscpricejson(null);
				custProdList.add(price);
			}
		}
		queryResult.setCustomerTypeList(customTypeList);
		queryResult.setGradeList(gradeList);
		queryResult.setCustProdPrice(custProdList);
		queryResult.setCustOrDiscTag(custOrDiscTag);
		
		if(productId != null ){
			SpProduct product = spProductMapper.selectByPrimaryKey(productId, storeId);
			if(product != null){
				queryResult.setCode(product.getCode());
				queryResult.setName(product.getName());
				queryResult.setHandcount(product.getHandcount());
				queryResult.setImage(product.getImage());
				queryResult.setImage1(product.getImage1());
				queryResult.setImage2(product.getImage2());
				queryResult.setImage3(product.getImage3());
				queryResult.setImage4(product.getImage4());
				queryResult.setSeason(product.getSeason());
				queryResult.setTimeDown(product.getTimeDown());
				queryResult.setTimeUp(product.getTimeUp());
				queryResult.setYear(product.getYear());
				queryResult.setWarnHigh(product.getWarnHigh());
				queryResult.setWarnLow(product.getWarnLow());
				queryResult.setBarcode(product.getBarcode());
				queryResult.setBarcodes(product.getBarcodes());
				queryResult.setColors(product.getColors());
				queryResult.setSizes(product.getSizes());
				queryResult.setUnit(product.getUnit());
				queryResult.setId(product.getId());
				queryResult.setBrandId(product.getBrandid()+"");
				queryResult.setProducttype(product.getProducttype()+"");
				queryResult.setIsDiscount(product.getIsDiscount());
				queryResult.setIsModify(product.getIsModify());
				queryResult.setGrade(product.getGrade());
				queryResult.setVideo1(product.getVideo1());
				queryResult.setVideo2(product.getVideo2());
				queryResult.setVideo3(product.getVideo3());
			}
			SsPrice price = ssPriceMapper.select(storeId, productId);
			queryResult.setPurchaseprice(price.getPurchaseprice());
			queryResult.setTagprice(price.getTagprice());
			queryResult.setFirsthPrice(price.getFirsthPrice());
			queryResult.setFifthPrice(price.getFifthPrice());
			queryResult.setSecondPrice(price.getSecondPrice());
			queryResult.setThirdPrice(price.getThirdPrice());
			queryResult.setFourthPrice(price.getFourthPrice());
			
			queryResult.setBrandList(null);
			queryResult.setProductSize(null);
			queryResult.setProductTypeList(null);
			queryResult.setUnitList(null);
			queryResult.setCustProdPrice(null);
			queryResult.setCustomerTypeList(null);
			queryResult.setPrivileges(null);
		}
		return queryResult;
	}

	@Override
	public String saveProduct(SpProduct product) {
		// 查询货号是否存在
		if(product.getId() != null){
			product.setStatus(0);
			spProductMapper.updateByPrimaryKeySelective(product);
			//判断是否需保存价格
			SsPrice price = new SsPrice();
			price.setFifthPrice(product.getFifthPrice());
			price.setFirsthPrice(product.getFirsthPrice());
			price.setStoreid(product.getStoreId());
			price.setFourthPrice(product.getFourthPrice());
			price.setProductid(product.getId());
			price.setPurchaseprice(product.getPurchaseprice());
			price.setTagprice(product.getTagprice());
			price.setThirdPrice(product.getThirdPrice());
			price.setSecondPrice(product.getSecondPrice());
			price.setModifyTime(new Date());
			ssPriceMapper.updateByPrimaryKeySelective(price);
			
			//判断是否需保存文件地址
			if(product.getFileList() != null && product.getFileList().size()>0){
				ssProductFileMapper.insertFiles(product.getFileList(),null);
			}
			return product.getId();
		}else{
			//保存商品
			product.setId(UUID.randomUUID().toString().replaceAll("\\-", ""));
			product.setStatus(0);
			product.setCreateTime(new Date());
			product.setModifyTime(new Date());
			product.setSaleNum(BigDecimal.valueOf(0));
			int save = spProductMapper.insertSelective(product);
			if(save > 0 ){
				SsPrice price = new SsPrice();
				price.setStoreid(product.getStoreId());
				price.setPurchaseprice(product.getPurchaseprice());
				price.setFirsthPrice(product.getFirsthPrice());
				price.setFifthPrice(product.getFifthPrice());
				price.setSecondPrice(product.getSecondPrice());
				price.setThirdPrice(product.getThirdPrice());
				price.setFourthPrice(product.getFourthPrice());
				price.setTagprice(product.getTagprice());
				price.setProductid(product.getId());
				price.setCreateTime(new Date());
				price.setModifyTime(new Date());
				price.setCreateBy(product.getCreateBy());
				price.setModifyBy(product.getModifyBy());
				//价格保存到价格表，颜色组保存到颜色表
				ssPriceMapper.insertSelective(price);
				
			}else{
				return null;
			}
			List<SsProductFile> files = product.getFileList();
			if(files !=null && files.size()>0){
				int op = ssProductFileMapper.insertFiles(files,null);
				if(op <= 0){
					return null;
				}
			}
		
			return product.getId();	
		}
	}

	@Override
	public List<SpProduct> selectProductById(List<String> ids,Integer storeId) {
		return spProductMapper.selectById(ids,storeId);
	}
	
	public void insertSuspendingProduct(SpProduct product,Integer buyerStoreId){
		
		//查询商品类型是否存在买家，不存在，新增
		SpPCate cate = spPCateMapper.getSpPCate(product.getBrandid(), product.getStoreId());
		SpPCate cate1 = spPCateMapper.getSpPCateByName(cate.getCatename(), buyerStoreId);
		int productType  = 0;
		if(cate1 == null){
			SpPCate type = new SpPCate();
			type.setCatename(cate.getCatename());
			type.setStoreId(buyerStoreId);
			type.setCreateTime(new Date());
			type.setFastcode(cate.getFastcode());
			productType = spPCateMapper.insertSelective(type);
		}
		if(productType > 0){
			product.setProducttype(productType);
		}
		//查询商品品牌是否存在买家，不存在，新增
		SpPBrand brand = spPBrandMapper.getSpPBrand(product.getBrandid(), product.getStoreId());
		SpPBrand brand1 = spPBrandMapper.getSpPBrandByName(brand.getBrandname(), buyerStoreId);
		int brandId  = 0;
		if(brand1 == null){
			SpPBrand b = new SpPBrand();
			b.setBrandname(brand.getBrandname());
			b.setStoreId(buyerStoreId);
			b.setCreateTime(new Date());
			b.setFastcode(brand.getFastcode());
			brandId = spPBrandMapper.insertSelective(b);
		}
		if(brandId > 0){
			product.setBrandid(brandId);
		}
		product.setStatus(1);//待处理状态
		int save = spProductMapper.insertSelective(product);
		if(save > 0 ){
			SsPrice price = new SsPrice();
			price.setStoreid(product.getStoreId());
			price.setPurchaseprice(product.getPurchaseprice());
			price.setTagprice(product.getTagprice());
			price.setProductid(product.getId());
			price.setCreateTime(new Date());
			price.setModifyTime(new Date());
			price.setCreateBy(product.getCreateBy());
			price.setModifyBy(product.getModifyBy());
			//价格保存到价格表，颜色组保存到颜色表
			ssPriceMapper.insertSelective(price);
		}
	}

	@Override
	public int deleteProducts(List<String> id, Integer storeId) {
		return spProductMapper.deleteByIds(storeId, id);
	}

	@Override
	public int updateProducts(ProductQuery  productQuery) {
		return spProductMapper.updateByIds(productQuery);
	}

	@Override
	public List<SsStock> takingStock(SpProduct product) {
		Integer currentStore = product.getStoreId();
		//根据storeiD查询集团的storeId
		SpStore store = spStoreMapper.selectByPrimaryKey(product.getStoreId());
		List<SpStore> stores = new  ArrayList<SpStore>();
		
		
		List<SsStock> stocks = new ArrayList<SsStock>();
		List<SsStock> stockShares = new ArrayList<SsStock>();
		SsStock stock = new SsStock();
		stock.setProductid(product.getId());
		stock.setStoreid(product.getStoreId());
		List<Integer> otherStoreIds = null;
		
		if(store.getParentid()!=null&&store.getInventoryShare()==1){//分店//查询允许共享的店铺
			store = new SpStore();
			store.setParentid(store.getParentid());
			store.setInventoryShare(1);//允许共享
			stores = spStoreMapper.selectStores(store, null);//查询允许共享的店铺
			
			if(stores.size()>0){
				otherStoreIds = new ArrayList<Integer>();
				for(SpStore store1 :stores){
					if(store1.getId()!=product.getStoreId()){
						otherStoreIds.add(store1.getId());
					}
					
				}
			}
			stock.setOtherStoreIds(otherStoreIds);
		}
		stocks = ssStockMapper.select(stock);
		List<SsStock> stockList = new ArrayList<SsStock>();
		//查询对应的供货商
		SsStoOrder order = ssStoOrderMapper.selectSupplierByProduct(product);
		if(stocks.size()>0&&order!=null){
			for(SsStock s : stocks){
				s.setSupplierStoreId(order.getSellerstoreid());
				stockList.add(s);
			}
		}
		
		return stockList;
		
		
	}

	@Override
	public List<SsProductFile> getImages(SsProductFile productFile) {
		Page page = new Page();
		page.setPageNum(productFile.getPageNum());
		return ssProductFileMapper.selectByStoreId(page,productFile);
	}

	@Override
	public Integer updateStock(List<SsStock> stocks) {
		int update =  ssStockMapper.batchUpdate(stocks);
		return update;
	}

	@Override
	public List<SsProductReview> getReviews(ProductQuery query) {
		return ssProductReviewMapper.selectReviews(query);
	}
	@Override
	public ProductQuery updateProductInit(ProductQuery query) {
		//获取商品等级
		List<SpCustProdPrice> custProdPrice = new ArrayList<SpCustProdPrice>();
		SpCustProdPrice spCustProdPrice = new SpCustProdPrice();
		spCustProdPrice.setType(1+"");
		spCustProdPrice.setStoreid(query.getStoreId());
		custProdPrice = spCustProdPriceMapper.select(spCustProdPrice);
				
		List<SpCustProdPrice> gradeList = new ArrayList<SpCustProdPrice>();
		if(custProdPrice.size()>0){
			for(SpCustProdPrice price : custProdPrice){
						SpCustProdPrice prodPrice = new SpCustProdPrice();
						prodPrice.setId(Integer.parseInt(price.getCustTypeCode()));
						prodPrice.setCusttypename(price.getCusttypename());
						gradeList.add(prodPrice);
				
					}
				}		
		query.setGradeList(gradeList);
		return query;
	}
	@Override
	public List<ProductQuery> getShareProduct(ProductQuery productQuery) {
		String openIdAndKey= null;
		boolean skipShare = false;
		if(!StringUtil.isBlank(productQuery.getCode())){//这是code指小程序加载时传的code，不是商品code，用来获取openid !"".equals(productQuery.getCode())
			try {
				openIdAndKey = weChatService.getOpenIdAndKey(productQuery.getCode(), productQuery.getAppid(), productQuery.getAppSecret());
			} catch (Exception e) {
				e.printStackTrace();
			}
			skipShare = true;
		}
		Integer custTypeCode =1;
		if(skipShare){
			JSONObject json = JSON.parseObject(openIdAndKey);
			String openid = json.getString("openid");
			ScWeChat chat = null;
			if(openid!=null){//进入小程序认证，
				chat = spUserService.login(openid);
			}
			//查询分享到对方的用户在商铺里面是什么客户级别，根据客户级别显示销售价，如果不属于客户，就默认最低客户级别，显示最高销售价
			SpClient client = new SpClient();
			client.setStoreId(productQuery.getStoreId());
			client.setType(1+"");
			client.setUserId(chat.getUserid());
			List<SpClient> clients = spClientMapper.select(client,null);
			
			if(clients.size()>0){
				client = clients.get(0);
				custTypeCode = Integer.parseInt(client.getCate());
			}
		}
		List<ProductQuery> products = new ArrayList<ProductQuery>();
		List<Map<String,Object>> maps = spProductMapper.selectProducts(productQuery,null);
		if(maps!=null && maps.size()>0){
			JSONArray jsonArray = (JSONArray) JSONArray.toJSON(maps);

			products = JSONArray.parseArray(jsonArray.toJSONString(), ProductQuery.class);
		}
		
		//商城商品列表设置销售价
		List<ProductQuery> productList = new ArrayList<ProductQuery>();
		if(products.size() >0){
			for(ProductQuery product : products){
				if(custTypeCode > 0 && skipShare){
					BigDecimal salePrice = setPrice(product, custTypeCode);
					product.setSalePrice(salePrice);
				}else{
					product.setSalePrice(product.getTagprice());
				}
				product.setFifthPrice(null);
				product.setFirsthPrice(null);
				product.setSecondPrice(null);
				product.setThirdPrice(null);
				product.setFourthPrice(null);
				product.setTagprice(null);
				product.setPurchaseprice(null);
				productList.add(product);
			}
		}
		
		return productList;
	}
	@Override
	public int makePopularize(ProductQuery productQuery) {
		productQuery.setPopularize(1);//推广商品
		return spProductMapper.updateByIds(productQuery);
	}
}
