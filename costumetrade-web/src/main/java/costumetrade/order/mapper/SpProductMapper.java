package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.query.ProductQuery;
@Mapper
public interface SpProductMapper {
    int deleteByIds(@Param("storeId")Integer storeId,@Param("ids")List<String> ids);
    
    int updateByIds(@Param("storeId")Integer storeId,@Param("ids")List<String> ids);

    int insert(SpProduct record);

    int insertSelective(SpProduct record);

    SpProduct selectByPrimaryKey(@Param("id")String id,@Param("storeId")Integer storeId);
    
    List<SpProduct> selectById(@Param("id")List<String> id,@Param("storeId")Integer storeId);

    int updateByPrimaryKeySelective(SpProduct record);

    int updateByPrimaryKey(SpProduct record);
    
    List<SpProduct> selectProducts(@Param("product")ProductQuery product , @Param("page")Page page);
    
    ProductQuery selectProduct(ProductQuery param);
}