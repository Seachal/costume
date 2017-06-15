package costumetrade.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.user.domain.SsPayment;
@Mapper
public interface SsPaymentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsPayment record);

    int insertSelective(SsPayment record);

    SsPayment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsPayment record);

    int updateByPrimaryKey(SsPayment record);
    
    SsPayment countRepay(SsPayment record);
}