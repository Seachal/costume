package costumetrade.order.query;

import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;

public class OrderDetailQuery extends Entity {
	private SsStoOrder order ;
    
    private List<SsStoDetail> detail;

    private static final long serialVersionUID = 1L;

	public SsStoOrder getOrder() {
		return order;
	}

	public void setOrder(SsStoOrder order) {
		this.order = order;
	}

	public List<SsStoDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<SsStoDetail> detail) {
		this.detail = detail;
	}
    
    
   
}