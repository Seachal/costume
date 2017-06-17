package costumetrade.order.domain;

import costumetrade.common.Entity;

public class Item extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String number;
	
	private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
