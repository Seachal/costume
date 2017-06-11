package costumetrade.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.user.domain.SpPrivilege;
import costumetrade.user.domain.SpPrivilegeEmployee;
import costumetrade.user.mapper.SpPrivilegeEmployeeMapper;
import costumetrade.user.mapper.SpPrivilegeMapper;
import costumetrade.user.service.ISpPrivilegeService;
@Service
@Transactional
public class SpPrivilegeService implements ISpPrivilegeService {
	@Autowired
	private SpPrivilegeMapper spPrivilegeMapper;
	@Autowired
	private SpPrivilegeEmployeeMapper spPrivilegeEmployeeMapper;
	
	@Override
	public List<SpPrivilege> getSpPrivilegeList() {
		return spPrivilegeMapper.getSpPrivilegeList();
	}

	@Override
	public boolean isPrivilegeExsist(String operate,String empId) {
        Map<String,String> paramMap  = new HashMap<String,String>();
        paramMap.put("operateCode", operate);
        paramMap.put("empId", empId);
        SpPrivilege tempPrivilege = spPrivilegeMapper.isPrivilegeExsist(paramMap);
        
		return null!=tempPrivilege && 0l!=tempPrivilege.getId();
	}
	
	public int saveSpPrivilegeEmployees(List<SpPrivilegeEmployee> privilegeEmployees){
		//每次传值为员工所勾选的权限，先对之前的权限记录删除，再保存新的权限
		deleteByEmployeeId(privilegeEmployees.get(0).getEmployeeId());
		int saveMenu = 0;

		saveMenu = spPrivilegeEmployeeMapper.saveSpPrivilegeEmployees(privilegeEmployees);
		if(saveMenu > 0){
			return 1;
		}else{
			return 0;
		}
	}
	
	public int deleteByEmployeeId(Long employeeId) {
		
		return  spPrivilegeEmployeeMapper.deleteByPrimaryKey(employeeId);
	}

	@Override
	public List<SpPrivilegeEmployee> getEmployeeSpPrivilegeList(String empId) {
		SpPrivilegeEmployee record =new SpPrivilegeEmployee();
		record.setEmployeeId(Long.parseLong(empId));
		return spPrivilegeEmployeeMapper.getEmployeeSpPrivilegeList(record);
	}
}
