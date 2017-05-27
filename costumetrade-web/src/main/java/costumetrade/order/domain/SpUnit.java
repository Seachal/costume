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

    /**
     *  店铺ID
     */
    private Integer storeid;

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

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
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
    
    
}