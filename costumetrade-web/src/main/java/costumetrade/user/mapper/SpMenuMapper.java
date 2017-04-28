package costumetrade.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.user.domain.SpMenu;
@Mapper
public interface SpMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpMenu record);

    int insertSelective(SpMenu record);

    SpMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpMenu record);

    int updateByPrimaryKey(SpMenu record);
    
    List<SpMenu>  getAllMenus();
    
    
}