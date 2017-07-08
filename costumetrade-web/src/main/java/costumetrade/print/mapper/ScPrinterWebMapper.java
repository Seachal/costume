package costumetrade.print.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.print.domain.ScPrinterWeb;
@Mapper
public interface ScPrinterWebMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScPrinterWeb record);

    int insertSelective(ScPrinterWeb record);

    ScPrinterWeb selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScPrinterWeb record);

    int updateByPrimaryKey(ScPrinterWeb record);
}