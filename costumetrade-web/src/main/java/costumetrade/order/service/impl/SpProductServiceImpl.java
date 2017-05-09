package costumetrade.order.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpPBrand;
import costumetrade.order.domain.SpPCate;
import costumetrade.order.domain.SpPColorCustom;
import costumetrade.order.domain.SpPSizeCustom;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.domain.SsPrice;
import costumetrade.order.enums.GradeTypeEnum;
import costumetrade.order.enums.SeasonTypeEnum;
import costumetrade.order.enums.UnitTypeEnum;
import costumetrade.order.mapper.SpPBrandMapper;
import costumetrade.order.mapper.SpPCateMapper;
import costumetrade.order.mapper.SpPColorCustomMapper;
import costumetrade.order.mapper.SpPSizeCustomMapper;
import costumetrade.order.mapper.SpProductMapper;
import costumetrade.order.mapper.SsPriceMapper;
import costumetrade.order.query.KeyParam;
import costumetrade.order.query.ProductDetailQuery;
import costumetrade.order.query.ProductInitQuery;
import costumetrade.order.query.ProductParam;
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
	
	@Override
	public List<ProductQuery> selectProducts(ProductParam productQuery) {
		return spProductMapper.selectProducts(productQuery);
	}

	@Override
	public ProductDetailQuery selectProduct(KeyParam keyParam) {
		return spProductMapper.selectProduct(keyParam);
	}

	@Override
	public ProductInitQuery productInit(int corpId) {
		List<SpPBrand> brands = spPBrandMapper.getSpPBrands(corpId);
		List<SpPCate> productTypes = spPCateMapper.getSpPCates(corpId);
		List<SpPSizeCustom> sizes = spPSizeCustomMapper.getSpPSizeCustoms(corpId);
		ProductInitQuery query = new ProductInitQuery();
		
		query.setCorpId(corpId);
		query.setProductBrand(brands);
		query.setProductSize(sizes);
		query.setProductType(productTypes);
		
		query.setGrade(GradeTypeEnum.getGradeTypeEnum());
		query.setSeason(SeasonTypeEnum.getSeasonTypeEnum());
		query.setUnit(UnitTypeEnum.getUnitTypeEnum());
		
		return query;
	}

	@Override
	public int saveProduct(SpProduct product) {
		// TODO Auto-generated method stub
		// 查询货号是否存在
		if(product.getId() != null){
			return 0;
		}else{
			if(product.getSizes() != null){
				//保存颜色组到颜色表，获取颜色组ID
				SpPColorCustom custom = new SpPColorCustom();
				custom.setCorpid(product.getCorpid());
				custom.setValue(product.getSizes());
				custom = spPColorCustomMapper.selectByCustomValue(custom);
				if(custom == null){
					custom = new SpPColorCustom();
					custom.setCustomname("自定义");
					custom.setCorpid(product.getCorpid());
					custom.setValue(product.getSizes());
					spPColorCustomMapper.insertSelective(custom);
					custom = spPColorCustomMapper.selectByCustomValue(custom);
				}
				product.setColors(custom.getId()+"");
			}
			//保存商品
			product.setStatus(0);
			int id = spProductMapper.insertSelective(product);
			if(id >0 ){
				SsPrice price = new SsPrice();
				price.setPackprice(product.getPackprice());
				price.setPurchaseprice(product.getPurchaseprice());
				price.setRetailprice(product.getRetailprice());
				price.setTagprice(product.getTagprice());
				price.setProductid(id+"");
				price.setCreateTime(new Date());
				price.setModifyTime(new Date());
				price.setCreateBy(product.getCreateBy());
				price.setModifyBy(product.getModifyBy());
				//价格保存到价格表，颜色组保存到颜色表
				ssPriceMapper.insertSelective(price);
			}else{
				return 0;
			}
			return 1;	
		}
	}
}
