package costumetrade.order.query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import costumetrade.common.Entity;
import costumetrade.order.domain.SpStoDetail;
import costumetrade.order.domain.SpStoOrder;

public class OrderQuery extends Entity {
    private SpStoOrder order ;
    
    private SpStoDetail[] detail;
    
    private int[] cartId;

    private static final long serialVersionUID = 1L;

	public SpStoOrder getOrder() {
		return order;
	}

	public void setOrder(SpStoOrder order) {
		this.order = order;
	}

	public SpStoDetail[] getDetail() {
		return detail;
	}

	public void setDetail(SpStoDetail[] detail) {
		this.detail = detail;
	}

	public int[] getCartId() {
		return cartId;
	}

	public void setCartId(int[] cartId) {
		this.cartId = cartId;
	}

	
    
    
   
}