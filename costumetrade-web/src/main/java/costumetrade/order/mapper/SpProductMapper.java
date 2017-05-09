package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpProduct;

import costumetrade.order.query.ProductDetailQuery;
import costumetrade.order.query.Param;
import costumetrade.order.query.ProductQuery;
@Mapper
public interface SpProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpProduct record);

    int insertSelective(SpProduct record);

    SpProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpProduct record);

    int updateByPrimaryKey(SpProduct record);
    
    List<ProductQuery> selectProducts(Param param);
    
    ProductDetailQuery selectProduct(Param param);
}