package costumetrade.report.control;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.report.domain.FinanceReportQuery;
import costumetrade.report.domain.GeneralReportQuery;
import costumetrade.report.domain.ProductReportQuery;
import costumetrade.report.domain.PurchaseReportQuery;
import costumetrade.report.domain.ReportQuery;
import costumetrade.report.domain.SaleReportQuery;
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
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		FinanceReportQuery report = spReportService.financeReport(query);
		if(report == null ){
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			result.setData(ResponseInfo.NOT_DATA.code);
		}else{
			result.setData(report);
		}
		return  result;
	}
	@RequestMapping("/purchaseAnalysisReport")
	@ResponseBody
	public ApiResponse purchaseAnalysisReport(@RequestBody PurchaseReportQuery query) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		ReportQuery reports = spReportService.purchaseAnalysisReport(query);
		if(reports == null ){
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			result.setData(ResponseInfo.NOT_DATA.code);
		}else{
			result.setData(reports);
		}
		return  result;
	}
	@RequestMapping("/realTimeInventory")
	@ResponseBody
	public ApiResponse realTimeInventory(@RequestBody PurchaseReportQuery query) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		ReportQuery reports = spReportService.realTimeInventory(query);
		if(reports == null ){
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			result.setData(ResponseInfo.NOT_DATA.code);
		}else{
			result.setData(reports);
		}
		return  result;
	}
	@RequestMapping("/employeeReport")
	@ResponseBody
	public ApiResponse employeeReport(@RequestBody PurchaseReportQuery query) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		ReportQuery reports = spReportService.employeeReport(query);
		if(reports == null ){
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			result.setData(ResponseInfo.NOT_DATA.code);
		}else{
			result.setData(reports);
		}
		return  result;
	}
	
	@RequestMapping("/saleSortReport")
	@ResponseBody
	public ApiResponse saleSortReport(@RequestBody SaleReportQuery query) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		List<SaleReportQuery> reports = spReportService.saleSortReport(query);
		if(reports == null ){
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			result.setData(ResponseInfo.NOT_DATA.code);
		}else{
			result.setData(reports);
		}
		return  result;
	}
	@RequestMapping("/purchaseReport")
	@ResponseBody
	public ApiResponse purchaseReport(PurchaseReportQuery query) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		ReportQuery report = spReportService.purchaseReport(query);
		if(report == null ){
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			result.setData(ResponseInfo.NOT_DATA.code);
		}else{
			result.setData(report);
		}
		return  result;
	}
	@RequestMapping("/purchaseReportByProductName")
	@ResponseBody
	public ApiResponse purchaseReportByProductName(ProductReportQuery query) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		ReportQuery report = spReportService.purchaseReportByProductName(query);
		if(report == null ){
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			result.setData(ResponseInfo.NOT_DATA.code);
		}else{
			result.setData(report);
		}
		return  result;
	}
	@RequestMapping("/generalReport")
	@ResponseBody
	public ApiResponse generalReport(@RequestBody GeneralReportQuery query) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		GeneralReportQuery report = spReportService.generalReport(query);
		if(report == null ){
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			result.setData(ResponseInfo.NOT_DATA.code);
		}else{
			result.setData(report);
		}
		return  result;
	}
	
}
