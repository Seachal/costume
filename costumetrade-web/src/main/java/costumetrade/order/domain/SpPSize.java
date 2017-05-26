package costumetrade.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import costumetrade.common.Entity;
import costumetrade.common.page.Page;

public class SpPSize extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  企业ID
     */
    private Integer storeId;

    /**
     *  识别编码:尺码名称，字符类型，允许1-10个字符（最多5个汉字）
     */
    private String sizename;

    /**
     *  条码编码:用于生成商品条码
     */
    private String barcode;

    /**
     *  创建时间:创建项的Unix时间戳，系统自动设置
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
    
    /**
     * 尺码加价
     * */
    private BigDecimal priceRaise;
    
    

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getSizename() {
        return sizename;
    }

    public void setSizename(String sizename) {
        this.sizename = sizename == null ? null : sizename.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
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



	public BigDecimal getPriceRaise() {
		return priceRaise;
	}

	public void setPriceRaise(BigDecimal priceRaise) {
		this.priceRaise = priceRaise;
	}

    
}