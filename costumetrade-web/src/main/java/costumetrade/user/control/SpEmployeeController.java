package costumetrade.user.control;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.cache.Cache;
import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.user.domain.SpEmployee;
import costumetrade.user.domain.SpPrivilegeEmployee;
import costumetrade.user.service.ISpPrivilegeService;
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
	@Autowired
	private ISpPrivilegeService privilegeService;
	
	@RequestMapping("/getAllEmployees")
	@ResponseBody
	public ApiResponse getAllEmployees(String storeId) {
		
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(storeId == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.name());
			return result;
		}
		List<SpEmployee> employeeLists = new ArrayList<SpEmployee>();
		employeeLists = spEmployeeService.getAllEmployees(storeId);

		return  ApiResponse.getInstance(employeeLists);
	}
	
	@RequestMapping("/employeeInit")
	@ResponseBody
	public ApiResponse employeeInit(String storeId) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(storeId == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.name());
			return result;
		}
		
		SpEmployee employee = spEmployeeService.employeeInit(storeId);
		
		return  ApiResponse.getInstance(employee);
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
		
		List<SpPrivilegeEmployee> privilegeEmployees = new ArrayList<SpPrivilegeEmployee>();
		
		for(Long privilegeId:spEmployee.getPrivilegeIds()){
			SpPrivilegeEmployee privilegeEmp = new SpPrivilegeEmployee();
			privilegeEmp.setEmployeeId(Long.valueOf(spEmployee.getId()));
			privilegeEmp.setPrivilegeId(privilegeId);;
			privilegeEmployees.add(privilegeEmp);
		}
		privilegeService.saveSpPrivilegeEmployees(privilegeEmployees);
		
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
	@RequestMapping("/getEmployee")
	@ResponseBody
	public ApiResponse getEmployee(String empId) {
		
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		if(StringUtils.isBlank(empId) ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		SpEmployee employee = spEmployeeService.getEmployee(empId);
		if(employee ==null){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(employee);
		}
		return result;
	}
	
}
