package costumetrade.order.query;

import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SsStoDetail;

public class OrderDetailParam extends Entity {
    private Integer corpId ;
    
    private List<SsStoDetail> detail;

    private static final long serialVersionUID = 1L;

	

	public Integer getCorpId() {
		return corpId;
	}

	public void setCorpId(Integer corpId) {
		this.corpId = corpId;
	}

	public List<SsStoDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<SsStoDetail> detail) {
		this.detail = detail;
	}
    
    
   
}