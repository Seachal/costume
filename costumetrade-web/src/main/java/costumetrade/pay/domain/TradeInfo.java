package costumetrade.pay.domain;

import java.math.BigDecimal;
import java.util.Date;

import costumetrade.common.Entity;

public class TradeInfo extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  交易号
     */
    private String tradeno;

    /**
     *  支付订单号
     */
    private String payorderno;

    /**
     *  交易状态 1：新建，2：支付成功，3：支付失败
     */
    private Integer status;

    /**
     *  交易金额
     */
    private BigDecimal tradeamt;

    /**
     *  
     */
    private Date createtime;

    /**
     *  
     */
    private String createby;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTradeno() {
        return tradeno;
    }

    public void setTradeno(String tradeno) {
        this.tradeno = tradeno == null ? null : tradeno.trim();
    }

    public String getPayorderno() {
        return payorderno;
    }

    public void setPayorderno(String payorderno) {
        this.payorderno = payorderno == null ? null : payorderno.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getTradeamt() {
        return tradeamt;
    }

    public void setTradeamt(BigDecimal tradeamt) {
        this.tradeamt = tradeamt;
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
}