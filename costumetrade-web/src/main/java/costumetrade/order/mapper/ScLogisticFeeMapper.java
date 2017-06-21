package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
}