package costumetrade.user.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.cache.Cache;
import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.user.domain.SpCustProdPrice;
import costumetrade.user.domain.SpCustomerType;
import costumetrade.user.domain.SsDataDictionary;
import costumetrade.user.service.SsDataDictionaryService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/dictionary")
@Controller
public class SsDataDictionaryController {
	@Autowired
	private SsDataDictionaryService ssDataDictionaryService;
	@Autowired
	private Cache cache;
	
	@RequestMapping("/getDataDictionarys")
	@ResponseBody
	public ApiResponse getDataDictionarys(String storeId) {
		return  ApiResponse.getInstance(ssDataDictionaryService.getDataDictionarys(storeId));
	}

	@RequestMapping("/saveDataDictionary")
	@ResponseBody
	public ApiResponse saveDataDictionary(@RequestBody List<SsDataDictionary> dictionarys) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(dictionarys == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.name());
			return result;
		}
		Integer save = ssDataDictionaryService.saveDataDictionary(dictionarys);
		if(save <= 0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}
		return result;

	}
	@RequestMapping("/saveTypeOrGradeRate")
	@ResponseBody
	public ApiResponse saveTypeOrGradeRate(@RequestBody List<SpCustProdPrice> spCustProdPrices) {
		
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(spCustProdPrices == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.name());
			return result;
		}
		int save = ssDataDictionaryService.saveTypeOrGradeRate(spCustProdPrices);
		if(save<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;
		
	}
	@RequestMapping("/getTypeOrGradeRate")
	@ResponseBody
	public ApiResponse getTypeOrGradeRate(SpCustProdPrice spCustProdPrice) {
		
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(spCustProdPrice == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.name());
			return result;
		}
		SpCustProdPrice price = ssDataDictionaryService.getTypeOrGradeRate(spCustProdPrice);
		if(price == null){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(price);
		}
		return result;
		
	}
	@RequestMapping("/saveCustomerType")
	@ResponseBody
	public ApiResponse saveCustomerType(@RequestBody SpCustomerType customerType) {
		
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(customerType == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.name());
			return result;
		}
		SpCustomerType type = ssDataDictionaryService.saveCustomType(customerType);
		if(type == null){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(type);
		}
		return result;
		
	}

	@RequestMapping("/deleteDataDictionary")
	@ResponseBody
	public ApiResponse deleteDataDictionary(Integer id) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		if(id == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		List<SsDataDictionary> dict = ssDataDictionaryService.deleteDataDictionary(id);
		if(dict == null){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			//result.setData(dict);
		}
		return result;
	}
	
	@RequestMapping("/initCustomType")
	@ResponseBody
	public ApiResponse initCustomType(SsDataDictionary dictionary){
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		return result;
	}
	
}
