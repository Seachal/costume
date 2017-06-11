package costumetrade.common.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mysql.jdbc.StringUtils;

import costumetrade.common.ColumnInfoVo;
import costumetrade.common.TableInfoVo;


public class TableInfoUtils {
	
    private static  Log loger = LogFactory.getLog(TableInfoUtils.class);
    
    public static TableInfoVo getTableInfoVo(String tableName) throws Exception{
    	
    	TableInfoVo tableInfoVo = new TableInfoVo();
    	Connection conn = null;
    	 ResultSet tableSet = null;
		try {
	        conn = DBHelper.getConnection();
	    	String catalog = conn.getCatalog();
	        DatabaseMetaData  metaData = conn.getMetaData();
		    tableSet = metaData.getTables(null, "%",tableName,new String[]{"TABLE"});
		    tableInfoVo.setTableName(getTableName(tableSet));
		    tableInfoVo.setCatalog(catalog);
		   
		    ResultSet colRet = metaData.getColumns(null,"%",tableName,"%");
		    List<ColumnInfoVo> columnInfoLisst = new ArrayList<ColumnInfoVo>();
		    
		    while(colRet.next()) {
		    	ColumnInfoVo columnVo = new ColumnInfoVo();
		    	columnVo.setColumnName(colRet.getString("COLUMN_NAME"));
		    	columnVo.setColumnType(colRet.getString("TYPE_NAME"));
		    	if("datetime".toUpperCase().equalsIgnoreCase(colRet.getString("TYPE_NAME"))
		    			||"timestamp".toUpperCase().equalsIgnoreCase(colRet.getString("TYPE_NAME"))
		    			||"date".toUpperCase().equalsIgnoreCase(colRet.getString("TYPE_NAME"))){
		    		columnVo.setLength(6);
		    	}else{
		    		columnVo.setLength(colRet.getInt("COLUMN_SIZE"));
		    	}
		    	
		    	columnInfoLisst.add(columnVo);
	        }
		    
		    Statement stmt = conn.createStatement(); 
		    ResultSet  results = stmt.executeQuery("select * from "+tableName); 
		    ResultSetMetaData resultMetaData = results.getMetaData(); 
		    int columnCount = resultMetaData.getColumnCount();
		    
		    for(int i=1;i<=columnCount;i++){
		    	if(resultMetaData.isAutoIncrement(i)){
		    		tableInfoVo.setIsAutoIncrement(true);
		    		break;
		    	}
		    }
		    tableInfoVo.setColumnVoList(columnInfoLisst);
			tableInfoVo.setCommnetMap(getColumnCommentByTableName(conn,tableName));
			String primaryKey = getPrimaryKey(conn,tableName);
			tableInfoVo.setPrimaryKey(primaryKey);
			

		} catch (Exception e) {
			loger.error("异常："+e.getMessage());
			try {
				tableSet.close();
				conn.close();
				
				throw e;
			} catch (SQLException e1) {
			}
		}finally{
			try {
				tableSet.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		
	    return tableInfoVo;
	    
    }
    
    public static String getTableName(ResultSet tableSet) throws SQLException {
    	if(null==tableSet){
    		return "";
    	}
    	String tableName = "";
        while(tableSet.next()) {
        	tableName = tableSet.getString("TABLE_NAME");
        }
        return tableName;
    }
    
    public static Map<String,String> getColumnCommentByTableName(Connection conn,String tableName) throws Exception {  
        Map<String,String> mapComment = new HashMap<String,String>();  
        
        Statement stmt = conn.createStatement();  
       
        ResultSet rs = stmt.executeQuery("show full columns from " + tableName);  
        while (rs.next()) {     
        	mapComment.put(rs.getString("Field"), rs.getString("Comment"));  
        }   
        rs.close();  
        return mapComment;  
    }  
    public static String getPrimaryKey(Connection conn,String tableName) {
        ResultSet rst;
    	String primaryKey = "";
		try {
			rst = conn.getMetaData().getPrimaryKeys(conn.getCatalog().toUpperCase(),null, tableName);

	        while (rst.next()) {
	        	primaryKey = rst.getString("COLUMN_NAME");
			}
		} catch (SQLException e) {
		}

        return primaryKey;
    }
    
    
    public static String getClassNameFirstBig(String columnName){
    	if(StringUtils.isNullOrEmpty(columnName)){
    		return "";
    	}

    	return columnName.replace(columnName.substring(0, 1), columnName.substring(0, 1).toUpperCase());
    }
    
    public static String getClassNameFirstSmall(String columnName){
    	if(StringUtils.isNullOrEmpty(columnName)){
    		return "";
    	}

    	return columnName.replace(columnName.substring(0, 1), columnName.substring(0, 1).toLowerCase());
    }
}
