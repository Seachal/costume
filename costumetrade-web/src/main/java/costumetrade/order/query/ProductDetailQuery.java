package costumetrade.order.query;

import costumetrade.common.Entity;
import java.math.BigDecimal;
import java.util.Date;

public class ProductDetailQuery extends Entity {
    /**
     *  商品编号
     */
    private Integer id;

    /**
     *  企业ID
     */
    private Integer storeId;

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
    
    private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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


	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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
    
    
    
    
    
}