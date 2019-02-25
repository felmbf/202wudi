package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User_Bean {

	//查找全部
	public List<User_Info> getAll() {
		
		List<User_Info> userData = new ArrayList<User_Info>();
		
		Connection conn = DBConn.getConnection();//连接数据库
		try {
			String sql = "select * from user";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();//查询
			System.out.println("查询结果：" + rs);
			
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String telnum = rs.getString("telnum");
				double yuE = rs.getDouble("yuE");
				User_Info info = new User_Info(username, password, telnum, yuE);
				userData.add(info);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("查询失败\n" + e.getMessage());
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
					System.out.println("关闭连接失败\n" + e2.getMessage());
				}
			}
		}
		return userData;
	}
	
	//登录判断
	public User_Info Login(String name_tel, String password) {
		User_Info user_info = null;
		String s = "", ss = "";
		String username;
		String ppassword;
		String telnum;
		double yuE;
		Connection conn = DBConn.getConnection();
		try {
			String sql = "select * from user where username = ?";
			String sqls = "select * from user where telnum = ?";
			
			
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			java.sql.PreparedStatement sts = conn.prepareStatement(sqls);
			
			//根据用户名判断
			st.setString(1, name_tel);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				s = rs.getString("password");
				username = rs.getString("username");
				ppassword = rs.getString("password");
				telnum = rs.getString("telnum");
				yuE = rs.getDouble("yuE");
				user_info = new User_Info(username, ppassword, telnum, yuE);
				System.out.println(s);
			}

			//根据手机号判断
			sts.setString(1, name_tel);
			rs = sts.executeQuery();
			if (rs.next()) {
				ss = rs.getString("password");
				username = rs.getString("username");
				ppassword = rs.getString("password");
				telnum = rs.getString("telnum");
				yuE = rs.getDouble("yuE");
				user_info = new User_Info(username, ppassword, telnum, yuE);
				System.out.println(ss);
			}
			
			if (s.equals(password) || ss.equals(password)) {
				System.out.println("账号密码正确！");
			} else {
				System.out.println("账号密码错误！");
				user_info = null;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("查询失败\n" + e.getMessage());
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
					System.out.println("关闭连接失败\n" + e2.getMessage());
				}
			}
		}
		
		return user_info;
	}
	
	//更新余额
	public Boolean UpdateYuE(String username, double updateCount) {
		Boolean result = false;
		Connection conn = DBConn.getConnection();
		try {
			String sql = "update user set yuE = yuE - ? where username = ?";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			st.setDouble(1, updateCount);
			st.setString(2, username);
			int rs = st.executeUpdate();
	
			if (rs > 0) {
				System.out.println("更新数据成功！");
				result = true;
			} else {
				System.out.println("更新数据失败！");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("查询失败\n" + e.getMessage());
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
					System.out.println("关闭连接失败\n" + e2.getMessage());
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		User_Bean bean = new User_Bean();
		bean.getAll();
		bean.Login("或许我爱萝莉", "123456");
		bean.Login("17367077037", "123456");
		//bean.UpdateYuE("或许我爱萝莉", 55.88);
	}
}
