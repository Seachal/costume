package costumetrade.order.domain;

import java.util.Date;

import costumetrade.common.Entity;

public class ScLogistics extends Entity {
    /**
     *  物流ID号
     */
    private Integer id;

    /**
     *  店铺编号
     */
    private Integer storeid;

    /**
     *  订单号
     */
    private String orderno;

    /**
     *  物流类型 1：发货物流，2：退货物流
     */
    private String logisticstype;

    /**
     *  物流单号
     */
    private String logisticsno;

    /**
     *  物流名称
     */
    private String logisticsname;

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

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getLogisticstype() {
        return logisticstype;
    }

    public void setLogisticstype(String logisticstype) {
        this.logisticstype = logisticstype == null ? null : logisticstype.trim();
    }

    public String getLogisticsno() {
        return logisticsno;
    }

    public void setLogisticsno(String logisticsno) {
        this.logisticsno = logisticsno == null ? null : logisticsno.trim();
    }

    public String getLogisticsname() {
        return logisticsname;
    }

    public void setLogisticsname(String logisticsname) {
        this.logisticsname = logisticsname == null ? null : logisticsname.trim();
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