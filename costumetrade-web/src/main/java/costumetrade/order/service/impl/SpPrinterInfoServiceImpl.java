package costumetrade.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpPrinterInfo;
import costumetrade.order.mapper.SpPrinterInfoMapper;
import costumetrade.order.service.SpPrinterInfoService;

@Transactional
@Service
public class SpPrinterInfoServiceImpl implements SpPrinterInfoService{
	@Autowired
	private SpPrinterInfoMapper spPrinterInfoMapper;
	
	public SpPrinterInfo getSpPrinterInfos(int  corpId) {

		return spPrinterInfoMapper.selectByPrimaryKey(corpId);
	}
	public int saveSpPrinterInfo(SpPrinterInfo spPrinterInfo) {
		int save = 0;
		//查询对应ID的员工是否存在，存在的话进行update 不存在save
		if(spPrinterInfo.getId() != null){

			SpPrinterInfo getColor = spPrinterInfoMapper.selectByPrimaryKey(spPrinterInfo.getId());
			if(getColor != null){
				save = spPrinterInfoMapper.updateByPrimaryKeySelective(spPrinterInfo);
			}else {
				save = spPrinterInfoMapper.insert(spPrinterInfo) ;
			}	
		}else {
			save = spPrinterInfoMapper.insert(spPrinterInfo) ;
		}
			
		 
		
		return save;
		
		 
	}
	public int deleteSpPrinterInfo(int id) {
	
		return spPrinterInfoMapper.deleteByPrimaryKey(id);
	}
	
	

}
