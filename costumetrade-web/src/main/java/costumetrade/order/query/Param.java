package costumetrade.order.query;

import java.math.BigDecimal;
import java.util.List;

public class Param {
	
	private Integer id;
	private Integer clientId;
	private Integer storeId;
	/**
	 * 货品列表查询  开始
	 */
	
	private String code; // 货号
	private String TimeUpOp; // 升序为asc，降序为desc
	private String priceOp; // 升序为asc，降序为desc
	private List<String> productType; // x商品类别
	private List<String> productBrand; // x商品品牌
	private List<String> productSeason; // x商品季节
	/**
	 * 货品列表查询  结束
	 */
	
	/*
	 * 下单参数  开始
	 * */
	private Integer memberTag; //1、普通会员   2、店家
	
	private List<Integer> productId;

	private List<String> productName;

	private List<BigDecimal> count;

	private List<String> color;

	private List<String> size;

	private List<BigDecimal> price;
	/*
	 * 下单参数  结束
	 * */
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = "%" + code + "%";
	}

	public String getTimeUpOp() {
		return TimeUpOp;
	}

	public void setTimeUpOp(String timeUpOp) {
		TimeUpOp = timeUpOp;
	}

	public String getPriceOp() {
		return priceOp;
	}

	public void setPriceOp(String priceOp) {
		this.priceOp = priceOp;
	}

	public List<String> getProductType() {
		return productType;
	}

	public void setProductType(List<String> productType) {
		this.productType = productType;
	}

	public List<String> getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(List<String> productBrand) {
		this.productBrand = productBrand;
	}

	public List<String> getProductSeason() {
		return productSeason;
	}

	public void setProductSeason(List<String> productSeason) {
		this.productSeason = productSeason;
	}

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

	public Integer getMemberTag() {
		return memberTag;
	}

	public void setMemberTag(Integer memberTag) {
		this.memberTag = memberTag;
	}
	
	
	
}
