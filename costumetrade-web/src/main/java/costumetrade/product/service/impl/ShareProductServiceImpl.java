package costumetrade.product.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.common.page.Page;
import costumetrade.common.util.StringUtil;
import costumetrade.order.query.ProductQuery;
import costumetrade.product.domain.ScPromotionalProduct;
import costumetrade.product.mapper.ScPromotionalProductMapper;
import costumetrade.product.mapper.ScUpgradeProductMapper;
import costumetrade.product.service.ShareProductService;

@Transactional
@Service
public class ShareProductServiceImpl implements ShareProductService {

	@Autowired
	private ScUpgradeProductMapper upgradeProductMapper;
	@Autowired
	private ScPromotionalProductMapper scPromotionalProductMapper;
	@Override
	public List<ScPromotionalProduct> getAllPromotionalProduct(String openid,Integer pageNum) {
		ScPromotionalProduct product = new ScPromotionalProduct();
		product.setRecommendedOpenid(openid);
		Page page = new Page();
		page.setPageNum(pageNum);
		List<ScPromotionalProduct> products =  scPromotionalProductMapper.getAllPromotionals(product,page);
		List<ScPromotionalProduct> result = new ArrayList<ScPromotionalProduct>();
		
		if(products!=null&&products.size()>0){
			for(ScPromotionalProduct pro : products){
				if(StringUtil.isNotBlank(pro.getProductImages())){
					pro.setImages(Arrays.asList(pro.getProductImages()));
					
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
	

}
