package costumetrade.pay.req;


public class DownloadBillReq extends WxBaseReq{

    /**
     * 对账单日期	
     */
	private String bill_date;
	/**
	 * 账单类型
	 */
	private String bill_type;
	
	
	public String getBill_date() {
		return bill_date;
	}
	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
	public String getBill_type() {
		return bill_type;
	}
	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}
	
	
}
