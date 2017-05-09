package costumetrade.order.service;

import java.util.List;

import costumetrade.order.domain.ScStoreAddr;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.query.OrderDetailQuery;
import costumetrade.order.query.OrderQuery;
import costumetrade.order.query.PayQuery;


public interface SpOrderService {

	/**
	 *下单
	 * 
	 * */
	public int saveOrders(List<SsStoDetail> details,SsStoOrder order,Integer member);
	
	/*
	 * 查询订单详情
	 * */
	public OrderDetailQuery getOrder(Integer orderId);
	/*
	 * 订单审核
	 * */
	public int orderAudit(OrderQuery param);
	/*
	 * 订单取消
	 * */
	public int orderCancel(OrderQuery param);
	/*
	 * 订单支付
	 * */
	public int orderPay(PayQuery param);
	/*
	 * 跳转订单页面查询地址
	 * */
	public ScStoreAddr orderInit(Integer clientId);
}
