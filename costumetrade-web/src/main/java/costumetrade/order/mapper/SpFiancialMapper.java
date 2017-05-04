package costumetrade.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpFiancial;
@Mapper
public interface SpFiancialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpFiancial record);

    int insertSelective(SpFiancial record);

    SpFiancial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpFiancial record);

    int updateByPrimaryKey(SpFiancial record);
}