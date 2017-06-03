package costumetrade.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;

import costumetrade.user.domain.PriceJson;
import costumetrade.user.domain.SpCustProdPrice;
import costumetrade.user.domain.SpEmployee;
import costumetrade.user.domain.SpPrivilege;
import costumetrade.user.mapper.SpCustProdPriceMapper;
import costumetrade.user.mapper.SpEmployeeMapper;
import costumetrade.user.mapper.SpPrivilegeMapper;
import costumetrade.user.service.SpEmployeeService;

@Transactional
@Service
public class SpEmployeeServiceImpl implements SpEmployeeService{
	@Autowired
	private SpEmployeeMapper spEmployeeMapper;
	@Autowired
	private SpPrivilegeMapper spPrivilegeMapper;
	@Autowired
	private SpCustProdPriceMapper spCustProdPriceMapper;
	
	public SpEmployee employeeInit(String storeId){
		
		List<SpPrivilege> spPrivileges =  spPrivilegeMapper.getSpPrivilegeList();
		
		List<SpCustProdPrice> customerTypeList = new ArrayList<SpCustProdPrice>();
		//获取商品等级对应的折扣率和毛利率
		List<SpCustProdPrice> custProdPrice = new ArrayList<SpCustProdPrice>();
		SpCustProdPrice spCustProdPrice = new SpCustProdPrice();
		spCustProdPrice.setType(2+"");
		spCustProdPrice.setStoreid(Integer.valueOf(storeId));
		custProdPrice = spCustProdPriceMapper.select(spCustProdPrice);
		
		if(custProdPrice.size()>0){
			for(SpCustProdPrice price : custProdPrice){
				SpCustProdPrice prodPrice = new SpCustProdPrice();
				prodPrice.setId(Integer.parseInt(price.getProdgrade()));
				prodPrice.setCusttypename(price.getCusttypename());
				customerTypeList.add(prodPrice);
			}
		}
		SpEmployee spEmployee = new SpEmployee();
		spEmployee.setSpPrivileges(spPrivileges);
		spEmployee.setCustomerTypeList(customerTypeList);
		return spEmployee;
	}
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

	
		return spEmployee.getId();
		
		 
	}
	@Override
	public int deleteEmployee(SpEmployee spEmployee) {
		if(spEmployee.getId() == null){
			return 0;
		}
		
		
		return spEmployeeMapper.deleteByPrimaryKey(spEmployee);
	}
	
	

}
