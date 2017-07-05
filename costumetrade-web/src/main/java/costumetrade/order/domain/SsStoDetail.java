package costumetrade.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import costumetrade.common.Entity;

public class SsStoDetail extends Entity {
    /**
     *  识别编号：服务器自动生成
     */
    private Integer id;

    /**
     *  ：对应stockorder的ID
     */
    private String orderid;

    /**
     *  店铺编号
     */
    private String storeid;

    /**
     *  手数标记
     */
    private Integer handtag;

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
    private BigDecimal count;

    /**
     *  商品单价
     */
    private BigDecimal price;

    /**
     *  创建时间
     */
    private Date createtime;

    /**
     *  创建人
     */
    private String createby;

    /**
     *  修改时间
     */
    private Date modifytime;

    /**
     *  修改人
     */
    private String modifyby;
    
    private BigDecimal average;

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

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public Integer getHandtag() {
        return handtag;
    }

    public void setHandtag(Integer handtag) {
        this.handtag = handtag;
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

	public BigDecimal getAverage() {
		return average;
	}

	public void setAverage(BigDecimal average) {
		this.average = average;
	}
    
    
}