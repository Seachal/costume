package costumetrade.order.domain;

import costumetrade.common.Entity;
import java.math.BigDecimal;
import java.util.Date;

public class SpCart extends Entity {
    /**
     *  主键
     */
    private Integer id;

    /**
     *  企业编号:
     */
    private Integer corpid;

    /**
     *  商品编号:
     */
    private Integer productid;

    /**
     *  商品名称
     */
    private String productname;

    /**
     *  图片
     */
    private String image;

    /**
     *  颜色
     */
    private String color;

    /**
     *  尺码
     */
    private String size;

    /**
     *  价格
     */
    private BigDecimal price;

    /**
     *  下标
     */
    private Integer indexed;

    /**
     *  手数：一件衣服有三个尺码 S，M,L如果选择全尺码，那么手数就是3件
     */
    private Integer handcount;

    /**
     *  数量
     */
    private BigDecimal count;

    /**
     *  合计金额:合计金额=商品单价*商品数量
     */
    private BigDecimal cost;

    /**
     *  折扣比例:
     */
    private BigDecimal discountratio;

    /**
     *  折扣金额:折扣金额=合计金额*折扣比例/100
     */
    private BigDecimal discount;

    /**
     *  去零金额:
     */
    private BigDecimal mchange;

    /**
     *  实际金额:实际金额=合计金额-折扣金额-去零金额
     */
    private BigDecimal realcost;

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

    public Integer getCorpid() {
        return corpid;
    }

    public void setCorpid(Integer corpid) {
        this.corpid = corpid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getIndexed() {
        return indexed;
    }

    public void setIndexed(Integer indexed) {
        this.indexed = indexed;
    }

    public Integer getHandcount() {
        return handcount;
    }

    public void setHandcount(Integer handcount) {
        this.handcount = handcount;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getDiscountratio() {
        return discountratio;
    }

    public void setDiscountratio(BigDecimal discountratio) {
        this.discountratio = discountratio;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getMchange() {
        return mchange;
    }

    public void setMchange(BigDecimal mchange) {
        this.mchange = mchange;
    }

    public BigDecimal getRealcost() {
        return realcost;
    }

    public void setRealcost(BigDecimal realcost) {
        this.realcost = realcost;
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