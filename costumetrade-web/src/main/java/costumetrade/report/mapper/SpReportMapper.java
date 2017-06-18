package costumetrade.report.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SpPCate;
import costumetrade.report.domain.FinanceReportQuery;
import costumetrade.report.domain.PurchaseReportQuery;
@Mapper
public interface SpReportMapper {
	
	FinanceReportQuery financeReport(FinanceReportQuery query);
	
	List<Map<String, Object>> purchaseSortReport(PurchaseReportQuery query);
}