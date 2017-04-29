package costumetrade.order.domain;

import costumetrade.common.Entity;

public class SpPrinterInfo extends Entity {
    /**
     *  
     */
    private String mbid;

    /**
     *  
     */
    private Integer mbuser;

    /**
     *  
     */
    private String mbimage;

    private static final long serialVersionUID = 1L;
    
    /**
     *  企业ID
     */
    private Integer corpid;

    /**
     *  分店ID
     */
    private String subid;
    
    private Integer id ;
    
    

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

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid == null ? null : subid.trim();
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid == null ? null : mbid.trim();
    }

    public Integer getMbuser() {
        return mbuser;
    }

    public void setMbuser(Integer mbuser) {
        this.mbuser = mbuser;
    }

    public String getMbimage() {
        return mbimage;
    }

    public void setMbimage(String mbimage) {
        this.mbimage = mbimage == null ? null : mbimage.trim();
    }
}