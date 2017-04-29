package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpPColor;
@Mapper
public interface SpPColorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpPColor record);

    int insertSelective(SpPColor record);

    SpPColor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpPColor record);

    int updateByPrimaryKey(SpPColor record);
    
    List<SpPColor>  getSpPColors(int corpId);
}