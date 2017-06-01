package costumetrade.order.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.SpPBrand;
import costumetrade.order.service.SpPBrandService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/brand")
@Controller
public class SpPBrandController {
	@Autowired
	private SpPBrandService spPBrandService;

	@RequestMapping("/getAllBrands")
	@ResponseBody
	public ApiResponse getAllBrands(SpPBrand brand) {
		
		List<SpPBrand> CateLists = new ArrayList<SpPBrand>();
		CateLists = spPBrandService.getSpPBrands(brand);

		return  ApiResponse.getInstance(CateLists);
	}

	@RequestMapping("/saveBrand")
	@ResponseBody
	public ApiResponse saveBrand( SpPBrand spPBrand) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(spPBrand == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int save = spPBrandService.saveSpPBrand(spPBrand);
		if(save<=0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}else{
			result.setData(save);
		}
		return result;

	}

	@RequestMapping("/deleteBrand")
	@ResponseBody
	public ApiResponse deleteBrand(@RequestParam List<Integer> ids) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
	
		int delete = spPBrandService.deleteSpPBrand(ids);
		if(delete<=0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}
		return result;
	}
	
}
