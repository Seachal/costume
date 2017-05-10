package costumetrade.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpPBrand;
import costumetrade.order.domain.SpPCate;
import costumetrade.order.domain.SpPSizeCustom;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.domain.SsPrice;
import costumetrade.order.domain.SsProductFile;
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
import costumetrade.order.query.ProductQuery;
import costumetrade.order.service.SpProductService;

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
	
	@Override
	public List<SpProduct> selectProducts(ProductQuery productQuery) {
		return spProductMapper.selectProducts(productQuery);
	}

	@Override
	public SpProduct selectProduct(ProductQuery queryDetail) {
		return spProductMapper.selectProduct(queryDetail);
	}

	@Override
	public ProductQuery productInit(int storeId) {
		List<SpPBrand> brands = spPBrandMapper.getSpPBrands(storeId);
		List<SpPCate> productTypes = spPCateMapper.getSpPCates(storeId);
		List<SpPSizeCustom> sizes = spPSizeCustomMapper.getSpPSizeCustoms(storeId);
		ProductQuery query = new ProductQuery();
		
		query.setStoreId(storeId);
		query.setBrandList(brands);
		query.setProductSize(sizes);
		query.setProductTypeList(productTypes);
		
		query.setGradeList(GradeTypeEnum.getGradeTypeEnum());
		query.setSeasonList(SeasonTypeEnum.getSeasonTypeEnum());
		query.setUnitList(UnitTypeEnum.getUnitTypeEnum());
		
		return query;
	}

	@Override
	public int saveProduct(SpProduct product) {
		// TODO Auto-generated method stub
		// 查询货号是否存在
		if(product.getId() != null){
			return 0;
		}else{
			//保存商品
			product.setStatus(0);
			int id = spProductMapper.insertSelective(product);
			if(id >0 ){
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
		
			if(product.getImage() != null && product.getImageName() != null ){
				SsProductFile file = new SsProductFile();
				file.setProductid(id);
				file.setStoreid(product.getStoreId());
				file.setFilename(product.getImageName());
				file.setUrl(product.getImage());
				file.setCreatetime(new Date());
				file.setCreateby(product.getCreateBy());
				files.add(file);
			}
			if(product.getImage1() != null && product.getImageName1() != null ){
				SsProductFile file = new SsProductFile();
				file.setProductid(id);
				file.setStoreid(product.getStoreId());
				file.setFilename(product.getImageName1());
				file.setUrl(product.getImage1());
				file.setCreatetime(new Date());
				file.setCreateby(product.getCreateBy());
				files.add(file);
			}
			if(product.getImage2() != null && product.getImageName2() != null ){
				SsProductFile file = new SsProductFile();
				file.setProductid(id);
				file.setStoreid(product.getStoreId());
				file.setFilename(product.getImageName2());
				file.setUrl(product.getImage2());
				file.setCreatetime(new Date());
				file.setCreateby(product.getCreateBy());
				files.add(file);
			}
			if(product.getImage3() != null && product.getImageName3() != null ){
				SsProductFile file = new SsProductFile();
				file.setProductid(id);
				file.setStoreid(product.getStoreId());
				file.setFilename(product.getImageName3());
				file.setUrl(product.getImage3());
				file.setCreatetime(new Date());
				file.setCreateby(product.getCreateBy());
				files.add(file);
			}
			if(product.getImage4() != null && product.getImageName4() != null ){
				SsProductFile file = new SsProductFile();
				file.setProductid(id);
				file.setStoreid(product.getStoreId());
				file.setFilename(product.getImageName4());
				file.setUrl(product.getImage4());
				file.setCreatetime(new Date());
				file.setCreateby(product.getCreateBy());
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
	public List<SpProduct> selectProductById(List<Integer> ids,Integer storeId) {
		return spProductMapper.selectById(ids,storeId);
	}
}
