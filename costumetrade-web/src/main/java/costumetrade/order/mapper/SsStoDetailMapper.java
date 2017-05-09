package costumetrade.order.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import costumetrade.order.domain.SsStoDetail;


@Mapper
public interface SsStoDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsStoDetail record);

    int insertSelective(SsStoDetail record);

    SsStoDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsStoDetail record);

    int updateByPrimaryKey(SsStoDetail record);
    
    int saveDetail(List<SsStoDetail> detail , Integer sellerStoreId);
    
    int saveDetailStore(List<SsStoDetail> detail , Integer buyStoreId,Integer sellerStoreId);
    
    List<SsStoDetail> selectByOrderId(Integer orderId);
    
}