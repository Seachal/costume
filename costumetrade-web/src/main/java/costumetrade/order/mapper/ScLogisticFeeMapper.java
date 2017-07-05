package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.order.domain.ScLogisticFee;
@Mapper
public interface ScLogisticFeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScLogisticFee record);

    int insertSelective(ScLogisticFee record);

    ScLogisticFee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScLogisticFee record);

    int updateByPrimaryKey(ScLogisticFee record);
    
    List<ScLogisticFee> selectLogisticFees();
    
    int updates(@Param("list")List<ScLogisticFee> list );
}