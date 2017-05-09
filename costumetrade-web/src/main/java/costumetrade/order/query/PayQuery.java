package costumetrade.order.query;

import java.math.BigDecimal;
import java.util.Date;

import costumetrade.common.Entity;
import costumetrade.order.domain.SsFinancial;

public class PayQuery extends Entity{

    
    private Integer operate;//订单状态  1：新增   2、已付款  3、审核  4、发货  5、收货  6、已取消
    private Integer corpId ;
   
    private SsFinancial ssFinancial;
    
    

	public SsFinancial getSsFinancial() {
		return ssFinancial;
	}

	public void setSsFinancial(SsFinancial ssFinancial) {
		this.ssFinancial = ssFinancial;
	}

	public Integer getOperate() {
		return operate;
	}

	public void setOperate(Integer operate) {
		this.operate = operate;
	}

	public Integer getCorpId() {
		return corpId;
	}

	public void setCorpId(Integer corpId) {
		this.corpId = corpId;
	}
    
    
}
