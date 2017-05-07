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
    
    private Integer clientId;

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

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	
    
    
    

}
