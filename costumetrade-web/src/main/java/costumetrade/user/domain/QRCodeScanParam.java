package costumetrade.user.domain;

import java.util.Date;

import costumetrade.common.Entity;

public class QRCodeScanParam extends Entity {
   
    private static final long serialVersionUID = 1L;

    private String storeId;
    
    private String id;
    /**
     * 扫描类型 1：加客户，2.加供应商 3.加朋友 4、加员工
     * */
    private Integer type;
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    
	
    
}