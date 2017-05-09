package costumetrade.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpClient;
@Mapper
public interface SpClientMapper {
    int insert(SpClient record);

    int insertSelective(SpClient record);
    
    SpClient selectByPrimaryKey(Integer clientId);
}