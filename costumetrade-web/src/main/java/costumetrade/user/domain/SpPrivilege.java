package costumetrade.user.domain;

import costumetrade.common.Entity;
import java.util.Date;

public class SpPrivilege extends Entity {
    /**
     *  目录ID
     */
    private Long id;

    /**
     *  店铺编号
     */
    private Long storeid;

    /**
     *  操作CODE
     */
    private String operateCode;

    /**
     *  操作名称
     */
    private String operateName;

    /**
     *  父ID没有表示null
     */
    private Long parentId;

    /**
     *  是否删除
     */
    private Integer isDel;

    /**
     *  创建时间
     */
    private Date createDate;

    /**
     *  修改时间
     */
    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode == null ? null : operateCode.trim();
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName == null ? null : operateName.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}