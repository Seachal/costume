package costumetrade.order.query;

import costumetrade.common.Entity;

public class KeyParam extends Entity{
	  /**
     *  编号
     */
    private Integer id;

    /**
     *  企业ID
     */
    private Integer corpId;
    
    private Integer subId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCorpId() {
		return corpId;
	}
	public void setCorpId(Integer corpId) {
		this.corpId = corpId;
	}

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}
    
    
    

}
