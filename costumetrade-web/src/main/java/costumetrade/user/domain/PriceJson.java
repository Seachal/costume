package costumetrade.user.domain;

import java.math.BigDecimal;

import costumetrade.common.Entity;

public class PriceJson extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 种类名称  or 等级名称
	 * */
	private String name;
	/**
	 * 毛利值   or 折扣值
	 * */
	private BigDecimal priceRaise;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null?null : name.trim();
	}

	public BigDecimal getPriceRaise() {
		return priceRaise;
	}

	public void setPriceRaise(BigDecimal priceRaise) {
		this.priceRaise = priceRaise;
	}


	
   
}