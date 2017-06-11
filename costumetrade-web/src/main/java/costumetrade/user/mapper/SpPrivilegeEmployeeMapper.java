package costumetrade.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.user.domain.SpPrivilegeEmployee;
@Mapper
public interface SpPrivilegeEmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpPrivilegeEmployee record);

    int insertSelective(SpPrivilegeEmployee record);

    SpPrivilegeEmployee selectByPrimaryKey(Long id);
    
    List<SpPrivilegeEmployee> getEmployeeSpPrivilegeList(SpPrivilegeEmployee record);

    int updateByPrimaryKeySelective(SpPrivilegeEmployee record);

    int updateByPrimaryKey(SpPrivilegeEmployee record);
    
    int saveSpPrivilegeEmployees(@Param("list")List<SpPrivilegeEmployee>  privilegeEmployees);
}