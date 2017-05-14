package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.order.domain.SpPColor;
@Mapper
public interface SpPColorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpPColor record);

    int insertSelective(SpPColor record);

    SpPColor selectByPrimaryKey(Integer id);
    
    SpPColor selectByName(@Param("colorname")String colorname, @Param("storeId")Integer storeId);
    
    List<SpPColor> getSpPColors(Integer storeId);

    int updateByPrimaryKeySelective(SpPColor record);

    int updateByPrimaryKey(SpPColor record);
    
}