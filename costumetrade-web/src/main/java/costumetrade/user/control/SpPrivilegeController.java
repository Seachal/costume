package costumetrade.user.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.user.domain.SpMenuEmployee;
import costumetrade.user.domain.SpPrivilege;
import costumetrade.user.domain.SpPrivilegeEmployee;
import costumetrade.user.service.ISpPrivilegeService;

@RequestMapping("/privilege")
@Controller
public class SpPrivilegeController {

	private ISpPrivilegeService privilegeService;
	
	@RequestMapping("/getList")
	@ResponseBody
	public ApiResponse getList() {
		ApiResponse result = new ApiResponse();
		List<SpPrivilege> privilegeList  = new ArrayList<SpPrivilege>();
		privilegeList = privilegeService.getSpPrivilegeList();
		result.setData(privilegeList);
		
		return result;
	}
	@RequestMapping("/addEmployeePrivilege")
	@ResponseBody
	public ApiResponse addEmployeeMenus(Long empId,List<Long> privilegeIdList) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(null==empId || null==privilegeIdList || privilegeIdList.size()<=0){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		List<SpPrivilegeEmployee> privilegeEmployees = new ArrayList<SpPrivilegeEmployee>();
		
		for(Long privilegeId:privilegeIdList){
			SpPrivilegeEmployee privilegeEmp = new SpPrivilegeEmployee();
			privilegeEmp.setEmployeeId(empId);
			privilegeEmp.setPrivilegeId(privilegeId);;
			privilegeEmployees.add(privilegeEmp);
		}
		privilegeService.saveSpPrivilegeEmployees(privilegeEmployees);
		return result;
	}
	
	@RequestMapping("/isPrivilegeExsist")
	@ResponseBody
	public ApiResponse isPrivilegeExsist(String operateCode,String empId) {
		ApiResponse result = new ApiResponse();
		Boolean isExsist = privilegeService.isPrivilegeExsist(operateCode, empId);
		result.setData(isExsist);
		return result;
	}
}
