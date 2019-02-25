package util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.dbutils.DbUtils;

public class DBConn {

	//�������ݿ�
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			//����Mysql��������
			DbUtils.loadDriver("com.mysql.jdbc.Driver");
			//��������
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/elm", 
					"root", 
					"123456");
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݿ�����ʧ��");
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
