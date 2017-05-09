package costumetrade.order.domain;

import costumetrade.common.Entity;

import java.math.BigDecimal;
import java.util.Date;

public class SpProduct extends Entity {
    /**
     *  商品编号
     */
    private Integer id;

    /**
     *  企业ID
     */
    private Integer storeId;

    /**
     *  实体状态 0:正常 1：作废
     */
    private Integer status;
    
    /** 
     * 货品级别
     * */
    private Integer levelType;

    /**
     *  商品货号
     */
    private String code;

    /**
     *  商品品牌Id号
     */
    private Integer brandid;

    /**
     *  商品种类
     */
    private Integer prducttype;

    /**
     *  商品名称
     */
    private String name;

    /**
     *  助记简码
     */
    private String fastcode;

    /**
     *  商品条码
     */
    private String barcode;

    /**
     *  
     */
    private String barcodes;

    /**
     *  商品级别
     */
    private String grade;

    /**
     *  商品单位
     */
    private String unit;

    /**
     *  商品颜色
     */
    private String colors;

    /**
     *  商品尺码
     */
    private String sizes;

    /**
     *  商品手数
     */
    private Integer handcount;

    /**
     *  商品年份
     */
    private String year;

    /**
     *  商品季节
     */
    private String season;

    /**
     *  月销标记
     */
    private String saltag;

    /**
     *  月销总量
     */
    private BigDecimal salmonth;

    /**
     *  商品图片
     */
    private String image;

    /**
     *  商品图片图片
     */
    private String image1;

    /**
     *  商品图片
     */
    private String image2;

    /**
     *  商品图片
     */
    private String image3;

    /**
     *  商品图片
     */
    private String image4;

    /**
     *  商品描述
     */
    private String description;

    /**
     *  预警下线
     */
    private Double warnLow;

    /**
     *  预警上线
     */
    private Double warnHigh;

    /**
     *  上架时间
     */
    private Date timeUp;

    /**
     *  下架时间
     */
    private Date timeDown;

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

    
    /**
     *  进货价
     */
    private BigDecimal purchaseprice;

    /**
     *  吊牌价
     */
    private BigDecimal tagprice;

    /**
     *  批发价
     */
    private BigDecimal wholeprice;

    /**
     *  打包价
     */
    private BigDecimal packprice;

    /**
     *  零售价
     */
    private BigDecimal retailprice;
    
    /**
     *  商品图片名称
     */
    private String imageName;

    /**
     *  商品图片图片名称
     */
    private String imageName1;

    /**
     *  商品图片名称
     */
    private String imageName2;

    /**
     *  商品图片名称
     */
    private String imageName3;

    /**
     *  商品图片名称
     */
    private String imageName4;
    
	private String productCode;
	
	private String productName;
	
	private BigDecimal price;
	

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

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public Integer getPrducttype() {
        return prducttype;
    }

    public void setPrducttype(Integer prducttype) {
        this.prducttype = prducttype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFastcode() {
        return fastcode;
    }

    public void setFastcode(String fastcode) {
        this.fastcode = fastcode == null ? null : fastcode.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(String barcodes) {
        this.barcodes = barcodes == null ? null : barcodes.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors == null ? null : colors.trim();
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes == null ? null : sizes.trim();
    }

    public Integer getHandcount() {
        return handcount;
    }

    public void setHandcount(Integer handcount) {
        this.handcount = handcount;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season == null ? null : season.trim();
    }

    public String getSaltag() {
        return saltag;
    }

    public void setSaltag(String saltag) {
        this.saltag = saltag == null ? null : saltag.trim();
    }

    public BigDecimal getSalmonth() {
        return salmonth;
    }

    public void setSalmonth(BigDecimal salmonth) {
        this.salmonth = salmonth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1 == null ? null : image1.trim();
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2 == null ? null : image2.trim();
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3 == null ? null : image3.trim();
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4 == null ? null : image4.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Double getWarnLow() {
        return warnLow;
    }

    public void setWarnLow(Double warnLow) {
        this.warnLow = warnLow;
    }

    public Double getWarnHigh() {
        return warnHigh;
    }

    public void setWarnHigh(Double warnHigh) {
        this.warnHigh = warnHigh;
    }

    public Date getTimeUp() {
        return timeUp;
    }

    public void setTimeUp(Date timeUp) {
        this.timeUp = timeUp;
    }

    public Date getTimeDown() {
        return timeDown;
    }

    public void setTimeDown(Date timeDown) {
        this.timeDown = timeDown;
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

	public BigDecimal getWholeprice() {
		return wholeprice;
	}

	public void setWholeprice(BigDecimal wholeprice) {
		this.wholeprice = wholeprice;
	}

	public BigDecimal getPackprice() {
		return packprice;
	}

	public void setPackprice(BigDecimal packprice) {
		this.packprice = packprice;
	}

	public BigDecimal getRetailprice() {
		return retailprice;
	}

	public void setRetailprice(BigDecimal retailprice) {
		this.retailprice = retailprice;
	}

	public Integer getLevelType() {
		return levelType;
	}

	public void setLevelType(Integer levelType) {
		this.levelType = levelType;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageName1() {
		return imageName1;
	}

	public void setImageName1(String imageName1) {
		this.imageName1 = imageName1;
	}

	public String getImageName2() {
		return imageName2;
	}

	public void setImageName2(String imageName2) {
		this.imageName2 = imageName2;
	}

	public String getImageName3() {
		return imageName3;
	}

	public void setImageName3(String imageName3) {
		this.imageName3 = imageName3;
	}

	public String getImageName4() {
		return imageName4;
	}

	public void setImageName4(String imageName4) {
		this.imageName4 = imageName4;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
    
	
    
}