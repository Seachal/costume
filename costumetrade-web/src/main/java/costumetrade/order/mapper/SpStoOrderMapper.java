package costumetrade.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpStoOrder;
import costumetrade.order.query.OrderDetailKeyParam;
@Mapper
public interface SpStoOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpStoOrder record);

    int insertSelective(SpStoOrder record);

    SpStoOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpStoOrder record);

    int updateByPrimaryKey(SpStoOrder record);
    
    SpStoOrder selectByOrderId(OrderDetailKeyParam param);
}