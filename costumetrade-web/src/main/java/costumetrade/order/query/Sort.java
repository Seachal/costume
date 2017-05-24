package costumetrade.order.query;

import costumetrade.common.Entity;

public class Sort extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String value;
	private String op;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	
	
}
