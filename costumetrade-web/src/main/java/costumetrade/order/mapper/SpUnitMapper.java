package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SpUnit;
@Mapper
public interface SpUnitMapper {
    int deleteByPrimaryKey(List<Integer> id);

    int insert(SpUnit record);

    int insertSelective(SpUnit record);

    SpUnit selectByPrimaryKey(Integer id);
    SpUnit getSpUnitByName(@Param("unit") String unit,@Param("storeId")Integer storeId);

    int updateByPrimaryKeySelective(SpUnit record);

    int updateByPrimaryKey(SpUnit record);
    
    List<SpUnit> getUnits(@Param("storeId")Integer storeId,@Param("page") Page page );
}