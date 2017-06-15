package costumetrade.order.service;

import java.util.List;
import java.util.Map;

import costumetrade.order.query.FinanceReportQuery;
import costumetrade.order.query.PurchaseReportQuery;


public interface SpReportService {

	/**
	 * 财务报表
	 * */
	public FinanceReportQuery financeReport(FinanceReportQuery query);
	/**
	 * 采购排行报表
	 * */
	public List<Map<String,Object>>  purchaseSortReport(PurchaseReportQuery query);
}
