package costumetrade.order.service;

import java.util.List;

import costumetrade.order.domain.ScStoreAddr;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.query.OrderDetailKeyParam;
import costumetrade.order.query.OrderDetailQuery;
import costumetrade.order.query.OrderQuery;
import costumetrade.order.query.Param;
import costumetrade.order.query.PayParam;


public interface SpOrderService {

	/**
	 *下单
	 * 
	 * */
	public int saveOrders(List<SsStoDetail> details,SsStoOrder order,Integer member);
	
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
	/*
	 * 跳转订单页面查询地址
	 * */
	public ScStoreAddr orderInit(Integer clientId);
}
