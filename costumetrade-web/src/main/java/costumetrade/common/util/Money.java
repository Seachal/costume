package costumetrade.common.util;

import java.math.BigDecimal;

public class Money {

	private Long cent;

	public Long getCent() {
		return cent;
	}

	public void setCent(Long cent) {
		this.cent = cent;
	}
	public String toSting(){
	  return BigDecimal.valueOf(cent).divide(new BigDecimal(100)).toString();
	}
	
	
}
