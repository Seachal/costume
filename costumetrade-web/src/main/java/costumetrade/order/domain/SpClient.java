package costumetrade.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.query.Rules;
import costumetrade.order.query.Sort;
import costumetrade.user.domain.SpCustProdPrice;

public class SpClient extends Entity {
    /**
     *  
     */
    private String id;

    /**
     *  当前店铺ID
     */
    private Integer storeId;
    /**
     *  对方店铺ID：针对店员
     */
    private Integer otherStoreId;
    /**
     *  普通用户ID：针对普通消费者
     */
    private Integer userId;
    
    private String openid;

    /**
     *  货品级别
     */
    private String prodlevels;

    /**
     *  类型：1：客户，2：供应商，3：朋友
     */
    private String type;

    /**
     *  客户类型
     */
    private String cate;

    /**
     *  助记简码
     */
    private String fastcode;

    /**
     *  客户卡号
     */
    private Integer status;

    /**
     *  联系名称
     */
    private String nickName;

    /**
     *  头像信息
     */
    private String image;

    /**
     *  身份证号
     */
    private String addressBack;

    /**
     *  生日信息
     */
    private Date birthdate;

    /**
     *  备用电话
     */
    private String phoneBack;

    /**
     *  联系电话
     */
    private String telephone;

    /**
     *  传真号码
     */
    private String fax;

    /**
     *  备注名称
     */
    private String remarkName;

    /**
     *  区域
     */
    private String region;

    /**
     *  省份
     */
    private String province;

    /**
     *  市
     */
    private String city;

    /**
     *  区
     */
    private String district;

    /**
     *  详细地址
     */
    private String address;

    /**
     *  开户银行
     */
    private String reallyName;

    /**
     *  银行账号
     */
    private String blankId;

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
    
    private BigDecimal points;

    /**
     *  修改人
     */
    private String modifyBy;
    
    private SpCustProdPrice prodPrice;
    
    private List<SpCustProdPrice> custProdPriceList;
    
    private Sort sort;
    private List<Rules>  rules;
    
    private List<String> districtList;
    
    private List<String> cateList;
    
    private String nameOp;
    
    private String timeOp;
    
    private String pointsOp;
    
    private Integer pageNum;
    
    private String name;
    
    private List<String> idArray;

    private static final long serialVersionUID = 1L;
    
    

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

  

    public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getProdlevels() {
        return prodlevels;
    }

    public void setProdlevels(String prodlevels) {
        this.prodlevels = prodlevels == null ? null : prodlevels.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate == null ? null : cate.trim();
    }

    public String getFastcode() {
        return fastcode;
    }

    public void setFastcode(String fastcode) {
        this.fastcode = fastcode == null ? null : fastcode.trim();
    }

  

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }


    public String getAddressBack() {
		return addressBack;
	}

	public void setAddressBack(String addressBack) {
		this.addressBack = addressBack;
	}

	public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

  

    public String getPhoneBack() {
		return phoneBack;
	}

	public void setPhoneBack(String phoneBack) {
		this.phoneBack = phoneBack;
	}

	public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }



    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }



    public String getBlankId() {
        return blankId;
    }

    public void setBlankId(String blankId) {
        this.blankId = blankId == null ? null : blankId.trim();
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

	public SpCustProdPrice getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(SpCustProdPrice prodPrice) {
		this.prodPrice = prodPrice;
	}

	public Integer getOtherStoreId() {
		return otherStoreId;
	}

	public void setOtherStoreId(Integer otherStoreId) {
		this.otherStoreId = otherStoreId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}



	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public String getReallyName() {
		return reallyName;
	}

	public void setReallyName(String reallyName) {
		this.reallyName = reallyName;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public List<Rules> getRules() {
		return rules;
	}

	public void setRules(List<Rules> rules) {
		this.rules = rules;
	}

	public String getNameOp() {
		return nameOp;
	}

	public void setNameOp(String nameOp) {
		this.nameOp = nameOp;
	}

	public String getTimeOp() {
		return timeOp;
	}

	public void setTimeOp(String timeOp) {
		this.timeOp = timeOp;
	}

	public String getPointsOp() {
		return pointsOp;
	}

	public void setPointsOp(String pointsOp) {
		this.pointsOp = pointsOp;
	}

	public List<String> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<String> districtList) {
		this.districtList = districtList;
	}

	public List<String> getCateList() {
		return cateList;
	}

	public void setCateList(List<String> cateList) {
		this.cateList = cateList;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public List<SpCustProdPrice> getCustProdPriceList() {
		return custProdPriceList;
	}

	public void setCustProdPriceList(List<SpCustProdPrice> custProdPriceList) {
		this.custProdPriceList = custProdPriceList;
	}

	public List<String> getIdArray() {
		return idArray;
	}

	public void setIdArray(List<String> idArray) {
		this.idArray = idArray;
	}

	public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}
    
    
}