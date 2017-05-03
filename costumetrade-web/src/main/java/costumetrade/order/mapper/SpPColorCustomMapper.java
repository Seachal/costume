package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpPColorCustom;
@Mapper
public interface SpPColorCustomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpPColorCustom record);

    int insertSelective(SpPColorCustom record);

    SpPColorCustom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpPColorCustom record);

    int updateByPrimaryKey(SpPColorCustom record);
    
    List<SpPColorCustom> getSpPColorCustoms(int corpId);
    
    SpPColorCustom selectByCustomValue(SpPColorCustom record);
}