package costumetrade.report.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.report.domain.FinanceReportQuery;
import costumetrade.report.domain.GeneralReportQuery;
import costumetrade.report.domain.ProductReportQuery;
import costumetrade.report.domain.PurchaseReportQuery;
import costumetrade.report.domain.SaleReportQuery;
@Mapper
public interface SpReportMapper {
	
	FinanceReportQuery financeReport(FinanceReportQuery query);
	
	List<Map<String, Object>> purchaseAnalysisReport(PurchaseReportQuery query);
	
	List<Map<String, Object>> realTimeInventory(@Param("query")PurchaseReportQuery query,@Param("page")Page page);
	
	List<Map<String, Object>> employeeReport(@Param("query")PurchaseReportQuery query,@Param("page")Page page);
	
	List<PurchaseReportQuery> purchaseReport3(@Param("query")PurchaseReportQuery query,@Param("page")Page page);
	
	List<ProductReportQuery> purchaseReport2(@Param("query")ProductReportQuery query,@Param("querys")List<ProductReportQuery> querys);
	
	List<PurchaseReportQuery> purchaseReport1(@Param("query")PurchaseReportQuery query,@Param("page")Page page);
	
	List<SaleReportQuery> saleSortReport(@Param("query")SaleReportQuery query,@Param("page")Page page);
	
	GeneralReportQuery generalOrder(GeneralReportQuery query);
	
	List<GeneralReportQuery> generalClient(GeneralReportQuery query);
	
	List<GeneralReportQuery> generalFee(GeneralReportQuery query);
	
	List<GeneralReportQuery> generalPayType1(GeneralReportQuery query);
	
	List<GeneralReportQuery> generalPayType2(GeneralReportQuery query);
	
	List<SaleReportQuery> profitAnalysis(SaleReportQuery query);
}