package costumetrade.pay.res;

public class ResRefundInfo extends WxBaseRes{

	
		private String device_info;
		/**
		 * 	微信订单号
		 */
	    private String transaction_id;
		/**
		 * 商户订单号
		 */
		private String	out_trade_no;
		 /**
		  * 商户退款单号
		  */
		private String out_refund_no;
		 /**
		  * 微信退款单号 
		  */
	    private String refund_id;
		 /**
		  * 退款渠道  	ORIGINAL—原路退款
	      *          BALANCE—退回到余额
		  */
		private String refund_channel;
	
	     /**
	      * 退款金额
	      */
		private String refund_fee;
		 /**
		  * 订单总金额
		  */
	    private String total_fee;
		 	/**
		 	 * 订单金额货币种类  订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
		 	 */
		private String fee_type;
		 	/**
		 	 * 现金支付金额  现金支付金额，单位为分，只能为整数，详见支付金额
		 	 */
		private String cash_fee ;	
		 	/**
		 	 * 现金退款金额 现金退款金额，单位为分，只能为整数，详见支付金额
		 	 */
		private String cash_refund_fee;
		 
		 
		public String getDevice_info() {
			return device_info;
		}
		public void setDevice_info(String device_info) {
			this.device_info = device_info;
		}
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
		public String getRefund_id() {
			return refund_id;
		}
		public void setRefund_id(String refund_id) {
			this.refund_id = refund_id;
		}
		public String getRefund_channel() {
			return refund_channel;
		}
		public void setRefund_channel(String refund_channel) {
			this.refund_channel = refund_channel;
		}
		public String getRefund_fee() {
			return refund_fee;
		}
		public void setRefund_fee(String refund_fee) {
			this.refund_fee = refund_fee;
		}
		public String getTotal_fee() {
			return total_fee;
		}
		public void setTotal_fee(String total_fee) {
			this.total_fee = total_fee;
		}
		public String getFee_type() {
			return fee_type;
		}
		public void setFee_type(String fee_type) {
			this.fee_type = fee_type;
		}
		public String getCash_fee() {
			return cash_fee;
		}
		public void setCash_fee(String cash_fee) {
			this.cash_fee = cash_fee;
		}
		public String getCash_refund_fee() {
			return cash_refund_fee;
		}
		public void setCash_refund_fee(String cash_refund_fee) {
			this.cash_refund_fee = cash_refund_fee;
		}	
	 
	 
}
