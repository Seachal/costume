package costumetrade.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpPSize;
import costumetrade.order.domain.SpPSizeKey;
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
		SpPSizeKey spPSizeKey = new SpPSizeKey();
		if(spPSize.getId() != null && spPSize.getCorpid() != null){
			spPSizeKey.setId(spPSize.getId());
			spPSizeKey.setCorpid(spPSize.getCorpid());

			SpPSize getSize = spPSizeMapper.selectByPrimaryKey(spPSizeKey);
			if(getSize != null){
				save = spPSizeMapper.updateByPrimaryKeySelective(spPSize);
			}else {
				save = spPSizeMapper.insert(spPSize) ;
			}
		}
		 
		
		return save;
		
		 
	}
	public int deleteSpPSize(SpPSizeKey spPSizeKey) {
	
		return spPSizeMapper.deleteByPrimaryKey(spPSizeKey);
	}
	
	

}
