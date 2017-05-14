package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.order.domain.SpPCate;
@Mapper
public interface SpPCateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpPCate record);

    int insertSelective(SpPCate record);

    SpPCate selectByPrimaryKey(Integer id);
  
    SpPCate getSpPCate(@Param("id")Integer id,@Param("storeId")Integer storeId);
    
    SpPCate getSpPCateByName(@Param("catename")String catename,@Param("storeId")Integer storeId);

    int updateByPrimaryKeySelective(SpPCate record);

    int updateByPrimaryKey(SpPCate record);
    
    List<SpPCate>  getSpPCates(int storeId);
}