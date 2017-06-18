package costumetrade.report.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.query.Filter;
import costumetrade.order.query.Rules;
import costumetrade.order.query.Sort;
import costumetrade.user.domain.SsPayment;

public class ReportQuery extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PurchaseReportQuery> purchaseReportQuerys;
	
	private List<ProductReportQuery> productReportQuerys;

	public List<PurchaseReportQuery> getPurchaseReportQuerys() {
		return purchaseReportQuerys;
	}

	public void setPurchaseReportQuerys(
			List<PurchaseReportQuery> purchaseReportQuerys) {
		this.purchaseReportQuerys = purchaseReportQuerys;
	}

	public List<ProductReportQuery> getProductReportQuerys() {
		return productReportQuerys;
	}

	public void setProductReportQuerys(List<ProductReportQuery> productReportQuerys) {
		this.productReportQuerys = productReportQuerys;
	}
	
	
	
}