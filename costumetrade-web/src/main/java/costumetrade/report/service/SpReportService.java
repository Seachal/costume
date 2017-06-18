package costumetrade.report.service;

import java.util.List;
import java.util.Map;

import costumetrade.report.domain.FinanceReportQuery;
import costumetrade.report.domain.ProductReportQuery;
import costumetrade.report.domain.PurchaseReportQuery;
import costumetrade.report.domain.ReportQuery;


public interface SpReportService {

	/**
	 * 财务报表
	 * */
	public FinanceReportQuery financeReport(FinanceReportQuery query);
	/**
	 * 采购分析报表
	 * */
	public List<Map<String,Object>>  purchaseSortReport(PurchaseReportQuery query);
	/**
	 * 采购报表
	 * */
	public ReportQuery  purchaseReport(PurchaseReportQuery query);
	/**
	 * 采购报表,点击某一个商品名称，获取对应的趋势图
	 * */
	public ReportQuery  purchaseReportByProductName(ProductReportQuery query);
}
