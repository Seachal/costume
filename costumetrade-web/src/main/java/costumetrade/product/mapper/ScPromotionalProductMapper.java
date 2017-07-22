package costumetrade.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.product.domain.ScPromotionalProduct;
@Mapper
public interface ScPromotionalProductMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScPromotionalProduct record);

    int insertSelective(ScPromotionalProduct record);

    ScPromotionalProduct selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScPromotionalProduct record);

    int updateByPrimaryKey(ScPromotionalProduct record);
    
    List<ScPromotionalProduct> getAllPromotionals(@Param("product")ScPromotionalProduct product,@Param("page")Page page);
    
    List<ScPromotionalProduct> getGroupPromotionals(@Param("product")ScPromotionalProduct product,@Param("page")Page page);
    
    int inserts(@Param("list")List<ScPromotionalProduct> list);
}