package costumetrade.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SpPBrand;
import costumetrade.order.enums.ResultTypeEnum;
import costumetrade.order.mapper.SpPBrandMapper;
import costumetrade.order.service.SpPBrandService;

@Transactional
@Service
public class SpPBrandServiceImpl implements SpPBrandService{
	@Autowired
	private SpPBrandMapper spPBrandMapper;
	
	public List<SpPBrand> getSpPBrands(SpPBrand spPBrand) {
		Page page = new Page();
		page.setPageNum(spPBrand.getPageNum());
		return spPBrandMapper.getSpPBrands(spPBrand.getStoreId(),page);
	}
	public int saveSpPBrand(SpPBrand spPBrand) {
		int save = 0;
		//查询对应品牌是否存在，存在的话进行update 不存在save
		SpPBrand getBrand = spPBrandMapper.getSpPBrandByName(spPBrand.getBrandname(), spPBrand.getStoreId());
		if(getBrand != null){
			return ResultTypeEnum.RESULT_EXISTS.getCode();
		}
		if(spPBrand!=null&&spPBrand.getId()==null) {
			save = spPBrandMapper.insert(spPBrand) ;
		}else{
			save = spPBrandMapper.updateByPrimaryKeySelective(spPBrand);
		}
		return spPBrand.getId();
		
		 
	}
	public int deleteSpPBrand(List<Integer>  ids) {
	
		return spPBrandMapper.deleteByPrimaryKey(ids);
	}
	
	

}
