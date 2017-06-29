package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SpPBrand;
@Mapper
public interface SpPBrandMapper {
    int insert(SpPBrand record);

    int insertSelective(SpPBrand record);
    
    List<SpPBrand>  getSpPBrands(@Param("storeId")	String storeId,@Param("page")Page page);
    
    int deleteByPrimaryKey(@Param("ids")List<Integer> ids);
    
    SpPBrand selectByPrimaryKey(Integer id);
    
    SpPBrand getSpPBrand(@Param("id")Integer id,@Param("storeId")String storeId);
    
    SpPBrand getSpPBrandByName(@Param("brandname")String brandname,@Param("storeId")String storeId);
    
    int updateByPrimaryKeySelective(SpPBrand record);
}