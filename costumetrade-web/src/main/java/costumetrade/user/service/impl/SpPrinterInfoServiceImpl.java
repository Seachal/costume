package costumetrade.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.httpclient.HttpClientUtils;

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
		
		if("1".equals(spPrinterInfo.getPrintType())){
			String user = getPrintUsaerId(spPrinterInfo);
			JSONObject json = JSON.parseObject(user);
			String userid = json.getString("showapi_userid");
			spPrinterInfo.setPrintUserId(userid);
		}
		if(printer == null){
			save = scPrinterInfoMapper.insert(spPrinterInfo) ;
		}else {
			save = scPrinterInfoMapper.updateByPrimaryKeySelective(spPrinterInfo);
		}
		return save;
		
		 
	}
	public String getPrintUsaerId(ScPrinterInfo spPrinterInfo){
		String url = "http://open.memobird.cn/home/setuserbind";
        Map<String,String> paramMap  = new HashMap<String,String>();
        paramMap.put("ak", "2ee8fdef2aff4871be0b45c824772c52");
        paramMap.put("timestamp",new Date().toString() );
        paramMap.put("memobirdID", spPrinterInfo.getPrintid());
        paramMap.put("useridentifying", new Date().getTime()+"" );
        String str =null;
        try {
        	str = HttpClientUtils.post(url, paramMap,"utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} 
        return str;
	}

}
