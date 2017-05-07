package costumetrade.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SsFinancial;
@Mapper
public interface SsFinancialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsFinancial record);

    int insertSelective(SsFinancial record);

    SsFinancial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsFinancial record);

    int updateByPrimaryKey(SsFinancial record);
    
    int insertFiancial();
}