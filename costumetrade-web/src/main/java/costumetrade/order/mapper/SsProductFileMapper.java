package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.common.page.Page;
import costumetrade.order.domain.SsProductFile;
@Mapper
public interface SsProductFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsProductFile record);

    int insertSelective(SsProductFile record);

    SsProductFile selectByPrimaryKey(Integer id);
    
    List<SsProductFile> selectByStoreId(@Param("page")Page page,@Param("ssProductFile")SsProductFile ssProductFile);

    int updateByPrimaryKeySelective(SsProductFile record);

    int updateByPrimaryKey(SsProductFile record);
    
    int insertFiles(@Param("files")List<SsProductFile> files ,@Param("storeId") Integer storeId);
}