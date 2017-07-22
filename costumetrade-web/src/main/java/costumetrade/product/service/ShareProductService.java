package costumetrade.product.service;

import java.util.List;

import costumetrade.product.domain.ScPromotionalProduct;
import costumetrade.product.domain.ScUpgradeProduct;

public interface ShareProductService {

	List<ScPromotionalProduct> getAllPromotionalProduct(String storeId,String openid,Integer pageNum);
	
	public ScPromotionalProduct getPromotionalProduct(String id);
	
	public int saveShareInfoToCustomers(ScPromotionalProduct product);
	
	List<ScPromotionalProduct> getGroupPromotionalProduct(String openid,Integer pageNum);
}
