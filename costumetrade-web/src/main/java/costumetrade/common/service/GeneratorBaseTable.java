package costumetrade.common.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import costumetrade.common.TableInfoVo;
import costumetrade.common.util.ConfigProperties;
import costumetrade.common.util.DBHelper;
import costumetrade.common.util.TableInfoUtils;

public class GeneratorBaseTable {
	
	private static  Log loger = LogFactory.getLog(TableInfoUtils.class);
	    
	public static void main(String[] args)  {
	    
	    
		try {
			generatorTable("8");
		} catch (Exception e) {
			loger.error("异常 "+e.getMessage());
		}	 
	}
	
	public static void generatorTable(String storeId){
		
	    String tableStr = ConfigProperties.getProperty("target.tables");
	    String[] tableSplit = tableStr.split(",");
	    Connection conn = null;
		try {
			
		    conn = DBHelper.getConnection();
		    if(null!=tableSplit && tableSplit.length>0){
		    	for(String tableName:tableSplit){
		    		TableInfoVo tableInfoVo = TableInfoUtils.getTableInfoVo(tableName);
		    		if(null!=tableInfoVo && null!=tableInfoVo.getColumnVoList()
		    				&& tableInfoVo.getColumnVoList().size()>0){
			    		String tableSQL = GenenatorTableService.generatorSQL(tableName,tableInfoVo,storeId);
			    		Statement stmt = conn.createStatement();
			    		          stmt.execute(tableSQL);
			    		          stmt.close();
		    		}
		        }
		    }
		} catch (Exception e) {
			loger.error("异常 "+e.getMessage());
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	     
}



