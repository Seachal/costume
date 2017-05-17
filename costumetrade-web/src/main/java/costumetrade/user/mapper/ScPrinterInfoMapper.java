package costumetrade.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.user.domain.ScPrinterInfo;
@Mapper
public interface ScPrinterInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScPrinterInfo record);

    int insertSelective(ScPrinterInfo record);

    ScPrinterInfo selectByPrimaryKey(Integer storeId);

    int updateByPrimaryKeySelective(ScPrinterInfo record);

    int updateByPrimaryKeyWithBLOBs(ScPrinterInfo record);

    int updateByPrimaryKey(ScPrinterInfo record);
}