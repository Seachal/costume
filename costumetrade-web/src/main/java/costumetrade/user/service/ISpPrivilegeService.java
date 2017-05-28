package costumetrade.user.service;

import java.util.List;

import costumetrade.user.domain.SpPrivilege;
import costumetrade.user.domain.SpPrivilegeEmployee;

public interface ISpPrivilegeService {

	List<SpPrivilege>  getSpPrivilegeList();
	
	boolean isPrivilegeExsist(String operate,String empId);
	
    int saveSpPrivilegeEmployees(List<SpPrivilegeEmployee> privilegeEmployees);
}
