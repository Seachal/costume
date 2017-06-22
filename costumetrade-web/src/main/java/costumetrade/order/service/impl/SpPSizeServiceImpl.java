package costumetrade.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpPSize;
import costumetrade.order.enums.ResultTypeEnum;
import costumetrade.order.mapper.SpPSizeMapper;
import costumetrade.order.service.SpPSizeService;

@Transactional
@Service
public class SpPSizeServiceImpl implements SpPSizeService{
	@Autowired
	private SpPSizeMapper spPSizeMapper;
	
	public List<SpPSize> getSpPSizes(int storeId,String productId) {
		SpPSize size = new SpPSize();
		size.setStoreId(storeId);
		size.setProductId(productId);
		return spPSizeMapper.getSpPSizes(size);
	}
	public int saveSpPSize(SpPSize spPSize) {
		int save = 0;
		//查询对应yanse是否存在，存在的话进行update 不存在save
		SpPSize getSize = spPSizeMapper.selectByName(spPSize);
		if(getSize != null){
			return ResultTypeEnum.RESULT_EXISTS.getCode();
		}else {
			save = spPSizeMapper.insert(spPSize) ;
		}
		return spPSize.getId();
		 
	}
	public int deleteSpPSize(List<Integer> ids) {
		return spPSizeMapper.deleteByPrimaryKey(ids);
	}
	
	

}
