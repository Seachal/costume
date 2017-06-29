package costumetrade.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.user.domain.SpUser;
@Mapper
public interface SpUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpUser record);

    int insertSelective(SpUser record);

    SpUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpUser record);

    int updateByPrimaryKey(SpUser record);
    
 
}