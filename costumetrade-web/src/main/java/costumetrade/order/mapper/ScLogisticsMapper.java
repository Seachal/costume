package costumetrade.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.ScLogistics;
@Mapper
public interface ScLogisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScLogistics record);

    int insertSelective(ScLogistics record);

    ScLogistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScLogistics record);

    int updateByPrimaryKey(ScLogistics record);
}