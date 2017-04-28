package costumetrade.user.service;

import java.util.List;

import costumetrade.user.domain.SpMenuEmployee;


public interface SpMenuEmployeeService {
	/*
	 * 获取所有员工权限
	 * 
	 * */
	public List<SpMenuEmployee> getAllMenuEmployees(Long employeeId);
	
	/*
	 * 新增员工权限
	 * 
	 * **/
	public int saveSpMenuEmployees(List<SpMenuEmployee> menuEmployees);
	
	/*
	 * 删除员工权限
	 * 
	 * */
	public int deleteByEmployeeId(Long employeeId);
}
