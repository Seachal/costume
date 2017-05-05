package costumetrade.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SsFinancial;
import costumetrade.order.query.PayParam;
@Mapper
public interface SsFinancialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsFinancial record);

    int insertSelective(SsFinancial record);

    SsFinancial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsFinancial record);

    int updateByPrimaryKey(SsFinancial record);
    
    int insertFiancial(PayParam param);
}