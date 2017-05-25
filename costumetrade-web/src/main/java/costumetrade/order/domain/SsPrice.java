package costumetrade.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import costumetrade.common.Entity;

public class SsPrice extends Entity {
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
     *  进货价
     */
    private BigDecimal purchaseprice;

    /**
     *  吊牌价
     */
    private BigDecimal tagprice;

    /**
     * 第三销售价
     */
    private BigDecimal thirdPrice;

    /**
     * 第二销售价 
     */
    private BigDecimal secondPrice;

    /**
     * 第一销售价
     */
    private BigDecimal firsthPrice;
    /**
     *  第五销售价
     */
    private BigDecimal fifthPrice;
    
    /**
     *  第四销售价
     */
    private BigDecimal fourthPrice;

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

    public BigDecimal getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(BigDecimal purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public BigDecimal getTagprice() {
        return tagprice;
    }

    public void setTagprice(BigDecimal tagprice) {
        this.tagprice = tagprice;
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

	public BigDecimal getThirdPrice() {
		return thirdPrice;
	}

	public void setThirdPrice(BigDecimal thirdPrice) {
		this.thirdPrice = thirdPrice;
	}

	public BigDecimal getSecondPrice() {
		return secondPrice;
	}

	public void setSecondPrice(BigDecimal secondPrice) {
		this.secondPrice = secondPrice;
	}

	public BigDecimal getFirsthPrice() {
		return firsthPrice;
	}

	public void setFirsthPrice(BigDecimal firsthPrice) {
		this.firsthPrice = firsthPrice;
	}

	public BigDecimal getFifthPrice() {
		return fifthPrice;
	}

	public void setFifthPrice(BigDecimal fifthPrice) {
		this.fifthPrice = fifthPrice;
	}

	public BigDecimal getFourthPrice() {
		return fourthPrice;
	}

	public void setFourthPrice(BigDecimal fourthPrice) {
		this.fourthPrice = fourthPrice;
	}
    
    
}