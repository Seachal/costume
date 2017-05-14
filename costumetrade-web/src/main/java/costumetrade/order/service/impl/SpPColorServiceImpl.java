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
	
	public List<SpPColor> getSpPColors(int storeId) {

		return spPColorMapper.getSpPColors(storeId);
	}
	public int saveSpPColor(SpPColor spPColor) {
		int save = 0;
		//查询对应颜色是否存在，存在的话进行update 不存在save
		SpPColor getColor = spPColorMapper.selectByName(spPColor.getColorname(), spPColor.getStoreId());
		if(getColor != null){
			return save;
		}else {
			save = spPColorMapper.insert(spPColor) ;
		}
		return save;
		
		 
	}
	public int deleteSpPColor(int id) {
		return spPColorMapper.deleteByPrimaryKey(id);
	}
	
	

}
