package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpProduct;
import costumetrade.order.query.ProductQuery;
@Mapper
public interface SpProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpProduct record);

    int insertSelective(SpProduct record);

    SpProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpProduct record);

    int updateByPrimaryKey(SpProduct record);
    
    List<SpProduct> selectProducts(ProductQuery param);
    
    SpProduct selectProduct(ProductQuery param);
}