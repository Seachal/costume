package costumetrade.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.order.domain.SsPrice;
@Mapper
public interface SsPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsPrice record);

    int insertSelective(SsPrice record);

    SsPrice selectByPrimaryKey(Integer id);
    
    SsPrice select(@Param("storeid")String storeid ,@Param("productid")String productid);

    int updateByPrimaryKeySelective(SsPrice record);

    int updateByPrimaryKey(SsPrice record);
}