package costumetrade.report.service;

import java.util.List;

import costumetrade.report.domain.FinanceReportQuery;
import costumetrade.report.domain.ProductReportQuery;
import costumetrade.report.domain.PurchaseReportQuery;
import costumetrade.report.domain.ReportQuery;
import costumetrade.report.domain.SaleReportQuery;


public interface SpReportService {

	/**
	 * 财务报表
	 * */
	public FinanceReportQuery financeReport(FinanceReportQuery query);
	/**
	 * 采购分析报表
	 * */
	public ReportQuery  purchaseAnalysisReport(PurchaseReportQuery query);
	
	/**
	 * 库存报表
	 * */
	public ReportQuery  realTimeInventory(PurchaseReportQuery query);
	/**
	 *会员报表
	 * */
	public ReportQuery  employeeReport(PurchaseReportQuery query);
	/**
	 * 采购报表
	 * */
	public ReportQuery  purchaseReport(PurchaseReportQuery query);
	/**
	 * 采购报表,点击某一个商品名称，获取对应的趋势图
	 * */
	public ReportQuery  purchaseReportByProductName(ProductReportQuery query);
	
	/**
	 * 销售排行
	 * */
	public List<SaleReportQuery>  saleSortReport(SaleReportQuery query);
}
