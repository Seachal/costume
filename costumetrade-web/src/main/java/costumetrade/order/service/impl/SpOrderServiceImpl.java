package costumetrade.order.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sf.openapi.common.entity.MessageResp;
import com.sf.openapi.express.sample.order.dto.DeliverConsigneeInfoDto;
import com.sf.openapi.express.sample.route.dto.RouteReqDto;
import com.sf.openapi.express.sample.route.dto.RouteRespDto;

import costumetrade.common.page.Page;
import costumetrade.common.util.OrderNoGenerator;
import costumetrade.common.util.StringUtil;
import costumetrade.order.domain.ScLogistics;
import costumetrade.order.domain.ScStoreAddr;
import costumetrade.order.domain.Sender;
import costumetrade.order.domain.SpClient;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.domain.SsCgsorder;
import costumetrade.order.domain.SsFinancial;
import costumetrade.order.domain.SsPrice;
import costumetrade.order.domain.SsProductReview;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.domain.SsStock;
import costumetrade.order.domain.SsStockTransfer;
import costumetrade.order.domain.YDLogisticsRequest;
import costumetrade.order.mapper.ScLogisticsMapper;
import costumetrade.order.mapper.ScStoreAddrMapper;
import costumetrade.order.mapper.SpClientMapper;
import costumetrade.order.mapper.SpProductMapper;
import costumetrade.order.mapper.SsCgsorderMapper;
import costumetrade.order.mapper.SsFinancialMapper;
import costumetrade.order.mapper.SsPriceMapper;
import costumetrade.order.mapper.SsProductReviewMapper;
import costumetrade.order.mapper.SsStoDetailMapper;
import costumetrade.order.mapper.SsStoOrderMapper;
import costumetrade.order.mapper.SsStockMapper;
import costumetrade.order.mapper.SsStockTransferMapper;
import costumetrade.order.query.OrderCountQuery;
import costumetrade.order.query.OrderDetailQuery;
import costumetrade.order.query.OrderQuery;
import costumetrade.order.service.SFLogisticsService;
import costumetrade.order.service.SpOrderService;
import costumetrade.order.service.SpProductService;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpStore;
import costumetrade.user.domain.SpUser;
import costumetrade.user.domain.SsDataDictionary;
import costumetrade.user.mapper.ScWeChatMapper;
import costumetrade.user.mapper.SpStoreMapper;
import costumetrade.user.mapper.SpUserMapper;
import costumetrade.user.mapper.SsDataDictionaryMapper;
import costumetrade.user.query.ScUserQuery;

@Transactional
@Service
public class SpOrderServiceImpl implements SpOrderService{
	@Autowired
	private SsStoDetailMapper ssStoDetailMapper;
	@Autowired
	private SsStoOrderMapper ssStoOrderMapper;
	
	@Autowired
	private SsFinancialMapper ssFinancialMapper;
	@Autowired
	private SpClientMapper spClientMapper;
	@Autowired 
	private ScStoreAddrMapper scStoreAddrMapper;
	@Autowired 
	private SpStoreMapper spStoreMapper;
	@Autowired
	private ScLogisticsMapper scLogisticsMapper;
	@Autowired
	private SsStockMapper ssStockMapper;
	@Autowired
	private SsStockTransferMapper ssStockTransferMapper;
	@Autowired
	private SpProductMapper spProductMapper;
	@Autowired
	private SpProductService spProductService;
	@Autowired
	private SFLogisticsService sFLogisticsService;
	@Autowired 
	private SsProductReviewMapper ssProductReviewMapper;
	@Autowired
	private ScWeChatMapper scWeChatMapper;
	@Autowired
	private SpUserMapper spUserMapper;
	@Autowired
	private SsDataDictionaryMapper ssDataDictionaryMapper;
	@Autowired
	private SsCgsorderMapper ssCgsorderMapper;
	@Autowired
	private SsPriceMapper ssPriceMapper;

	@Override
	public Integer saveOrders(List<SsStoDetail> details,SsStoOrder order,String openid) {

		String orderNo = OrderNoGenerator.generate("");
		List<SsStoDetail> detail = new ArrayList<SsStoDetail>();
		int save = 0;
		BigDecimal count = new BigDecimal(0.0);
		BigDecimal cost =   new BigDecimal(0.0);
		OrderQuery param = new OrderQuery();
	
		ScWeChat wechat1 = scWeChatMapper.selectByOpenId(openid);
		//针对采购单 获取得到买家，根据销售单，确定卖家
		if(wechat1!=null){
			if(StringUtil.isNotBlank(wechat1.getStoreid())){ //普通会员
				if("1".equals(order.getOrdertype())){
					order.setBuyerstoreid(wechat1.getStoreid());
					order.setRealcost(order.getTotalamt());
					order.setDebetamt(order.getTotalamt());
				}
				if(wechat1.getStoreid().equals(order.getBuyerstoreid())){
					param.setOrderTypeTag(1);//采购单
				}
				if(wechat1.getStoreid().equals(order.getSellerstoreid())){
					param.setOrderTypeTag(2);//销售单
				}
			}else{
				if("1".equals(order.getOrdertype())){
					order.setBuyerstoreid(wechat1.getUserid());
					order.setDebetamt(order.getTotalamt());
				}
			}
		}else{
			return 0;
		}
		
		//根据clientId 设置卖家 买家  开单
		SpClient client = spClientMapper.selectByPrimaryKey(order.getClientId());
		if(client !=null){
			ScWeChat wechat = scWeChatMapper.selectByOpenId(client.getOpenid());
			if("1".equals(client.getType())){//开销售单
				if(StringUtil.isNotBlank(wechat.getStoreid())){
					param.setBuyerstoreid(wechat.getStoreid());
					order.setBuyerstoreid(wechat.getStoreid());
				}else if(StringUtil.isNotBlank(wechat.getUserid())){
					param.setBuyerstoreid(wechat.getUserid());
					order.setBuyerstoreid(wechat.getUserid());
				}
				param.setSellerstoreid(order.getSellerstoreid());
				
			}else if("2".equals(client.getType())){//开采购单
				if(StringUtil.isNotBlank(wechat.getStoreid())){
					param.setSellerstoreid(wechat.getStoreid());
					order.setSellerstoreid(wechat.getStoreid());
				}
				param.setBuyerstoreid(order.getBuyerstoreid());
				
			}else if("3".equals(client.getType())){//
				if(StringUtil.isNotBlank(order.getBuyerstoreid())){
					if(StringUtil.isNotBlank(wechat.getStoreid())){
						param.setSellerstoreid(wechat.getStoreid());
					}else{
						param.setSellerstoreid(wechat.getUserid());
					}	
				}else if(StringUtil.isNotBlank(order.getSellerstoreid())){
					if(StringUtil.isNotBlank(wechat.getStoreid())){
						param.setBuyerstoreid(wechat.getStoreid());
					}else{
						param.setBuyerstoreid(wechat.getUserid());
					}	
				}
			}
			
		}else{
			param.setBuyerstoreid(order.getBuyerstoreid());
			param.setSellerstoreid(order.getSellerstoreid());
		}
		if(param.getBuyerstoreid().equals(param.getSellerstoreid())){//自己不能开自家的订单
			return 3;
		}
		
		
		if(wechat1!=null){
			order.setOperator(wechat1.getId()+"");
		}else{
			return 0;
		}
		//订单状态 1：新增  2、已付款 3、审核 4、发货 5、收货 6、已取消
		if(details.size() > 0){
			for(SsStoDetail d : details){
				SsStoDetail d1 = d;
				d1.setOrderid(orderNo);
				cost = cost.add((d.getCount()).multiply(d.getPrice()))  ;
				count = count.add(d.getCount());
				d1.setCreateby(order.getOperator());
				d1.setCreatetime(new Date());
				if("1".equals(order.getOrdertype())){
					d1.setStoreid(order.getSellerstoreid());
				}else if("2".equals(order.getOrdertype())){
					d1.setStoreid(wechat1.getStoreid());
				}
				detail.add(d1);
			}
			order.setPayorderno(orderNo+"");
			order.setOrderstatus(1);
			order.setOrdertime(new Date());
		}
		if("2".equals(order.getOrdertype())){//线下单据，一旦提交，就是完成状态，库存会相应扣减   2、添加金额交易记录
			
			order.setOrderstatus(5);
			order.setOrdertype(2+"");
			
			//param.setClientId(clientId);
			param.setOpenid(openid);
			param.setOperate(5);
			order.setOrdertime(new Date());
			param.setIsContinue(order.getIsContinue());
			param.setOrderType(2);
			if(param.getOrderTypeTag()==1){
				param.setIsContinue(true);
			}
			boolean operatestock = orderStock(param,detail);
			if(!operatestock){
				return 2;
			}
			SsFinancial record = new SsFinancial();
			record.setOrderno(orderNo);
			record.setPay(order.getRealcost());
			record.setPaydate(new Date());
			record.setBuyerid(order.getBuyerstoreid());
			record.setSellerid(order.getSellerstoreid());
			record.setTradeno(OrderNoGenerator.generate(""));
			record.setPayType(order.getPaycate1());
			ssFinancialMapper.insertSelective(record );
			
			//计算积分
			
			
		}
		
		
		if(StringUtil.isNotBlank(order.getSellerstoreid())){
			ssStoDetailMapper.saveDetailStore(detail,order.getSellerstoreid());
		}else if(StringUtil.isNotBlank(order.getBuyerstoreid())){
			ssStoDetailMapper.saveDetailStore(detail,order.getBuyerstoreid());
		}
		save = ssStoOrderMapper.insertStore(order,order.getBuyerstoreid());
		return save;

	}
	
	public void calculatePoints(OrderQuery param){
		ScWeChat record = new ScWeChat();
		
		if(param.getOrderTypeTag()==1&&StringUtil.isNotBlank(param.getSellerstoreid())){//采购单
			record.setStoreid(param.getSellerstoreid());
		}
		if(param.getOrderTypeTag()==2&&StringUtil.isNotBlank(param.getBuyerstoreid())){//销售单
			
		}
		scWeChatMapper.selectWechat(record );
	}
	
	public void  setCustPrice(List<SsStoDetail> details,SsStoOrder order,String openid){
		/**
		 * 1/确定订单类型
		 * 2、针对采购单，计算平均成本
		 * 		计算规则，先取出某个商品对应的平均成本价，如果大于0，那就把最新订单 和 之前的 取平均，如果=0 则汇总以前的采购单再取平均成本
		 * 3、保存平均成本价
		 * */
		ScWeChat wechat = scWeChatMapper.selectByOpenId(openid);
		SsStoOrder spStoOrder = new SsStoOrder();
		if(wechat!=null){
			if(StringUtil.isNotBlank(wechat.getStoreid())){
				spStoOrder.setBuyerstoreid(wechat.getStoreid());
			}else{
				return ;
			}
		}
		List<String> idArray = new ArrayList<String>();
		for(SsStoDetail detail : details){
			if(StringUtil.isNotBlank(detail.getProductid())){
				idArray.add(detail.getProductid());
			}
		}
		List<String> idFirstArray = new ArrayList<String>();
		List<SsPrice> updates = new ArrayList<SsPrice>();
		//查询价格表中对应的成本价
		List<SsPrice> prices =  ssPriceMapper.selectPrices(wechat.getStoreid(), idArray);
		if(prices!=null && prices.size()>0){
			for(SsPrice price : prices){
				if(price.getCustPrice().compareTo(BigDecimal.ZERO) == 0 ||price.getCustPrice()==null){//表示第一次算成本单价
					idFirstArray.add(price.getProductid());
				}else{
					for(SsStoDetail detail : details){
						if(detail.getProductid().equals(price.getProductid())){
							price.setCustPrice(detail.getCount().multiply(detail.getPrice()).add(price.getCustPrice()).divide(detail.getCount().add(BigDecimal.ONE),2,RoundingMode.HALF_UP));
						}
					}
					updates.add(price);
				}
			}
		}
		
		List<SsStoDetail> ssStoDetails = new ArrayList<SsStoDetail>();
		if(idFirstArray.size()>0){
			spStoOrder.setIdArray(idArray);
			ssStoDetails = ssStoDetailMapper.avePriceByProducts(spStoOrder);
		}
		
		
		if(ssStoDetails.size()>0){
			for(SsPrice price : prices){
				for(SsStoDetail detail : ssStoDetails){
					if(detail.getProductid().equals(price.getProductid())){
						price.setCustPrice((detail.getAverage().add(price.getCustPrice())).divide((detail.getCount().add(BigDecimal.ONE)), 2, BigDecimal.ROUND_HALF_UP));
						updates.add(price);	
					}
				}
			}
		}
		
		if(updates.size()>0){
			ssPriceMapper.updates(updates);
		}
		
	}
	@Override
	public OrderDetailQuery getOrder(String orderNo ,Integer orderType, String openid) {
		OrderDetailQuery	query = new OrderDetailQuery();	
		//ScWeChat wechat = scWeChatMapper.selectByOpenId(openid);
		SsStoOrder spStoOrder = new SsStoOrder();
		spStoOrder.setPayorderno(orderNo);
		List<SsStoOrder> spStoOrders = new ArrayList<SsStoOrder>();
		List<SsStoDetail> ssStoDetails = new ArrayList<SsStoDetail>();
		SpStore store = new SpStore();
		SpUser user = new SpUser();
		SsStoOrder order = new SsStoOrder();
		
		spStoOrders = ssStoOrderMapper.selectByOrderStore(spStoOrder,null);
		ssStoDetails = ssStoDetailMapper.selectByOrderId(orderNo,null);
		
		if(orderType == 1){
			if(spStoOrders.size()>0){
				order = spStoOrders.get(0);
				user = spUserMapper.selectByPrimaryKey(order.getBuyerstoreid());
				if(user !=null && StringUtil.isNotBlank(user.getId())){
					order.setReceiverImage(user.getName());
					order.setReceiverImage(user.getPhoto());
				}else{
					store= spStoreMapper.selectByPrimaryKey(order.getBuyerstoreid());
					if(user !=null && StringUtil.isNotBlank(user.getId())){
						order.setReceiverImage(store.getName());
						order.setReceiverImage(store.getStorephoto());
					}
				}
				
			}
		}
		query.setSsStoDetail(ssStoDetails);
		query.setSsStoOrder(order);
		return query;
	}

	@Override
	public int orderOperate(OrderQuery param) {

		int operate = 0;
		SsStoOrder spStoOrder = new SsStoOrder();
		ScWeChat wechat = scWeChatMapper.selectByOpenId(param.getOpenid());
		
		spStoOrder.setSellerstoreid(param.getSellerstoreid());
		spStoOrder.setBuyerstoreid(param.getBuyerstoreid());
		spStoOrder.setPayorderno(param.getOrderNo());
		
		SsStoOrder order = new SsStoOrder();
		List<SsStoOrder> stos =  ssStoOrderMapper.selectByOrderStore(spStoOrder, null);
		if(stos!=null&&stos.size()>0){
			order = stos.get(0);
		}
		
		spStoOrder.setOrderstatus(param.getOperate());
		
		/**审核配货逻辑整理：
		1、确定有没有库存
		2、修改库存记录ss_stock  卖家
		3、新增对库存的交易记录   卖家
		 * 
		 * */
	
		if(param.getOperate() == 3){ //审核  配货
			spStoOrder.setOrderstatus(8);
			boolean operatestock =orderStock(param,null);
			if(!operatestock){
				return 2;
			}
		}else if(param.getOperate() == 8){
			spStoOrder.setOrderstatus(3);
		}
		/**
		 * 只对当日订单允许作废
		 * */
		if(param.getOperate() == 7){
			boolean o = orderCancellation(param);
			if(!o){
				return 3;
			}
		}
		//设置欠款金额
		if(param.getOperate() == 2 ){
			if(param.getPayable()!=null&&StringUtil.isNotBlank(order.getPayorderno())){
				if(param.getPayStatus()==1){
					spStoOrder.setPaycost1(param.getPayable());
					spStoOrder.setDebetamt(order.getTotalamt().subtract(param.getPayable()));
				}else if(param.getPayStatus()==2){
					spStoOrder.setPaycost1(BigDecimal.ZERO);
					spStoOrder.setDebetamt(param.getPayable());
				}
				if(order.getDebetamt().compareTo(BigDecimal.ZERO)==0){
					spStoOrder.setDebetamt(order.getTotalamt().subtract(param.getPayable()));
				}else{
					spStoOrder.setDebetamt(order.getDebetamt().subtract(param.getPayable()));
					spStoOrder.setPaycost1(order.getPaycost1().add(param.getPayable()));
				}
			}
			if(StringUtil.isBlank(order.getPaycate1())){
				spStoOrder.setPaycate1(param.getPaycate1());
			}
		}
		operate = ssStoOrderMapper.updateByPrimaryKeySelectiveStore(spStoOrder);
		if(operate > 0){
			return 1;
		}else{
			return 0;
		}
		
	}
	public Boolean orderStock(OrderQuery param,List<SsStoDetail> d){
		boolean operate = true;
		List<SsStoDetail> ssStoDetails = new ArrayList<SsStoDetail>();
		List<SsStock> ssStocks = new ArrayList<SsStock>();
		List<SsStock> ssStock = new ArrayList<SsStock>(); //对应店家查询的现有库存
		
		List<SsStockTransfer> transfers = new ArrayList<SsStockTransfer>();
		List<SsStockTransfer> ssStockTransferBuyyer = new ArrayList<SsStockTransfer>();//买家交易记录
		List<SsStockTransfer> ssStockTransferSeller = new ArrayList<SsStockTransfer>(); //卖家交易记录
		List<SpProduct> product = new ArrayList<SpProduct>(); //商品集合信息
		List<String> ids = new ArrayList<String>();//商品ID集合
		
		ScWeChat wechat = scWeChatMapper.selectByOpenId(param.getOpenid());
		
		boolean stockTag = true;
		if(d == null || d.size()==0){
			ssStoDetails = ssStoDetailMapper.selectByOrderId(param.getOrderNo(),param.getSellerstoreid());
		}else{
			ssStoDetails =d;
		}
		
		for(SsStoDetail detail : ssStoDetails){
			
			SsStock stock = new SsStock();
			stock.setProductid(detail.getProductid());
			stock.setProductcolor(detail.getProductcolor());
			stock.setProductsize(detail.getProductsize());
			stock.setStocknum(detail.getCount());
			stock.setProductPrice(detail.getPrice());
			stock.setStoreid(param.getSellerstoreid());
			stock.setCreateby(detail.getCreateby());
			
			List<String> otherStoreIds = new ArrayList<String>();
			otherStoreIds.add(param.getSellerstoreid());
			stock.setOtherStoreIds(otherStoreIds );
			
			ssStocks.add(stock);
			ids.add(detail.getProductid());
		
			List<SsStock> s = ssStockMapper.selectStock(stock);
			if(s == null || s.size()<=0 ){
				stockTag = false ; //库存不存在时，在库存记录中保存一条负库存记录
				stock.setCreatetime(new Date());
				stock.setCreateby(wechat.getId()+"");
				ssStock.add(stock);
			}else{
				ssStock.add(s.get(0));
			}
			SsStockTransfer transfer = new SsStockTransfer();
			transfer.setProductid(detail.getProductid());
			transfer.setProductcolor(detail.getProductcolor());
			transfer.setProductsize(detail.getProductsize());
			transfer.setCount(detail.getCount());
			transfer.setCreatedate(new Date());
			transfer.setTransfertoid(param.getSellerstoreid());
			transfer.setTransferfromid(param.getBuyerstoreid());
			transfer.setAmount(detail.getCount().multiply(detail.getPrice()));
			transfer.setCreateby(stock.getCreateby());
			transfers.add(transfer);
		}
		int updateSellerStock = 0;
		if(stockTag||param.getIsContinue()){//当库存缺少，并允许继续操作配货时，控制
			/**审核配货逻辑整理：
			1、确定有没有库存
			2、修改库存记录ss_stock  卖家
			3、新增对库存的交易记录   卖家
			 * 
			 * */
			for(SsStockTransfer transfer : transfers){
				transfer.setStockType(2);//调出
				ssStockTransferSeller.add(transfer);
			}
			if(param.getOperate() == 3||( param.getOperate() == 5 && param.getOrderType() == 2)){
				if(StringUtil.isNotBlank(param.getSellerstoreid())){
					ssStockTransferMapper.insert(ssStockTransferSeller,param.getSellerstoreid());//新增卖家交易记录
					for(int i=0 ; i< ssStocks.size(); i++){
						if(ssStocks.get(i).getProductid().equals(ssStock.get(i).getProductid())
								&& ssStocks.get(i).getProductcolor().equals(ssStock.get(i).getProductcolor())
								&& ssStocks.get(i).getProductsize().equals(ssStock.get(i).getProductsize())){
							SsStock stock = new SsStock();
							//库存不存在时，在库存记录中保存一条负库存记录
							stock = ssStock.get(i);
							if(stock.getId() == null){
								stock.setStocknum(new BigDecimal(0).subtract(ssStocks.get(i).getStocknum()));
								updateSellerStock = ssStockMapper.insertSelective(stock);
							}else{
								stock.setStoreid(param.getSellerstoreid());
								stock.setStocknum(stock.getStocknum().subtract(ssStocks.get(i).getStocknum()));
								updateSellerStock = ssStockMapper.updateByPrimaryKeySelective(stock); //更新卖家库存
							}
//							//更新卖家的商品销量
//							SpProduct p = spProductMapper.selectByPrimaryKey(ids.get(i), param.getSellerstoreid());
//							if(p!=null){
//								p.setSaleNum(p.getSaleNum().add(ssStocks.get(i).getStocknum()));
//								spProductMapper.updateByPrimaryKeySelective(p);
//							}
							
							
						}
					}
				}
				
			}
			/**审核配货逻辑整理：
			1、确定有没有库存
			2、修改库存记录ss_stock  买家
			3、新增对库存的交易记录   买家
			 * 
			 * */
			product = spProductMapper.selectById(ids, param.getSellerstoreid());
			if(StringUtil.isNotBlank(param.getBuyerstoreid())){
				if(wechat.getStoreid() != null && param.getOperate() == 5){//如果买家的角色是店家，那么当买家收货或者开采购单
					for(SsStockTransfer transfer : transfers){
						transfer.setStockType(1);//调ru
						ssStockTransferBuyyer.add(transfer);
					}
					ssStockTransferMapper.insert(ssStockTransferBuyyer,param.getBuyerstoreid());//  新增买家交易记录
					for(int i=0 ; i< ssStocks.size(); i++){
						SsStock stock = new SsStock();
						stock = ssStocks.get(i);
						stock.setStoreid(param.getBuyerstoreid());
						
						List<String> otherStoreIds = new ArrayList<String>();
						otherStoreIds.add(param.getBuyerstoreid());
						stock.setOtherStoreIds(otherStoreIds );
						
						List<SsStock> stocks = ssStockMapper.selectStock(stock);//买家库存，存在更新，不存在新增库存
						SsStock stockBuyyer = new SsStock();
						if(stocks != null && stocks.size()>0 ){
							stockBuyyer = stocks.get(0);
							stock.setId(stockBuyyer.getId());
						}
						if(StringUtil.isBlank(stockBuyyer.getStoreid()) ){
							for(SpProduct p : product){
								SpProduct pro = spProductMapper.selectByPrimaryKey(p.getId(), param.getBuyerstoreid());
								if(pro!=null){
									continue;
								}
								if(p.getId().equals(ssStocks.get(i).getProductid())){
									p.setPurchaseprice(ssStoDetails.get(i).getPrice());
									spProductService.insertSuspendingProduct(p, param.getBuyerstoreid());
								}
							}
							ssStockMapper.insertSelective(stock);
						}else{
							if(stockBuyyer.getStocknum() ==null){
								stockBuyyer.setStocknum(new BigDecimal(0));
							}
							stock.setStocknum(stockBuyyer.getStocknum().add(ssStocks.get(i).getStocknum()));
							updateSellerStock = ssStockMapper.updateByPrimaryKeySelective(stock); //更新买家库存
						}
					}
				}
				if(param.getOperate() == 5){
					if(wechat.getStoreid().equals(param.getBuyerstoreid())){
						//采购单计算平均成本价
						setCustPrice(ssStoDetails, null, param.getOpenid());
					}
				}
			}
		
			
		
		}else{
			operate =false;
		}
		return operate;
	}
	
	
	public Boolean orderCancellation(OrderQuery param){
		/**
		 * 判断用户身份  店家/普通消费者
		 * 普通消费者   修改卖家库存  加
		 * 店家   修改买方库存 减  修改卖方库存  加
		 * 
		 * 只允许当天的订单允许作废
		 * */
		
		
		List<SsStoDetail> ssStoDetails = ssStoDetailMapper.selectByOrderId(param.getOrderNo(),param.getSellerstoreid());
		
		List<SsStock> ssStockSellers = new ArrayList<SsStock>();
		List<SsStock> ssStockBuyers = new ArrayList<SsStock>();
		
		boolean operate =true;
		Boolean isStore =false;
		ScWeChat wechat = scWeChatMapper.selectByOpenId(param.getOpenid());
		if(wechat != null){
			if(wechat.getStoreid() != null){
				isStore =true;
			}
		}
		if(ssStoDetails!=null && ssStoDetails.size()>0){
			for(SsStoDetail detail : ssStoDetails){
				SsStock stock = new SsStock();
				stock.setProductid(detail.getProductid());
				stock.setProductcolor(detail.getProductcolor());
				stock.setProductsize(detail.getProductsize());
				stock.setStoreid(param.getSellerstoreid());
				
				List<SsStock> ssStockSeller = new ArrayList<SsStock>();
				if(StringUtil.isNotBlank(param.getSellerstoreid())){
					List<String> otherStoreIds = new ArrayList<String>();
					otherStoreIds.add(param.getSellerstoreid());
					stock.setOtherStoreIds(otherStoreIds );
					ssStockSeller = ssStockMapper.selectStock(stock);
				}
				
				List<SsStock> ssStockBuyer = null;
				if(isStore&&StringUtil.isNotBlank(param.getBuyerstoreid())){//修改卖家库存  加  修改买方库存 减  修改卖方库存  加
					stock.setStoreid(param.getBuyerstoreid());
					List<String> otherStoreId = new ArrayList<String>();
					otherStoreId.add(param.getBuyerstoreid());
					stock.setOtherStoreIds(otherStoreId );
					
					ssStockBuyer = ssStockMapper.selectStock(stock);
				}
				if(ssStockSeller!=null&& ssStockSeller.size()>0){
					stock.setStocknum(ssStockSeller.get(0).getStocknum().add(detail.getCount()));
					stock.setModifytime(new Date());
					ssStockSellers.add(stock);
				}
				if(ssStockBuyer!=null&& ssStockBuyer.size()>0){
					stock.setStocknum(ssStockSeller.get(0).getStocknum().subtract(detail.getCount()));
					stock.setModifytime(new Date());
					ssStockBuyers.add(stock);
				}
			

			}
		}
		int update =0 ;
		if(ssStockSellers!=null&& ssStockSellers.size()>0){
			update = ssStockMapper.batchUpdate(ssStockSellers);
		}
		if(ssStockBuyers!=null&& ssStockBuyers.size()>0){
			update = ssStockMapper.batchUpdate(ssStockBuyers);
		}
		if(update >0){
			operate =true;
		}else{
			operate =false;
		}
		return operate;
	}
	
	
	@Override
	public int orderPay(SsFinancial ssFinancial) {
		int operate = 0 ;
		ssFinancial.setTradeno(OrderNoGenerator.generate(""));
		ssFinancial.setPaydate(new Date());
		
		SsStoOrder spStoOrder = new SsStoOrder();
		spStoOrder.setSellerstoreid(ssFinancial.getSellerid());
		spStoOrder.setPayorderno(ssFinancial.getOrderno());
		spStoOrder.setBuyerstoreid(ssFinancial.getBuyerid());
		spStoOrder.setOrderstatus(ssFinancial.getOperate());
		spStoOrder.setPaycate1(ssFinancial.getPayType());
		spStoOrder.setPaycost1(ssFinancial.getPay());
		spStoOrder.setRealcost(ssFinancial.getPay());
		
		ScWeChat wechat = scWeChatMapper.selectByOpenId(ssFinancial.getOpenid());
		
		if(wechat.getStoreid() == null){
			operate = ssStoOrderMapper.updateByPrimaryKeySelective(spStoOrder);
		}else{
			operate = ssStoOrderMapper.updateByPrimaryKeySelectiveStore(spStoOrder);
		}
		if(operate <= 0){
			return 0;
		}
		operate = ssFinancialMapper.insertSelective(ssFinancial);
		return operate;
	}
	@Override
	public ScStoreAddr orderInit(String openid) {
		/*
		 * 调转下单界面，自动初始化收货地址
		 * 如果是会员则获取client中的地址，
		 * 如果是店家则获取store中的收货地址
		 * 返回地址信息ScStoreAddr
		 * */
		ScStoreAddr addr = new ScStoreAddr();
		addr.setIsdefault(1);
		ScWeChat wechat = scWeChatMapper.selectByOpenId(openid);
		
		if(wechat != null){
			if(StringUtil.isNotBlank(wechat.getStoreid())){
				addr.setUserid(wechat.getStoreid()); 
			}else{
				addr.setUserid(wechat.getUserid());
			}
		}else {
			return null;
		}
		List<ScStoreAddr >addrs = scStoreAddrMapper.selectAddr(addr);
		if(addrs!=null && addrs.size()>0){
			addr = addrs.get(0);
		}else {
			return null;
		}
		return addr;
	}
	@Override
	public SsStoOrder order(String orderNo, String storeId) {
		return ssStoOrderMapper.selectByOrderNo(orderNo,storeId);
	}
	@Override
	public List<SsStoOrder> getOrders(Integer orderType, Integer orderStatus,
			String openid,Integer pageNum,String payOrderNo) {
		
		ScWeChat wechat = scWeChatMapper.selectByOpenId(openid);
		SsStoOrder spStoOrder = new SsStoOrder();
		List<SsStoOrder> spStoOrders = new ArrayList<SsStoOrder>();
		/*1、待付款  2、待发货   3、待收货  4、待审核 5、全部6、待配货
		orderStatus 值 备注
		待付款                          1，3                 新增，审核通过
		待发货                          8 ,2                 配货
		待收货                          4                    已发货
		全部                         所有状态                线上单据+线下单据
		
		
		销售单列签：
		orderStatus 值 备注
		待审核                          1                    新增
		待配货                          2 ,3  待付款，已付款
		待发货                          8                    已配货
		全部                         所有状态              线上单据+线下单据
		 * 先审核 后付款
		 * */
		List<Integer> status = new ArrayList<Integer>();
		if(orderType == 1){  //采购单列签
			if(orderStatus == 2){
				status.add(8);
				status.add(2);
				spStoOrder.setOrdertype(1+"");//线上订单
			}else if(orderStatus == 3){
				status.add(4);
				spStoOrder.setOrdertype(1+"");//线上订单
			}else if(orderStatus == 1){
				status.add(1);
				status.add(3);
				spStoOrder.setOrdertype(1+"");//线上订单
			}
		}else if(orderType == 2){ //销售单列签
			if(orderStatus == 4){
				status.add(1);
				spStoOrder.setOrdertype(1+"");//线上订单
			}else if(orderStatus == 2){
				status.add(8);
				spStoOrder.setOrdertype(1+"");//线上订单
			}else if(orderStatus == 6){
				status.add(2);
				status.add(3);
				spStoOrder.setOrdertype(1+"");//线上订单
			}
		}else if(orderType == 3||orderType == 4){//总报表查询采购单
			status.add(5);
		}
		if(orderStatus!=null&&orderStatus == 5){
			status.add(5);
			status.add(7);
		}
		spStoOrder.setStatus(status);
		
		Page page = new Page();
		page.setPageNum(pageNum);
		
		int count = 0;
		if(orderType == 1|| orderType == 3){  //采购单列签
			if(wechat.getStoreid() == null){
				spStoOrder.setBuyerstoreid(wechat.getUserid());
				//count = ssStoOrderMapper.selectByOrderMemberCount(spStoOrder);
			}else{
				spStoOrder.setStoreId(wechat.getStoreid());
				spStoOrder.setBuyerstoreid(wechat.getStoreid());
				//count = ssStoOrderMapper.selectByOrderStoreCount(spStoOrder);
			}
			
		}else if(orderType == 2 || orderType == 4){//销售单列签
			if(wechat.getStoreid() != null){
				spStoOrder.setStoreId(wechat.getStoreid());
				spStoOrder.setSellerstoreid(wechat.getStoreid());
				//spStoOrders = ssStoOrderMapper.selectByOrderStore(spStoOrder,page);
				//count = ssStoOrderMapper.selectByOrderStoreCount(spStoOrder);
			}
		}
		spStoOrder.setPayorderno(payOrderNo);
		spStoOrders = ssStoOrderMapper.selectByOrderStore(spStoOrder,page);
		OrderQuery query = new OrderQuery();
		List<SsStoOrder> spStoOrders1 = new ArrayList<SsStoOrder>();
		if(orderStatus!=null&&orderStatus == 5&&spStoOrders.size()>0){
			for(SsStoOrder o : spStoOrders){
				Boolean b = canCancellation(o);
				o.setCanCancellation(b);
				spStoOrders1.add(o);
			}
			query.setSsStoOrder(spStoOrders1);
		}else{
			query.setSsStoOrder(spStoOrders);
		}
		return spStoOrders;
	}
	
	public Boolean canCancellation(SsStoOrder order){
		
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
	    SimpleDateFormat formater2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    boolean canCancellation =false;
	    Date currentDayEnd = null;
	    try {
	    	currentDayEnd = formater2.parse(formater.format(new Date())+ " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    //只允许当天的订单 允许作废
	    if(order.getOrdertime().getTime()>currentDayEnd.getTime()){
	    	canCancellation =true;
	    }
	    return canCancellation;
	}
	@Override
	public int confirmLogistic(ScLogistics scLogistics) {
		scLogistics.setCreatetime(new Date());
		ScLogistics Logistics =scLogisticsMapper.selectByLogistic(scLogistics);
		int save = 0;
		if(Logistics !=null){
			scLogistics.setId(Logistics.getId());
			save =scLogisticsMapper.updateByPrimaryKeySelective(scLogistics);
		}else {
			save =scLogisticsMapper.insertSelective(scLogistics);
		}
		return save ;
	}
	@Override
	public Object queryLogistic(SsStoOrder ssStoOrder) {
		Object obj = new MessageResp<List<RouteRespDto>>();
		ScLogistics Logistics = new ScLogistics();
		Logistics.setOrderno(ssStoOrder.getPayorderno());
		Logistics = scLogisticsMapper.selectByLogistic(Logistics);
		if(Logistics != null && "SF".equals(Logistics.getLogisticsname())){
			RouteReqDto routeReqDto = new RouteReqDto();
			routeReqDto.setMethodType(1);
			routeReqDto.setTrackingNumber(Logistics.getLogisticsno());
			routeReqDto.setTrackingType(1);
			obj = sFLogisticsService.queryRouteSF(routeReqDto);
		}else if(Logistics != null && "YD".equals(Logistics.getLogisticsCode())){
			YDLogisticsRequest request = new YDLogisticsRequest();
			request.setOrderId(ssStoOrder.getPayorderno());
			request.setMailno(Logistics.getLogisticsno());
			obj =sFLogisticsService.queryOrderYD(request );
		}else if(Logistics != null && "ZTO".equals(Logistics.getLogisticsCode())){
			obj =sFLogisticsService.queryZTO(Logistics.getLogisticsno());
		}
		return obj;
	}
	@Override
	public int saveReview(SsProductReview ssProductReview) {
		ssProductReview.setCreatetime(new Date());
		return ssProductReviewMapper.insertSelective(ssProductReview);
	}
	@Override
	public OrderCountQuery countOrders(String openid) {
		ScWeChat wechat = scWeChatMapper.selectByOpenId(openid);
		List<SsStoOrder> orders = new ArrayList<SsStoOrder>();
		boolean isStore = false;
		if(wechat!=null){
			if(StringUtil.isNotBlank(wechat.getStoreid())){//店员身份
				orders = ssStoOrderMapper.selectByOrderStoreCount(wechat.getStoreid());
				isStore = true;
			}else if(StringUtil.isNotBlank(wechat.getUserid())){//普通消费者
				orders = ssStoOrderMapper.selectByOrderStoreCount(wechat.getUserid());
			}
		}
		
		Integer purchaseCount=0;//采购单总数
	    Integer pNoPayCount=0;//采购单未付款数量
	    Integer pNoShipCount=0 ;//采购单未发货数量
	    Integer pNoReceiptCount=0 ;//采购单未收货数量
	    
	    Integer saleCount=0 ;//销售单总数
	    Integer sNoPayCount=0 ;//销售单未收款数量
	    Integer sNoShipCount=0 ;//销售单未发货数量
	    Integer sNoAuditCount=0 ;//销售单未审核配货数量
	    
	    Integer ordersCount=0;//订单总数量
		/**
		 * 		采购单列签：1
				orderStatus 值                    备注
				待付款                          1，3                 新增，审核通过
				待发货                          8 ,2                  配货,f付款
				待收货                          4                    已发货
				全部                         所有状态                线上单据+线下单据
				
				
				销售单列签：
				orderStatus 值 备注
				待审核                          1                    新增
				待配货                          2,3                  付款,审核
				待发货                          8                    已配货
				全部                         所有状态              线上单据+线下单据
		 * 
		 * */
		if(orders.size()>0){
			for(SsStoOrder order : orders){
				//采购汇总
				if((isStore&&wechat.getStoreid().equals(order.getBuyerstoreid()))
						||(!isStore&&order.getBuyerstoreid().equals(wechat.getUserid()))){
					//purchaseCount += order.getCount();
					if(1==order.getOrderstatus()||3==order.getOrderstatus()){
						pNoPayCount += order.getCount();
					}else if(8==order.getOrderstatus()||2==order.getOrderstatus()){
						pNoShipCount += order.getCount();
					}else if(4==order.getOrderstatus()){
						pNoReceiptCount += order.getCount();
					}
				}
				//销售汇总
				if(isStore&&wechat.getStoreid().equals(order.getSellerstoreid())){
					//saleCount += order.getCount();
					if(1==order.getOrderstatus()){
						sNoPayCount += order.getCount();
					}else if(2==order.getOrderstatus()||3==order.getOrderstatus()){
						sNoAuditCount += order.getCount();
					}else if(8==order.getOrderstatus()){
						sNoShipCount += order.getCount();
					}
				}
				ordersCount += 1;
			}
		}
		//ordersCount = saleCount+purchaseCount;
		OrderCountQuery query = new OrderCountQuery();
		query.setOrdersCount(ordersCount);
		query.setpNoPayCount(pNoPayCount);
		query.setpNoReceiptCount(pNoReceiptCount);
		query.setpNoShipCount(pNoShipCount);
		query.setsNoAuditCount(sNoAuditCount);
		query.setsNoPayCount(sNoPayCount);
		query.setsNoShipCount(sNoShipCount);
		return query;
	}
	@Override
	public List<SsDataDictionary> orderFeeInit(String openid) {
		List<SsDataDictionary> dicts = new ArrayList<SsDataDictionary>();
		List<SsDataDictionary> dictList = new ArrayList<SsDataDictionary>();
		SsDataDictionary dict = new SsDataDictionary();
		ScWeChat wechat = scWeChatMapper.selectByOpenId(openid);
		if(wechat!=null){
			dict.setStoreId(wechat.getStoreid());
		}else{
			dict.setStoreId(-1+"");
		}
		
		dict.setDictGroup("FEE_TYPE");
		dicts = ssDataDictionaryMapper.select(dict);
		if(dicts.size()>0){
			for(SsDataDictionary d:dicts){
				SsDataDictionary dictionary = new SsDataDictionary();
				dictionary.setDictValue(d.getDictValue());
				dictionary.setId(d.getSortNum());
				dictList.add(d);
			}
		}
		return dictList;
	}
	@Override
	public int saveOrderFee(List<SsCgsorder> orders) {
		return ssCgsorderMapper.saveFeeOrders(orders);
	}
	@Override
	public int updateOrder(OrderQuery param) {
		int update = 0;
		//更新订单数据 1、根据订单号在普通消费者的订单表中查询记录，存在就更新普通消费者的订单信息，否则就在买家（店家）的订单表中更新数据
		SsStoOrder spStoOrder = new SsStoOrder();
		spStoOrder.setPayorderno(param.getOrder().getPayorderno());
		if(StringUtils.isNotBlank(param.getOrder().getPayorderno())){
			spStoOrder = param.getOrder();
			if(StringUtils.isBlank(spStoOrder.getPaycate1())&&StringUtils.isBlank(spStoOrder.getPaycate2())){
				spStoOrder.setOrderstatus(3);//修改运费提交，直接审核通过
			}
			update =ssStoOrderMapper.updateByPrimaryKeySelectiveStore(spStoOrder);
		}
		if(param.getStoDetails()!=null&&param.getStoDetails().size()>0){
			update =ssStoDetailMapper.updateByPrimaryKeySelectiveStore(param.getStoDetails());
		}
		return update;
	}
	@Override
	public List<Object> logisticInit(SsStoOrder ssStoOrder) {
		List<Object> objects = new ArrayList<Object>();
		
		List<SsStoOrder> ssStoOrders = ssStoOrderMapper.selectByOrderStore(ssStoOrder,null);
		SsStoOrder spStoOrder = new SsStoOrder();
		if(ssStoOrders!=null && ssStoOrders.size()>0){
			
			spStoOrder = ssStoOrders.get(0);
			ScStoreAddr record = new ScStoreAddr();
			if(StringUtil.isNotBlank(spStoOrder.getShipaddress())){
				record.setUserid(spStoOrder.getBuyerstoreid());
				record.setAddress(spStoOrder.getShipaddress());
				List<ScStoreAddr> records=  scStoreAddrMapper.selectAddr(record);
				if(records!=null&&records.size()>0){//收货人
					record = records.get(0);
					Sender receiver = new Sender();
					receiver.setAddress(record.getAddress());
					receiver.setCity(record.getProvince()+","+record.getCity()+","+record.getCity());
					receiver.setMobile(record.getPhone());
					receiver.setName(record.getContact());
					receiver.setPhone(record.getPhone());
					objects.add(receiver);
				}
			}
			if(StringUtil.isNotBlank(spStoOrder.getLogisticsCode())){
				record = new ScStoreAddr();
				record.setUserid(spStoOrder.getSellerstoreid());
				record.setIsdefault(1);
				List<ScStoreAddr> records=  scStoreAddrMapper.selectAddr(record);
				if(records!=null&&records.size()>0){//发货人
					record = records.get(0);
					Sender sender = new Sender();
					sender.setAddress(record.getAddress());
					sender.setCity(record.getProvince()+","+record.getCity()+","+record.getCity());
					sender.setMobile(record.getPhone());
					sender.setName(record.getContact());
					sender.setPhone(record.getPhone());
					objects.add(sender);
				}
			}
		}
		objects.add(spStoOrder);
		return objects;
	}

	@Override
	public List<SsDataDictionary> enterPay(String storeId) {
		List<String> list = new ArrayList<String>();
		list.add("PAY_QRCODE");//
		List<SsDataDictionary> dict = ssDataDictionaryMapper.selectDictionarys(list,storeId);
		
		SpStore store = spStoreMapper.selectByPrimaryKey(storeId);
		if(dict!=null&&dict.size()>0){
			SsDataDictionary dic = new SsDataDictionary();
			if(store!=null){
				dic.setStoreId(storeId);
				dic.setDictText("银行卡信息");
				dic.setDictValue(store.getDescription());
			}
			dict.add(dic);
		}
		return dict;
	}
	
	

}
