package util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.dbutils.DbUtils;

public class DBConn {

	//连接数据库
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			//加载Mysql数据驱动
			DbUtils.loadDriver("com.mysql.jdbc.Driver");
			//建立连接
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/elm", 
					"root", 
					"123456");
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
		
		return conn;
	}
	
	public static void main(String[] args) {
		Connection conn = DBConn.getConnection();
		if (conn == null) {
			System.out.println("Failed!");
		} else {
			System.out.println("Succeed!");
		}
	}
}
