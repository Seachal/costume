package costumetrade.order.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.SpPSize;
import costumetrade.order.domain.SpPSizeCustom;
import costumetrade.order.query.FinanceReportQuery;
import costumetrade.order.service.SpReportService;

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

	
}
