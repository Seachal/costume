package costumetrade.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpPColor;
import costumetrade.order.mapper.SpPColorMapper;
import costumetrade.order.service.SpPColorService;

@Transactional
@Service
public class SpPColorServiceImpl implements SpPColorService{
	@Autowired
	private SpPColorMapper spPColorMapper;
	
	public List<SpPColor> getSpPColors(int cropId) {

		return spPColorMapper.getSpPColors(cropId);
	}
	public int saveSpPColor(SpPColor spPColor) {
		int save = 0;
		//查询对应ID的员工是否存在，存在的话进行update 不存在save
		if(spPColor.getId() != null){

			SpPColor getColor = spPColorMapper.selectByPrimaryKey(spPColor.getId());
			if(getColor != null){
				save = spPColorMapper.updateByPrimaryKeySelective(spPColor);
			}	
		}else {
			save = spPColorMapper.insert(spPColor) ;
		}
			
		 
		
		return save;
		
		 
	}
	public int deleteSpPColor(int id) {
	
		return spPColorMapper.deleteByPrimaryKey(id);
	}
	
	

}
