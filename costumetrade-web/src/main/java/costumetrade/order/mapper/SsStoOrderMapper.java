package costumetrade.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SsStoOrder;

@Mapper
public interface SsStoOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SsStoOrder record);

    int insert(SsStoOrder record,Integer sellerStoreId);
    
    int insertStore(SsStoOrder record,Integer buyStoreId,Integer sellerStoreId);

    SsStoOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsStoOrder record);

    int updateByPrimaryKey(SsStoOrder record);
    
    SsStoOrder selectByOrderId(Integer orderId);
}