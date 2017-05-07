package costumetrade.order.query;

import java.util.List;

public class ProductParam {
	
	private Integer clientId;
	private Integer storeId;   
	private String code;   //货号
	private  String TimeUpOp;   //升序为asc，降序为desc
	private String priceOp;    //升序为asc，降序为desc
	private List<String> productType;  //x商品类别
	private List<String> productBrand;  //x商品品牌
	private List<String> productSeason;  //x商品季节
	
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
		this.code = code;
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
	
	
	
}
