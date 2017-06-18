package costumetrade.report.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.user.domain.SsPayment;

public class FinanceReportQuery extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal saleIncome;
	
	private BigDecimal salePay;
	
	private BigDecimal purchaseIncome;
	
	private BigDecimal purchasePay;
	
	private BigDecimal customerRepay;
	
	private BigDecimal suppierRepay;
	
	private BigDecimal feeIncome;
	
	private BigDecimal feePay;
	
	private BigDecimal count;
	
	private Integer storeId;
	
	private String openid;
	
	private Date timeFrom;
	
	private Date timeTo;
	
	private int days;
	
	private String payCate;

	public BigDecimal getSaleIncome() {
		return saleIncome==null?BigDecimal.ZERO:saleIncome;
	}

	public void setSaleIncome(BigDecimal saleIncome) {
		this.saleIncome = saleIncome;
	}

	public BigDecimal getSalePay() {
		return salePay==null?BigDecimal.ZERO:salePay;
	}

	public void setSalePay(BigDecimal salePay) {
		this.salePay = salePay;
	}

	public BigDecimal getPurchaseIncome() {
		return purchaseIncome==null?BigDecimal.ZERO:purchaseIncome;
	}

	public void setPurchaseIncome(BigDecimal purchaseIncome) {
		this.purchaseIncome = purchaseIncome;
	}

	public BigDecimal getPurchasePay() {
		return purchasePay==null?BigDecimal.ZERO:purchasePay;
	}

	public void setPurchasePay(BigDecimal purchasePay) {
		this.purchasePay = purchasePay;
	}

	public BigDecimal getCustomerRepay() {
		return customerRepay==null?BigDecimal.ZERO:customerRepay;
	}

	public void setCustomerRepay(BigDecimal customerRepay) {
		this.customerRepay = customerRepay;
	}

	public BigDecimal getSuppierRepay() {
		return suppierRepay;
	}

	public void setSuppierRepay(BigDecimal suppierRepay) {
		this.suppierRepay = suppierRepay;
	}

	public BigDecimal getFeeIncome() {
		return feeIncome;
	}

	public void setFeeIncome(BigDecimal feeIncome) {
		this.feeIncome = feeIncome;
	}

	public BigDecimal getFeePay() {
		return feePay;
	}

	public void setFeePay(BigDecimal feePay) {
		this.feePay = feePay;
	}

	public BigDecimal getCount() {
		count = saleIncome.subtract(salePay).add(purchaseIncome).subtract(purchasePay).add(customerRepay).add(suppierRepay).subtract(feePay);
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Date getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Date getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getPayCate() {
		return payCate;
	}

	public void setPayCate(String payCate) {
		this.payCate = payCate;
	}
	
	
	
	
}