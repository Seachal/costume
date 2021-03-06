package costumetrade.order.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.query.ProductQuery;
@Mapper
public interface SpProductMapper {
    int deleteByIds(@Param("storeId")String storeId,@Param("ids")List<String> ids);
    
    int updateByIds(ProductQuery  productQuery);

    int insert(SpProduct record);

    int insertSelective(SpProduct record);

    SpProduct selectByPrimaryKey(@Param("id")String id,@Param("storeId")String storeId);
    
    List<SpProduct> selectById(@Param("id")List<String> id,@Param("storeId")String storeId);

    int updateByPrimaryKeySelective(SpProduct record);

    int updateByPrimaryKey(SpProduct record);
    
    List<Map<String,Object>> selectProducts(@Param("product")ProductQuery product , @Param("page")Page page);
    
    ProductQuery selectProduct(ProductQuery param);
    
    List<SpProduct> selectPopulars(SpProduct record);
}