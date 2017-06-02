package costumetrade.order.query;



import costumetrade.common.Entity;


public class OrderCountQuery extends Entity {
	
	
    private static final long serialVersionUID = 1L;
    
    private Integer purchaseCount ;//采购单总数
    
    private Integer pNoPayCount ;//采购单未付款数量
    private Integer pNoShipCount ;//采购单未发货数量
    private Integer pNoReceiptCount ;//采购单未收货数量
    
    private Integer saleCount ;//销售单总数
    
    private Integer sNoPayCount ;//销售单未收款数量
    private Integer sNoShipCount ;//销售单未发货数量
    private Integer sNoAuditCount ;//销售单未审核配货数量
    
    private Integer ordersCount;//订单总数量

	public Integer getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(Integer purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public Integer getpNoPayCount() {
		return pNoPayCount;
	}

	public void setpNoPayCount(Integer pNoPayCount) {
		this.pNoPayCount = pNoPayCount;
	}

	public Integer getpNoShipCount() {
		return pNoShipCount;
	}

	public void setpNoShipCount(Integer pNoShipCount) {
		this.pNoShipCount = pNoShipCount;
	}

	public Integer getpNoReceiptCount() {
		return pNoReceiptCount;
	}

	public void setpNoReceiptCount(Integer pNoReceiptCount) {
		this.pNoReceiptCount = pNoReceiptCount;
	}

	public Integer getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}

	public Integer getsNoPayCount() {
		return sNoPayCount;
	}

	public void setsNoPayCount(Integer sNoPayCount) {
		this.sNoPayCount = sNoPayCount;
	}

	public Integer getsNoShipCount() {
		return sNoShipCount;
	}

	public void setsNoShipCount(Integer sNoShipCount) {
		this.sNoShipCount = sNoShipCount;
	}

	public Integer getsNoAuditCount() {
		return sNoAuditCount;
	}

	public void setsNoAuditCount(Integer sNoAuditCount) {
		this.sNoAuditCount = sNoAuditCount;
	}

	public Integer getOrdersCount() {
		return ordersCount;
	}

	public void setOrdersCount(Integer ordersCount) {
		this.ordersCount = ordersCount;
	}
    
    

 
   
}