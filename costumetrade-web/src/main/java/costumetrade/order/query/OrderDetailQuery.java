package costumetrade.order.query;

import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;

public class OrderDetailQuery extends Entity {
	
	private Integer corpId;
	private Integer orderId;
	private Integer operate;
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

	public Integer getCorpId() {
		return corpId;
	}

	public void setCorpId(Integer corpId) {
		this.corpId = corpId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOperate() {
		return operate;
	}

	public void setOperate(Integer operate) {
		this.operate = operate;
	}
    
    
   
}