package costumetrade.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.user.domain.SsDataDictionary;
@Mapper
public interface SsDataDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsDataDictionary record);

    int insertSelective(SsDataDictionary record);
    
    int insertDatas(@Param("datas")List<SsDataDictionary> datas);

    SsDataDictionary selectByPrimaryKey(Integer id);
    
    List<SsDataDictionary> select(SsDataDictionary record);
    
    List<SsDataDictionary> selectDictionarys(@Param("list")List<String> list,@Param("storeId")String storeId);
    
    List<SsDataDictionary> getDataDictionarys(String storeId);
    
    int updateByPrimaryKeySelective(SsDataDictionary record);
    
    int updateDatas(@Param("datas")List<SsDataDictionary> record);

    int updateByPrimaryKey(SsDataDictionary record);
}