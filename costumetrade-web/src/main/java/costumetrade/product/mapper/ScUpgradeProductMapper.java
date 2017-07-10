package costumetrade.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.product.domain.ScUpgradeProduct;


@Mapper
public interface ScUpgradeProductMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(ScUpgradeProduct record);

    int insertSelective(ScUpgradeProduct record);

    ScUpgradeProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScUpgradeProduct record);

    int updateByPrimaryKey(ScUpgradeProduct record);
    
    List<ScUpgradeProduct> getAllProduct();
}