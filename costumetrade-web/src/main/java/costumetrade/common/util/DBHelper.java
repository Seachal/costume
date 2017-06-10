package costumetrade.common.util;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
  
public class DBHelper {  
	
    public static final String url = ConfigProperties.getProperty("jdbc.url");  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = ConfigProperties.getProperty("jdbc.username");  
    public static final String password = ConfigProperties.getProperty("jdbc.password");  
  
    public static Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public static Connection getConnection() {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return conn;
    }  
  
    public PreparedStatement getPst() {
		return pst;
	}

	public void close() {  
        try {  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
} 