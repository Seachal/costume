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

public class GeneralReportQuery extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String openid;
	
	private String storeId;
	
	private BigDecimal saleAmount;
	
	private BigDecimal purchaseAmount;
	
	private BigDecimal clientAmount;
	
	private BigDecimal feeAmount;
	
	private List<PayTypeQuery> payTypeQuery;
	
	private BigDecimal ordersCount;
	
	private BigDecimal puchaseOrderCount;
	
	private BigDecimal saleOrderCount;
	
	private BigDecimal clientOrderCount;
	
	private OrdersQuery ordersQuery;
	
	private Integer orderType ;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public BigDecimal getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}

	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public BigDecimal getClientAmount() {
		return clientAmount;
	}

	public void setClientAmount(BigDecimal clientAmount) {
		this.clientAmount = clientAmount;
	}

	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public List<PayTypeQuery>  getPayTypeQuery() {
		return payTypeQuery;
	}

	public void setPayTypeQuery(List<PayTypeQuery>  payTypeQuery) {
		this.payTypeQuery = payTypeQuery;
	}

	public BigDecimal getOrdersCount() {
		return ordersCount;
	}

	public void setOrdersCount(BigDecimal ordersCount) {
		this.ordersCount = ordersCount;
	}

	public BigDecimal getPuchaseOrderCount() {
		return puchaseOrderCount;
	}

	public void setPuchaseOrderCount(BigDecimal puchaseOrderCount) {
		this.puchaseOrderCount = puchaseOrderCount;
	}

	public BigDecimal getSaleOrderCount() {
		return saleOrderCount;
	}

	public void setSaleOrderCount(BigDecimal saleOrderCount) {
		this.saleOrderCount = saleOrderCount;
	}

	public BigDecimal getClientOrderCount() {
		return clientOrderCount;
	}

	public void setClientOrderCount(BigDecimal clientOrderCount) {
		this.clientOrderCount = clientOrderCount;
	}

	public OrdersQuery getOrdersQuery() {
		return ordersQuery;
	}

	public void setOrdersQuery(OrdersQuery ordersQuery) {
		this.ordersQuery = ordersQuery;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	
	
}