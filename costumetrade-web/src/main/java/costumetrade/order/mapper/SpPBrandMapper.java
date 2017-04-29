package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpPBrand;
import costumetrade.order.domain.SpPBrandKey;
@Mapper
public interface SpPBrandMapper {
    int deleteByPrimaryKey(SpPBrandKey key);

    int insert(SpPBrand record);

    int insertSelective(SpPBrand record);

    SpPBrand selectByPrimaryKey(SpPBrandKey key);

    int updateByPrimaryKeySelective(SpPBrand record);

    int updateByPrimaryKey(SpPBrand record);
    
    List<SpPBrand> getSpPBrands(int corpId);
}