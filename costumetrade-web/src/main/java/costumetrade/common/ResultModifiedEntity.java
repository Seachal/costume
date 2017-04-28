package costumetrade.common;

public class ResultModifiedEntity extends Entity{
	private static final long serialVersionUID = -3761914766997667728L; 
	
	private Object value;
	
	private Object valueStr;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValueStr() {
		return valueStr;
	}

	public void setValueStr(Object valueStr) {
		this.valueStr = valueStr;
	}
}
