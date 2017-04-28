package costumetrade.user.domain;

import costumetrade.common.Entity;
import java.util.Date;

public class SpMenuEmployee extends Entity {
    /**
     *  
     */
    private Long id;

    /**
     *  
     */
    private Long menuId;

    /**
     *  
     */
    private Long employeeId;

    /**
     *  
     */
    private Date createDate;

    /**
     *  
     */
    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}