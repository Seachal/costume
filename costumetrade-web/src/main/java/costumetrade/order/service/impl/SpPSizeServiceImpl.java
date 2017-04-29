package costumetrade.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpPSize;
import costumetrade.order.mapper.SpPSizeMapper;
import costumetrade.order.service.SpPSizeService;

@Transactional
@Service
public class SpPSizeServiceImpl implements SpPSizeService{
	@Autowired
	private SpPSizeMapper spPSizeMapper;
	
	public List<SpPSize> getSpPSizes(int cropId) {

		return spPSizeMapper.getSpPSizes(cropId);
	}
	public int saveSpPSize(SpPSize spPSize) {
		int save = 0;
		//查询对应ID的员工是否存在，存在的话进行update 不存在save
		if(spPSize.getId() != null){
			SpPSize getSize = spPSizeMapper.selectByPrimaryKey(spPSize.getId());
			if(getSize != null){
				save = spPSizeMapper.updateByPrimaryKeySelective(spPSize);
			}else {
				save = spPSizeMapper.insert(spPSize) ;
			}
		}else {
			save = spPSizeMapper.insert(spPSize) ;
		}
		
		return save;
		 
	}
	public int deleteSpPSize(int id) {
	
		return spPSizeMapper.deleteByPrimaryKey(id);
	}
	
	

}
