package costumetrade.order.domain;

import java.util.Date;

import costumetrade.common.Entity;

public class SsProductReview extends Entity {
    /**
     *  
     */
    private Integer id;

    /**
     *  店铺ID号
     */
    private String storeid;

    /**
     *  货号ID
     */
    private String productid;

    /**
     *  客户头像
     */
    private String headphoto;

    /**
     *  客户名称
     */
    private String clientname;

    /**
     *  评论详情
     */
    private String msg;
    
    /**
     * 评价是上传的图片地址  是一个数组的字符串
     * */
    private String imageUrl;
    /**
     *  创建时间
     */
    private Date createtime;

    /**
     *  
     */
    private String createby;
    
    private Integer countReview;

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

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getHeadphoto() {
        return headphoto;
    }

    public void setHeadphoto(String headphoto) {
        this.headphoto = headphoto == null ? null : headphoto.trim();
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname == null ? null : clientname.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getCountReview() {
		return countReview;
	}

	public void setCountReview(Integer countReview) {
		this.countReview = countReview;
	}
    
    
}