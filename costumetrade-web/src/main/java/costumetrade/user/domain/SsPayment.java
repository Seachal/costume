package costumetrade.user.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import costumetrade.common.Entity;

public class SsPayment extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  
     */
    private String storeid;

    /**
     *  还款对象类型 1：客户还款，2：供货商还款
     */
    private String payobjtype;

    /**
     *  单号
     */
    private String payno;

    /**
     *  
     */
    private String uuid;

    /**
     *  对账对象ID号
     */
    private String clientId;

    /**
     *  付款方式 1：现金，2：微信，3：支付宝，4：银行卡
     */
    private String paytype;

    /**
     *  还款金额
     */
    private BigDecimal payamt;

    /**
     *  单据类型 1：收客户欠款，2：欠供应商欠款
     */
    private String billtype;

    /**
     *  还款时间
     */
    private Date paytime;

    /**
     *  
     */
    private Date updatetime;

    /**
     *  
     */
    private String updateby;
    
    private BigDecimal receivable;//应收
    private BigDecimal payable;//应付
    
    private String name;
    
    private List<SsDataDictionary> dictionarys;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getPayobjtype() {
        return payobjtype;
    }

    public void setPayobjtype(String payobjtype) {
        this.payobjtype = payobjtype == null ? null : payobjtype.trim();
    }

    public String getPayno() {
        return payno;
    }

    public void setPayno(String payno) {
        this.payno = payno == null ? null : payno.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public BigDecimal getPayamt() {
        return payamt;
    }

    public void setPayamt(BigDecimal payamt) {
        this.payamt = payamt;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }
	public BigDecimal getReceivable() {
		return receivable;
	}

	public void setReceivable(BigDecimal receivable) {
		this.receivable = receivable ==null?BigDecimal.ZERO:receivable;
	}

	public BigDecimal getPayable() {
		return payable;
	}

	public void setPayable(BigDecimal payable) {
		this.payable = payable==null?BigDecimal.ZERO:payable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SsDataDictionary> getDictionarys() {
		return dictionarys;
	}

	public void setDictionarys(List<SsDataDictionary> dictionarys) {
		this.dictionarys = dictionarys;
	}
	
}