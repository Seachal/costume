package costumetrade.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.user.domain.SpMenuEmployee;
@Mapper
public interface SpMenuEmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpMenuEmployee record);

    int insertSelective(SpMenuEmployee record);

    SpMenuEmployee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpMenuEmployee record);

    int updateByPrimaryKey(SpMenuEmployee record);
    
    List<SpMenuEmployee> getAllMenuEmployees(Long employeeId);
    
    int saveSpMenuEmployees(@Param("list")List<SpMenuEmployee> list);
    
    int deleteByEmployeeId(Long employeeId);
    
   // int insertMenuEmployees();
}