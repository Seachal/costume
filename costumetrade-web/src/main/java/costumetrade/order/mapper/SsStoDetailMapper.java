package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.order.domain.SsStoDetail;


@Mapper
public interface SsStoDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsStoDetail record);

    int insertSelective(SsStoDetail record);

    SsStoDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsStoDetail record);

    int updateByPrimaryKey(SsStoDetail record);
    
    int saveDetail(@Param("detail")List<SsStoDetail> detail , @Param("sellerStoreId")Integer sellerStoreId);
    
    int saveDetailStore(@Param("detail")List<SsStoDetail> detail , @Param("storeId")Integer storeId);
    
    List<SsStoDetail> selectByOrderId(@Param("orderNo")String orderNo,@Param("storeId")Integer storeId);
    
    List<SsStoDetail> selectByOrderIdMember(@Param("orderNo")String orderNo);
    
}