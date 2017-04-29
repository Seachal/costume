package costumetrade.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpPBrand;
import costumetrade.order.domain.SpPBrandKey;
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
		SpPBrandKey spPBrandKey = new SpPBrandKey();
		if(spPBrand.getId() != null && spPBrand.getCorpid() != null){
			spPBrandKey.setId(spPBrand.getId());
			spPBrandKey.setCorpid(spPBrand.getCorpid());

			SpPBrand getSize = spPBrandMapper.selectByPrimaryKey(spPBrandKey);
			if(getSize != null){
				save = spPBrandMapper.updateByPrimaryKeySelective(spPBrand);
			}else {
				save = spPBrandMapper.insert(spPBrand) ;
			}
		}
		 
		
		return save;
		
		 
	}
	public int deleteSpPBrand(SpPBrandKey spPBrandKey) {
	
		return spPBrandMapper.deleteByPrimaryKey(spPBrandKey);
	}
	
	

}