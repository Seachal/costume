package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpProduct;
import costumetrade.order.query.KeyParam;
import costumetrade.order.query.ProductDetailQuery;
import costumetrade.order.query.ProductInitQuery;
import costumetrade.order.query.ProductsQuery;
@Mapper
public interface SpProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpProduct record);

    int insertSelective(SpProduct record);

    SpProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpProduct record);

    int updateByPrimaryKey(SpProduct record);
    
    List<SpProduct> selectProducts(ProductsQuery productQuery);
    
    ProductDetailQuery selectProduct(KeyParam keyParam);
    
    ProductInitQuery productInit(int corpId);
    
    SpProduct selectByCode(SpProduct record);
}