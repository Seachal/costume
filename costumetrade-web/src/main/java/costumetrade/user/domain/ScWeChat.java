package costumetrade.user.domain;

import costumetrade.common.Entity;
import java.util.Date;

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
    private Integer storeid;

    /**
     *  员工编号
     */
    private Integer empid;

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
    private Integer userid;

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

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}