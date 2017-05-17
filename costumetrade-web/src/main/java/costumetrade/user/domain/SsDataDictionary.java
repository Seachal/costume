package costumetrade.user.domain;

import costumetrade.common.Entity;
import java.util.Date;

public class SsDataDictionary extends Entity {
    /**
     *  
     */
    private Integer id;
    
    private Integer storeId;

    /**
     *  分组名称
     */
    private String dictGroupName;

    /**
     *  分组
     */
    private String dictGroup;

    /**
     *  数据字典的值
     */
    private String dictValue;

    /**
     *  数据字典的显示文本
     */
    private String dictText;

    /**
     *  排序
     */
    private Integer sortNum;

    /**
     *  状态 0:正常，1:删除
     */
    private Integer status;

    /**
     *  
     */
    private Date createTime;

    /**
     *  
     */
    private String createBy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictGroupName() {
        return dictGroupName;
    }

    public void setDictGroupName(String dictGroupName) {
        this.dictGroupName = dictGroupName == null ? null : dictGroupName.trim();
    }

    public String getDictGroup() {
        return dictGroup;
    }

    public void setDictGroup(String dictGroup) {
        this.dictGroup = dictGroup == null ? null : dictGroup.trim();
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }

    public String getDictText() {
        return dictText;
    }

    public void setDictText(String dictText) {
        this.dictText = dictText == null ? null : dictText.trim();
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
    
    
}