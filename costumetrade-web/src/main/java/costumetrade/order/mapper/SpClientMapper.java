package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SpClient;
@Mapper
public interface SpClientMapper {
    int insert(SpClient record);

    int insertSelective(SpClient record);
   
    int deleteById(String clientId);
    
    SpClient selectByPrimaryKey(String clientId);
    
    int updateByPrimaryKeySelective(SpClient record);
    
    
    List<SpClient> select(@Param("client")SpClient client,@Param("page") Page page);
    
    List<SpClient> selectDistincts(@Param("client")SpClient client
);
}