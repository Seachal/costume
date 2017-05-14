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
	
	public List<SpPBrand> getSpPBrands(int storeId) {

		return spPBrandMapper.getSpPBrands(storeId);
	}
	public int saveSpPBrand(SpPBrand spPBrand) {
		int save = 0;
		//查询对应品牌是否存在，存在的话进行update 不存在save
		SpPBrand getBrand = spPBrandMapper.getSpPBrandByName(spPBrand.getBrandname(), spPBrand.getStoreId());
		if(getBrand != null){
			return save;
		}else {
			save = spPBrandMapper.insert(spPBrand) ;
		}
		return save;
		
		 
	}
	public int deleteSpPBrand(int  id) {
	
		return spPBrandMapper.deleteByPrimaryKey(id);
	}
	
	

}
