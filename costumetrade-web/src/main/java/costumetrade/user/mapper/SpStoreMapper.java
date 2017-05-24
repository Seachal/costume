package costumetrade.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.user.domain.SpStore;
@Mapper
public interface SpStoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpStore record);

    int insertSelective(SpStore record);

    SpStore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpStore record);

    int updateByPrimaryKey(SpStore record);
}