package costumetrade.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.ScFocusShop;
@Mapper
public interface ScFocusShopMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScFocusShop record);

    int insertSelective(ScFocusShop record);

    ScFocusShop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScFocusShop record);

    int updateByPrimaryKey(ScFocusShop record);
}