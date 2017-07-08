package costumetrade.print.domain;

import costumetrade.common.Entity;
import java.util.Date;

public class ScPrinterWeb extends Entity {
    /**
     *  主键
     */
    private Integer id;

    /**
     *  企业ID
     */
    private String storeid;

    /**
     *  打印机编码
     */
    private String printid;

    /**
     *  
     */
    private Date createTime;

    /**
     *  
     */
    private String createBy;

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
        this.storeid = storeid == null ? null : storeid.trim();
    }

    public String getPrintid() {
        return printid;
    }

    public void setPrintid(String printid) {
        this.printid = printid == null ? null : printid.trim();
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
}