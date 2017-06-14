package costumetrade.order.service;

import costumetrade.order.query.FinanceReportQuery;


public interface SpReportService {

	/**
	 * 财务报表
	 * */
	public FinanceReportQuery financeReport(FinanceReportQuery query);
}
