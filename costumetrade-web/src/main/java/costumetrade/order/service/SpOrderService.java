package costumetrade.order.service;

import java.util.List;

import costumetrade.order.domain.ScStoreAddr;
import costumetrade.order.domain.SsFinancial;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.query.OrderDetailQuery;
import costumetrade.order.query.OrderQuery;


public interface SpOrderService {

	/**
	 *下单
	 * 
	 * */
	public SsStoOrder saveOrders(List<SsStoDetail> details,SsStoOrder order,Integer clientId);
	
	/*
	 * 查询订单详情
	 * */
	public OrderDetailQuery getOrder(String orderNo ,Integer orderType, Integer clientId);
	/*
	 * 查询订单列表
	 * */
	public List<SsStoOrder> getOrders(Integer orderType, Integer orderStatus , Integer clientId);
	/*
	 * 订单审核
	 * */
	public int orderAudit(OrderQuery param);
	/*
	 * 订单取消
	 * */
	public int orderOperate(OrderQuery param);
	/*
	 * 订单支付
	 * */
	public int orderPay(SsFinancial ssFinancial);
	/*
	 * 跳转订单页面查询地址
	 * */
	public ScStoreAddr orderInit(Integer clientId);
	/*
	 * 根据订单号 查询订单
	 * */
	public SsStoOrder order(String orderNo , Integer storeId);
}
