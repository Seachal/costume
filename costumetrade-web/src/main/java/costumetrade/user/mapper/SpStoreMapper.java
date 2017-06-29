package costumetrade.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.user.domain.SpStore;
@Mapper
public interface SpStoreMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpStore record);

    int insertSelective(SpStore record);

    SpStore selectByPrimaryKey(String id);
    
    List<SpStore> selectStores(@Param("store")SpStore store,@Param("page") Page page);

    int updateByPrimaryKeySelective(SpStore record);

    int updateByPrimaryKey(SpStore record);
}