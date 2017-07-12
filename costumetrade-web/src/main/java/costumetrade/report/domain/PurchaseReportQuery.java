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

public class PurchaseReportQuery extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal quantity;
	
	private BigDecimal amount;
	
	private String productName;
	
	private String productColor;
	
	private String productSize;
	
	private String brandName;
	
	private String gradeName;
	
	private String storeName;
	
	private String operator;
	
	private String openid;
	/**
	 * 供应商名称
	 * */
	private String clientName;
	/**
	 * 创建人
	 * */
	private String createBy;
	
	
	private Date timeFrom;
	
	private Date timeTo;
	
	private int days;
	
	private Sort sort;
	
	private Filter filter;
	
	private String storeId;
	
	private String productId;
	
	private Integer pageNum;
	
	private List<Rules>  rules;
	
	private List<String> productNameArray; // 商品类别
	
	private List<String> productTypeArray; // 商品类别
	
	private List<String> productBrandArray; // 商品品牌
	
	private List<String> productColorArray; //
	
	private List<String> productSizeArray; //
	
	private List<String> productClientCustomerArray; //
	
	private List<String> productClientSuppierArray; //
	
	private List<String> operatorArray;
	
	private List<String> productSeasonArray;
	
	private List<String> clientIdArray;
	
	private String quantityOp;
	
	private String amountOp;
	
	private Integer buyerStoreId;
	
	private Integer sellerStoreId;
	
	private Integer reportType;
	
	private Integer groupTag;
	
	private BigDecimal pucharseAmount;
	
	private BigDecimal saleAmount;

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public List<Rules> getRules() {
		return rules;
	}

	public void setRules(List<Rules> rules) {
		this.rules = rules;
	}

	public List<String> getProductTypeArray() {
		return productTypeArray;
	}

	public void setProductTypeArray(List<String> productTypeArray) {
		this.productTypeArray = productTypeArray;
	}

	public List<String> getProductBrandArray() {
		return productBrandArray;
	}

	public void setProductBrandArray(List<String> productBrandArray) {
		this.productBrandArray = productBrandArray;
	}

	public List<String> getProductColorArray() {
		return productColorArray;
	}

	public void setProductColorArray(List<String> productColorArray) {
		this.productColorArray = productColorArray;
	}

	public List<String> getProductSizeArray() {
		return productSizeArray;
	}

	public void setProductSizeArray(List<String> productSizeArray) {
		this.productSizeArray = productSizeArray;
	}

	public List<String> getProductClientCustomerArray() {
		return productClientCustomerArray;
	}

	public void setProductClientCustomerArray(
			List<String> productClientCustomerArray) {
		this.productClientCustomerArray = productClientCustomerArray;
	}

	public List<String> getProductClientSuppierArray() {
		return productClientSuppierArray;
	}

	public void setProductClientSuppierArray(List<String> productClientSuppierArray) {
		this.productClientSuppierArray = productClientSuppierArray;
	}

	public List<String> getOperatorArray() {
		return operatorArray;
	}

	public void setOperatorArray(List<String> operatorArray) {
		this.operatorArray = operatorArray;
	}

	public List<String> getClientIdArray() {
		return clientIdArray;
	}

	public void setClientIdArray(List<String> clientIdArray) {
		this.clientIdArray = clientIdArray;
	}

	public String getQuantityOp() {
		return quantityOp;
	}

	public void setQuantityOp(String quantityOp) {
		this.quantityOp = quantityOp;
	}

	public String getAmountOp() {
		return amountOp;
	}

	public void setAmountOp(String amountOp) {
		this.amountOp = amountOp;
	}

	public List<String> getProductSeasonArray() {
		return productSeasonArray;
	}

	public void setProductSeasonArray(List<String> productSeasonArray) {
		this.productSeasonArray = productSeasonArray;
	}

	public List<String> getProductNameArray() {
		return productNameArray;
	}

	public void setProductNameArray(List<String> productNameArray) {
		this.productNameArray = productNameArray;
	}

	public Integer getBuyerStoreId() {
		return buyerStoreId;
	}

	public void setBuyerStoreId(Integer buyerStoreId) {
		this.buyerStoreId = buyerStoreId;
	}

	public Integer getSellerStoreId() {
		return sellerStoreId;
	}

	public void setSellerStoreId(Integer sellerStoreId) {
		this.sellerStoreId = sellerStoreId;
	}

	public Integer getReportType() {
		return reportType;
	}

	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Integer getGroupTag() {
		return groupTag;
	}

	public void setGroupTag(Integer groupTag) {
		this.groupTag = groupTag;
	}

	public BigDecimal getPucharseAmount() {
		return pucharseAmount;
	}

	public void setPucharseAmount(BigDecimal pucharseAmount) {
		this.pucharseAmount = pucharseAmount;
	}

	public BigDecimal getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}
	
	
	
	
	
	
}