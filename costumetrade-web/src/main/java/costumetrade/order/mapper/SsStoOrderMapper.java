package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.query.OrderQuery;

@Mapper
public interface SsStoOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SsStoOrder record);

    int insert(@Param("record")SsStoOrder record,@Param("sellerStoreId")String sellerStoreId);
    
    int insertStore(@Param("record")SsStoOrder record,@Param("storeId")String storeId);

    SsStoOrder selectByPrimaryKey(Integer id);
    
    List<SsStoOrder> selectSupplierByProduct(SpProduct product);
    
    SsStoOrder selectByOrderNo(@Param("orderNo")String orderNo,@Param("storeId")String storeId);
    
    SsStoOrder selectByTradeNo(@Param("tradeNo")String tradeNo,@Param("storeId")String storeId);
    
    int updateByPrimaryKeySelective(SsStoOrder record);
    
    int updateByPrimaryKeySelectiveStore(SsStoOrder record);

    int updateByPrimaryKey(SsStoOrder record);
    
    SsStoOrder selectByOrderId(String orderId);
    
    List<SsStoOrder> selectByOrderMember(@Param("spStoOrder")SsStoOrder spStoOrder ,@Param("page") Page page);
    
    List<SsStoOrder> selectByOrderStore(@Param("spStoOrder")SsStoOrder spStoOrder ,@Param("page") Page page);
    
    List<SsStoOrder> selectByOrderMemberCount();
    
    List<SsStoOrder> selectByOrderStoreCount(@Param("storeId")String storeId);
    
    List<SsStoOrder> financialCountingOrders(OrderQuery query);
    
    List<OrderQuery> financialCounting(OrderQuery query);
}