package costumetrade.order.domain;

import costumetrade.common.Entity;
import java.math.BigDecimal;
import java.util.Date;

public class SsCgsorder extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  店铺ID
     */
    private String storeId;

    /**
     *  状态
     */
    private Integer status;

    /**
     *  费用类型
     */
    private String cate;

    /**
     *  费用单名称
     */
    private String catename;

    /**
     *  支付类型
     */
    private String paycate;

    /**
     *  支付金额
     */
    private BigDecimal paycost;

    /**
     *  备注
     */
    private String remarks;

    /**
     *  费用单时间
     */
    private Date orderTime;

    /**
     *  创建时间
     */
    private Long createTime;

    /**
     *  
     */
    private String createBy;

    /**
     *  修改时间
     */
    private Long modifyTime;

    /**
     *  
     */
    private String modifyBy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id ;
    }


    public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate == null ? null : cate.trim();
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename == null ? null : catename.trim();
    }

    public String getPaycate() {
        return paycate;
    }

    public void setPaycate(String paycate) {
        this.paycate = paycate == null ? null : paycate.trim();
    }

    public BigDecimal getPaycost() {
        return paycost;
    }

    public void setPaycost(BigDecimal paycost) {
        this.paycost = paycost;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }
}