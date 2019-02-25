package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Rest_Bean {

	//查找全部
	public List<Rest_Info> getAll() {
		List<Rest_Info> restData = new ArrayList<Rest_Info>();
		
		Connection conn = DBConn.getConnection();//连接数据库
		try {
			String sql = "select * from restaurant";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();//查询
			System.out.println("查询结果：" + rs);
			
			while (rs.next()) {
				String restName = rs.getString("restName");
				String restIconUrl = rs.getString("restIconUrl");
				int salesCount = rs.getInt("salesCount");
				Rest_Info info = new Rest_Info(restName, restIconUrl, salesCount);
				System.out.println(restName);
				restData.add(info);
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
	
		/*for (int i = 0; i < restData.size(); i++) {
			System.out.println(restData.get(i).getRestName());
		}*/
		return restData;
	}
	
	//根据商家名查找商家
	public List<Rest_Info> getRestForName(String Name) {
		List<Rest_Info> rest_Info = new ArrayList<Rest_Info>();
		
		Connection conn = DBConn.getConnection();//连接数据库
		try {
			String sql = "select * from restaurant where restName= ? ";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, Name);
			ResultSet rs = st.executeQuery();//查询
			System.out.println("查询结果：" + rs);

			if (rs.next()) {
				String restName = rs.getString("restName");
				String restIconUrl = rs.getString("restIconUrl");
				int salesCount = rs.getInt("salesCount");
				Rest_Info info = new Rest_Info(restName, restIconUrl, salesCount);
				System.out.println(restName);
				rest_Info.add(info);
			} else {
				System.out.println("failed");
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
		
		return rest_Info;
	}
	
	//更新已售数量
	public Boolean updateSalesCount(String restName, int addCount) {
		Boolean result = false;
		Connection conn = DBConn.getConnection();
		try {
			String sql = "update restaurant set salesCount = salesCount + ? where restName = ?";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, addCount);
			st.setString(2, restName);
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
		Connection conn = DBConn.getConnection();
		Rest_Bean bean = new Rest_Bean();
		bean.getAll();
		bean.getRestForName("鸡不厌炸");
	}
}
