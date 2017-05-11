package costumetrade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import costumetrade.order.domain.SsStockTransfer;
@Mapper
public interface SsStockTransferMapper {
    int insert(@Param("buyTranfer")List<SsStockTransfer> buyTranfer ,@Param("buyerId")Integer buyerId,  @Param("sellerTranfer")List<SsStockTransfer> sellerTranfer,@Param("sellerId")Integer sellerId);

    int insertSelective(SsStockTransfer record);
}