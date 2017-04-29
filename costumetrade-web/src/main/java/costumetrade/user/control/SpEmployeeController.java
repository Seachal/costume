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
import costumetrade.user.domain.SpEmployee;
import costumetrade.user.service.SpEmployeeService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/employee")
@Controller
public class SpEmployeeController {
	@Autowired
	private SpEmployeeService spEmployeeService;
	@Autowired
	private Cache cache;
	
	@RequestMapping("/getAllEmployees")
	@ResponseBody
	public ApiResponse getAllEmployees(String subId) {
		
		List<SpEmployee> employeeLists = new ArrayList<SpEmployee>();
		employeeLists = spEmployeeService.getAllEmployees(subId);
		CacheableLong cacheLong = (CacheableLong)cache.get("产品_品牌_样式_id");
		if(null!=cacheLong){
			cache.add("产品_品牌_样式_id", new CacheableLong(cacheLong.getLong()+2l));
		}else{
			CacheableLong  count = new CacheableLong(10);
			cache.add("产品_品牌_样式_id", count);
		}
		return  ApiResponse.getInstance(employeeLists);
	}

	@RequestMapping("/saveEmployee")
	@ResponseBody
	public ApiResponse saveEmployee(SpEmployee spEmployee) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(spEmployee == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg("员工信息为空，不能保存");
			return result;
		}
		int save = spEmployeeService.saveEmployee(spEmployee);
		if(save<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;

	}

	@RequestMapping("/deleteEmployee")
	@ResponseBody
	public ApiResponse deleteEmployee(@RequestParam SpEmployee spEmployee) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		if(spEmployee == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int delete = spEmployeeService.deleteEmployee(spEmployee);
		if(delete<=0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		return result;
	}
	
}
