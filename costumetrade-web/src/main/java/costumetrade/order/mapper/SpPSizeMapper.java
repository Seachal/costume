package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpPSize;
import costumetrade.order.domain.SpPSizeKey;
@Mapper
public interface SpPSizeMapper {
    int deleteByPrimaryKey(SpPSizeKey key);

    int insert(SpPSize record);

    int insertSelective(SpPSize record);

    SpPSize selectByPrimaryKey(SpPSizeKey key);

    int updateByPrimaryKeySelective(SpPSize record);

    int updateByPrimaryKey(SpPSize record);
    
    List<SpPSize> getSpPSizes(int corpId);
}