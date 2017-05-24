package costumetrade.order.query;

import java.util.List;

import costumetrade.common.Entity;

public class Rules extends Entity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filed ;
	private List<String> value ;
	public String getFiled() {
		return filed;
	}
	public void setFiled(String filed) {
		this.filed = filed;
	}
	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}
	
}
