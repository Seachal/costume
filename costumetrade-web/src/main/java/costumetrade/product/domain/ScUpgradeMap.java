package costumetrade.product.domain;

import costumetrade.common.Entity;

public class ScUpgradeMap extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  
     */
    private String openid;

    /**
     *  
     */
    private String storeId;
    
    private String productId;
    //0:初始化，1：成功
    private Integer status;
    
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
}