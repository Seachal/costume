package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.order.domain.SpPBrand;
@Mapper
public interface SpPBrandMapper {
    int insert(SpPBrand record);

    int insertSelective(SpPBrand record);
    
    List<SpPBrand>  getSpPBrands(int storeId);
    
    int deleteByPrimaryKey(Integer id);
    
    SpPBrand selectByPrimaryKey(Integer id);
    
    SpPBrand getSpPBrand(@Param("id")Integer id,@Param("storeId")Integer storeId);
    
    SpPBrand getSpPBrandByName(@Param("brandname")String brandname,@Param("storeId")Integer storeId);
    
    int updateByPrimaryKeySelective(SpPBrand record);
}