package costumetrade.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.user.domain.SpPrivilege;
@Mapper
public interface SpPrivilegeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpPrivilege record);

    int insertSelective(SpPrivilege record);

    SpPrivilege selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpPrivilege record);

    int updateByPrimaryKey(SpPrivilege record);
    
    List<SpPrivilege> getSpPrivilegeList();
    
    SpPrivilege isPrivilegeExsist(Map<String,String> paramMap);
}