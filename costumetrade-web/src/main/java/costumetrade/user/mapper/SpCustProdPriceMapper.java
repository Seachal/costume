package costumetrade.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.user.domain.SpCustProdPrice;
@Mapper
public interface SpCustProdPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpCustProdPrice record);

    int insertSelective(SpCustProdPrice record);

    SpCustProdPrice selectByPrimaryKey(Integer id);
    
    List<SpCustProdPrice> select(SpCustProdPrice record);

    int updateByPrimaryKeySelective(SpCustProdPrice record);

    int updateByPrimaryKey(SpCustProdPrice record);
    
    int saveTypeOrGradeRates(@Param("custProdPrices")List<SpCustProdPrice> custProdPrices);
}