package costumetrade.order.domain;

import costumetrade.common.Entity;
import java.math.BigDecimal;

public class ScLogisticFee extends Entity {
    /**
     *  主键
     */
    private Integer id;

    /**
     *  物流公司代号
     */
    private String logisticCode;

    /**
     *  物流公司名称
     */
    private String logisticName;

    /**
     *  运费类型
     */
    private String feeType;

    /**
     *  运费规则描述
     */
    private String description;

    /**
     *  固定运费
     */
    private BigDecimal fixedFee;

    /**
     *  免邮金额
     */
    private BigDecimal freeFee;

    /**
     *  序号
     */
    private Integer sortNo;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode == null ? null : logisticCode.trim();
    }

    public String getLogisticName() {
        return logisticName;
    }

    public void setLogisticName(String logisticName) {
        this.logisticName = logisticName == null ? null : logisticName.trim();
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public BigDecimal getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(BigDecimal fixedFee) {
        this.fixedFee = fixedFee;
    }

    public BigDecimal getFreeFee() {
        return freeFee;
    }

    public void setFreeFee(BigDecimal freeFee) {
        this.freeFee = freeFee;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}