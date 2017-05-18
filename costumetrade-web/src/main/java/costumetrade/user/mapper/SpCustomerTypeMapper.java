package costumetrade.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.user.domain.SpCustomerType;
@Mapper
public interface SpCustomerTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpCustomerType record);

    int insertSelective(SpCustomerType record);

    SpCustomerType selectByPrimaryKey(Integer id);
    
    SpCustomerType selectByName(SpCustomerType record);

    int updateByPrimaryKeySelective(SpCustomerType record);

    int updateByPrimaryKey(SpCustomerType record);
}