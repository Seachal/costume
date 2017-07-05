package costumetrade.report.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.common.page.Page;
import costumetrade.common.util.StringUtil;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.query.Rules;
import costumetrade.order.service.SpOrderService;
import costumetrade.report.domain.FinanceReportQuery;
import costumetrade.report.domain.GeneralReportQuery;
import costumetrade.report.domain.PayTypeQuery;
import costumetrade.report.domain.ProductReportQuery;
import costumetrade.report.domain.PurchaseReportQuery;
import costumetrade.report.domain.ReportQuery;
import costumetrade.report.domain.SaleReportQuery;
import costumetrade.report.mapper.SpReportMapper;
import costumetrade.report.service.SpReportService;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpEmployee;
import costumetrade.user.domain.SpStore;
import costumetrade.user.domain.SsDataDictionary;
import costumetrade.user.domain.SsPayment;
import costumetrade.user.mapper.ScWeChatMapper;
import costumetrade.user.mapper.SpEmployeeMapper;
import costumetrade.user.mapper.SpStoreMapper;
import costumetrade.user.mapper.SsDataDictionaryMapper;
import costumetrade.user.mapper.SsPaymentMapper;

@Transactional
@Service
public class SpReportServiceImpl implements SpReportService{
	@Autowired
	private SpReportMapper spReportMapper;
	@Autowired
	private ScWeChatMapper scWeChatMapper;
	@Autowired
	private SpEmployeeMapper spEmployeeMapper;
	@Autowired
	private SpStoreMapper spStoreMapper;
	@Autowired
	private SsDataDictionaryMapper ssDataDictionaryMapper;
	@Autowired
	private SpOrderService spOrderService;
	@Autowired
	private SsPaymentMapper ssPaymentMapper;
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
	public ReportQuery purchaseAnalysisReport(
			PurchaseReportQuery query) {
		ReportQuery reportQuery = new ReportQuery();
	
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
		List<Map<String,Object>> maps =  spReportMapper.purchaseAnalysisReport(query);
		if(maps!=null){
			for(int i=0;i<maps.size();i++){
				Map<String,Object> map = maps.get(i);
				Map<String,Object> m = new HashMap<String, Object>();
				String createBy  = (String) map.get("createBy");
				//查询创建人中文
				if(StringUtil.isNotBlank(createBy)){
					ScWeChat we = scWeChatMapper.selectByPrimaryKey(Integer.parseInt(createBy));
					if(we!=null){
						if(StringUtil.isNotBlank(we.getEmpid()+"")){//操作者是员工
							SpEmployee e  = new SpEmployee();
							e.setId(we.getEmpid());
							e = spEmployeeMapper.selectByPrimaryKey(e);
							createBy = e.getName();
						}else if(StringUtil.isNotBlank(we.getStoreid()+"")){//操作者是店家
							SpStore store = new SpStore();
							store = spStoreMapper.selectByPrimaryKey(we.getStoreid());
							createBy = store.getName();
						}
					}
					m.put("createBy", createBy);
				}
				m.put(query.getFilter().getField(), map.get(query.getFilter().getField()));
				m.put("quantity", map.get("quantity"));
				mapList.add(m);
			}
		}
		List<Map<String,Object>> mapList1 = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> maps1 =  spReportMapper.realTimeInventory(query,null);
		if(maps1!=null){
			for(int i=0;i<maps1.size();i++){
				Map<String,Object> map = maps1.get(i);
				Map<String,Object> m = new HashMap<String, Object>();
				m.put(query.getFilter().getField(), map.get(query.getFilter().getField()));
				m.put("quantity", map.get("quantity"));
				mapList1.add(m);
				
			}
		}
		reportQuery.setInvReportQuerys(mapList1);
		reportQuery.setPurchaseQuerys(mapList);
		return reportQuery;
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
	@SuppressWarnings("unused")
	@Override
	public ReportQuery purchaseReport(PurchaseReportQuery query) {
		ReportQuery reportQuery = new ReportQuery();
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
	
		if(query.getSort() != null){
			if("quantityOp".equals(query.getSort().getValue())){
				query.setQuantityOp(query.getSort().getOp());
			}else if("amountOp".equals(query.getSort().getValue())){
				query.setAmountOp(query.getSort().getOp());
			}
		}
		List<Rules> rules = query.getRules();
		List<String> clientIdArray = new ArrayList<String>();
		if(rules != null && rules.size()>0){
			for(int i=0 ; i< rules.size() ;i++){
				if("productTypeArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					query.setProductTypeArray(rules.get(i).getValue());
				}else if("productBrandArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					query.setProductBrandArray(rules.get(i).getValue());
				}if("productSeasonArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					query.setProductSeasonArray(rules.get(i).getValue());
				}if("productColorArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					query.setProductColorArray(rules.get(i).getValue());
				}if("productSizeArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					query.setProductSizeArray(rules.get(i).getValue());
				}if("operatorArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					query.setOperatorArray(rules.get(i).getValue());
				}if("productClientCustomerArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					query.setProductClientCustomerArray(rules.get(i).getValue());
				}if("productClientSuppierArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					query.setProductClientSuppierArray(rules.get(i).getValue());
				}
			}
		}
		//供应商ID 和 客户ID  合并
		if(query.getProductClientCustomerArray()!=null && query.getProductClientCustomerArray().size()>0){
			for(String s: query.getProductClientCustomerArray()){
				clientIdArray.add(s);
			}
		}
		if(query.getProductClientSuppierArray()!=null && query.getProductClientSuppierArray().size()>0){
			for(String s: query.getProductClientSuppierArray()){
				clientIdArray.add(s);
			}
		}
		if(clientIdArray.size()>0){
			query.setClientIdArray(clientIdArray);
		}else{
			query.setClientIdArray(null);
		}
		Page page = new Page();
		page.setPageNum(query.getPageNum());
		//通过过滤查询条件，找到商品 商品列表默认十个
		List<PurchaseReportQuery> maps1 =  spReportMapper.purchaseReport3(query,page);
		List<String> productNames = new ArrayList<String>();
		if(maps1 !=null&& maps1.size()>0){
			for(PurchaseReportQuery q : maps1){
				productNames.add(q.getProductName());
			}
		}
		//通过商品，再根据商品分组汇总
		List<PurchaseReportQuery> maps2 = null;
		if(productNames.size()>0){
			query.setProductNameArray(productNames);
			maps2 =spReportMapper.purchaseReport1(query);
		}
		
		List<ProductReportQuery> maps3 =null;
		if(maps2 !=null){
			
			reportQuery.setPurchaseReportQuerys(maps2);
			
			ProductReportQuery q = new ProductReportQuery();
			q.setTimeFrom(query.getTimeFrom());
			q.setTimeTo(query.getTimeTo());
			q.setProductName(maps2.get(0).getProductName());
			q.setStoreId(query.getStoreId());
			q.setReportType(query.getReportType());
			ReportQuery r =  purchaseReportByProductName(q);
			
			reportQuery.setProductReportQuerys(r.getProductReportQuerys());
		}
		
		return reportQuery;
	}
	
	public  List<ProductReportQuery> getDatePoor(ProductReportQuery q) {
		 
	    long nh = 1000 * 60 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = q.getTimeTo().getTime()-q.getTimeFrom().getTime();
	    // 计算差多少小时
	    long hour = diff / nh +1;
	    long timeInterval = hour / 8;
	    List<Date> timeFroms = new ArrayList<Date>();
	    timeFroms.add(q.getTimeFrom());
	    List<Date> timeTos = new ArrayList<Date>();
	    Date startDate = q.getTimeFrom();
	    for(int i=0;i<7; i++){
	    	Calendar c=Calendar.getInstance();
		    c.setTime(startDate);
		    c.add(Calendar.HOUR_OF_DAY, Integer.parseInt(timeInterval+""));
	    	if(c.getTime().getTime()<q.getTimeTo().getTime()){
	    		 timeFroms.add(c.getTime());
	    		 timeTos.add(c.getTime());
	    	}
	    	startDate = c.getTime();
	    }
	    timeTos.add(q.getTimeTo());
	    q.setTimeFroms(timeFroms);
	    q.setTimeTos(timeTos);
	    List<ProductReportQuery> querys = new ArrayList<ProductReportQuery>();
	    for(int i=0;i<timeFroms.size();i++){
	    	ProductReportQuery report = new ProductReportQuery();
	    	report.setProductName(q.getProductName());
	    	report.setTimeFrom(timeFroms.get(i));
	    	report.setTimeTo(timeTos.get(i));
	    	report.setStoreId(q.getStoreId());
	    	report.setReportType(q.getReportType());
	    	querys.add(report);
	    }
	    return querys;
	}
	@Override
	public ReportQuery purchaseReportByProductName(ProductReportQuery query) {
		ScWeChat wechat = scWeChatMapper.selectByOpenId(query.getOpenid());//根据当前操作者的openid 获取当前店铺storeId
		
		if(wechat !=null){
			if(wechat.getStoreid()!=null){
				query.setStoreId(wechat.getStoreid());
			}else{
				return null;
			}
		}
		ReportQuery reportQuery =new ReportQuery();
		
		//根据时间从，时间到获取8个时间区间，根据时间区间去汇总区间中的采购数量
		List<ProductReportQuery> querys = getDatePoor(query);
		//默认查询第一个商品所对应的趋势图
		if(querys.size()>0){
			query = querys.get(0);
			querys.remove(0);
			System.out.println(querys.size());
		}
		 List<ProductReportQuery> maps3 =null;
		maps3 =  spReportMapper.purchaseReport2(query,querys);
		reportQuery.setProductReportQuerys(maps3);
		return reportQuery;
	}
	@Override
	public ReportQuery realTimeInventory(PurchaseReportQuery query) {
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
		if(query.getSort() != null){
			if("quantityOp".equals(query.getSort().getValue())){
				query.setQuantityOp(query.getSort().getOp());
			}else if("amountOp".equals(query.getSort().getValue())){
				query.setAmountOp(query.getSort().getOp());
			}
		}
		List<Rules> rules = query.getRules();

		if(rules != null && rules.size()>0){
			for(int i=0 ; i< rules.size() ;i++){
				if("productTypeArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					query.setProductTypeArray(rules.get(i).getValue());
				}else if("productBrandArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					query.setProductBrandArray(rules.get(i).getValue());
				}if("productSeasonArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					query.setProductSeasonArray(rules.get(i).getValue());
				}if("productColorArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					query.setProductColorArray(rules.get(i).getValue());
				}if("productSizeArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					query.setProductSizeArray(rules.get(i).getValue());
				}if("operatorArray".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					query.setOperatorArray(rules.get(i).getValue());
				}
			}
		}
		List<Map<String,Object>> mapList1 = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> maps1 =  spReportMapper.realTimeInventory(query,null);
		//过滤字段，重新组装数组
		mapList1 = getMaps(maps1, query);
		
		ReportQuery reportQuery = new ReportQuery();
		reportQuery .setInvReportQuerys(mapList1);
		return null;
	}
	@Override
	public ReportQuery employeeReport(PurchaseReportQuery query) {
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
		if(query.getSort() != null){
			if("quantityOp".equals(query.getSort().getValue())){
				query.setQuantityOp(query.getSort().getOp());
			}else if("amountOp".equals(query.getSort().getValue())){
				query.setAmountOp(query.getSort().getOp());
			}
		}
		List<Map<String,Object>> mapList1 = new ArrayList<Map<String,Object>>();
		Page page = new Page();
		page.setPageNum(query.getPageNum());
		List<Map<String,Object>> maps1 =  spReportMapper.employeeReport(query,page);
		//过滤字段，重新组装数组
		mapList1 = getMaps(maps1, query);
		
		ReportQuery reportQuery = new ReportQuery();
		reportQuery.setEmployeeQuerys(mapList1);
		return reportQuery;
	}
	
	public List<Map<String,Object>> getMaps(List<Map<String,Object>> maps,PurchaseReportQuery query){
		List<Map<String,Object>> mapList1 = new ArrayList<Map<String,Object>>();
		if(maps!=null){
			for(int i=0;i<maps.size();i++){
				Map<String,Object> map = maps.get(i);
				Map<String,Object> m = new HashMap<String, Object>();
				m.put(query.getFilter().getField(), map.get(query.getFilter().getField()));
				m.put("quantity", map.get("quantity"));
				m.put("amount", map.get("amount"));
				mapList1.add(m);
				
			}
		}
		return mapList1;
	}
	@Override
	public List<SaleReportQuery> saleSortReport(SaleReportQuery query) {
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
		if(query.getSort() != null){
			if("quantityOp".equals(query.getSort().getValue())){
				query.setQuantityOp(query.getSort().getOp());
			}else if("amountOp".equals(query.getSort().getValue())){
				query.setAmountOp(query.getSort().getOp());
			}
		}
		
		Page page = new Page();
		page.setPageNum(query.getPageNum());
		List<SaleReportQuery> querys =  spReportMapper.saleSortReport(query,page);
		//计算毛利值
		List<SaleReportQuery> list = new ArrayList<SaleReportQuery>();
		if(querys!=null && querys.size()>0){
			for(SaleReportQuery q : list){
				if(q.getSaleAmount() !=null && q.getPucharseAmount()!=null){
					q.setGrossProfit((q.getSaleAmount().subtract(q.getPucharseAmount())).divide(q.getSaleAmount()));
					list.add(q);
				}
			}
		}
		return list;
	}
	@Override
	public GeneralReportQuery generalReport(GeneralReportQuery query) {
		
		GeneralReportQuery result = new GeneralReportQuery();
		
		PurchaseReportQuery q = new PurchaseReportQuery();
		if(query.getTimeFrom() ==null ){
			query.setTimeFrom(setTimeFrom());
			q.setTimeFrom(setTimeFrom());
		}
		if(query.getTimeTo() == null){
			query.setTimeTo(setTimeTo());
			q.setTimeTo(setTimeTo());
		}
		ScWeChat wechat = scWeChatMapper.selectByOpenId(query.getOpenid());//根据当前操作者的openid 获取当前店铺storeId
		
		if(wechat !=null){
			if(wechat.getStoreid()!=null){
				query.setStoreId(wechat.getStoreid());
				q.setStoreId(wechat.getStoreid());
			}else{
				return null;
			}
		}
		//获取库存数
		List<Map<String,Object>> map =  spReportMapper.realTimeInventory(q, null);
		if(map!=null && map.size()>0){
			Map<String,Object> m = map.get(0);
			result.setStockNum((BigDecimal) m.get("quantity"));
		}
		//查询销售额，成本额，销售单数，采购单数，未付金额，未收金额
		GeneralReportQuery q1 = spReportMapper.generalOrder(query);
		if(q1 != null){
			result.setNoPayAmount(q1.getNoPayAmount());
			result.setNoReciptAmount(q1.getNoReciptAmount());
			result.setPuchaseCount(q1.getPuchaseCount());
			result.setSaleCount(q1.getSaleCount());
			result.setPuchaseOrderCount(q1.getPuchaseOrderCount());
			result.setSaleOrderCount(q1.getSaleOrderCount());
			result.setPurchaseAmount(q1.getPurchaseAmount());
			result.setSaleAmount(q1.getSaleAmount());
		}
		//还款单金额
		List<GeneralReportQuery> q2 = spReportMapper.generalClient(query);
		if(q2!=null && q2.size()>0){
			result.setClientAmount(q2.get(0).getReciptAmount());
			result.setClientOrderCount(q2.get(0).getClientOrderCount());
			result.setNoPayAmount(result.getNoPayAmount().subtract(q2.get(0).getPayAmount()));
			result.setNoReciptAmount(result.getNoReciptAmount().subtract(q2.get(0).getReciptAmount()));
		}
		//费用单费用
		List<GeneralReportQuery> q3 = spReportMapper.generalFee(query);
		if(q3!=null && q3.size()>0){
			result.setFeeAmount(q3.get(0).getFeeAmount());
		}
		
		List<PayTypeQuery> payTypeQuerys = new ArrayList<PayTypeQuery>();
		//查询订单中的 支付方式对应付款金额，收款金额
		query.setGroupByTag(1);//根据支付方式进行分组
		List<GeneralReportQuery> payTypeAmountList1 = spReportMapper.generalClient(query);
		List<PayTypeQuery> payTypeQuerys1 =  getPayTypeQuerys(payTypeAmountList1);
		
		List<GeneralReportQuery> payTypeAmountList2 = spReportMapper.generalFee(query);
		List<PayTypeQuery> payTypeQuerys2 =  getPayTypeQuerys(payTypeAmountList2);
		

		List<GeneralReportQuery> payTypeAmountList3 = spReportMapper.generalPayType1(query);
		List<PayTypeQuery> payTypeQuerys3 =  getPayTypeQuerys(payTypeAmountList3);
		
		List<GeneralReportQuery> payTypeAmountList4 = spReportMapper.generalPayType2(query);
		List<PayTypeQuery> payTypeQuerys4 =  getPayTypeQuerys(payTypeAmountList4);
		
		//查询有多少种支付方式
		List<String> list = new ArrayList<String>();
		list.add("PAY_TYPE");//获取支付类型
		List<SsDataDictionary> dict = ssDataDictionaryMapper.selectDictionarys(list,query.getStoreId());
		if(dict!=null&& dict.size()>0){
			for(int i=0 ; i< dict.size() ;i++){
				
				String payType = dict.get(i).getDictValue();
				PayTypeQuery payTypeQuery = new PayTypeQuery();
				payTypeQuery.setPayType(payType);
				payTypeQuery.setPayAmount(new BigDecimal(0));
				payTypeQuery.setReceiptAmount(new BigDecimal(0));
				
				for(int j=0;j<payTypeQuerys1.size();j++){
					if(payType.equals(payTypeQuerys1.get(j).getPayType())){
						payTypeQuery.setPayAmount(payTypeQuery.getPayAmount().add(payTypeQuerys1.get(j).getPayAmount()));
						payTypeQuery.setReceiptAmount(payTypeQuery.getReceiptAmount().add(payTypeQuerys1.get(j).getReceiptAmount()));
					}
				}
				
				for(int j=0;j<payTypeQuerys2.size();j++){
					if(payType.equals(payTypeQuerys2.get(j).getPayType())){
						payTypeQuery.setPayAmount(payTypeQuery.getPayAmount().add(payTypeQuerys2.get(j).getPayAmount()));
						payTypeQuery.setReceiptAmount(payTypeQuery.getReceiptAmount().add(payTypeQuerys2.get(j).getReceiptAmount()));
					}
				}
				
				for(int j=0;j<payTypeQuerys3.size();j++){
					if(payType.equals(payTypeQuerys3.get(j).getPayType())){
						payTypeQuery.setPayAmount(payTypeQuery.getPayAmount().add(payTypeQuerys3.get(j).getPayAmount()));
						payTypeQuery.setReceiptAmount(payTypeQuery.getReceiptAmount().add(payTypeQuerys3.get(j).getReceiptAmount()));
					}
				}
				
				for(int j=0;j<payTypeQuerys4.size();j++){
					if(payType.equals(payTypeQuerys4.get(j).getPayType())){
						payTypeQuery.setPayAmount(payTypeQuery.getPayAmount().add(payTypeQuerys4.get(j).getPayAmount()));
						payTypeQuery.setReceiptAmount(payTypeQuery.getReceiptAmount().add(payTypeQuerys4.get(j).getReceiptAmount()));
					}
				}
				
				payTypeQuerys.add(payTypeQuery);
			}
		}
		result.setPayTypeQuery(payTypeQuerys);
		List<SsStoOrder> orders1 = spOrderService.getOrders(3,null,query.getOpenid(),query.getPageNum());
		List<SsStoOrder> orders2 = spOrderService.getOrders(4,null,query.getOpenid(),query.getPageNum());
		List<SsPayment> pays = ssPaymentMapper.selects(query);
		
		result.setPucharseOrders(orders1);
		result.setSaleOrders(orders2);
		result.setPayments(pays);
		return result;
	}
	
	public List<PayTypeQuery> getPayTypeQuerys(List<GeneralReportQuery> payTypeAmountList){
		List<PayTypeQuery> payTypeQuerys = new ArrayList<PayTypeQuery>();
		if(payTypeAmountList!=null && payTypeAmountList.size()>0){
			for(GeneralReportQuery q4 : payTypeAmountList){
				PayTypeQuery pay = new PayTypeQuery();
				pay.setPayType(q4.getPayCate());
				pay.setPayAmount(q4.getPayAmount());
				pay.setReceiptAmount(q4.getReciptAmount());
				payTypeQuerys.add(pay);
			}
		}
		return payTypeQuerys;
	}
}


