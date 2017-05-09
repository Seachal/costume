package costumetrade.order.query;

import java.math.BigDecimal;
import java.util.Date;

import costumetrade.common.Entity;

public class ProductQuery extends Entity{

	
	private Integer storeId; 
	
	private Integer id;
	
	private String productCode;
	
	private String productName;
	
	private BigDecimal price;
	
	private Date timeUp ;
	
	private String image;

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getTimeUp() {
		return timeUp;
	}

	public void setTimeUp(Date timeUp) {
		this.timeUp = timeUp;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	
	
	
}
