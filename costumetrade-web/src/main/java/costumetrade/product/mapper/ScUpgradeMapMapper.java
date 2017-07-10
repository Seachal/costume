package costumetrade.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.product.domain.ScUpgradeMap;
@Mapper
public interface ScUpgradeMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScUpgradeMap record);

    int insertSelective(ScUpgradeMap record);

    ScUpgradeMap selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScUpgradeMap record);

    int updateByPrimaryKey(ScUpgradeMap record);
    
    int updateByOpenId(ScUpgradeMap record);
}