package costumetrade.order.domain;

import java.util.Date;

import costumetrade.common.Entity;
import costumetrade.user.domain.SpCustProdPrice;

public class SpClient extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  当前店铺ID
     */
    private Integer storeid;
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
    private String vipcard;

    /**
     *  联系名称
     */
    private String contact;

    /**
     *  头像信息
     */
    private String image;

    /**
     *  身份证号
     */
    private String cardid;

    /**
     *  生日信息
     */
    private Date birthdate;

    /**
     *  移动电话
     */
    private String phone;

    /**
     *  联系电话
     */
    private String telephone;

    /**
     *  传真号码
     */
    private String fax;

    /**
     *  邮箱地址
     */
    private String email;

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
    private String blank;

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

    /**
     *  修改人
     */
    private String modifyBy;
    
    private SpCustProdPrice prodPrice;

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

    public String getVipcard() {
        return vipcard;
    }

    public void setVipcard(String vipcard) {
        this.vipcard = vipcard == null ? null : vipcard.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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

    public String getBlank() {
        return blank;
    }

    public void setBlank(String blank) {
        this.blank = blank == null ? null : blank.trim();
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
    
    
}