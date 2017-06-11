package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.order.domain.SsCgsorder;
@Mapper
public interface SsCgsorderMapper {
    int deleteByPrimaryKey(String id);

    int insert(SsCgsorder record);

    int insertSelective(SsCgsorder record);

    SsCgsorder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SsCgsorder record);

    int updateByPrimaryKey(SsCgsorder record);
    
    int saveFeeOrders(@Param("orders") List<SsCgsorder> orders);
}