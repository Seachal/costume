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
import costumetrade.order.domain.SpPrice;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.enums.GradeTypeEnum;
import costumetrade.order.enums.SeasonTypeEnum;
import costumetrade.order.enums.UnitTypeEnum;
import costumetrade.order.mapper.SpPBrandMapper;
import costumetrade.order.mapper.SpPCateMapper;
import costumetrade.order.mapper.SpPColorCustomMapper;
import costumetrade.order.mapper.SpPSizeCustomMapper;
import costumetrade.order.mapper.SpPriceMapper;
import costumetrade.order.mapper.SpProductMapper;
import costumetrade.order.query.KeyParam;
import costumetrade.order.query.ProductDetailQuery;
import costumetrade.order.query.ProductInitQuery;
import costumetrade.order.query.ProductsQuery;
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
	private SpPriceMapper spPriceMapper;
	
	@Override
	public List<SpProduct> selectProducts(ProductsQuery productQuery) {
		// TODO Auto-generated method stub
		return spProductMapper.selectProducts(productQuery);
	}

	@Override
	public ProductDetailQuery selectProduct(KeyParam keyParam) {
		// TODO Auto-generated method stub
		return spProductMapper.selectProduct(keyParam);
	}

	@Override
	public ProductInitQuery productInit(int corpId) {
		// TODO Auto-generated method stub
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
		SpProduct productExist = spProductMapper.selectByCode(product);
		if(productExist != null){
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
			int save = spProductMapper.insertSelective(product);
			if(save > 0 ){
				SpProduct productNew = spProductMapper.selectByCode(product);
				SpPrice price = new SpPrice();
				price.setSale0(product.getSale0());
				price.setSale1(product.getSale1());
				price.setSale2(product.getSale2());
				price.setSale3(product.getSale3());
				price.setStock(product.getStock());
				price.setSubid(productNew.getCorpid());
				price.setProdId(productNew.getId());
				price.setCreateTime(new Date());
				price.setModifyTime(new Date());
				price.setCreateBy(productNew.getCreateBy());
				price.setModifyBy(productNew.getModifyBy());
				//价格保存到价格表，颜色组保存到颜色表
				spPriceMapper.insertSelective(price);
			}else{
				return 0;
			}
			return 1;	
		}
	}
}
