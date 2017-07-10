package costumetrade.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.product.domain.ScUpgradeProduct;
import costumetrade.product.mapper.ScUpgradeProductMapper;
import costumetrade.product.service.UpgradeProductService;

@Transactional
@Service
public class UpgradeProductServiceImpl implements UpgradeProductService {

	@Autowired
	private ScUpgradeProductMapper upgradeProductMapper;
	@Override
	public List<ScUpgradeProduct> getAllProduct(){ 
		return upgradeProductMapper.getAllProduct();
	}

}
