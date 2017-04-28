package costumetrade.user.service;

import java.util.List;

import costumetrade.user.domain.SpEmployee;


public interface SpEmployeeService {
	/*
	 * 获取所有员工信息
	 * 
	 * */
	public List<SpEmployee> getAllEmployees(String subId);
	/**
	 * 保存员工信息
	 * 
	 * */
	public int saveEmployee(SpEmployee spEmployee);
	
	/**
	 * 删除员工信息
	 * 
	 * */
	public int deleteEmployee(SpEmployee spEmployee);
}
