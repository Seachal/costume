package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SsProductReview;
import costumetrade.order.query.ProductQuery;
@Mapper
public interface SsProductReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsProductReview record);

    int insertSelective(SsProductReview record);

    SsProductReview selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsProductReview record);

    int updateByPrimaryKey(SsProductReview record);
    
    List<SsProductReview> selectReviews(@Param("query")ProductQuery query ,@Param("page")Page page);
    
    SsProductReview selectReviewCount(@Param("query")ProductQuery query );
}