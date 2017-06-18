package costumetrade.report.control;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.report.domain.FinanceReportQuery;
import costumetrade.report.domain.PurchaseReportQuery;
import costumetrade.report.service.SpReportService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/report")
@Controller
public class SpReportController {
	@Autowired
	private SpReportService spReportService;

	@RequestMapping("/financeReport")
	@ResponseBody
	public ApiResponse financeReport(FinanceReportQuery query) {
		
		
		FinanceReportQuery report = spReportService.financeReport(query);

		return  ApiResponse.getInstance(report);
	}
	@RequestMapping("/purchaseSortReport")
	@ResponseBody
	public ApiResponse purchaseSortReport(PurchaseReportQuery query) {
		
		
		List<Map<String,Object>> reports = spReportService.purchaseSortReport(query);
		
		return  ApiResponse.getInstance(reports);
	}

	
}
