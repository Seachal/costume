package costumetrade.order.query;

import costumetrade.common.Entity;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;

public class OrderQuery extends Entity {
    private SsStoOrder order ;
    
    private SsStoDetail[] detail;
    
    private int[] cartId;

    private static final long serialVersionUID = 1L;

	public SsStoOrder getOrder() {
		return order;
	}

	public void setOrder(SsStoOrder order) {
		this.order = order;
	}

	public SsStoDetail[] getDetail() {
		return detail;
	}

	public void setDetail(SsStoDetail[] detail) {
		this.detail = detail;
	}

	public int[] getCartId() {
		return cartId;
	}

	public void setCartId(int[] cartId) {
		this.cartId = cartId;
	}

	
    
    
   
}