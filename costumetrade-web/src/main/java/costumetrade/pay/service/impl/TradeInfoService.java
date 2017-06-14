package costumetrade.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.common.enums.PayTypeEnum;
import costumetrade.order.domain.SsFinancial;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.mapper.SsFinancialMapper;
import costumetrade.order.mapper.SsStoOrderMapper;
import costumetrade.pay.domain.TradeInfo;
import costumetrade.pay.mapper.TradeInfoMapper;
import costumetrade.pay.service.ITradeInfoService;
@Service
@Transactional
public class TradeInfoService implements ITradeInfoService {
	
     @Autowired
	private TradeInfoMapper  tradeInfoMapper;
     @Autowired
	private SsFinancialMapper  ssFinancialMapper;
     @Autowired
	private SsStoOrderMapper  ssStoOrderMapper;
     
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return tradeInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TradeInfo record) {
		return tradeInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(TradeInfo record) {
		return tradeInfoMapper.insertSelective(record);
	}

	@Override
	public TradeInfo selectByPrimaryKey(Integer id) {
		return tradeInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TradeInfo record) {
		return tradeInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TradeInfo record) {
		return tradeInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public void updateTradeWithFinancial(String tradeOutNo, String openid,
			String transactionId) {
		
           //test
		   Integer  storeId = 1;
		   SsStoOrder order =  ssStoOrderMapper.selectByTradeNo(tradeOutNo, storeId);
		   SsFinancial ssFinancial = new SsFinancial();
		   ssFinancial.setTradeno(tradeOutNo);
		   ssFinancial.setRelaNo(transactionId);
		   ssFinancial.setIncome(order.getTotalamt());
		   ssFinancial.setPayType(PayTypeEnum.PAY_WEIXIN.getKey());
		   ssFinancial.setBuyerid(order.getBuyerstoreid());
		   ssFinancial.setSellerid(order.getSellerstoreid());
		   ssFinancial.setPay(order.getRealcost());
		  
		   ssFinancialMapper.insert(ssFinancial);

		   TradeInfo tradeInfo = new TradeInfo();
		   tradeInfo.setStatus(2);
		   tradeInfo.setTradeno(tradeOutNo);
		   
		   tradeInfoMapper.updateByTradeOutNoSelective(tradeInfo);
		   
		
	}

}
