package costumetrade.order.query;

import costumetrade.common.Entity;

public class Filter extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String field;
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	
	
}
