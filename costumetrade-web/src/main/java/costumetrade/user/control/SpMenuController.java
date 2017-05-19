package costumetrade.user.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.user.domain.SpMenu;
import costumetrade.user.domain.SpMenuEmployee;
import costumetrade.user.service.SpMenuEmployeeService;
import costumetrade.user.service.SpMenuService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/menu")
@Controller
public class SpMenuController {
	@Autowired
	private SpMenuService spMenuService;

	@Autowired
	private SpMenuEmployeeService spMenuEmployeeService;
	
	@RequestMapping("/getAllMenus")
	@ResponseBody
	public ApiResponse getAllMenus() {

		ApiResponse result = new ApiResponse();
		List<SpMenu> menuLists = new ArrayList<SpMenu>();
		menuLists = spMenuService.getAllMenus();
		result.setData(menuLists);
		return result;
	}

	@RequestMapping("/addEmployeeMenus")
	@ResponseBody
	public ApiResponse addEmployeeMenus(Long empId,List<Long> menuIdList) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(null==empId || null==menuIdList || menuIdList.size()<=0){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		List<SpMenuEmployee> spMenuEmployees = new ArrayList<SpMenuEmployee>();
		
		for(Long menuId:menuIdList){
			SpMenuEmployee menuEmp = new SpMenuEmployee();
			menuEmp.setEmployeeId(empId);
			menuEmp.setMenuId(menuId);
			spMenuEmployees.add(menuEmp);
		}
	    spMenuEmployeeService.saveSpMenuEmployees(spMenuEmployees);
		return result;
	}
	
	@RequestMapping("/getEmployeeMenus")
	@ResponseBody
	public ApiResponse getEmployeeMenus(Long employeeId) {

		List<SpMenuEmployee> menuEmployeeLists = new ArrayList<SpMenuEmployee>();
		menuEmployeeLists = spMenuEmployeeService.getAllMenuEmployees(employeeId);

		return ApiResponse.getInstance(menuEmployeeLists);
	}
	
}
