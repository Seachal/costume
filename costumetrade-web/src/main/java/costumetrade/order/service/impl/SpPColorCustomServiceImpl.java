package costumetrade.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpPColorCustom;
import costumetrade.order.mapper.SpPColorCustomMapper;
import costumetrade.order.service.SpPColorCustomService;

@Transactional
@Service
public class SpPColorCustomServiceImpl implements SpPColorCustomService{
	@Autowired
	private SpPColorCustomMapper spPColorCustomMapper;
	
	public List<SpPColorCustom> getSpPColorCustoms(int cropId) {

		return spPColorCustomMapper.getSpPColorCustoms(cropId);
	}
	public int saveSpPColorCustom(SpPColorCustom spPColorCustom) {
		int save = 0;
		//查询对应ID是否存在，存在的话进行update 不存在save
		if(spPColorCustom.getId() != null){
			
			SpPColorCustom getColor = spPColorCustomMapper.selectByPrimaryKey(spPColorCustom.getId());
			if(getColor != null){
				save = spPColorCustomMapper.updateByPrimaryKeySelective(spPColorCustom);
			}	
		}else {
			save = spPColorCustomMapper.insert(spPColorCustom) ;
		}
		return save;
		
		 
	}
	public int deleteSpPColorCustom(int id) {
	
		return spPColorCustomMapper.deleteByPrimaryKey(id);
	}

}
