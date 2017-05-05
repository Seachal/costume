package costumetrade.order.query;

import java.math.BigDecimal;
import java.util.Date;

import costumetrade.common.Entity;

public class PayParam extends Entity{

    
    private Integer operate;//订单状态  1：新增   2、已付款  3、审核  4、发货  5、收货  6、已取消
    private Integer corpId ;
   
    /**
     *  
     */
    private Integer id;

    /**
     *  收付记录标识
     */
    private String financialTag;

    /**
     *  单号
     */
    private String orderNo;

    /**
     *  买家客户号
     */
    private Integer buyerClientId;

    /**
     *  卖家客户号
     */
    private Integer sellerClientId;

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
     *  收付对象标识
     */
    private Long relaId;

    /**
     *  单位类型：使用标准表
     */
    private Short relaType;

    /**
     *  收款日期
     */
    private BigDecimal incomeDate;

    /**
     *  收付类型：使用标准码receiptPay_type
     */
    private Short receiptpayType;

    /**
     *  支付类型:使用标准表：payType
     */
    private Short payType;

    /**
     *  付款日期
     */
    private Date payDate;

    /**
     *  付款卡号
     */
    private String partnerCardno;

    /**
     *  付款银行：使用标准码，可以是支付宝等，使用标准码bankname
     */
    private Short payBank;

    /**
     *  支付渠道，使用标准码paychannel
     */
    private Short payChannel;

    /**
     *  业务类型:使用标准表，0 初始数据
     */
    private Short bussiType;

    /**
     *  用于建立和收支的关系，
     */
    private String relaNo;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFinancialTag() {
        return financialTag;
    }

    public void setFinancialTag(String financialTag) {
        this.financialTag = financialTag == null ? null : financialTag.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getBuyerClientId() {
        return buyerClientId;
    }

    public void setBuyerClientId(Integer buyerClientId) {
        this.buyerClientId = buyerClientId;
    }

    public Integer getSellerClientId() {
        return sellerClientId;
    }

    public void setSellerClientId(Integer sellerClientId) {
        this.sellerClientId = sellerClientId;
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

    public Long getRelaId() {
        return relaId;
    }

    public void setRelaId(Long relaId) {
        this.relaId = relaId;
    }

    public Short getRelaType() {
        return relaType;
    }

    public void setRelaType(Short relaType) {
        this.relaType = relaType;
    }

    public BigDecimal getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(BigDecimal incomeDate) {
        this.incomeDate = incomeDate;
    }

    public Short getReceiptpayType() {
        return receiptpayType;
    }

    public void setReceiptpayType(Short receiptpayType) {
        this.receiptpayType = receiptpayType;
    }

    public Short getPayType() {
        return payType;
    }

    public void setPayType(Short payType) {
        this.payType = payType;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getPartnerCardno() {
        return partnerCardno;
    }

    public void setPartnerCardno(String partnerCardno) {
        this.partnerCardno = partnerCardno == null ? null : partnerCardno.trim();
    }

    public Short getPayBank() {
        return payBank;
    }

    public void setPayBank(Short payBank) {
        this.payBank = payBank;
    }

    public Short getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(Short payChannel) {
        this.payChannel = payChannel;
    }

    public Short getBussiType() {
        return bussiType;
    }

    public void setBussiType(Short bussiType) {
        this.bussiType = bussiType;
    }

    public String getRelaNo() {
        return relaNo;
    }

    public void setRelaNo(String relaNo) {
        this.relaNo = relaNo == null ? null : relaNo.trim();
    }

	public Integer getOperate() {
		return operate;
	}

	public void setOperate(Integer operate) {
		this.operate = operate;
	}

	public Integer getCorpId() {
		return corpId;
	}

	public void setCorpId(Integer corpId) {
		this.corpId = corpId;
	}
    
    
}
