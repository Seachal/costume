package costumetrade.order.query;

import java.math.BigDecimal;
import java.util.List;

import costumetrade.common.Entity;

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

	/*
	 * 下单参数  开始
	 * */
	private Integer clientId; //1、普通会员   2、店家
	
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

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
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
 
   
}