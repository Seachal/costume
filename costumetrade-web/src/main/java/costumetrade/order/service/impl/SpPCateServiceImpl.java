package costumetrade.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpPCate;
import costumetrade.order.mapper.SpPCateMapper;
import costumetrade.order.service.SpPCateService;

@Transactional
@Service
public class SpPCateServiceImpl implements SpPCateService{
	@Autowired
	private SpPCateMapper spPCateMapper;
	
	public List<SpPCate> getSpPCates(int cropId) {

		return spPCateMapper.getSpPCates(cropId);
	}
	public int saveSpPCate(SpPCate spPCate) {
		int save = 0;
		//查询对应ID的员工是否存在，存在的话进行update 不存在save
		if(spPCate.getId() != null){
			
			SpPCate getCate = spPCateMapper.selectByPrimaryKey(spPCate.getId());
			if(getCate != null){
				save = spPCateMapper.updateByPrimaryKeySelective(spPCate);
			}else {
				save = spPCateMapper.insert(spPCate) ;
			}
		}else {
			save = spPCateMapper.insert(spPCate) ;
		}
		 
		
		return save;
		
		 
	}
	public int deleteSpPCate(int id) {
	
		return spPCateMapper.deleteByPrimaryKey(id);
	}
	
	
	

}
