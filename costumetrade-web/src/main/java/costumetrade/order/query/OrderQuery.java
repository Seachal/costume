package costumetrade.order.query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.user.domain.SsPayment;

public class OrderQuery extends Entity {
	  /**
     *  编号
     */
    private Integer id;
    /**
     *  卖家客户编号
     */
    private Integer sellerstoreid;

    /**
     *  买家分店编号
     */
    private Integer buyerstoreid;

    private Integer orderType;
    /**
     *  企业ID
     */
    private Integer storeId;
    
    private Integer userId;
    
    private String orderNo;
    
    private Integer operate;//订单状态  1：新增   2、已付款  3、审核  4、发货  5、收货  6、已取消
    
    private Boolean isContinue = false;//默认库存缺少时，不继续操作
    
    
    /***
     * 
     * 对账 期初 期末查询 开始
     * 
     * */
    private String begining;//期初、期末
    private BigDecimal receivable;//应收
    private BigDecimal payable;//应付
    
    private Date timeFrom;//期初时间到
    
    private Date timeTo;//期末时间到
    
    private List<SsStoOrder> orders;//销售单或者采购单
    
    private List<SsPayment> payments;//还款单
    
    private Integer clientType;//对账人身份，是客户还是供应商
    
    private String clientId;//对账人
    
    private  BigDecimal receivableTotalAmount;//应收总计
    
    private BigDecimal payableTotalAmount;//应付总计
   
    /***
     * 
     * 对账 期初 期末查询 结束
     * 
     * */
	/*
	 * 下单参数  开始
	 * */
	private String openid; //1、普通会员   2、店家
	
	private List<String> productIdArray;

	private List<String> productNameArray;
	
	private List<String> productUnitArray;
	

	private List<BigDecimal> countArray;

	private List<String> colorArray;

	private List<String> sizeArray;

	private List<BigDecimal> priceArray;
	/*
	 * 下单参数  结束
	 * */
	private Integer count ;
    
	private List<SsStoOrder> ssStoOrder;
	
	private List<SsStoDetail> stoDetails;
	
	private SsStoOrder order;
	
	private Integer pageNum;
	
    private static final long serialVersionUID = 1L;



	
	public List<String> getProductIdArray() {
		return productIdArray;
	}

	public void setProductIdArray(List<String> productIdArray) {
		this.productIdArray = productIdArray;
	}

	public List<String> getProductNameArray() {
		return productNameArray;
	}

	public void setProductNameArray(List<String> productNameArray) {
		this.productNameArray = productNameArray;
	}

	public List<BigDecimal> getCountArray() {
		return countArray;
	}

	public void setCountArray(List<BigDecimal> countArray) {
		this.countArray = countArray;
	}

	public List<String> getColorArray() {
		return colorArray;
	}

	public void setColorArray(List<String> colorArray) {
		this.colorArray = colorArray;
	}

	public List<String> getSizeArray() {
		return sizeArray;
	}

	public void setSizeArray(List<String> sizeArray) {
		this.sizeArray = sizeArray;
	}

	public List<BigDecimal> getPriceArray() {
		return priceArray;
	}

	public void setPriceArray(List<BigDecimal> priceArray) {
		this.priceArray = priceArray;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOperate() {
		return operate;
	}

	public void setOperate(Integer operate) {
		this.operate = operate;
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

	public Integer getSellerstoreid() {
		return sellerstoreid;
	}

	public void setSellerstoreid(Integer sellerstoreid) {
		this.sellerstoreid = sellerstoreid;
	}

	public Integer getBuyerstoreid() {
		return buyerstoreid;
	}

	public void setBuyerstoreid(Integer buyerstoreid) {
		this.buyerstoreid = buyerstoreid;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public List<String> getProductUnitArray() {
		return productUnitArray;
	}

	public void setProductUnitArray(List<String> productUnitArray) {
		this.productUnitArray = productUnitArray;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<SsStoOrder> getSsStoOrder() {
		return ssStoOrder;
	}

	public void setSsStoOrder(List<SsStoOrder> ssStoOrder) {
		this.ssStoOrder = ssStoOrder;
	}

	public Boolean getIsContinue() {
		return isContinue;
	}

	public void setIsContinue(Boolean isContinue) {
		this.isContinue = isContinue;
	}

	public String getBegining() {
		return begining;
	}

	public void setBegining(String begining) {
		this.begining = begining;
	}

	public BigDecimal getReceivable() {
		return receivable;
	}

	public void setReceivable(BigDecimal receivable) {
		this.receivable = receivable ==null?BigDecimal.ZERO:receivable;
	}

	public BigDecimal getPayable() {
		return payable;
	}

	public void setPayable(BigDecimal payable) {
		this.payable = payable==null?BigDecimal.ZERO:payable;
	}

	public List<SsStoOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<SsStoOrder> orders) {
		this.orders = orders;
	}

	public List<SsPayment> getPayments() {
		return payments;
	}

	public void setPayments(List<SsPayment> payments) {
		this.payments = payments;
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

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public BigDecimal getReceivableTotalAmount() {
		if(receivable == null){
			receivable = BigDecimal.ZERO;
		}
		if(payable == null){
			payable = BigDecimal.ZERO;
		}
		if(receivable.compareTo(payable)==1){
			receivableTotalAmount =receivable.subtract(payable);
		}
		return receivableTotalAmount;
	}

	public void setReceivableTotalAmount(BigDecimal receivableTotalAmount) {
		this.receivableTotalAmount = receivableTotalAmount;
	}

	public BigDecimal getPayableTotalAmount() {
		if(receivable == null){
			receivable = BigDecimal.ZERO;
		}
		if(payable == null){
			payable = BigDecimal.ZERO;
		}
		if(payable.compareTo(receivable)==1){
			payableTotalAmount =payable.subtract(receivable);
		}
		return payableTotalAmount;
	}

	public void setPayableTotalAmount(BigDecimal payableTotalAmount) {
		this.payableTotalAmount = payableTotalAmount;
	}

	public List<SsStoDetail> getStoDetails() {
		return stoDetails;
	}

	public void setStoDetails(List<SsStoDetail> stoDetails) {
		this.stoDetails = stoDetails;
	}

	public SsStoOrder getOrder() {
		return order;
	}

	public void setOrder(SsStoOrder order) {
		this.order = order;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	
	
}