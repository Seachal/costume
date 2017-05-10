package costumetrade.order.query;

import java.math.BigDecimal;
import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;

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

    /**
     *  企业ID
     */
    private Integer storeId;
    
    private String orderNo;
    
    private Integer operate;//订单状态  1：新增   2、已付款  3、审核  4、发货  5、收货  6、已取消

	/*
	 * 下单参数  开始
	 * */
	private Integer clientId; //1、普通会员   2、店家
	
	private List<Integer> productId;

	private List<String> productName;

	private List<BigDecimal> count;

	private List<String> color;

	private List<String> size;

	private List<BigDecimal> price;
	/*
	 * 下单参数  结束
	 * */

    
    private static final long serialVersionUID = 1L;



	public List<Integer> getProductId() {
		return productId;
	}

	public void setProductId(List<Integer> productId) {
		this.productId = productId;
	}

	public List<String> getProductName() {
		return productName;
	}

	public void setProductName(List<String> productName) {
		this.productName = productName;
	}

	public List<BigDecimal> getCount() {
		return count;
	}

	public void setCount(List<BigDecimal> count) {
		this.count = count;
	}

	public List<String> getColor() {
		return color;
	}

	public void setColor(List<String> color) {
		this.color = color;
	}

	public List<String> getSize() {
		return size;
	}

	public void setSize(List<String> size) {
		this.size = size;
	}

	public List<BigDecimal> getPrice() {
		return price;
	}

	public void setPrice(List<BigDecimal> price) {
		this.price = price;
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
 
   
}