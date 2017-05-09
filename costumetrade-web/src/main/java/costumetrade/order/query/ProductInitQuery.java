package costumetrade.order.query;

import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SpPBrand;
import costumetrade.order.domain.SpPCate;
import costumetrade.order.domain.SpPSizeCustom;
import costumetrade.order.enums.GradeTypeEnum;
import costumetrade.order.enums.SeasonTypeEnum;
import costumetrade.order.enums.UnitTypeEnum;

public class ProductInitQuery extends Entity{
	
	private Integer storeId;   

	private List<SpPCate> productType;  //x商品类别
	private List<SpPBrand> productBrand;  //x商品品牌
	private List<SpPSizeCustom> productSize;  //x商品尺碼組
	
	private List<String> unit;
	private List<String> season;
	private List<String> grade;
	

	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public List<SpPCate> getProductType() {
		return productType;
	}
	public void setProductType(List<SpPCate> productType) {
		this.productType = productType;
	}
	public List<SpPBrand> getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(List<SpPBrand> productBrand) {
		this.productBrand = productBrand;
	}
	public List<SpPSizeCustom> getProductSize() {
		return productSize;
	}
	public void setProductSize(List<SpPSizeCustom> productSize) {
		this.productSize = productSize;
	}

	public List<String> getGrade() {
		return grade;
	}
	public void setGrade(List<String> grade) {
		this.grade = grade;
	}
	public List<String> getUnit() {
		return unit;
	}
	public void setUnit(List<String> unit) {
		this.unit = unit;
	}
	public List<String> getSeason() {
		return season;
	}
	public void setSeason(List<String> season) {
		this.season = season;
	}

	
	
	
	
}
