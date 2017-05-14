package costumetrade.pay.req;

import costumetrade.pay.utils.Money;


public class RefundReq extends WxBaseReq{

	/**
	 * 微信订单号 二选一
	 */
	private  String transaction_id;
	/**
	 * 商户订单号  二选一
	 */
	private String out_trade_no ;
	/**
	 * 商户退款单号
	 */
	private String out_refund_no;
	/**
	 * 总金额
	 */
	private Long total_fee;
	/**
	 * 退款金额  退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
	 */ 
	private String refund_fee ;
	/**
	 * 货币种类 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 */
	private String refund_fee_type;
 	/**
 	 * 操作员  操作员帐号, 默认为商户号
 	 */
	private String op_user_id;
	
	
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public Long getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Long total_fee) {
		this.total_fee = total_fee;
	}
	public String getRefund_fee() {
		return refund_fee;
	}
	
	public long getFenRefundFee() {
		Money money = new Money(this.refund_fee);
		return money.getCent();
	}
	
	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}
	public String getRefund_fee_type() {
		return refund_fee_type;
	}
	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}
	public String getOp_user_id() {
		return op_user_id;
	}
	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}
	
	
}
