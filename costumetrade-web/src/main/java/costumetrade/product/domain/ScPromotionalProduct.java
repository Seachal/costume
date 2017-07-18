package costumetrade.product.domain;

import java.util.Date;
import java.util.List;

import costumetrade.common.Entity;

public class ScPromotionalProduct extends Entity {
    /**
     *  主键
     */
    private String id;

    /**
     *  店铺
     */
    private String storeid;

    /**
     *  标题
     */
    private String title;

    /**
     *  推荐人头像
     */
    private String photo;

    /**
     *  货品
     */
    private String products;

    /**
     *  创建人
     */
    private String createBy;

    /**
     *  创建时间
     */
    private Date createTime;

    /**
     *  被推荐人openid
     */
    private String recommendedOpenid;

    /**
     *  推荐人
     */
    private String promoterId;

    /**
     *  被推荐人unionid
     */
    private String recommendedUnionid;

    /**
     *  推荐人名称
     */
    private String promoterName;
    
    private Boolean checkAllTag;
    
    private String promoterAddress;
    
    private List<String> images;
    
    private String productImages;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid == null ? null : storeid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products == null ? null : products.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRecommendedOpenid() {
        return recommendedOpenid;
    }

    public void setRecommendedOpenid(String recommendedOpenid) {
        this.recommendedOpenid = recommendedOpenid == null ? null : recommendedOpenid.trim();
    }

    public String getPromoterId() {
        return promoterId;
    }

    public void setPromoterId(String promoterId) {
        this.promoterId = promoterId == null ? null : promoterId.trim();
    }

    public String getRecommendedUnionid() {
        return recommendedUnionid;
    }

    public void setRecommendedUnionid(String recommendedUnionid) {
        this.recommendedUnionid = recommendedUnionid == null ? null : recommendedUnionid.trim();
    }

    public String getPromoterName() {
        return promoterName;
    }

    public void setPromoterName(String promoterName) {
        this.promoterName = promoterName == null ? null : promoterName.trim();
    }

	public Boolean getCheckAllTag() {
		return checkAllTag;
	}

	public void setCheckAllTag(Boolean checkAllTag) {
		this.checkAllTag = checkAllTag;
	}

	public String getPromoterAddress() {
		return promoterAddress;
	}

	public void setPromoterAddress(String promoterAddress) {
		this.promoterAddress = promoterAddress;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getProductImages() {
		return productImages;
	}

	public void setProductImages(String productImages) {
		this.productImages = productImages;
	}

	
    
    
}