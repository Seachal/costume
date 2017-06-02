package costumetrade.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.print.attribute.HashAttributeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;











import com.alibaba.fastjson.JSONArray;
import com.mysql.fabric.xmlrpc.base.Array;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SpClient;
import costumetrade.order.domain.SpPBrand;
import costumetrade.order.domain.SpPCate;
import costumetrade.order.domain.SpPSizeCustom;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.domain.SpUnit;
import costumetrade.order.domain.SsPrice;
import costumetrade.order.domain.SsProductFile;
import costumetrade.order.domain.SsProductReview;
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
import costumetrade.order.mapper.SsStockMapper;
import costumetrade.order.query.ProductQuery;
import costumetrade.order.query.Rules;
import costumetrade.order.service.SpProductService;
import costumetrade.user.domain.PriceJson;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpCustProdPrice;
import costumetrade.user.domain.SpStore;
import costumetrade.user.domain.SpUser;
import costumetrade.user.domain.SsDataDictionary;
import costumetrade.user.mapper.ScWeChatMapper;
import costumetrade.user.mapper.SpCustProdPriceMapper;
import costumetrade.user.mapper.SpStoreMapper;
import costumetrade.user.mapper.SsDataDictionaryMapper;

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
		}
	
		Page page = new Page();
		page.setPageNum(productQuery.getPageNum());
		
		productQuery.setGrades(grades);
		productQuery.setCustTypeCode(custTypeCode);
		List<ProductQuery> products = spProductMapper.selectProducts(productQuery,page);
		//商城商品列表设置销售价
		List<ProductQuery> productList = new ArrayList<ProductQuery>();
		if(products.size() >0){
			for(ProductQuery product : products){
				if(custTypeCode >0){
					BigDecimal salePrice = setPrice(product, custTypeCode);
					product.setSalePrice(salePrice);
				}
				productList.add(product);
			}
		}
		
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
			client.setStoreid(productQuery.getStoreId());
			client.setType(1+"");
			clients = spClientMapper.select(client);
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
			}
		}
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
		
		List<String> list = new ArrayList<String>();
		list.add("SELLING_METHOD");//售价方式
		List<SsDataDictionary> dict = ssDataDictionaryMapper.selectDictionarys(list,storeId);
		
		ProductQuery queryResult = new ProductQuery();
		
		queryResult.setStoreId(storeId);
		queryResult.setBrandList(brands);
		queryResult.setProductSize(sizes);
		queryResult.setProductTypeList(productTypes);
		queryResult.setUnitList(units);
		//获取售价生成方式，value=1表示按照毛利，value=2表示按照折扣
		//设置生成方式默认值：根据店铺类型
		SpStore spStore = spStoreMapper.selectByPrimaryKey(storeId);
		String custOrDiscTag = "1"; 
		if(spStore != null){
			custOrDiscTag = spStore.getStoreType();
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
		spCustProdPrice.setType(1+"");
		spCustProdPrice.setStoreid(storeId);
		custProdPrice = spCustProdPriceMapper.select(spCustProdPrice);
		
		List<SpCustProdPrice> gradeList = new ArrayList<SpCustProdPrice>();
		List<SpCustProdPrice> custProdList = new ArrayList<SpCustProdPrice>();
		if(custProdPrice.size()>0){
			for(SpCustProdPrice price : custProdPrice){
				price.setCustPriceJson((List<PriceJson>) JSONArray.parse(price.getCustpricejson()));
				price.setDiscPriceJson((List<PriceJson>) JSONArray.parse(price.getDiscpricejson()));
				
				SpCustProdPrice prodPrice = new SpCustProdPrice();
				prodPrice.setId(Integer.parseInt(price.getProdgrade()));
				prodPrice.setCusttypename(price.getCusttypename());
				gradeList.add(prodPrice);
				
				price.setCustpricejson(null);
				price.setDiscpricejson(null);
				custProdList.add(price);
			}
		}
		
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
	public int updateProducts(List<String> id, Integer storeId) {
		return spProductMapper.updateByIds(storeId, id);
	}

	@Override
	public List<SsStock> takingStock(SpProduct product) {
		SsStock stock = new SsStock();
		stock.setProductid(product.getId());
		stock.setStoreid(product.getStoreId());
		return ssStockMapper.select(stock);
	}

	@Override
	public List<SsProductFile> getImages(SsProductFile productFile) {
		return ssProductFileMapper.selectByStoreId(null,productFile.getFilename());
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
}
