package costumetrade.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpPBrand;
import costumetrade.order.mapper.SpPBrandMapper;
import costumetrade.order.service.SpPBrandService;

@Transactional
@Service
public class SpPBrandServiceImpl implements SpPBrandService{
	@Autowired
	private SpPBrandMapper spPBrandMapper;
	
	public List<SpPBrand> getSpPBrands(int corpId) {

		return spPBrandMapper.getSpPBrands(corpId);
	}
	public int saveSpPBrand(SpPBrand spPBrand) {
		int save = 0;
		//查询对应ID的员工是否存在，存在的话进行update 不存在save
	
		if(spPBrand.getId() != null){
			
			SpPBrand getBrand = spPBrandMapper.selectByPrimaryKey(spPBrand.getId());
			if(getBrand != null){
				save = spPBrandMapper.updateByPrimaryKeySelective(spPBrand);
			}else {
				save = spPBrandMapper.insert(spPBrand) ;
			}
		}else {
			save = spPBrandMapper.insert(spPBrand) ;
		}
		 
		
		return save;
		
		 
	}
	public int deleteSpPBrand(int  id) {
	
		return spPBrandMapper.deleteByPrimaryKey(id);
	}
	
	

}
