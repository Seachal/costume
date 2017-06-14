package costumetrade.order.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.common.util.DateFormatUtils;
import costumetrade.order.mapper.SpReportMapper;
import costumetrade.order.query.FinanceReportQuery;
import costumetrade.order.service.SpReportService;
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
		//如果时间没有传就是默认当天的 零点 到 24点
		Calendar calendar1 = Calendar.getInstance();  
		if(query.getTimeFrom() ==null ){
			
	        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),  
	                0, 0, 0);  
			query.setTimeFrom(calendar1.getTime());
		}
		if(query.getTimeTo() == null){
	        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),  
	        		 23, 59, 59);  
			query.setTimeTo(calendar1.getTime());
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
	

}
