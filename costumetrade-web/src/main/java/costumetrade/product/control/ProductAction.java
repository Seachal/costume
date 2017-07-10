package costumetrade.product.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.product.domain.ScUpgradeProduct;
import costumetrade.product.service.UpgradeProductService;

@RequestMapping("/product")
@Controller
public class ProductAction {
	@Autowired
	private UpgradeProductService upgradeProductService;
	
	@RequestMapping("/getList")
	@ResponseBody
	public ApiResponse getList() {
		List<ScUpgradeProduct> productList = upgradeProductService.getAllProduct();
		return  ApiResponse.getInstance(productList);
	}
}
