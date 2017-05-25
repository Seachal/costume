package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SpPCate;
@Mapper
public interface SpPCateMapper {
    int deleteByPrimaryKey(List<Integer> ids);

    int insert(SpPCate record);

    int insertSelective(SpPCate record);

    SpPCate selectByPrimaryKey(Integer id);
  
    SpPCate getSpPCate(@Param("id")Integer id,@Param("storeId")Integer storeId);
    
    SpPCate getSpPCateByName(@Param("catename")String catename,@Param("storeId")Integer storeId);

    int updateByPrimaryKeySelective(SpPCate record);

    int updateByPrimaryKey(SpPCate record);
    
    List<SpPCate> getSpPCates(@Param("storeId")Integer storeId,@Param("page")Page page);
}