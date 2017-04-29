package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpPCate;
@Mapper
public interface SpPCateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpPCate record);

    int insertSelective(SpPCate record);

    SpPCate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpPCate record);

    int updateByPrimaryKey(SpPCate record);
    
    List<SpPCate>  getSpPCates(int corpId);
}