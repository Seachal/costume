package costumetrade.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SpPCate;
import costumetrade.order.mapper.SpPCateMapper;
import costumetrade.order.service.SpPCateService;

@Transactional
@Service
public class SpPCateServiceImpl implements SpPCateService{
	@Autowired
	private SpPCateMapper spPCateMapper;
	
	public List<SpPCate> getSpPCates(SpPCate spPCate) {
		Page page = new Page();
		page.setPageNum(spPCate.getPageNum());
		return spPCateMapper.getSpPCates(spPCate.getStoreId(),page);
	}
	public int saveSpPCate(SpPCate spPCate) {
		int save = 0;
		//查询对应ID的员工是否存在，存在的话进行update 不存在save
		SpPCate getCate = spPCateMapper.getSpPCateByName(spPCate.getCatename(), spPCate.getStoreId());
		if(getCate != null){
			return save;
		}else {
			save = spPCateMapper.insert(spPCate) ;
		}
		return spPCate.getId();
		
		 
	}
	public int deleteSpPCate(List<Integer> ids) {
		return spPCateMapper.deleteByPrimaryKey( ids);
	}
	
	
	

}
