package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.query.OrderQuery;
import costumetrade.order.query.ProductQuery;


@Mapper
public interface SsStoDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsStoDetail record);

    int insertSelective(SsStoDetail record);

    SsStoDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsStoDetail record);

    int updateByPrimaryKey(SsStoDetail record);
    
    int saveDetail(@Param("detail")List<SsStoDetail> detail , @Param("sellerStoreId")Integer sellerStoreId);
    
    int updateByPrimaryKeySelectiveStore(@Param("details")List<SsStoDetail> details);
    
    int updateByPrimaryKeySelectiveMember(@Param("details")List<SsStoDetail> details);
    
    int saveDetailStore(@Param("detail")List<SsStoDetail> detail , @Param("storeId")Integer storeId);
    
    List<SsStoDetail> selectByOrderId(@Param("orderNo")String orderNo,@Param("storeId")Integer storeId);
    
    List<SsStoDetail> selectByOrderIdMember(@Param("orderNo")String orderNo);
    
    List<ProductQuery> selectProductsByClient(@Param("query")OrderQuery query,@Param("page")Page page);
    
}