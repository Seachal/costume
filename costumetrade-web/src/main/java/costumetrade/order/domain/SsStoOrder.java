package costumetrade.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import costumetrade.common.Entity;

public class SsStoOrder extends Entity {
    /**
     *  识别编号：服务器自动生成
     */
    private Integer id;

    /**
     *  卖家客户编号
     */
    private Integer sellerstoreid;

    /**
     *  买家分店编号
     */
    private Integer buyerstoreid;

    private String buyer;
    
    private String seller;
    /**
     *  经办人
     */
    private String operator;

    /**
     *  订单号
     */
    private String payorderno;

    /**
     *  退款时间
     */
    private Date refunddate;
    /**
     * 运费
     * */
    private BigDecimal freight;

    /**
     *  外部订单号
     */
    private String relatedouttradeno;

    /**
     *  退款状态 1:未退款，2：已退款，3：退款完成,4:退款失败
     */
    private String refundstatus;

    /**	
     *  订单类型 1：线上订单，2：线下订单
     */
    private String ordertype;

    /**
     *  订单状态 1：新增，2：已付款，3、已审核，4：已发货，5：已完成，6：取消
     */
    private Integer orderstatus;
    
    private String logisticsCode;

    /**
     *  配发类型
     */
    private Integer shiptype;

    /**
     *  配发状态
     */
    private Integer shipstatus;

    /**
     *  收货名称
     */
    private String shipcontact;

    /**
     *  收货电话
     */
    private String shipphone;

    /**
     *  收货地址
     */
    private String shipaddress;

    /**
     *  往来店铺编号
     */
    private String contactstoreid;

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
    private BigDecimal debetamt;

    /**
     *  开单时间
     */
    private Date ordertime;

    /**
     *  创建时间
     */
    private Date createtime;

    /**
     *  创建人
     */
    private String createby;

    /**
     *  修改时间
     */
    private Date modifytime;

    /**
     *  修改人
     */
    private String modifyby;
    
    private List<Integer>  status;
    
    private Integer storeId ;
    
    private String openid ;
  
    private Integer pageNum;
    
    private Integer count;
    
    private String clientId;
    
    private String receiverName;
    
    private String receiverImage;
    
    private static final long serialVersionUID = 1L;
    
   
    
    

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerstoreid() {
        return sellerstoreid;
    }

    public void setSellerstoreid(Integer sellerstoreid) {
        this.sellerstoreid = sellerstoreid;
    }

    public Integer getBuyerstoreid() {
        return buyerstoreid;
    }

    public void setBuyerstoreid(Integer buyerstoreid) {
        this.buyerstoreid = buyerstoreid;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getPayorderno() {
        return payorderno;
    }

    public void setPayorderno(String payorderno) {
        this.payorderno = payorderno == null ? null : payorderno.trim();
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

    public Integer getShiptype() {
        return shiptype;
    }

    public void setShiptype(Integer shiptype) {
        this.shiptype = shiptype;
    }

    public Integer getShipstatus() {
        return shipstatus;
    }

    public void setShipstatus(Integer shipstatus) {
        this.shipstatus = shipstatus;
    }

    public String getShipcontact() {
        return shipcontact;
    }

    public void setShipcontact(String shipcontact) {
        this.shipcontact = shipcontact == null ? null : shipcontact.trim();
    }

    public String getShipphone() {
        return shipphone;
    }

    public void setShipphone(String shipphone) {
        this.shipphone = shipphone == null ? null : shipphone.trim();
    }

    public String getShipaddress() {
        return shipaddress;
    }

    public void setShipaddress(String shipaddress) {
        this.shipaddress = shipaddress == null ? null : shipaddress.trim();
    }

    public String getContactstoreid() {
        return contactstoreid;
    }

    public void setContactstoreid(String contactstoreid) {
        this.contactstoreid = contactstoreid == null ? null : contactstoreid.trim();
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
        return totalamt==null?BigDecimal.ZERO:totalamt;
    }

    public void setTotalamt(BigDecimal totalamt) {
        this.totalamt = totalamt==null?BigDecimal.ZERO:totalamt;
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
        return realcost==null?BigDecimal.ZERO:realcost;
    }

    public void setRealcost(BigDecimal realcost) {
        this.realcost = realcost==null?BigDecimal.ZERO:realcost;
    }
    public String getPaycate1() {
		return paycate1;
	}

	public void setPaycate1(String paycate1) {
		this.paycate1 = paycate1;
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

    public BigDecimal getDebetamt() {
        return debetamt;
    }

    public void setDebetamt(BigDecimal debetamt) {
        this.debetamt = debetamt;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
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

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getModifyby() {
        return modifyby;
    }

    public void setModifyby(String modifyby) {
        this.modifyby = modifyby == null ? null : modifyby.trim();
    }

	public List<Integer> getStatus() {
		return status;
	}

	public void setStatus(List<Integer> status) {
		this.status = status;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}


	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public String getLogisticsCode() {
		return logisticsCode;
	}

	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverImage() {
		return receiverImage;
	}

	public void setReceiverImage(String receiverImage) {
		this.receiverImage = receiverImage;
	}
    
    
}