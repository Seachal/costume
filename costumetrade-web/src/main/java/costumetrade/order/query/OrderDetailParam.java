package costumetrade.order.query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import costumetrade.common.Entity;
import costumetrade.order.domain.SpStoDetail;
import costumetrade.order.domain.SpStoOrder;

public class OrderDetailParam extends Entity {
    private Integer corpId ;
    
    private List<SpStoDetail> detail;

    private static final long serialVersionUID = 1L;

	

	public Integer getCorpId() {
		return corpId;
	}

	public void setCorpId(Integer corpId) {
		this.corpId = corpId;
	}

	public List<SpStoDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<SpStoDetail> detail) {
		this.detail = detail;
	}
    
    
   
}