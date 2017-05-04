package costumetrade.order.domain;

import costumetrade.common.Entity;
import java.math.BigDecimal;
import java.util.Date;

public class SpStoOrder extends Entity {
    /**
     *  识别编号：服务器自动生成
     */
    private Integer id;

    /**
     *  商品店铺
     */
    private Integer corpid;

    /**
     *  商品分店
     */
    private String esub;

    /**
     *  卖家客户编号
     */
    private Integer sellerid;

    /**
     *  卖家分店编号
     */
    private Integer sellersubid;

    /**
     *  买家分店编号
     */
    private Integer buyersubid;

    /**
     *  买家客户编号
     */
    private Integer buyerid;

    /**
     *  退款时间
     */
    private Date refunddate;

    /**
     *  外部订单号
     */
    private String relatedouttradeno;

    /**
     *  订单号
     */
    private String payorderno;

    /**
     *  退款状态 1:未退款，2：已退款，3：退款完成,4:退款失败
     */
    private String refundstatus;

    /**
     *  订单类型
     */
    private String ordertype;

    /**
     *  订单状态
     */
    private Integer orderstatus;

    /**
     *  配发类型
     */
    private Integer shipCate;

    /**
     *  配发状态
     */
    private Integer shipState;

    /**
     *  收货名称
     */
    private String shipContact;

    /**
     *  收货电话
     */
    private String shipPhone;

    /**
     *  收货地址
     */
    private String shipAddress;

    /**
     *  往来分店编号
     */
    private String uid;

    /**
     *  往来分店名称
     */
    private String uname;

    /**
     *  备注信息
     */
    private String remarks;

    /**
     *  合计数量
     */
    private BigDecimal totalnum;

    /**
     *  合计金额
     */
    private BigDecimal totalamt;

    /**
     *  折扣比例
     */
    private Integer discountratio;

    /**
     *  折扣金额
     */
    private BigDecimal discount;

    /**
     *  去零金额
     */
    private BigDecimal mchange;

    /**
     *  扣税金额
     */
    private BigDecimal tax;

    /**
     *  实际金额
     */
    private BigDecimal realcost;

    /**
     *  支付方式一
     */
    private String paycate1;

    /**
     *  支付金额一
     */
    private BigDecimal paycost1;

    /**
     *  支付方式二
     */
    private String paycate2;

    /**
     *  支付金额二
     */
    private BigDecimal paycost2;

    /**
     *  欠款金额
     */
    private BigDecimal debet;

    /**
     *  开单时间
     */
    private Date orderTime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCorpid() {
        return corpid;
    }

    public void setCorpid(Integer corpid) {
        this.corpid = corpid;
    }

    public String getEsub() {
        return esub;
    }

    public void setEsub(String esub) {
        this.esub = esub == null ? null : esub.trim();
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getSellersubid() {
        return sellersubid;
    }

    public void setSellersubid(Integer sellersubid) {
        this.sellersubid = sellersubid;
    }

    public Integer getBuyersubid() {
        return buyersubid;
    }

    public void setBuyersubid(Integer buyersubid) {
        this.buyersubid = buyersubid;
    }

    public Integer getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(Integer buyerid) {
        this.buyerid = buyerid;
    }

    public Date getRefunddate() {
        return refunddate;
    }

    public void setRefunddate(Date refunddate) {
        this.refunddate = refunddate;
    }

    public String getRelatedouttradeno() {
        return relatedouttradeno;
    }

    public void setRelatedouttradeno(String relatedouttradeno) {
        this.relatedouttradeno = relatedouttradeno == null ? null : relatedouttradeno.trim();
    }

    public String getPayorderno() {
        return payorderno;
    }

    public void setPayorderno(String payorderno) {
        this.payorderno = payorderno == null ? null : payorderno.trim();
    }

    public String getRefundstatus() {
        return refundstatus;
    }

    public void setRefundstatus(String refundstatus) {
        this.refundstatus = refundstatus == null ? null : refundstatus.trim();
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype == null ? null : ordertype.trim();
    }

    public Integer getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Integer getShipCate() {
        return shipCate;
    }

    public void setShipCate(Integer shipCate) {
        this.shipCate = shipCate;
    }

    public Integer getShipState() {
        return shipState;
    }

    public void setShipState(Integer shipState) {
        this.shipState = shipState;
    }

    public String getShipContact() {
        return shipContact;
    }

    public void setShipContact(String shipContact) {
        this.shipContact = shipContact == null ? null : shipContact.trim();
    }

    public String getShipPhone() {
        return shipPhone;
    }

    public void setShipPhone(String shipPhone) {
        this.shipPhone = shipPhone == null ? null : shipPhone.trim();
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress == null ? null : shipAddress.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public BigDecimal getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(BigDecimal totalnum) {
        this.totalnum = totalnum;
    }

    public BigDecimal getTotalamt() {
        return totalamt;
    }

    public void setTotalamt(BigDecimal totalamt) {
        this.totalamt = totalamt;
    }

    public Integer getDiscountratio() {
        return discountratio;
    }

    public void setDiscountratio(Integer discountratio) {
        this.discountratio = discountratio;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getMchange() {
        return mchange;
    }

    public void setMchange(BigDecimal mchange) {
        this.mchange = mchange;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getRealcost() {
        return realcost;
    }

    public void setRealcost(BigDecimal realcost) {
        this.realcost = realcost;
    }

    public String getPaycate1() {
        return paycate1;
    }

    public void setPaycate1(String paycate1) {
        this.paycate1 = paycate1 == null ? null : paycate1.trim();
    }

    public BigDecimal getPaycost1() {
        return paycost1;
    }

    public void setPaycost1(BigDecimal paycost1) {
        this.paycost1 = paycost1;
    }

    public String getPaycate2() {
        return paycate2;
    }

    public void setPaycate2(String paycate2) {
        this.paycate2 = paycate2 == null ? null : paycate2.trim();
    }

    public BigDecimal getPaycost2() {
        return paycost2;
    }

    public void setPaycost2(BigDecimal paycost2) {
        this.paycost2 = paycost2;
    }

    public BigDecimal getDebet() {
        return debet;
    }

    public void setDebet(BigDecimal debet) {
        this.debet = debet;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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