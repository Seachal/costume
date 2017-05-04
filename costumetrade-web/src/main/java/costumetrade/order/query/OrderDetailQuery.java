package costumetrade.order.query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SpStoDetail;
import costumetrade.order.domain.SpStoOrder;

public class OrderDetailQuery extends Entity {
	private SpStoOrder order ;
    
    private List<SpStoDetail> detail;

    private static final long serialVersionUID = 1L;

	public SpStoOrder getOrder() {
		return order;
	}

	public void setOrder(SpStoOrder order) {
		this.order = order;
	}

	public List<SpStoDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<SpStoDetail> detail) {
		this.detail = detail;
	}
    
    
   
}