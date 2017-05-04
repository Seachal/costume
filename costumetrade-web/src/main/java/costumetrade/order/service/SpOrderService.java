package costumetrade.order.service;

import costumetrade.order.query.OrderDetailKeyParam;
import costumetrade.order.query.OrderDetailQuery;
import costumetrade.order.query.OrderQuery;
import costumetrade.order.query.PayParam;


public interface SpOrderService {

	/**
	 *下单
	 * 
	 * */
	public int saveOrders(OrderQuery query);
	
	/*
	 * 查询订单详情
	 * */
	public OrderDetailQuery getOrder(OrderDetailKeyParam param);
	/*
	 * 订单审核
	 * */
	public int orderAudit(OrderDetailKeyParam param);
	/*
	 * 订单取消
	 * */
	public int orderCancel(OrderDetailKeyParam param);
	/*
	 * 订单支付
	 * */
	public int orderPay(PayParam param);
}
