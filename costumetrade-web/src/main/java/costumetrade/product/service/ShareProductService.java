package costumetrade.product.service;

import java.util.List;

import costumetrade.product.domain.ScPromotionalProduct;
import costumetrade.product.domain.ScUpgradeProduct;

public interface ShareProductService {

	List<ScPromotionalProduct> getAllPromotionalProduct(String openid,Integer pageNum);
	
	public ScPromotionalProduct getPromotionalProduct(String id);
}
