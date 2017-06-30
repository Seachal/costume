package costumetrade.report.domain;

import java.math.BigDecimal;

import costumetrade.common.Entity;

public class PayTypeQuery extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String payType;
	
	private BigDecimal receiptAmount;

	private BigDecimal payAmount;
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public BigDecimal getReceiptAmount() {
		return receiptAmount;
	}

	public void setReceiptAmount(BigDecimal receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	
	

}
