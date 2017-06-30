package costumetrade.report.domain;

import java.util.List;
import java.util.Map;

import costumetrade.common.Entity;


public class ReportQuery extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PurchaseReportQuery> purchaseReportQuerys;
	
	private List<Map<String,Object>> InvReportQuerys;
	
	private List<Map<String,Object>> purchaseQuerys;
	
	private List<Map<String,Object>> employeeQuerys;
	
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

	public List<Map<String, Object>> getInvReportQuerys() {
		return InvReportQuerys;
	}

	public void setInvReportQuerys(List<Map<String, Object>> invReportQuerys) {
		InvReportQuerys = invReportQuerys;
	}

	public List<Map<String, Object>> getPurchaseQuerys() {
		return purchaseQuerys;
	}

	public void setPurchaseQuerys(List<Map<String, Object>> purchaseQuerys) {
		this.purchaseQuerys = purchaseQuerys;
	}

	public List<Map<String, Object>> getEmployeeQuerys() {
		return employeeQuerys;
	}

	public void setEmployeeQuerys(List<Map<String, Object>> employeeQuerys) {
		this.employeeQuerys = employeeQuerys;
	}

	
	
	
	
}