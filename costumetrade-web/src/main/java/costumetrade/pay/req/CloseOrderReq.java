package costumetrade.pay.req;


public class CloseOrderReq extends WxBaseReq{

	/**
	 *  商户订单号
	 */
	private String out_trade_no;

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
	
}
