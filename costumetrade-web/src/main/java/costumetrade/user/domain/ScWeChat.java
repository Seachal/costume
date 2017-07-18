package costumetrade.user.domain;

import java.util.Date;

import costumetrade.common.Entity;

public class ScWeChat extends Entity {
    /**
     *  主键
     */
    private Integer id;

    /**
     *  微信openId账号
     */
    private String openid;

    /**
     *  店铺编号
     */
    private String storeid;

    /**
     *  员工编号
     */
    private String empid;

    /**
     *  创建时间
     */
    private Date createtime;

    /**
     *  创建者
     */
    private String createby;

    /**
     *  普通用户
     */
    private String userid;
    
    private String unionid;
    
    private String name ;
    
    private String photo;
    
    private String buyyer;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBuyyer() {
		return buyyer;
	}

	public void setBuyyer(String buyyer) {
		this.buyyer = buyyer;
	}
    
    
}