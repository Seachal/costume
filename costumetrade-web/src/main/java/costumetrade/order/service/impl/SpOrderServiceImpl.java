package costumetrade.order.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.common.util.OrderNoGenerator;
import costumetrade.order.domain.ScStoreAddr;
import costumetrade.order.domain.SpClient;
import costumetrade.order.domain.SsFinancial;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.mapper.ScStoreAddrMapper;
import costumetrade.order.mapper.SpCartMapper;
import costumetrade.order.mapper.SpClientMapper;
import costumetrade.order.mapper.SsFinancialMapper;
import costumetrade.order.mapper.SsStoDetailMapper;
import costumetrade.order.mapper.SsStoOrderMapper;
import costumetrade.order.query.OrderDetailKeyParam;
import costumetrade.order.query.OrderDetailQuery;

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
	@Autowired
	private SpClientMapper spClientMapper;
	@Autowired 
	private ScStoreAddrMapper scStoreAddrMapper;
	
	@Override
	public int saveOrders(List<SsStoDetail> details,SsStoOrder order ,Integer memberTag) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		String orderNo = OrderNoGenerator.generate(date);
		List<SsStoDetail> detail = new ArrayList<SsStoDetail>();
		List<Integer> list = new ArrayList<Integer>();
		int save = 0;
		BigDecimal count = new BigDecimal(0.0);
		BigDecimal cost =   new BigDecimal(0.0);
		
		//订单状态 1：新增  2、已付款 3、审核 4、发货 5、收货 6、已取消
		if(details.size() > 0){
			for(SsStoDetail d : details){
				SsStoDetail d1 = d;
				d1.setOrderid(orderNo);
				cost = cost.add((d.getCount()).multiply(d.getPrice()))  ;
				count = count.add(d.getCount());
				detail.add(d);
			}
			order.setPayorderno(orderNo+"");
			order.setTotalamt(cost);
			order.setTotalnum(count);
			order.setRealcost(cost);
			order.setOrderstatus(1);
			order.setOrdertime(new Date());
			order.setOrdertype(1+"");
		}
		
		if(memberTag == 1){ //普通会员
			ssStoDetailMapper.saveDetail(detail,order.getSellerstoreid());
			save = ssStoOrderMapper.insert(order,order.getSellerstoreid());
		}
		if(memberTag == 2){ //店家
			ssStoDetailMapper.saveDetailStore(detail,order.getBuyerstoreid(),order.getSellerstoreid());
			save = ssStoOrderMapper.insertStore(order,order.getBuyerstoreid(),order.getSellerstoreid());
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
	@Override
	public ScStoreAddr orderInit(Integer clientId) {
		/*
		 * 调转下单界面，自动初始化收货地址
		 * 如果是会员则获取client中的地址，
		 * 如果是店家则获取store中的收货地址
		 * 返回地址信息ScStoreAddr
		 * */
		ScStoreAddr addr = new ScStoreAddr();
		SpClient client = spClientMapper.selectByPrimaryKey(clientId);
		if(client != null){
			if(client.getStoreid() == null){
				addr.setAddress(client.getAddress());
				addr.setContact(client.getContact());
				addr.setPhone(client.getPhone());
			}else{
				addr = scStoreAddrMapper.selectAddr(client.getStoreid());
			}
		}else{
			return addr;
		}
		return addr;
	}
	
	

}
