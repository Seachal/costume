package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpCart;
import costumetrade.order.query.PayParam;
@Mapper
public interface SpCartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpCart record);

    int insertSelective(SpCart record);

    SpCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpCart record);

    int updateByPrimaryKey(SpCart record);
    
    List<SpCart> getSpCarts(int corpId);
    
    int deleteByIds(List<Integer> list);
    
    int insert(PayParam param );
}