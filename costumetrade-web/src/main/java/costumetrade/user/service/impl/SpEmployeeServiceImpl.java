package costumetrade.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.user.domain.SpEmployee;
import costumetrade.user.mapper.SpEmployeeMapper;
import costumetrade.user.service.SpEmployeeService;

@Transactional
@Service
public class SpEmployeeServiceImpl implements SpEmployeeService{
	@Autowired
	private SpEmployeeMapper spEmployeeMapper;
	@Override
	public List<SpEmployee> getAllEmployees(String subId) {

		return spEmployeeMapper.getAllEmployees(subId);
	}
	@Override
	public int saveEmployee(SpEmployee spEmployee) {
		int save = 0;
		if(spEmployee.getId() != null){

			SpEmployee getEmployee = spEmployeeMapper.selectByPrimaryKey(spEmployee);
			if(getEmployee != null){
				save = spEmployeeMapper.updateByPrimaryKeySelective(spEmployee);
			}
		}else {
			save = spEmployeeMapper.insert(spEmployee) ;
		}
		 
		//查询对应ID的员工是否存在，存在的话进行update 不存在save
		return save;
		
		 
	}
	@Override
	public int deleteEmployee(SpEmployee spEmployee) {
		if(spEmployee.getId() == null){
			return 0;
		}
		
		
		return spEmployeeMapper.deleteByPrimaryKey(spEmployee);
	}
	
	

}
