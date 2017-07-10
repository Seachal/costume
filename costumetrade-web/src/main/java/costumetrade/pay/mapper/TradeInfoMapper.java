package costumetrade.pay.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.pay.domain.TradeInfo;
@Mapper
public interface TradeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TradeInfo record);

    int insertSelective(TradeInfo record);

    TradeInfo selectByPrimaryKey(Integer id);
    
    TradeInfo selectOutTradeNo(String outTradeNo);
    
    int updateByPrimaryKeySelective(TradeInfo record);

    int updateByPrimaryKey(TradeInfo record);
    
    int updateByTradeOutNoSelective(TradeInfo record);
}