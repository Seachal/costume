package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.order.domain.SpPSize;
@Mapper
public interface SpPSizeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpPSize record);

    int insertSelective(SpPSize record);

    SpPSize selectByPrimaryKey(Integer id);
    
    SpPSize selectByName(@Param("storeId")Integer storeId , @Param("sizeName")String sizeName);

    int updateByPrimaryKeySelective(SpPSize record);

    int updateByPrimaryKey(SpPSize record);
    
    List<SpPSize> getSpPSizes(int storeId);
}