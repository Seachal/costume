package costumetrade.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import costumetrade.common.Entity;

public class SsFinancial extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  交易单号
     */
    private String tradeno;

    /**
     *  订单号
     */
    private String orderno;
    /**
     *  关联对象号
     */
    private String relaNo;

    /**
     *  买家客户号
     */
    private Integer buyerid;

    /**
     *  卖家客户号
     */
    private Integer sellerid;

    /**
     *  备注
     */
    private String remark;

    /**
     *  收入
     */
    private BigDecimal income;

    /**
     *  支出
     */
    private BigDecimal pay;

    /**
     *  收款日期
     */
    private BigDecimal incomedate;

    /**
     *  支付类型  1:现金支付，2：支付宝支付，3：银行卡支付，4：微信支付，5：票据支付
     */
    private Short payType;

    /**
     *  付款日期
     */
    private Date paydate;

    /**
     *  付款卡号
     */
    private String cardno;

    /**
     *  付款银行：使用标准码，可以是支付宝等，使用标准码bankname
     */
    private Short payBank;

    /**
     *  业务类型:使用标准表，0 初始数据
     */
    private Short bussiType;
    
    private Integer operate;//订单状态  1：新增   2、已付款  3、审核  4、发货  5、收货  6、已取消
    
    private Integer clientId;

    private static final long serialVersionUID = 1L;
    
    

    public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

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

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Integer getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(Integer buyerid) {
        this.buyerid = buyerid;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public BigDecimal getIncomedate() {
        return incomedate;
    }

    public void setIncomedate(BigDecimal incomedate) {
        this.incomedate = incomedate;
    }

    public Short getPayType() {
        return payType;
    }

    public void setPayType(Short payType) {
        this.payType = payType;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    public Short getPayBank() {
        return payBank;
    }

    public void setPayBank(Short payBank) {
        this.payBank = payBank;
    }

    public Short getBussiType() {
        return bussiType;
    }

    public void setBussiType(Short bussiType) {
        this.bussiType = bussiType;
    }

	public Integer getOperate() {
		return operate;
	}

	public void setOperate(Integer operate) {
		this.operate = operate;
	}

	public String getRelaNo() {
		return relaNo;
	}

	public void setRelaNo(String relaNo) {
		this.relaNo = relaNo;
	}
    
    
}