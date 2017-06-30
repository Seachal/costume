package costumetrade.report.domain;

import java.math.BigDecimal;
import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SsStoOrder;

public class OrdersQuery extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal count;
	
	private BigDecimal invCount;
	
	private List<SsStoOrder> orders;

	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public BigDecimal getInvCount() {
		return invCount;
	}

	public void setInvCount(BigDecimal invCount) {
		this.invCount = invCount;
	}

	public List<SsStoOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<SsStoOrder> orders) {
		this.orders = orders;
	}
	
	
}
