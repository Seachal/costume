package costumetrade.order.mapper;

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
    ScStoreAddr selectAddr(Integer storeId);
}