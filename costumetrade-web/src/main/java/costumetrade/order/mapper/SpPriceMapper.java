package costumetrade.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpPrice;
@Mapper
public interface SpPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpPrice record);

    int insertSelective(SpPrice record);

    SpPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpPrice record);

    int updateByPrimaryKey(SpPrice record);
}