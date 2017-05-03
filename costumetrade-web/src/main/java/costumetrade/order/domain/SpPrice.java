package costumetrade.order.domain;

import costumetrade.common.Entity;
import java.math.BigDecimal;
import java.util.Date;

public class SpPrice extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  分店编号
     */
    private Integer subid;

    /**
     *  商品编号
     */
    private Integer prodId;

    /**
     *  入库单价
     */
    private BigDecimal stock;

    /**
     *  零售价
     */
    private BigDecimal sale0;

    /**
     *  批发价
     */
    private BigDecimal sale1;

    /**
     *  打包价
     */
    private BigDecimal sale2;

    /**
     *  吊牌价
     */
    private BigDecimal sale3;

    /**
     *  修改时间
     */
    private Date modifyTime;

    /**
     *  修改人
     */
    private String modifyBy;

    /**
     *  创建时间
     */
    private Date createTime;

    /**
     *  创建人
     */
    private String createBy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubid() {
        return subid;
    }

    public void setSubid(Integer subid) {
        this.subid = subid;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public BigDecimal getSale0() {
        return sale0;
    }

    public void setSale0(BigDecimal sale0) {
        this.sale0 = sale0;
    }

    public BigDecimal getSale1() {
        return sale1;
    }

    public void setSale1(BigDecimal sale1) {
        this.sale1 = sale1;
    }

    public BigDecimal getSale2() {
        return sale2;
    }

    public void setSale2(BigDecimal sale2) {
        this.sale2 = sale2;
    }

    public BigDecimal getSale3() {
        return sale3;
    }

    public void setSale3(BigDecimal sale3) {
        this.sale3 = sale3;
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
}