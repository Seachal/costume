package costumetrade.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.user.domain.SpEmployee;

@Mapper
public interface SpEmployeeMapper {
    int deleteByPrimaryKey(SpEmployee key);

    int insert(SpEmployee record);

    int insertSelective(SpEmployee record);

    SpEmployee selectByPrimaryKey(SpEmployee key);

    int updateByPrimaryKeySelective(SpEmployee record);

    int updateByPrimaryKey(SpEmployee record);
    
    List<SpEmployee>  getAllEmployees(String subId);
    
    int  deleteEmployee(SpEmployee record );
    
    List<SpEmployee>  selects(SpEmployee record);
}