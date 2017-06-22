package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.ScStoreAddr;
@Mapper
public interface ScStoreAddrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScStoreAddr record);

    int insertSelective(ScStoreAddr record);

    ScStoreAddr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScStoreAddr record);

    int updateByPrimaryKey(ScStoreAddr record);
    
    List<ScStoreAddr> selectAddr(ScStoreAddr record);
}