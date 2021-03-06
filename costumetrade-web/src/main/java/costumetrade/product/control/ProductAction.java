package costumetrade.product.control;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.common.util.StringUtil;
import costumetrade.order.query.ProductQuery;
import costumetrade.order.service.SpProductService;
import costumetrade.product.domain.ScPromotionalProduct;
import costumetrade.product.domain.ScUpgradeProduct;
import costumetrade.product.service.ShareProductService;
import costumetrade.product.service.UpgradeProductService;

@RequestMapping("/product")
@Controller
public class ProductAction {
	@Autowired
	private UpgradeProductService upgradeProductService;
	@Autowired
	private ShareProductService shareProductService;
	@Autowired
	private SpProductService spProductService;
	@RequestMapping("/getList")
	@ResponseBody
	public ApiResponse getList() {
		List<ScUpgradeProduct> productList = upgradeProductService.getAllProduct();
		return  ApiResponse.getInstance(productList);
	}
	
	@RequestMapping("/getAllPromotionalProduct")
	@ResponseBody
	public ApiResponse getAllPromotionalProduct(String storeId,String openid,Integer pageNum) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(StringUtils.isBlank(openid)||StringUtils.isBlank(storeId)||pageNum==null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		List<ScPromotionalProduct> productList = shareProductService.getAllPromotionalProduct(storeId,openid, pageNum);
		if(productList==null||productList.size()<=0){
			result.setData(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
		}else{
			result.setData(productList);
		}
		return  result;
	}
	@RequestMapping("/getGroupPromotionalProduct")
	@ResponseBody
	public ApiResponse getGroupPromotionalProduct(String openid,Integer pageNum) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(StringUtils.isBlank(openid)||pageNum==null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		List<ScPromotionalProduct> productList = shareProductService.getGroupPromotionalProduct(openid, pageNum);
		if(productList==null||productList.size()<=0){
			result.setData(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
		}else{
			result.setData(productList);
		}
		return  result;
	}
	@RequestMapping("/getPromotionalProduct")
	@ResponseBody
	public ApiResponse getPromotionalProduct(ProductQuery query) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(query==null||StringUtil.isBlank(query.getId())){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		ScPromotionalProduct p = new ScPromotionalProduct();
		p = shareProductService.getPromotionalProduct(query.getId());
		
		query.setCheckAllTag(p.getCheckAllTag());
		if(StringUtil.isNotBlank(p.getProductIds())){
			query.setIdArray(Arrays.asList(p.getProductIds()));
		}
		if(query.getCheckAllTag()==null&&query.getIdArray()==null){
			result.setData(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}
		List<ProductQuery> productList = spProductService.getShareProduct(query);
		if(productList==null||productList.size()<=0){
			result.setData(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
		}else{
			result.setData(productList);
		}
		return  result;
	}
	
	@RequestMapping("/saveShareInfoToCustomers")
	@ResponseBody
	public ApiResponse saveShareInfoToCustomers(ScPromotionalProduct product) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(product == null||StringUtil.isBlank(product.getOpenid())||product.getCheckAllTag()==null
				||product.getProductIdArray()==null||product.getProductIdArray().size()<=0){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int save = shareProductService.saveShareInfoToCustomers(product);
		
		if(save<=0){
			result.setData(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
		}
		return  result;
	}
}
