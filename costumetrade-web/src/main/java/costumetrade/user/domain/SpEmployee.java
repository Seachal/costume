package costumetrade.user.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import costumetrade.common.Entity;

public class SpEmployee extends Entity implements Serializable{
	private static final long serialVersionUID = 1L;
    /**
     *  员工状态:
     */
    private Integer status;

    /**
     *  所属分店:
     */
    private String subbelong;

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
     *  授权信息:
     */
    private String permission;

    /**
     *  员工姓名：
     */
    private String name;

    /**
     *  员工密码:
     */
    private String password;

    /**
     *  联系电话
     */
    private String phone;

    /**
     *  移动电话
     */
    private String cphone;

    /**
     *  地址
     */
    private String address;

    /**
     *  邮箱
     */
    private String email;

    /**
     *  生日
     */
    private Date birthdate;

    /**
     *  身份证
     */
    private String cardId;
    
    /**
     *  
     */
    private Integer storeId;

    /**
     *  员工编号:
     */
    private Integer id;
    
    private String weChatNo;
    
    private BigDecimal discount;
    
    private BigDecimal zeroPrice;
    
    private String modifyPrice ;

   
    private List<Long> privilegeIds;
    
    private List<SpPrivilege> spPrivileges;
    
    List<SpCustProdPrice> customerTypeList;
    
    public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubbelong() {
        return subbelong;
    }

    public void setSubbelong(String subbelong) {
        this.subbelong = subbelong == null ? null : subbelong.trim();
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone == null ? null : cphone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

	public String getWeChatNo() {
		return weChatNo;
	}

	public void setWeChatNo(String weChatNo) {
		this.weChatNo = weChatNo;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getZeroPrice() {
		return zeroPrice;
	}

	public void setZeroPrice(BigDecimal zeroPrice) {
		this.zeroPrice = zeroPrice;
	}

	public String getModifyPrice() {
		return modifyPrice;
	}

	public void setModifyPrice(String modifyPrice) {
		this.modifyPrice = modifyPrice;
	}

	public List<Long> getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(List<Long> privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public List<SpPrivilege> getSpPrivileges() {
		return spPrivileges;
	}

	public void setSpPrivileges(List<SpPrivilege> spPrivileges) {
		this.spPrivileges = spPrivileges;
	}

	public List<SpCustProdPrice> getCustomerTypeList() {
		return customerTypeList;
	}

	public void setCustomerTypeList(List<SpCustProdPrice> customerTypeList) {
		this.customerTypeList = customerTypeList;
	}

	
    
    
}