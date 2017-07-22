package costumetrade.product.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.query.ProductQuery;

public class ScPromotionalProduct extends Entity {
    /**
     *  主键
     */
    private String id;

    /**
     *  推荐人店铺ID
     */
    private String promoterStoreid;

    /**
     *  标题
     */
    private String title;

    /**
     *  推荐人店铺头像
     */
    private String promoterPhoto;

    /**
     *  货品id数组
     */
    private String productIds;

    /**
     *  创建人
     */
    private String createBy;

    /**
     *  创建时间
     */
    private Date createTime;

    /**
     *  被推荐人：普通消费者userID 店铺是storeId
     */
    private String recommendedId;

    /**
     *  推荐人店铺名称
     */
    private String promoterName;

    /**
     *  全选标志
     */
    private Boolean checkAllTag;

    /**
     *  地址
     */
    private String promoterAddress;

    /**
     *  默认展示图片
     */
    private String productImages;

    /**
     *  分享类型1、微信分享 2、粉丝分享
     */
    private String shareType;

    /**
     *  分享员工
     */
    private String shareEmployee;

    /**
     *  0、未读，1、已读
     */
    private String readStatus;

    /**
     *  阅读时间
     */
    private Date readTime;
    
    private Integer readCount;
    
    private List<String> images;
    
    private BigDecimal noReadCount;
    
    private List<String> clientIds;
    
    private String openid ;
    
    private List<String> productIdArray;
    
    private List<ProductQuery> products;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPromoterStoreid() {
        return promoterStoreid;
    }

    public void setPromoterStoreid(String promoterStoreid) {
        this.promoterStoreid = promoterStoreid == null ? null : promoterStoreid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPromoterPhoto() {
        return promoterPhoto;
    }

    public void setPromoterPhoto(String promoterPhoto) {
        this.promoterPhoto = promoterPhoto == null ? null : promoterPhoto.trim();
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds == null ? null : productIds.trim();
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

    public String getRecommendedId() {
        return recommendedId;
    }

    public void setRecommendedId(String recommendedId) {
        this.recommendedId = recommendedId == null ? null : recommendedId.trim();
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
        this.promoterAddress = promoterAddress == null ? null : promoterAddress.trim();
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages == null ? null : productImages.trim();
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType == null ? null : shareType.trim();
    }

    public String getShareEmployee() {
        return shareEmployee;
    }

    public void setShareEmployee(String shareEmployee) {
        this.shareEmployee = shareEmployee == null ? null : shareEmployee.trim();
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus == null ? null : readStatus.trim();
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public BigDecimal getNoReadCount() {
		return noReadCount;
	}

	public void setNoReadCount(BigDecimal noReadCount) {
		this.noReadCount = noReadCount;
	}

	public List<String> getClientIds() {
		return clientIds;
	}

	public void setClientIds(List<String> clientIds) {
		this.clientIds = clientIds;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public List<String> getProductIdArray() {
		return productIdArray;
	}

	public void setProductIdArray(List<String> productIdArray) {
		this.productIdArray = productIdArray;
	}

	public List<ProductQuery> getProducts() {
		return products;
	}

	public void setProducts(List<ProductQuery> products) {
		this.products = products;
	}
    
    
}