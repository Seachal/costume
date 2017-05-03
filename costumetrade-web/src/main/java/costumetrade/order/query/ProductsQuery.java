package costumetrade.order.query;

import java.util.List;

public class ProductsQuery {
	
	private Integer corpId;   
	private Integer subId;   
	private String code;   //货号
	private  String TimeUpOp;   //升序为asc，降序为desc
	private String priceOp;    //升序为asc，降序为desc
	private List<String> productType;  //x商品类别
	private List<String> productBrand;  //x商品品牌
	private List<String> productSeason;  //x商品季节
	public Integer getCorpId() {
		return corpId;
	}
	public void setCorpId(Integer corpId) {
		this.corpId = corpId;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
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
