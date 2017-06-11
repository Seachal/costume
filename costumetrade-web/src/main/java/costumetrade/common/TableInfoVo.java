package costumetrade.common;

import java.util.List;
import java.util.Map;

public class TableInfoVo {

	private String tableName;
	private String catalog;
	private String primaryKey;
	private Boolean isAutoIncrement=false;
	private List<ColumnInfoVo> columnVoList;
	
	private  Map<String,String> commnetMap;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<ColumnInfoVo> getColumnVoList() {
		return columnVoList;
	}

	public void setColumnVoList(List<ColumnInfoVo> columnVoList) {
		this.columnVoList = columnVoList;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public Map<String, String> getCommnetMap() {
		return commnetMap;
	}

	public void setCommnetMap(Map<String, String> commnetMap) {
		this.commnetMap = commnetMap;
	}

	public Boolean getIsAutoIncrement() {
		return isAutoIncrement;
	}

	public void setIsAutoIncrement(Boolean isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}
	
	
}
