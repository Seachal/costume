package costumetrade.order.query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SpPBrand;
import costumetrade.order.domain.SpPCate;
import costumetrade.order.domain.SpPSizeCustom;
import costumetrade.order.enums.SeasonTypeEnum;
import costumetrade.order.enums.UnitTypeEnum;

public class ProductQuery extends Entity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *  商品编号
     */
    private String id;

    /**
     *  企业ID
     */
    private Integer storeId;
    /**
     *  实体状态 0:正常 1：待处理 2、作废
     */
    private Integer status;

    /**
     *  商品货号
     */
    private String code;

    /**
     *  商品品牌Id号
     */
    private String brand;

    /**
     *  商品种类
     */
    private String prducttype;

    /**
     *  商品名称
     */
    private String name;

  
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
     *  商品季节
     */
    private List<SeasonTypeEnum> seasonList;
    
    private  List<String> gradeList;
    
    private  List<UnitTypeEnum> unitList;
    
    private List<String> priceNameList ;

    
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
     *  上架时间
     */
    private Date timeUp;

    /**
     *  下架时间
     */
    private Date timeDown;

    
    private String storeName;
    
    private String storeImage;
    
    private BigDecimal salePrice;
    
    private BigDecimal originalPrice;
    

	private Integer clientId;
	
    
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
     *  预警下线
     */
    private Double warnLow;

    /**
     *  预警上线
     */
    private Double warnHigh;
    
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
	 * 货品列表查询  开始
	 */
	

	private String timeUpOp; // 升序为asc，降序为desc
	private String priceOp; // 升序为asc，降序为desc
	private List<String> productTypeArray; // 商品类别
	private List<String> productBrandArray; // 商品品牌
	private List<String> productSeasonArray; // 商品季节
	private String saleOp;
	/**
	 * 货品列表查询  结束
	 */
	private List<SpPSizeCustom> productSize;  //x商品尺碼組
	

	private List<SpPBrand> brandList ;
	private List<SpPCate> productTypeList;
	private List<SpPSizeCustom> sizeList;
	private BigDecimal price ;
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code =code ;
	}

	public String getTimeUpOp() {
		return timeUpOp;
	}

	public void setTimeUpOp(String timeUpOp) {
		this.timeUpOp = timeUpOp;
	}

	public String getPriceOp() {
		return priceOp;
	}

	public void setPriceOp(String priceOp) {
		this.priceOp = priceOp;
	}

	

	public List<String> getProductTypeArray() {
		return productTypeArray;
	}

	public void setProductTypeArray(List<String> productTypeArray) {
		this.productTypeArray = productTypeArray;
	}

	public List<String> getProductBrandArray() {
		return productBrandArray;
	}

	public void setProductBrandArray(List<String> productBrandArray) {
		this.productBrandArray = productBrandArray;
	}

	public List<String> getProductSeasonArray() {
		return productSeasonArray;
	}

	public void setProductSeasonArray(List<String> productSeasonArray) {
		this.productSeasonArray = productSeasonArray;
	}

	public void setSaleOp(String saleOp) {
		this.saleOp = saleOp;
	}

	public List<SpPSizeCustom> getProductSize() {
		return productSize;
	}

	public void setProductSize(List<SpPSizeCustom> productSize) {
		this.productSize = productSize;
	}

	

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPrducttype() {
		return prducttype;
	}

	public void setPrducttype(String prducttype) {
		this.prducttype = prducttype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public String getSizes() {
		return sizes;
	}

	public void setSizes(String sizes) {
		this.sizes = sizes;
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
		this.year = year;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreImage() {
		return storeImage;
	}

	public void setStoreImage(String storeImage) {
		this.storeImage = storeImage;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public List<SpPBrand> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<SpPBrand> brandList) {
		this.brandList = brandList;
	}

	public List<SpPCate> getProductTypeList() {
		return productTypeList;
	}

	public void setProductTypeList(List<SpPCate> productTypeList) {
		this.productTypeList = productTypeList;
	}

	public List<SpPSizeCustom> getSizeList() {
		return sizeList;
	}

	public void setSizeList(List<SpPSizeCustom> sizeList) {
		this.sizeList = sizeList;
	}

	public List<SeasonTypeEnum> getSeasonList() {
		return seasonList;
	}

	public void setSeasonList(List<SeasonTypeEnum> seasonList) {
		this.seasonList = seasonList;
	}



	public List<UnitTypeEnum> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<UnitTypeEnum> unitList) {
		this.unitList = unitList;
	}

	public String getSaleOp() {
		return saleOp;
	}

	public void setSaleNum(String saleOp) {
		this.saleOp = saleOp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<String> getPriceNameList() {
		return priceNameList;
	}

	public void setPriceNameList(List<String> priceNameList) {
		this.priceNameList = priceNameList;
	}

	public void setGradeList(List<String> gradeList) {
		this.gradeList = gradeList;
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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBarcodes() {
		return barcodes;
	}

	public void setBarcodes(String barcodes) {
		this.barcodes = barcodes;
	}

	public List<String> getGradeList() {
		return gradeList;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	
	
	

	

	
	
	
}
