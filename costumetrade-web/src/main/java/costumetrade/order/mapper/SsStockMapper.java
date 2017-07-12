package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.order.domain.SsStock;
@Mapper
public interface SsStockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsStock record);

    int insertSelective(SsStock record);

    List<SsStock> select(SsStock record);

    int updateByPrimaryKeySelective(SsStock record);

    int updateByPrimaryKey(SsStock record);
    
    int batchUpdate(@Param("stocks")List<SsStock> stocks);
    
    List<SsStock> selectStock(SsStock record);
}