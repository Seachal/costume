package costumetrade.order.mapper;

import java.util.List;

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
    
    List<SsPrice> selectPrices(@Param("storeid")String storeid,@Param("idArray")List<String> idArray);

    int updateByPrimaryKeySelective(SsPrice record);

    int updateByPrimaryKey(SsPrice record);
    
    int  updates(List<SsPrice> list);
}