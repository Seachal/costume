package costumetrade.user.domain;

import costumetrade.common.Entity;
import java.util.Date;

public class ScPrinterInfo extends Entity {
    /**
     *  主键
     */
    private Integer id;

    /**
     *  企业ID
     */
    private Integer storeid;

    /**
     *  打印机编码
     */
    private String printid;

    /**
     *  店铺logo
     */
    private String imageLogo;

    /**
     *  店铺二维码
     */
    private String imageCode;

    /**
     *  是否打印店铺二维码
     */
    private Integer isprintcode;

    /**
     *  是否打印店铺logo
     */
    private Integer isprintlogo;

    /**
     *  微信收款二维码
     */
    private String imageWechat;

    /**
     *  支付宝收款二维码
     */
    private String imageAlipay;

    /**
     *  
     */
    private Date createTime;

    /**
     *  
     */
    private String createby;

    /**
     *  银行信息
     */
    private String bankmessage;

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

    public String getPrintid() {
        return printid;
    }

    public void setPrintid(String printid) {
        this.printid = printid == null ? null : printid.trim();
    }

    public String getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(String imageLogo) {
        this.imageLogo = imageLogo == null ? null : imageLogo.trim();
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode == null ? null : imageCode.trim();
    }

    public Integer getIsprintcode() {
        return isprintcode;
    }

    public void setIsprintcode(Integer isprintcode) {
        this.isprintcode = isprintcode;
    }

    public Integer getIsprintlogo() {
        return isprintlogo;
    }

    public void setIsprintlogo(Integer isprintlogo) {
        this.isprintlogo = isprintlogo;
    }

    public String getImageWechat() {
        return imageWechat;
    }

    public void setImageWechat(String imageWechat) {
        this.imageWechat = imageWechat == null ? null : imageWechat.trim();
    }

    public String getImageAlipay() {
        return imageAlipay;
    }

    public void setImageAlipay(String imageAlipay) {
        this.imageAlipay = imageAlipay == null ? null : imageAlipay.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public String getBankmessage() {
        return bankmessage;
    }

    public void setBankmessage(String bankmessage) {
        this.bankmessage = bankmessage == null ? null : bankmessage.trim();
    }
}