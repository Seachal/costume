package costumetrade.pay.req;

import costumetrade.pay.utils.Money;




public class PayInfoReq extends WxBaseReq{
	
	    /**
	     * 商品描述
	     */
		 private String 	body;
		/**
		 * 商品详情
		 */
		 private String 	 detail;
		/**
		 * 附加数据
		 */
		 private String 	attach;
		/**
		 * 商户订单号
		 */
		 private String 	out_trade_no;
		 	/**
		 	 * 货币类型
		 	 */
		 private String 	fee_type;
		 	/**
		 	 * 总金额
		 	 */
		 private String 	total_fee;
		 	/**
		 	 * 终端IP
		 	 */
		 private String 	spbill_create_ip;
		 	/**
		 	 * 交易起始时间 	 20091225091010 	订单生成时间，格式为yyyyMMddHHmmss，
		 	 */
		 private String 	time_start; 
			/**
			 * 交易结束时间 20091227091010 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则 注意：最短失效时间间隔必须大于5分钟
			 */
		 private String 	time_expire;
		
			/**
			 * 商品标记
			 */
		 private String 	goods_tag;
		 	/**
		 	 * 通知地址 http://www.weixin.qq.com/wxpay/pay.php 	接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
		 	 */
		 private String 	notify_url ;	
		 		/**
		 		 * 交易类型
		 		 */
		 private String 	trade_type="JSAPI";
		 	/**
		 	 * 商品ID
		 	 */
		 private String 	product_id;
		 	/**
		 	 * 指定支付方式 
		 	 */
		private String 	limit_pay 	;
			/**
			 * 用户标识 此参数必传，用户在商户appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
			 */
		private String 	openid ;
		
		private String storeId;
		
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
		public String getDetail() {
			return detail;
		}
		public void setDetail(String detail) {
			this.detail = detail;
		}
		public String getAttach() {
			return attach;
		}
		public void setAttach(String attach) {
			this.attach = attach;
		}
		public String getOut_trade_no() {
			return out_trade_no;
		}
		public void setOut_trade_no(String out_trade_no) {
			this.out_trade_no = out_trade_no;
		}
		public String getFee_type() {
			return fee_type;
		}
		public void setFee_type(String fee_type) {
			this.fee_type = fee_type;
		}
		public String getTotal_fee() {
			return total_fee;
		}
		public long getTotalFeeFenLong() {
			Money  money = new Money(this.total_fee);
			return money.getCent();
		}
		public void setTotal_fee(String total_fee) {
			this.total_fee = total_fee;
		}
		public String getSpbill_create_ip() {
			return spbill_create_ip;
		}
		public void setSpbill_create_ip(String spbill_create_ip) {
			this.spbill_create_ip = spbill_create_ip;
		}
		public String getTime_start() {
			return time_start;
		}
		public void setTime_start(String time_start) {
			this.time_start = time_start;
		}
		public String getTime_expire() {
			return time_expire;
		}
		public void setTime_expire(String time_expire) {
			this.time_expire = time_expire;
		}
		public String getGoods_tag() {
			return goods_tag;
		}
		public void setGoods_tag(String goods_tag) {
			this.goods_tag = goods_tag;
		}
		public String getNotify_url() {
			return notify_url;
		}
		public void setNotify_url(String notify_url) {
			this.notify_url = notify_url;
		}
		public String getTrade_type() {
			return trade_type;
		}
		public void setTrade_type(String trade_type) {
			this.trade_type = trade_type;
		}
		public String getProduct_id() {
			return product_id;
		}
		public void setProduct_id(String product_id) {
			this.product_id = product_id;
		}
		public String getLimit_pay() {
			return limit_pay;
		}
		public void setLimit_pay(String limit_pay) {
			this.limit_pay = limit_pay;
		}
		public String getOpenid() {
			return openid;
		}
		public void setOpenid(String openid) {
			this.openid = openid;
		}
		public String getStoreId() {
			return storeId;
		}
		public void setStoreId(String storeId) {
			this.storeId = storeId;
		}	
	
	
}
