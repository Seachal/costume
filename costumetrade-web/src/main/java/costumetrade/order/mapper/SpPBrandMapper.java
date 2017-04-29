package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpPBrand;
@Mapper
public interface SpPBrandMapper {
    int insert(SpPBrand record);

    int insertSelective(SpPBrand record);
    
    List<SpPBrand>  getSpPBrands(int corpId);
    
    int deleteByPrimaryKey(Integer id);
    
    SpPBrand selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(SpPBrand record);
}