package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpStoDetail;
import costumetrade.order.query.OrderDetailKeyParam;
import costumetrade.order.query.OrderDetailParam;
@Mapper
public interface SpStoDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpStoDetail record);

    int insertSelective(SpStoDetail record);

    SpStoDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpStoDetail record);

    int updateByPrimaryKey(SpStoDetail record);
    
    int saveDetail(OrderDetailParam param);
    
    List<SpStoDetail>  selectByOrderId(OrderDetailKeyParam param);
    
    int updateStatus(OrderDetailKeyParam param);
}