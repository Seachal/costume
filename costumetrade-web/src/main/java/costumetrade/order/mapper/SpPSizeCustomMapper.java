package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpPSizeCustom;
@Mapper
public interface SpPSizeCustomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpPSizeCustom record);

    int insertSelective(SpPSizeCustom record);

    SpPSizeCustom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpPSizeCustom record);

    int updateByPrimaryKey(SpPSizeCustom record);
    
    List<SpPSizeCustom>  getSpPSizeCustoms(int corpId);
    
    SpPSizeCustom selectByCustomValue(SpPSizeCustom record);
}