package costumetrade.report.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.report.domain.FinanceReportQuery;
import costumetrade.report.domain.PurchaseReportQuery;
import costumetrade.report.mapper.SpReportMapper;
import costumetrade.report.service.SpReportService;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.mapper.ScWeChatMapper;

@Transactional
@Service
public class SpReportServiceImpl implements SpReportService{
	@Autowired
	private SpReportMapper spReportMapper;
	@Autowired
	private ScWeChatMapper scWeChatMapper;
	@Override
	public FinanceReportQuery financeReport(FinanceReportQuery query) {
		if(query.getTimeFrom() ==null ){
			query.setTimeFrom(setTimeFrom());
		}
		if(query.getTimeTo() == null){
			query.setTimeTo(setTimeTo());
		}
		ScWeChat wechat = scWeChatMapper.selectByOpenId(query.getOpenid());//根据当前操作者的openid 获取当前店铺storeId
		if(wechat !=null){//客户对账
			if(wechat.getStoreid()!=null){
				query.setStoreId(wechat.getStoreid());
			}else{
				return null;
			}
		}
		FinanceReportQuery report = spReportMapper.financeReport(query);
		return report;
	}
	@Override
	public List<Map<String,Object>> purchaseSortReport(
			PurchaseReportQuery query) {
		if(query.getTimeFrom() ==null ){
			query.setTimeFrom(setTimeFrom());
		}
		if(query.getTimeTo() == null){
			query.setTimeTo(setTimeTo());
		}
		ScWeChat wechat = scWeChatMapper.selectByOpenId(query.getOpenid());//根据当前操作者的openid 获取当前店铺storeId
		
		if(wechat !=null){
			if(wechat.getStoreid()!=null){
				query.setStoreId(wechat.getStoreid());
			}else{
				return null;
			}
		}
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> maps =  spReportMapper.purchaseSortReport(query);
		if(maps!=null){
			for(int i=0;i<maps.size();i++){
				Map<String,Object> map = maps.get(i);
				Map<String,Object> m = new HashMap<String, Object>();
				m.put(query.getFilter().getField(), map.get(query.getFilter().getField()));
				m.put("quantity", map.get("quantity"));
				m.put("amount", map.get("amount"));
				mapList.add(m);
				
			}
		}else{
			return mapList;
		}
		return mapList;
	}
	
	
	public Date setTimeFrom(){
		//如果时间没有传就是默认当天的 零点 到 24点
		Calendar calendar1 = Calendar.getInstance(); 
		calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),  
                0, 0, 0);  
		return calendar1.getTime();
	}
	public Date setTimeTo(){
		//如果时间没有传就是默认当天的 零点 到 24点
		Calendar calendar1 = Calendar.getInstance(); 
		calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),  
       		 23, 59, 59); 
		return calendar1.getTime();
	}

}
