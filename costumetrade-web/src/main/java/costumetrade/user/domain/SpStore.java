package costumetrade.user.domain;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.common.Entity;

public class SpStore extends Entity {
    /**
     *  分店编号:
     */
    private String id;

    /**
     *  上级店铺编号:
     */
    private String parentid;

    /**
     *  店铺头像
     */
    private String storephoto;
    
    /**
     * 店铺类型
     * */
    private Integer storeType;
    
    private Integer status;
    
    private Integer inventoryShare;
    /**
     *  分店名称:
     */
    private String name;

    /**
     *  服务器地址:店铺局域网服务器地址，用于共有打印服务
     */
    private String server;

    /**
     *  联系人名:
     */
    private String contact;

    /**
     *  联系电话:
     */
    private String phone;

    /**
     *  移动电话:
     */
    private String cphone;

    /**
     *  联系地址:
     */
    private String address;

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
     *  固定二维码:授权信息
     */
    private String weTicket;

    /**
     *  固定二维码:链接信息，可自由生成二维码图片
     */
    private String weUrl;

    /**
     *  所属地区
     */
    private String region;

    /**
     *  微信号
     */
    private String wechat;

    /**
     *  生日
     */
    private Date birthday;

    /**
     *  店铺描述
     */
    private String description;
    
    private String storeId;
    
    private String userId;
    
    private List<String> images;
    
    private List<String > idArray;
    
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getStorephoto() {
        return storephoto;
    }

    public void setStorephoto(String storephoto) {
        this.storephoto = storephoto == null ? null : storephoto.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server == null ? null : server.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
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

    public String getWeTicket() {
        return weTicket;
    }

    public void setWeTicket(String weTicket) {
        this.weTicket = weTicket == null ? null : weTicket.trim();
    }

    public String getWeUrl() {
        return weUrl;
    }

    public void setWeUrl(String weUrl) {
        this.weUrl = weUrl == null ? null : weUrl.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getStoreType() {
		return storeType;
	}

	public void setStoreType(Integer storeType) {
		this.storeType = storeType;
	}

	public Integer getInventoryShare() {
		return inventoryShare;
	}

	public void setInventoryShare(Integer inventoryShare) {
		this.inventoryShare = inventoryShare ;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public List<String> getIdArray() {
		return idArray;
	}

	public void setIdArray(List<String> idArray) {
		this.idArray = idArray;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	
	
    
}