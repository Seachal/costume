package costumetrade.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.user.domain.SpUser;
@Mapper
public interface SpUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpUser record);

    int insertSelective(SpUser record);

    SpUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpUser record);

    int updateByPrimaryKey(SpUser record);
    
 
}