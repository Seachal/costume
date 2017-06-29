package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.order.domain.SsStockTransfer;
@Mapper
public interface SsStockTransferMapper {
    int insert(@Param("tranfer")List<SsStockTransfer> tranfer ,@Param("storeId")String storeId);

    int insertSelective(SsStockTransfer record);
}