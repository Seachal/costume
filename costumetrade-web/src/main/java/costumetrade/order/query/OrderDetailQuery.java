package costumetrade.order.query;

import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;



public class OrderDetailQuery extends Entity {
	
	private SsStoOrder ssStoOrder;
	private List<SsStoDetail> ssStoDetail;
	
	public SsStoOrder getSsStoOrder() {
		return ssStoOrder;
	}
	public void setSsStoOrder(SsStoOrder ssStoOrder) {
		this.ssStoOrder = ssStoOrder;
	}
	public List<SsStoDetail> getSsStoDetail() {
		return ssStoDetail;
	}
	public void setSsStoDetail(List<SsStoDetail> ssStoDetail) {
		this.ssStoDetail = ssStoDetail;
	}
	
	
   
}