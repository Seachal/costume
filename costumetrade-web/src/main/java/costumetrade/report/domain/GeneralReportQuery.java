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
	 * 123
	 */
	private static final long serialVersionUID = 1L;
	
	private String openid;
	
	private String storeId;
	//销售金额
	private BigDecimal saleAmount;
	//采购金额
	private BigDecimal purchaseAmount;
	//未付款金额
	private BigDecimal noPayAmount;
	//未收款金额
	private BigDecimal noReciptAmount;
	//支付类型
	private String payCate;
	//付款金额
	private BigDecimal payAmount;
	//收款金额
	private BigDecimal reciptAmount;
	//往来收款金额
	private BigDecimal clientAmount;
	//费用单金额
	private BigDecimal feeAmount;
	
	private List<PayTypeQuery> payTypeQuery;
	//订单总数
	private BigDecimal ordersCount;
	//采购单数量
	private BigDecimal puchaseOrderCount;
	//销售单数量
	private BigDecimal saleOrderCount;
	//采购数量
	private BigDecimal puchaseCount;
	//销售数量
	private BigDecimal saleCount;
	//往来订单数量
	private BigDecimal clientOrderCount;
	
	private OrdersQuery ordersQuery;
	
	private Integer orderType ;
	
	private Integer groupByTag;
	
	private Date timeFrom;
	
	private Date timeTo;
	//库存数量
	private BigDecimal stockNum;
	
	private List<SsStoOrder> pucharseOrders;
	
	private List<SsStoOrder> saleOrders;
	
	private List<SsPayment> payments;
	
	private Integer pageNum;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public BigDecimal getSaleAmount() {
		if(saleAmount==null){
			saleAmount = BigDecimal.ZERO;
		}
		return saleAmount;
	}

	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}

	public BigDecimal getPurchaseAmount() {
		if(purchaseAmount==null){
			purchaseAmount = BigDecimal.ZERO;
		}
		return purchaseAmount;
	}

	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public BigDecimal getClientAmount() {
		if(clientAmount==null){
			clientAmount = BigDecimal.ZERO;
		}
		
		return clientAmount;
	}

	public void setClientAmount(BigDecimal clientAmount) {
		this.clientAmount = clientAmount;
	}

	public BigDecimal getFeeAmount() {
		if(feeAmount==null){
			feeAmount = BigDecimal.ZERO;
		}
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
		if(ordersCount==null){
			ordersCount = BigDecimal.ZERO;
		}
		return ordersCount;
	}

	public void setOrdersCount(BigDecimal ordersCount) {
		this.ordersCount = ordersCount;
	}

	public BigDecimal getPuchaseOrderCount() {
		if(puchaseOrderCount==null){
			puchaseOrderCount = BigDecimal.ZERO;
		}
		return puchaseOrderCount;
	}

	public void setPuchaseOrderCount(BigDecimal puchaseOrderCount) {
		this.puchaseOrderCount = puchaseOrderCount;
	}

	public BigDecimal getSaleOrderCount() {
		if(saleOrderCount==null){
			saleOrderCount = BigDecimal.ZERO;
		}
		return saleOrderCount;
	}

	public void setSaleOrderCount(BigDecimal saleOrderCount) {
		this.saleOrderCount = saleOrderCount;
	}

	public BigDecimal getClientOrderCount() {
		if(clientOrderCount==null){
			clientOrderCount = BigDecimal.ZERO;
		}
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

	public Integer getGroupByTag() {
		return groupByTag;
	}

	public void setGroupByTag(Integer groupByTag) {
		this.groupByTag = groupByTag;
	}

	public BigDecimal getNoPayAmount() {
		if(noPayAmount==null){
			noPayAmount = BigDecimal.ZERO;
		}
		return noPayAmount;
	}

	public void setNoPayAmount(BigDecimal noPayAmount) {
		this.noPayAmount = noPayAmount;
	}

	public BigDecimal getNoReciptAmount() {
		if(noReciptAmount==null){
			noReciptAmount = BigDecimal.ZERO;
		}
		return noReciptAmount;
	}

	public void setNoReciptAmount(BigDecimal noReciptAmount) {
		this.noReciptAmount = noReciptAmount;
	}

	public String getPayCate() {
		return payCate;
	}

	public void setPayCate(String payCate) {
		this.payCate = payCate;
	}

	public BigDecimal getPayAmount() {
		if(payAmount==null){
			payAmount = BigDecimal.ZERO;
		}
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public BigDecimal getReciptAmount() {
		if(reciptAmount==null){
			reciptAmount = BigDecimal.ZERO;
		}
		return reciptAmount;
	}

	public void setReciptAmount(BigDecimal reciptAmount) {
		this.reciptAmount = reciptAmount;
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

	public BigDecimal getPuchaseCount() {
		if(puchaseCount==null){
			puchaseCount = BigDecimal.ZERO;
		}
		return puchaseCount;
	}

	public void setPuchaseCount(BigDecimal puchaseCount) {
		this.puchaseCount = puchaseCount;
	}

	public BigDecimal getSaleCount() {
		if(saleCount==null){
			saleCount = BigDecimal.ZERO;
		}
		return saleCount;
	}

	public void setSaleCount(BigDecimal saleCount) {
		this.saleCount = saleCount;
	}

	public BigDecimal getStockNum() {
		if(stockNum==null){
			stockNum = BigDecimal.ZERO;
		}
		return stockNum;
	}

	public void setStockNum(BigDecimal stockNum) {
		this.stockNum = stockNum;
	}

	public List<SsStoOrder> getPucharseOrders() {
		return pucharseOrders;
	}

	public void setPucharseOrders(List<SsStoOrder> pucharseOrders) {
		this.pucharseOrders = pucharseOrders;
	}

	public List<SsStoOrder> getSaleeOrders() {
		return saleOrders;
	}

	public void setSaleOrders(List<SsStoOrder> saleOrders) {
		this.saleOrders = saleOrders;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public List<SsPayment> getPayments() {
		return payments;
	}

	public void setPayments(List<SsPayment> payments) {
		this.payments = payments;
	}
	
	
	
}