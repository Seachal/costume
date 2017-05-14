package costumetrade.pay.mapper;

import costumetrade.pay.domain.TradeInfo;

public interface TradeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TradeInfo record);

    int insertSelective(TradeInfo record);

    TradeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeInfo record);

    int updateByPrimaryKey(TradeInfo record);
    
    int updateByTradeOutNoSelective(TradeInfo record);
}