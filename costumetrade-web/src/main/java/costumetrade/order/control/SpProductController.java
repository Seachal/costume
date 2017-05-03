package costumetrade.order.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.query.KeyParam;
import costumetrade.order.query.ProductDetailQuery;
import costumetrade.order.query.ProductInitQuery;
import costumetrade.order.query.ProductsQuery;
import costumetrade.order.service.SpProductService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/product")
@Controller
public class SpProductController {
	@Autowired
	private SpProductService spProductService;


	@RequestMapping("/getProducts")
	@ResponseBody
	public ApiResponse getAllroducts(ProductsQuery productQuery) {
		
		List<SpProduct> roductLists = new ArrayList<SpProduct>();
	
		roductLists = spProductService.selectProducts(productQuery);

		return  ApiResponse.getInstance(roductLists);
	}
	
	@RequestMapping("/getProductDetail")
	@ResponseBody
	public ApiResponse getProduct(KeyParam keyParam) {
		
		
		ProductDetailQuery query = spProductService.selectProduct(keyParam);
		
		return  ApiResponse.getInstance(query);
	}
	
	@RequestMapping("/getProductInit")
	@ResponseBody
	public ApiResponse getProductInit(int corpId) {
		
		
		ProductInitQuery query = spProductService.productInit(corpId);
		
		return  ApiResponse.getInstance(query);
	}
	
	@RequestMapping("/saveProduct")
	@ResponseBody
	public ApiResponse saveProduct(SpProduct product) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		int save = spProductService.saveProduct(product);
		if(save<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return  result;
	}

	
	
}
