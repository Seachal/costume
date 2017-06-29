package costumetrade.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import costumetrade.common.Entity;

public class SsStockTransfer extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  库存单类型1:调入，2：调出，3：报溢，4：报损
     */
    private Integer stockType;

    /**
     *  转移数量
     */
    private BigDecimal count;

    /**
     *  单价
     */
    private BigDecimal price;

    /**
     *  金额
     */
    private BigDecimal amount;

    /**
     *  本客户
     */
    private String transferfromid;

    /**
     *  对方客户
     */
    private String transfertoid;

    /**
     *  
     */
    private Date createdate;

    /**
     *  
     */
    private String createby;

    /**
     *  商品id
     */
    private String productid;

    /**
     *  颜色
     */
    private String productcolor;

    /**
     *  尺码
     */
    private String productsize;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransferfromid() {
        return transferfromid;
    }

    public void setTransferfromid(String transferfromid) {
        this.transferfromid = transferfromid;
    }

    public String getTransfertoid() {
        return transfertoid;
    }

    public void setTransfertoid(String transfertoid) {
        this.transfertoid = transfertoid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid == null ? null : productid.trim();
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
}