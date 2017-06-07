package costumetrade.order.domain;

import java.util.Date;

import costumetrade.common.Entity;
import costumetrade.common.page.Page;

public class SpUnit extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  单位
     */
    private String unit;

    /**
     *  创建时间
     */
    private Date createTime;
    
    private Integer status;

    /**
     *  店铺ID
     */
    private Integer storeId;

    /**
     *  创建人
     */
    private String createBy;
    
    private Page page;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
    
}