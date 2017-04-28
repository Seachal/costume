package costumetrade.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.user.domain.SpMenuEmployee;
import costumetrade.user.mapper.SpMenuEmployeeMapper;
import costumetrade.user.service.SpMenuEmployeeService;

@Transactional
@Service
public class SpMenuEmployeeServiceImpl implements SpMenuEmployeeService{
	@Autowired
	private SpMenuEmployeeMapper spMenuEmployeeMapper;

	@Override
	public List<SpMenuEmployee> getAllMenuEmployees(Long employeeId) {
		return spMenuEmployeeMapper.getAllMenuEmployees(employeeId);
	}

	@Override
	public int saveSpMenuEmployees(List<SpMenuEmployee> menuEmployees) {
		//每次传值为员工所勾选的权限，先对之前的权限记录删除，再保存新的权限
		deleteByEmployeeId(menuEmployees.get(0).getEmployeeId());
		int saveMenu = 0;

		saveMenu = spMenuEmployeeMapper.saveSpMenuEmployees(menuEmployees);
		if(saveMenu > 0){
			return 1;
		}else{
			return 0;
		}
		
	}

	@Override
	public int deleteByEmployeeId(Long employeeId) {
		
		return  spMenuEmployeeMapper.deleteByEmployeeId(employeeId);
	}

	

}
