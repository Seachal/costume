package costumetrade.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SsProductReview;
@Mapper
public interface SsProductReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsProductReview record);

    int insertSelective(SsProductReview record);

    SsProductReview selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsProductReview record);

    int updateByPrimaryKey(SsProductReview record);
}