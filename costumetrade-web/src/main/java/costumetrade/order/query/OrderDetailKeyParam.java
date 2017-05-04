package costumetrade.order.query;

import costumetrade.common.Entity;

public class OrderDetailKeyParam extends Entity{
	  /**
     *  编号
     */
    private Integer id;

    /**
     *  企业ID
     */
    private Integer corpId;
    
    private String orderId;
    
    private Integer operate;//订单状态  1：新增   2、已付款  3、审核  4、发货  5、收货  6、已取消

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCorpId() {
		return corpId;
	}
	public void setCorpId(Integer corpId) {
		this.corpId = corpId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getOperate() {
		return operate;
	}

	public void setOperate(Integer operate) {
		this.operate = operate;
	}

	
    
    

}
