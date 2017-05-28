package costumetrade.user.domain;

import costumetrade.common.Entity;
import java.util.Date;

public class SpPrivilegeEmployee extends Entity {
    /**
     *  
     */
    private Long id;

    /**
     *  权限ID
     */
    private Long privilegeId;

    /**
     *  员工ID
     */
    private Long employeeId;

    /**
     *  创建时间
     */
    private Date createDate;

    /**
     *  修改时间
     */
    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
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