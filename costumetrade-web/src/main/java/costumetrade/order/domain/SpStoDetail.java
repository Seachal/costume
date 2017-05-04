package costumetrade.order.domain;

import costumetrade.common.Entity;
import java.math.BigDecimal;
import java.util.Date;

public class SpStoDetail extends Entity {
    /**
     *  识别编号：服务器自动生成
     */
    private Integer id;

    /**
     *  ：对应stockorder的ID
     */
    private String orderid;

    /**
     *  分店编号
     */
    private String subid;

    /**
     *  单据序号
     */
    private Integer indexed;

    /**
     *  手数标记
     */
    private Integer handtag;

    /**
     *  单据状态
     */
    private Integer status;

    /**
     *  单据类型
     */
    private Integer ordertype;

    /**
     *  商品编号
     */
    private String productid;

    /**
     *  商品名称
     */
    private String productname;

    /**
     *  商品单位
     */
    private String productunit;

    /**
     *  商品颜色
     */
    private String productcolor;

    /**
     *  商品尺码
     */
    private String productsize;

    /**
     *  合计数量
     */
    private BigDecimal totalnum;

    /**
     *  商品单价
     */
    private BigDecimal price;

    /**
     *  合计金额
     */
    private BigDecimal totalamt;

    /**
     *  折扣金额
     */
    private Integer discountratio;

    /**
     *  折扣金额
     */
    private BigDecimal discount;

    /**
     *  去零金额
     */
    private BigDecimal ridzeroamt;

    /**
     *  扣税金额
     */
    private BigDecimal taxamt;

    /**
     *  实际金额
     */
    private BigDecimal realamt;

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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid == null ? null : subid.trim();
    }

    public Integer getIndexed() {
        return indexed;
    }

    public void setIndexed(Integer indexed) {
        this.indexed = indexed;
    }

    public Integer getHandtag() {
        return handtag;
    }

    public void setHandtag(Integer handtag) {
        this.handtag = handtag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid == null ? null : productid.trim();
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getProductunit() {
        return productunit;
    }

    public void setProductunit(String productunit) {
        this.productunit = productunit == null ? null : productunit.trim();
    }

    public String getProductcolor() {
        return productcolor;
    }

    public void setProductcolor(String productcolor) {
        this.productcolor = productcolor == null ? null : productcolor.trim();
    }

    public String getProductsize() {
        return productsize;
    }

    public void setProductsize(String productsize) {
        this.productsize = productsize == null ? null : productsize.trim();
    }

    public BigDecimal getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(BigDecimal totalnum) {
        this.totalnum = totalnum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalamt() {
        return totalamt;
    }

    public void setTotalamt(BigDecimal totalamt) {
        this.totalamt = totalamt;
    }

    public Integer getDiscountratio() {
        return discountratio;
    }

    public void setDiscountratio(Integer discountratio) {
        this.discountratio = discountratio;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getRidzeroamt() {
        return ridzeroamt;
    }

    public void setRidzeroamt(BigDecimal ridzeroamt) {
        this.ridzeroamt = ridzeroamt;
    }

    public BigDecimal getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(BigDecimal taxamt) {
        this.taxamt = taxamt;
    }

    public BigDecimal getRealamt() {
        return realamt;
    }

    public void setRealamt(BigDecimal realamt) {
        this.realamt = realamt;
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
}