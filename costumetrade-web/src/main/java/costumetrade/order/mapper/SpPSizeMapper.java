package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.order.domain.SpPSize;
@Mapper
public interface SpPSizeMapper {
    int deleteByPrimaryKey(@Param("ids")List<Integer> ids);

    int insert(SpPSize record);

    int insertSelective(SpPSize record);

    SpPSize selectByPrimaryKey(Integer id);
    
    SpPSize selectByName(@Param("size")SpPSize size );

    int updateByPrimaryKeySelective(SpPSize record);

    int updateByPrimaryKey(SpPSize record);
    
    List<SpPSize> getSpPSizes(@Param("size")SpPSize size );
    
    int updates(@Param("sizes")List<SpPSize> sizes);
    
    int inserts(@Param("sizes")List<SpPSize> sizes);
}