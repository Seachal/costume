package costumetrade.order.service;

import java.util.List;

import com.sf.openapi.common.entity.MessageResp;
import com.sf.openapi.express.sample.route.dto.RouteRespDto;

import costumetrade.order.domain.ScLogistics;
import costumetrade.order.domain.ScStoreAddr;
import costumetrade.order.domain.SsCgsorder;
import costumetrade.order.domain.SsFinancial;
import costumetrade.order.domain.SsProductReview;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.query.OrderCountQuery;
import costumetrade.order.query.OrderDetailQuery;
import costumetrade.order.query.OrderQuery;
import costumetrade.user.domain.SsDataDictionary;


public interface SpOrderService {

	/**
	 *下单
	 * 
	 * */

	public Integer saveOrders(List<SsStoDetail> details,SsStoOrder order,String openid);

	
	/*
	 * 查询订单详情
	 * */
	public OrderDetailQuery getOrder(String orderNo ,Integer orderType, String openid);
	/*
	 * 查询订单列表
	 * */
	public List<SsStoOrder> getOrders(Integer orderType, Integer orderStatus , String openid,Integer pageNum);
	
	/*
	 * 订单取消
	 * */
	public int orderOperate(OrderQuery param);
	/*
	 * 订单修改
	 * */
	public int updateOrder(OrderQuery param);
	/*
	 * 订单支付
	 * */
	public int orderPay(SsFinancial ssFinancial);
	/*
	 * 跳转订单页面查询地址
	 * */
	public ScStoreAddr orderInit(String openid);
	/*
	 * 根据订单号 查询订单
	 * */
	public SsStoOrder order(String orderNo , String storeId);
	
	public List<SsDataDictionary> orderFeeInit(String  openid);
	
	
	public int saveOrderFee(List<SsCgsorder> orders);
	/**
	 * 确定物流  绑定订单号到物流
	 * */
	
	public int confirmLogistic(ScLogistics scLogistics);
	/**
	 * 查询物流
	 * */
	
	public MessageResp<List<RouteRespDto>> queryLogistic(SsStoOrder ssStoOrder);
	
	/**
	 * 评价
	 * */
	
	public int saveReview(SsProductReview ssProductReview);
	
	/**
	 * 统计订单数量
	 * */
	public OrderCountQuery countOrders(String openid);
	
	
	public List<Object> logisticInit(SsStoOrder ssStoOrder);
}
