package costumetrade.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import costumetrade.common.Entity;

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
    private BigDecimal stocknum;

    /**
     *  入库金额
     */
    private BigDecimal productPrice;

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
    /**
     * 商品编码
     * */
    private String code;
    /**
     * 商品名称
     * */
    private String name;
    
    private List<Integer> otherStoreIds;
    
    private Integer currentStore;
    /*
     * 供货商ID
     * 
     * **/
    private Integer supplierStoreId;
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

    public BigDecimal getStocknum() {
        return stocknum;
    }

    public void setStocknum(BigDecimal stocknum) {
        this.stocknum = stocknum;
    }



    public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getOtherStoreIds() {
		return otherStoreIds;
	}

	public void setOtherStoreIds(List<Integer> otherStoreIds) {
		this.otherStoreIds = otherStoreIds;
	}

	public Integer getCurrentStore() {
		return currentStore;
	}

	public void setCurrentStore(Integer currentStore) {
		this.currentStore = currentStore;
	}

	public Integer getSupplierStoreId() {
		return supplierStoreId;
	}

	public void setSupplierStoreId(Integer supplierStoreId) {
		this.supplierStoreId = supplierStoreId;
	}
    
    
}