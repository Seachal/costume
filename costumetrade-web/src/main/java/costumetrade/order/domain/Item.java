package costumetrade.order.domain;

import costumetrade.common.Entity;

public class Item extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name="";
	
	private String number="";
	
	private String remark="";
	
	private String id ="";
	
	private String category="";
	
	private String material="";
	
	private String size="";
	
	private String weight="";
	
	private String unitprice="";
	
	private String quantity="";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? "" : name.trim();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number== null ? "" : number.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark== null ? "" : remark.trim();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id== null ? "" : id.trim();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category== null ? "" : category.trim();
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material== null ? "" : material.trim();
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size== null ? "" : size.trim();
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight== null ? "" : weight.trim();
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice== null ? "" : unitprice.trim();
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity== null ? "" : quantity.trim();
	}
	
	
}
