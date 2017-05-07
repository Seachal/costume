package costumetrade.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.query.OrderDetailKeyParam;
@Mapper
public interface SsStoOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsStoOrder record);

    int insertSelective(SsStoOrder record);

    SsStoOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsStoOrder record);

    int updateByPrimaryKey(SsStoOrder record);
    
    SsStoOrder selectByOrderId(OrderDetailKeyParam param);
}