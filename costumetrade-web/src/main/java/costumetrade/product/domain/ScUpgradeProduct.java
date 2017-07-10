package costumetrade.product.domain;

import costumetrade.common.Entity;
import java.math.BigDecimal;
import java.util.Date;

public class ScUpgradeProduct extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  会员ID
     */
    private String memberid;

    /**
     *  会员姓名
     */
    private String name;

    /**
     *  价格
     */
    private BigDecimal price;

    /**
     *  有效期 几个月
     */
    private Integer expiredmonth;

    /**
     *  生效时间
     */
    private Date effectivetime;

    /**
     *  失效时间
     */
    private Date expiredtime;

    /**
     *  图片1
     */
    private String img1;

    /**
     *  图片2
     */
    private String img2;

    /**
     *  图片3
     */
    private String img3;

    /**
     *  描述
     */
    private String description;

    /**
     *  创建时间
     */
    private Date createtime;

    /**
     *  修改时间
     */
    private Date modifytime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid == null ? null : memberid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getExpiredmonth() {
        return expiredmonth;
    }

    public void setExpiredmonth(Integer expiredmonth) {
        this.expiredmonth = expiredmonth;
    }

    public Date getEffectivetime() {
        return effectivetime;
    }

    public void setEffectivetime(Date effectivetime) {
        this.effectivetime = effectivetime;
    }

    public Date getExpiredtime() {
        return expiredtime;
    }

    public void setExpiredtime(Date expiredtime) {
        this.expiredtime = expiredtime;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1 == null ? null : img1.trim();
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2 == null ? null : img2.trim();
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3 == null ? null : img3.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
}