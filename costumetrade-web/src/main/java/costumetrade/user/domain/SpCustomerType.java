package costumetrade.user.domain;

import costumetrade.common.Entity;
import java.util.Date;

public class SpCustomerType extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  店铺编号
     */
    private Integer storeid;

    /**
     *  类型名称
     */
    private String typename;

    /**
     *  积分需求
     */
    private Integer integration;

    /**
     *  销售价类型：1、吊牌价；2、批发价；3、打包价；4、零售价
     */
    private String saletype;
    /*
     * 商品可见级别
     * */
    private String visibleGrade;
    
    

    /**
     *  创建时间
     */
    private Date createTime;

    /**
     *  创建人
     */
    private String createBy;

    /**
     *  修改时间
     */
    private Date modifyTime;

    /**
     *  修改人
     */
    private String modifyBy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public String getSaletype() {
        return saletype;
    }

    public void setSaletype(String saletype) {
        this.saletype = saletype == null ? null : saletype.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

	public String getVisibleGrade() {
		return visibleGrade;
	}

	public void setVisibleGrade(String visibleGrade) {
		this.visibleGrade = visibleGrade  == null ? null : visibleGrade.trim();;
	}
    
    
}