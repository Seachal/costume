package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SpPColor;
import costumetrade.order.domain.SpPSize;
@Mapper
public interface SpPColorMapper {
    int deleteByPrimaryKey(@Param("ids")List<Integer> ids);

    int insert(SpPColor record);

    int insertSelective(SpPColor record);

    SpPColor selectByPrimaryKey(Integer id);
    
    SpPColor selectByName(@Param("color")SpPColor color);
    
    List<SpPColor> getSpPColors(@Param("color")SpPColor color,@Param("page")Page page);

    int updateByPrimaryKeySelective(SpPColor record);

    int updateByPrimaryKey(SpPColor record);
    
    int updates(@Param("colors")List<SpPColor> colors);
    
    int inserts(@Param("colors")List<SpPColor> colors);
    
}