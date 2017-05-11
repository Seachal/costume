package costumetrade.order.domain;

import costumetrade.common.Entity;
import java.math.BigDecimal;
import java.util.Date;

public class SsStock extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  店铺编号
     */
    private Integer storeid;

    /**
     *  商品编号
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

    /**
     *  入库数量
     */
    private Double stocknum;

    /**
     *  入库金额
     */
    private BigDecimal stockamt;

    /**
     *  修改时间
     */
    private Date modifytime;

    /**
     *  修改人
     */
    private String modifyby;

    /**
     *  创建时间
     */
    private Date createtime;

    /**
     *  创建人
     */
    private String createby;

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

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
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

    public Double getStocknum() {
        return stocknum;
    }

    public void setStocknum(Double stocknum) {
        this.stocknum = stocknum;
    }

    public BigDecimal getStockamt() {
        return stockamt;
    }

    public void setStockamt(BigDecimal stockamt) {
        this.stockamt = stockamt;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getModifyby() {
        return modifyby;
    }

    public void setModifyby(String modifyby) {
        this.modifyby = modifyby == null ? null : modifyby.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }
}