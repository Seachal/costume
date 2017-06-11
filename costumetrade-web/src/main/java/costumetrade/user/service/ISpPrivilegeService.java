package costumetrade.user.service;

import java.util.List;

import costumetrade.user.domain.SpPrivilege;
import costumetrade.user.domain.SpPrivilegeEmployee;

public interface ISpPrivilegeService {

	List<SpPrivilege>  getSpPrivilegeList();
	/*获取员工权限*/
	List<SpPrivilegeEmployee>  getEmployeeSpPrivilegeList(String empId);
	
	boolean isPrivilegeExsist(String operate,String empId);
	
    int saveSpPrivilegeEmployees(List<SpPrivilegeEmployee> privilegeEmployees);
}
