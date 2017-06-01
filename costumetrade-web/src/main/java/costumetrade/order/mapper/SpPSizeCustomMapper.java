package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SpPSizeCustom;
@Mapper
public interface SpPSizeCustomMapper {
    int deleteByPrimaryKey(@Param("ids")List<Integer> ids);

    int insert(SpPSizeCustom record);

    int insertSelective(SpPSizeCustom record);

    SpPSizeCustom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpPSizeCustom record);

    int updateByPrimaryKey(SpPSizeCustom record);
    
    List<SpPSizeCustom>  getSpPSizeCustoms(@Param("storeId")int storeId,@Param("page") Page page);
    
    SpPSizeCustom  getSpPSizeCustom(@Param("storeId")int storeId,@Param("customName")String customName);
    
    SpPSizeCustom selectByCustomValue(SpPSizeCustom record);
}