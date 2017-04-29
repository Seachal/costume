package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpPSize;
@Mapper
public interface SpPSizeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpPSize record);

    int insertSelective(SpPSize record);

    SpPSize selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpPSize record);

    int updateByPrimaryKey(SpPSize record);
    
    List<SpPSize> getSpPSizes(int corpId);
}