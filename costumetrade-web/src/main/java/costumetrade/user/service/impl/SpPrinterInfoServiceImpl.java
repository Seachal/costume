package costumetrade.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.user.domain.ScPrinterInfo;
import costumetrade.user.mapper.ScPrinterInfoMapper;
import costumetrade.user.service.SpPrinterInfoService;


@Transactional
@Service
public class SpPrinterInfoServiceImpl implements SpPrinterInfoService{
	@Autowired
	private ScPrinterInfoMapper scPrinterInfoMapper;
	
	public ScPrinterInfo getSpPrinterInfos(String  storeId) {

		return scPrinterInfoMapper.selectByPrimaryKey(storeId);
	}
	public int saveSpPrinterInfo(ScPrinterInfo spPrinterInfo) {
		int save = 0;
		//查询对应打印配置是否存在，存在的话进行update 不存在save
		ScPrinterInfo printer = scPrinterInfoMapper.selectByPrimaryKey(spPrinterInfo.getStoreid());
		if(printer == null){
			save = scPrinterInfoMapper.insert(spPrinterInfo) ;
		}else {
			save = scPrinterInfoMapper.updateByPrimaryKeySelective(spPrinterInfo);
		}
		return save;
		
		 
	}


}
