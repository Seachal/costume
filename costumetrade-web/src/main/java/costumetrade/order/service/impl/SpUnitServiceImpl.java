package costumetrade.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpUnit;
import costumetrade.order.mapper.SpUnitMapper;
import costumetrade.order.service.SpUnitService;

@Transactional
@Service
public class SpUnitServiceImpl implements SpUnitService{
	@Autowired
	private SpUnitMapper spUnitMapper;
	
	public List<SpUnit> getSpUnits(SpUnit spUnit) {
		return spUnitMapper.getUnits(spUnit.getStoreid(),spUnit.getPage());
	}
	public int saveSpUnit(SpUnit spUnit) {
		int save = 0;
		//查询对应ID的员工是否存在，存在的话进行update 不存在save
		SpUnit getCate = spUnitMapper.getSpUnitByName(spUnit.getUnit(), spUnit.getStoreid());
		if(getCate != null){
			return save;
		}else {
			save = spUnitMapper.insert(spUnit) ;
		}
		return save;
		
		 
	}
	public int deleteSpUnit(List<Integer> ids) {
		return spUnitMapper.deleteByPrimaryKey( ids);
	}
	
	
	

}
