package costumetrade.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.order.domain.SpPrinterInfo;
@Mapper
public interface SpPrinterInfoMapper {
    int deleteByPrimaryKey(int  id );

    int insert(SpPrinterInfo record);

    int insertSelective(SpPrinterInfo record);

    SpPrinterInfo selectByPrimaryKey(int  id );

    int updateByPrimaryKeySelective(SpPrinterInfo record);

    int updateByPrimaryKeyWithBLOBs(SpPrinterInfo record);

    int updateByPrimaryKey(SpPrinterInfo record);
    
}