package costumetrade.user.domain;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

import costumetrade.common.Entity;

public class SpCustProdPrice extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  店铺ID
     */
    private Integer storeid;

    /**
     *  种类 1：货品级别，2：会员类型
     */
    private String type;

    /**
     *  名称(货品种类名称or等级名称)
     */
    private String custtypename;

    /**
     *  货品级别（一、二、三、四、五）
     */
    private String prodgrade;

    /**
     *  毛利值
     */
    private String custpricejson;

    /**
     *  折扣值
     */
    private String discpricejson;
    

    /**
     *  创建时间
     */
    private Date createtime;

    /**
     *  创建人
     */
    private String createby;
    
    private List<PriceJson> custPriceJson;
    
    private List<PriceJson> discPriceJson;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCusttypename() {
        return custtypename;
    }

    public void setCusttypename(String custtypename) {
        this.custtypename = custtypename == null ? null : custtypename.trim();
    }

    public String getProdgrade() {
        return prodgrade;
    }

    public void setProdgrade(String prodgrade) {
        this.prodgrade = prodgrade == null ? null : prodgrade.trim();
    }

    public String getCustpricejson() {
        return custpricejson ;
    }

    public void setCustpricejson(String custpricejson) {
        this.custpricejson = custpricejson == null ? null : custpricejson.trim();
    }

    public String getDiscpricejson() {
        return discpricejson ;
    }

    public void setDiscpricejson(String discpricejson) {
        this.discpricejson = discpricejson == null ? null : discpricejson.trim();
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

	public List<PriceJson> getCustPriceJson() {
		return custPriceJson;//
	}

	public void setCustPriceJson(List<PriceJson> custPriceJson) {
		this.custPriceJson = custPriceJson;
	}

	public List<PriceJson> getDiscPriceJson() {
		
		return discPriceJson;//
	}

	public void setDiscPriceJson(List<PriceJson> discPriceJson) {
		this.discPriceJson = discPriceJson;
	}
	
    
    
}