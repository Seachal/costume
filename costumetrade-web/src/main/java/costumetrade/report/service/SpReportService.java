package costumetrade.report.service;

import java.util.List;
import java.util.Map;

import costumetrade.report.domain.FinanceReportQuery;
import costumetrade.report.domain.PurchaseReportQuery;


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
