package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.ScFocusShop;
@Mapper
public interface ScFocusShopMapper {
    int deleteByPrimaryKey(ScFocusShop record);

    int insert(ScFocusShop record);

    int insertSelective(ScFocusShop record);

    ScFocusShop selectByPrimaryKey(Integer id);
    
    List<ScFocusShop> select(ScFocusShop record);

    int updateByPrimaryKeySelective(ScFocusShop record);

    int updateByPrimaryKey(ScFocusShop record);
}