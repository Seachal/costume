package costumetrade.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SsFinancial;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.mapper.SpCartMapper;
import costumetrade.order.mapper.SsFinancialMapper;
import costumetrade.order.mapper.SsStoDetailMapper;
import costumetrade.order.mapper.SsStoOrderMapper;
import costumetrade.order.query.OrderDetailKeyParam;
import costumetrade.order.query.OrderDetailParam;
import costumetrade.order.query.OrderDetailQuery;
import costumetrade.order.query.OrderQuery;
import costumetrade.order.query.PayParam;
import costumetrade.order.service.SpOrderService;

@Transactional
@Service
public class SpOrderServiceImpl implements SpOrderService{
	@Autowired
	private SsStoDetailMapper ssStoDetailMapper;
	@Autowired
	private SsStoOrderMapper ssStoOrderMapper;
	@Autowired
	private SpCartMapper spCartMapper;
	@Autowired
	private SsFinancialMapper ssFinancialMapper;
	@Override
	public int saveOrders(OrderQuery query) {
		long orderNo=new Date().getTime();
		SsStoOrder order = query.getOrder();
		List<SsStoDetail> detail = new ArrayList<SsStoDetail>();
		SsStoDetail[] detailSto = query.getDetail();
		
		int[] cartIds = query.getCartId();
		List<Integer> list = new ArrayList<Integer>();
		
		int save = 0;
		BigDecimal count = new BigDecimal(0.0);
		BigDecimal cost =   new BigDecimal(0.0);
		
		//订单状态 1：新增  2、已付款 3、审核 4、发货 5、收货 6、已取消
		if(detailSto != null && detailSto.length>0){
			for(int i=0 ; i<detailSto.length-1 ; i++){
				SsStoDetail d = detailSto[i];
				d.setOrderid(orderNo+"");
				cost = cost.add((d.getCount()).multiply(d.getPrice()))  ;
				count = count.add(d.getCount());
				detail.add(d);
			}
			if(detail.size() > 0){
				OrderDetailParam param = new OrderDetailParam();
				param.setDetail(detail);
				ssStoDetailMapper.saveDetail(param);
			}else{
				return save;
			}
			order.setPayorderno(orderNo+"");
			order.setTotalamt(cost);
			order.setTotalnum(count);
			order.setOrderstatus(1);
			order.setOrdertime(new Date());
			order.setOrdertype(1+"");
			save = ssStoOrderMapper.insertSelective(order);
			
			//下单成功，则删除购物车
			if(cartIds !=null && cartIds.length>0){
				for(int j=0;j<cartIds.length-1;j++){
					list.add(cartIds[j]);
				}
				if(list.size() > 0 && save > 0){
					save = spCartMapper.deleteByIds(list);
				}
			}
		}
		return save;
	}
	@Override
	public OrderDetailQuery getOrder(OrderDetailKeyParam param) {
		OrderDetailQuery	query = new OrderDetailQuery();	
		query.setDetail(ssStoDetailMapper.selectByOrderId(param));
		query.setOrder(ssStoOrderMapper.selectByOrderId(param));
		return query;
	}
	@Override
	public int orderAudit(OrderDetailKeyParam param) {
		int operate = 0;
		SsStoOrder spStoOrder = ssStoOrderMapper.selectByOrderId(param);
		if(spStoOrder.getOrderstatus() == 2){ //订单状态  1：新增   2、已付款  3、审核  4、发货  5、收货  6、已取消
			spStoOrder = new SsStoOrder();
			spStoOrder.setSellerstoreid(param.getCorpId());
			spStoOrder.setPayorderno(param.getOrderId());
			spStoOrder.setOrderstatus(param.getOperate());
			operate = ssStoOrderMapper.updateByPrimaryKeySelective(spStoOrder);
			if( operate > 0){
				return 1;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
		
	}
	@Override
	public int orderCancel(OrderDetailKeyParam param) {
		// TODO Auto-generated method stub
		int operate = 0;
		SsStoOrder spStoOrder = ssStoOrderMapper.selectByOrderId(param);
		if(spStoOrder.getOrderstatus() == 1){ //订单状态  1：新增   2、已付款  3、审核  4、发货  5、收货  6、已取消
			spStoOrder = new SsStoOrder();
			spStoOrder.setSellerstoreid(param.getCorpId());
			spStoOrder.setPayorderno(param.getOrderId());
			spStoOrder.setOrderstatus(param.getOperate());
			operate = ssStoOrderMapper.updateByPrimaryKeySelective(spStoOrder);
			if(operate > 0){
				return 1;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	@Override
	public int orderPay(PayParam param) {
		SsFinancial ssFinancial = new SsFinancial();
		ssFinancial = param.getSsFinancial();
		
		SsStoOrder spStoOrder = new SsStoOrder();
		spStoOrder.setSellerstoreid(param.getCorpId());
		spStoOrder.setPayorderno(ssFinancial.getOrderno());
		spStoOrder.setOrderstatus(param.getOperate());
		int operate = ssStoOrderMapper.updateByPrimaryKeySelective(spStoOrder);
		
		if(operate <= 0){
			return 0;
		}
		if(ssFinancial.getPayType() == 1){ //1、线下支付  2、微信支付
			operate = ssFinancialMapper.insertSelective(ssFinancial);
		}	
		return operate;
	}
	
	

}
