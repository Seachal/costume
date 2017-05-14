package costumetrade.pay.service;

import costumetrade.pay.domain.TradeInfo;

public interface ITradeInfoService {

    int deleteByPrimaryKey(Integer id);

    int insert(TradeInfo record);

    int insertSelective(TradeInfo record);

    TradeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeInfo record);

    int updateByPrimaryKey(TradeInfo record);
    void updateTradeWithFinancial(String tradeOutNo,String   openid,String transactionId);
}
