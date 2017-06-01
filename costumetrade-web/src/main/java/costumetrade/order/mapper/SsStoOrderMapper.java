package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SsStoOrder;

@Mapper
public interface SsStoOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SsStoOrder record);

    int insert(@Param("record")SsStoOrder record,@Param("sellerStoreId")Integer sellerStoreId);
    
    int insertStore(@Param("record")SsStoOrder record,@Param("storeId")Integer storeId);

    SsStoOrder selectByPrimaryKey(Integer id);
    
    SsStoOrder selectByOrderNo(@Param("orderNo")String orderNo,@Param("storeId")Integer storeId);
    
    SsStoOrder selectByTradeNo(@Param("tradeNo")String tradeNo,@Param("storeId")Integer storeId);
    
    int updateByPrimaryKeySelective(SsStoOrder record);
    
    int updateByPrimaryKeySelectiveStore(SsStoOrder record);

    int updateByPrimaryKey(SsStoOrder record);
    
    SsStoOrder selectByOrderId(Integer orderId);
    
    List<SsStoOrder> selectByOrderMember(@Param("spStoOrder")SsStoOrder spStoOrder ,@Param("page") Page page);
    
    List<SsStoOrder> selectByOrderStore(@Param("spStoOrder")SsStoOrder spStoOrder ,@Param("page") Page page);
    int selectByOrderMemberCount(@Param("spStoOrder")SsStoOrder spStoOrder);
    
    int selectByOrderStoreCount(@Param("spStoOrder")SsStoOrder spStoOrder);
}