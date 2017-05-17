package costumetrade.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpPBrand;
import costumetrade.order.domain.SpPCate;
import costumetrade.order.domain.SpPSizeCustom;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.domain.SsPrice;
import costumetrade.order.domain.SsProductFile;
import costumetrade.order.domain.SsStock;
import costumetrade.order.enums.GradeTypeEnum;
import costumetrade.order.enums.SeasonTypeEnum;
import costumetrade.order.enums.UnitTypeEnum;
import costumetrade.order.mapper.SpPBrandMapper;
import costumetrade.order.mapper.SpPCateMapper;
import costumetrade.order.mapper.SpPColorCustomMapper;
import costumetrade.order.mapper.SpPSizeCustomMapper;
import costumetrade.order.mapper.SpProductMapper;
import costumetrade.order.mapper.SsPriceMapper;
import costumetrade.order.mapper.SsProductFileMapper;
import costumetrade.order.mapper.SsStockMapper;
import costumetrade.order.query.ProductQuery;
import costumetrade.order.service.SpProductService;
import costumetrade.user.domain.SsDataDictionary;
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
	
	@Override
	public List<SpProduct> selectProducts(ProductQuery productQuery) {
		List<String> season = new ArrayList<String>();
		if(productQuery.getProductSeasonArray() !=null && productQuery.getProductSeasonArray().size()>0){
			for(int i = 0 ; i<productQuery.getProductSeasonArray().size(); i++){
				season.add(Enum.valueOf(SeasonTypeEnum.class,productQuery.getProductSeasonArray().get(i)).getValue());
			}
			
		}
		if(season.size()>0){
			productQuery.setProductSeasonArray(season);
		}
		return spProductMapper.selectProducts(productQuery);
	}

	@Override
	public ProductQuery selectProduct(ProductQuery queryDetail) {
		return spProductMapper.selectProduct(queryDetail);
	}

	@Override
	public ProductQuery productInit(int storeId) {
		List<SpPBrand> brands = spPBrandMapper.getSpPBrands(storeId);
		List<SpPCate> productTypes = spPCateMapper.getSpPCates(storeId);
		List<SpPSizeCustom> sizes = spPSizeCustomMapper.getSpPSizeCustoms(storeId);
		
		List<String> list = new ArrayList<String>();
		list.add("SALE_PRICE");//价格名称
		list.add("PRODUCT_LEVEL");//货品级别
		List<SsDataDictionary> dict = ssDataDictionaryMapper.selectDictionarys(list);
		
		ProductQuery query = new ProductQuery();
		
		query.setStoreId(storeId);
		query.setBrandList(brands);
		query.setProductSize(sizes);
		query.setProductTypeList(productTypes);
		query.setSeasonList(SeasonTypeEnum.getSeasonTypeEnum());
		query.setUnitList(UnitTypeEnum.getUnitTypeEnum());
		
		List<String> gradeList = new ArrayList<String>();
		List<String> priceNameList = new ArrayList<String>();
		if(dict !=null && dict.size()>0){
			for(SsDataDictionary dictionary : dict){
				if("SALE_PRICE".equals(dictionary.getDictGroup())){
					priceNameList.add(dictionary.getDictValue());
				}else if("PRODUCT_LEVEL".equals(dictionary.getDictGroup())){
					gradeList.add(dictionary.getDictValue());
				}
			}
		}
		query.setPriceNameList(priceNameList);
		query.setGradeList(gradeList);
		return query;
	}

	@Override
	public int saveProduct(SpProduct product) {
		// 查询货号是否存在
		if(product.getId() != null){
			product.setStatus(0);
			return spProductMapper.updateByPrimaryKeySelective(product);
		}else{
			//保存商品
			product.setId(UUID.randomUUID().toString());
			product.setStatus(0);
			if(product.getSeason() != null){
				product.setSeason(Enum.valueOf(SeasonTypeEnum.class,product.getSeason()).getValue());
			}
//			if(product.getGrade() != null){
//				product.setGrade(Enum.valueOf(GradeTypeEnum.class,product.getGrade()).getValue());
//			}
			if(product.getUnit() != null){
				product.setUnit(Enum.valueOf(UnitTypeEnum.class,product.getUnit()).getValue());
			}
			product.setSaleNum(BigDecimal.valueOf(0));
			String id = spProductMapper.insertSelective(product);
			if(id != null ){
				SsPrice price = new SsPrice();
				price.setStoreid(product.getStoreId());
				price.setPackprice(product.getPackprice());
				price.setPurchaseprice(product.getPurchaseprice());
				price.setRetailprice(product.getRetailprice());
				price.setTagprice(product.getTagprice());
				price.setProductid(id);
				price.setCreateTime(new Date());
				price.setModifyTime(new Date());
				price.setCreateBy(product.getCreateBy());
				price.setModifyBy(product.getModifyBy());
				//价格保存到价格表，颜色组保存到颜色表
				ssPriceMapper.insertSelective(price);
				
			}else{
				return 0;
			}
			List<SsProductFile> files = new ArrayList<SsProductFile>();
		
			if(product.getImage() != null && product.getImageName() != null && product.getReduceImage() !=null){
				SsProductFile file = new SsProductFile();
				file.setProductid(id);
				file.setStoreid(product.getStoreId());
				file.setFilename(product.getImageName());
				file.setUrl(product.getImage());
				file.setCreatetime(new Date());
				file.setResizeFixUrl(product.getReduceImage());
				file.setCreateby(product.getCreateBy());
				files.add(file);
			}
			if(product.getImage1() != null && product.getImageName1() != null && product.getReduceImage1() !=null ){
				SsProductFile file = new SsProductFile();
				file.setProductid(id);
				file.setStoreid(product.getStoreId());
				file.setFilename(product.getImageName1());
				file.setUrl(product.getImage1());
				file.setCreatetime(new Date());
				file.setCreateby(product.getCreateBy());
				file.setResizeFixUrl(product.getReduceImage1());
				files.add(file);
			}
			if(product.getImage2() != null && product.getImageName2() != null && product.getReduceImage2() !=null){
				SsProductFile file = new SsProductFile();
				file.setProductid(id);
				file.setStoreid(product.getStoreId());
				file.setFilename(product.getImageName2());
				file.setUrl(product.getImage2());
				file.setCreatetime(new Date());
				file.setCreateby(product.getCreateBy());
				file.setResizeFixUrl(product.getReduceImage2());
				files.add(file);
			}
			if(product.getImage3() != null && product.getImageName3() != null && product.getReduceImage3() !=null){
				SsProductFile file = new SsProductFile();
				file.setProductid(id);
				file.setStoreid(product.getStoreId());
				file.setFilename(product.getImageName3());
				file.setUrl(product.getImage3());
				file.setCreatetime(new Date());
				file.setCreateby(product.getCreateBy());
				file.setResizeFixUrl(product.getReduceImage3());
				files.add(file);
			}
			if(product.getImage4() != null && product.getImageName4() != null && product.getReduceImage4() !=null){
				SsProductFile file = new SsProductFile();
				file.setProductid(id);
				file.setStoreid(product.getStoreId());
				file.setFilename(product.getImageName4());
				file.setUrl(product.getImage4());
				file.setCreatetime(new Date());
				file.setCreateby(product.getCreateBy());
				file.setResizeFixUrl(product.getReduceImage4());
				files.add(file);
			}
			if(files.size()>0){
				int op = ssProductFileMapper.insertFiles(files,product.getStoreId());
				if(op <= 0){
					return 0;
				}
			}
			return 1;	
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
			product.setPrducttype(productType);
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
		String id = spProductMapper.insertSelective(product);
		if(id != null ){
			SsPrice price = new SsPrice();
			price.setStoreid(product.getStoreId());
			price.setPackprice(product.getPackprice());
			price.setPurchaseprice(product.getPurchaseprice());
			price.setRetailprice(product.getRetailprice());
			price.setTagprice(product.getTagprice());
			price.setProductid(id);
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
	public List<SsProductFile> getImages(SpProduct product) {
		return ssProductFileMapper.selectByStoreId(product.getStoreId(),product.getImageName());
	}
}
