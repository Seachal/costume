package costumetrade.order.domain;

import costumetrade.common.Entity;

import java.math.BigDecimal;
import java.util.Date;

public class SpPBrand extends Entity {
    /**
     *  助记简码
     */
    private String fastcode;

    /**
     *  零级商品利润
     */
    private BigDecimal profitD0;

    /**
     *  一级商品利润
     */
    private BigDecimal profitD1;

    /**
     *  二级商品利润
     */
    private BigDecimal profitD2;

    /**
     *  三级商品利润
     */
    private BigDecimal profitD3;

    /**
     *  四级商品利润
     */
    private BigDecimal profitD4;

    /**
     *  五级商品利润
     */
    private BigDecimal profitD5;

    /**
     *  计价精度
     */
    private BigDecimal profitPrecise;

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

    private static final long serialVersionUID = 1L;
    /**
     *  企业ID
     */
    private Integer corpid;

    /**
     *  品牌编号ID
     */
    private Integer id;

    public Integer getCorpid() {
        return corpid;
    }

    public void setCorpid(Integer corpid) {
        this.corpid = corpid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFastcode() {
        return fastcode;
    }

    public void setFastcode(String fastcode) {
        this.fastcode = fastcode == null ? null : fastcode.trim();
    }

    public BigDecimal getProfitD0() {
        return profitD0;
    }

    public void setProfitD0(BigDecimal profitD0) {
        this.profitD0 = profitD0;
    }

    public BigDecimal getProfitD1() {
        return profitD1;
    }

    public void setProfitD1(BigDecimal profitD1) {
        this.profitD1 = profitD1;
    }

    public BigDecimal getProfitD2() {
        return profitD2;
    }

    public void setProfitD2(BigDecimal profitD2) {
        this.profitD2 = profitD2;
    }

    public BigDecimal getProfitD3() {
        return profitD3;
    }

    public void setProfitD3(BigDecimal profitD3) {
        this.profitD3 = profitD3;
    }

    public BigDecimal getProfitD4() {
        return profitD4;
    }

    public void setProfitD4(BigDecimal profitD4) {
        this.profitD4 = profitD4;
    }

    public BigDecimal getProfitD5() {
        return profitD5;
    }

    public void setProfitD5(BigDecimal profitD5) {
        this.profitD5 = profitD5;
    }

    public BigDecimal getProfitPrecise() {
        return profitPrecise;
    }

    public void setProfitPrecise(BigDecimal profitPrecise) {
        this.profitPrecise = profitPrecise;
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
}