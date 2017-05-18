package costumetrade.user.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.cache.Cache;
import costumetrade.cache.CacheableLong;
import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.user.domain.SpCustomerType;
import costumetrade.user.domain.SpEmployee;
import costumetrade.user.domain.SsDataDictionary;
import costumetrade.user.service.SpEmployeeService;
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
	public ApiResponse getDataDictionarys(Integer storeId) {
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
		List<SsDataDictionary> dict = ssDataDictionaryService.saveDataDictionary(dictionarys);
		if(dict == null){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(dict);
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
