package costumetrade.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpStoDetail;
import costumetrade.order.domain.SpStoOrder;
import costumetrade.order.mapper.SpCartMapper;
import costumetrade.order.mapper.SpStoDetailMapper;
import costumetrade.order.mapper.SpStoOrderMapper;
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
	private SpStoDetailMapper spStoDetailMapper;
	@Autowired
	private SpStoOrderMapper spStoOrderMapper;
	@Autowired
	private SpCartMapper spCartMapper;
	@Override
	public int saveOrders(OrderQuery query) {
		long orderNo=new Date().getTime();
		SpStoOrder order = query.getOrder();
		List<SpStoDetail> detail = new ArrayList<SpStoDetail>();
		SpStoDetail[] detailSto = query.getDetail();
		
		int[] cartIds = query.getCartId();
		List<Integer> list = new ArrayList<Integer>();
		
		int save = 0;
		BigDecimal count = new BigDecimal(0.0);
		BigDecimal cost =   new BigDecimal(0.0);
		
		//订单状态 1：新增  2、已付款 3、审核 4、发货 5、收货 6、已取消
		if(detailSto != null && detailSto.length>0){
			for(int i=0 ; i<detailSto.length-1 ; i++){
				SpStoDetail d = detailSto[i];
				d.setOrderid(orderNo+"");
				d.setStatus(1);
				d.setSubid(order.getCorpid()+"");
				d.setTotalamt((d.getTotalnum()).multiply(d.getPrice()));
				cost = cost.add((d.getTotalnum()).multiply(d.getPrice()))  ;
				count = count.add(d.getTotalnum());
				detail.add(d);
			}
			if(detail.size() > 0){
				OrderDetailParam param = new OrderDetailParam();
				param.setCorpId(order.getCorpid());
				param.setDetail(detail);
				spStoDetailMapper.saveDetail(param);
			}else{
				return save;
			}
			order.setPayorderno(orderNo+"");
			order.setTotalamt(cost);
			order.setTotalnum(count);
			order.setOrderstatus(1);
			order.setOrderTime(new Date());
			order.setOrdertype(1+"");
			save = spStoOrderMapper.insertSelective(order);
			
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
		query.setDetail(spStoDetailMapper.selectByOrderId(param));
		query.setOrder(spStoOrderMapper.selectByOrderId(param));
		return query;
	}
	@Override
	public int orderAudit(OrderDetailKeyParam param) {
		int operate = 0;
		SpStoOrder spStoOrder = spStoOrderMapper.selectByOrderId(param);
		if(spStoOrder.getOrderstatus() == 2){ //订单状态  1：新增   2、已付款  3、审核  4、发货  5、收货  6、已取消
			spStoOrder = new SpStoOrder();
			spStoOrder.setCorpid(param.getCorpId());
			spStoOrder.setPayorderno(param.getOrderId());
			spStoOrder.setOrderstatus(param.getOperate());
			int update = spStoDetailMapper.updateStatus(param);
			operate = spStoOrderMapper.updateByPrimaryKeySelective(spStoOrder);
			if(update > 0 && operate > 0){
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
		SpStoOrder spStoOrder = spStoOrderMapper.selectByOrderId(param);
		if(spStoOrder.getOrderstatus() == 1 || spStoOrder.getOrderstatus() == 2){ //订单状态  1：新增   2、已付款  3、审核  4、发货  5、收货  6、已取消
			spStoOrder = new SpStoOrder();
			spStoOrder.setCorpid(param.getCorpId());
			spStoOrder.setPayorderno(param.getOrderId());
			spStoOrder.setOrderstatus(param.getOperate());
			int update = spStoDetailMapper.updateStatus(param);
			operate = spStoOrderMapper.updateByPrimaryKeySelective(spStoOrder);
			if(update > 0 && operate > 0){
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
		// TODO Auto-generated method stub
		OrderDetailKeyParam param1 = new OrderDetailKeyParam();
		param1.setCorpId(param.getCorpId());
		param1.setOperate(param.getOperate());
		param1.setOrderId(param.getOrderNo());
		int update = spStoDetailMapper.updateStatus(param1);
		
		SpStoOrder spStoOrder = new SpStoOrder();
		spStoOrder.setCorpid(param.getCorpId());
		spStoOrder.setPayorderno(param.getOrderId());
		spStoOrder.setOrderstatus(param.getOperate());
		int operate = spStoOrderMapper.updateByPrimaryKeySelective(spStoOrder);
		if(update <= 0 || operate <= 0){
			return 0;
		}else{
			return spCartMapper.insert(param);
			
		}
		
	}
	
	

}
