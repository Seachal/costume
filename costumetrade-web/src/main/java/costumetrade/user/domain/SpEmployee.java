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
    private String nickName;

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
    private String phoneBack;

    /**
     *  移动电话
     */
    private String telephone;

    /**
     *  地址
     */
    private String address;

    /**
     *  邮箱
     */
    private String addressBack;

    /**
     *  生日
     */
    private Date birthdate;

    /**
     *  身份证
     */
    private String reallyName;
    
    private String image ;
    
    /**
     *  
     */
    private String storeId;

    /**
     *  员工编号:
     */
    private String id;
    
    private String weChatNo;
    
    private BigDecimal discount;
    
    private BigDecimal zeroPrice;
    
    private String modifyPrice ;

   
    private List<Long> privilegeIds;
    
    private List<SpPrivilege> spPrivileges;
    
    private List<SpCustProdPrice> customerTypeList;
    
    private List<SpStore> chainStores;
    
    private List<SpPrivilegeEmployee> privilegeEmployees;
    
    private String openid ;
    
    private String scene;
    
    public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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


    

    public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }



    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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

	public String getPhoneBack() {
		return phoneBack;
	}

	public void setPhoneBack(String phoneBack) {
		this.phoneBack = phoneBack;
	}

	public String getAddressBack() {
		return addressBack;
	}

	public void setAddressBack(String addressBack) {
		this.addressBack = addressBack;
	}

	public String getReallyName() {
		return reallyName;
	}

	public void setReallyName(String reallyName) {
		this.reallyName = reallyName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<SpStore> getChainStores() {
		return chainStores;
	}

	public void setChainStores(List<SpStore> chainStores) {
		this.chainStores = chainStores;
	}

	public List<SpPrivilegeEmployee> getPrivilegeEmployees() {
		return privilegeEmployees;
	}

	public void setPrivilegeEmployees(List<SpPrivilegeEmployee> privilegeEmployees) {
		this.privilegeEmployees = privilegeEmployees;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	
    
    
}