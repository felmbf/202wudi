package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Food_Bean {
	
	//查找全部
	public List<Food_Info> getAll() {
		List<Food_Info> foodData = new ArrayList<Food_Info>();
		
		Connection conn = DBConn.getConnection();//连接数据库
		try {
			String sql = "select * from food";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();//查询
			System.out.println("查询结果：" + rs);
			
			while (rs.next()) {
				String foodName = rs.getString("foodName");
				String foodType = rs.getString("foodType");
				String restName = rs.getString("restName");
				int salesCount = rs.getInt("salesCount");
				double foodPrice = rs.getDouble("foodPrice");
				Food_Info info = new Food_Info(foodName, foodType, restName, salesCount, foodPrice);
				foodData.add(info);
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
	
		/*for (int i = 0; i < foodData.size(); i++) {
			System.out.println(foodData.get(i).getRestName());
		}*/
		return foodData;
	}
	
	//根据商家名查找商品
	public List<Food_Info> getForRest(String RName) {
		List<Food_Info> foodData = new ArrayList<Food_Info>();
		Connection conn = DBConn.getConnection();
		try {
			String sql = "select * from food where restName = ?";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, RName);
			ResultSet rs = st.executeQuery();//查询
			
			System.out.println("查询结果：" + rs);
			
			while (rs.next()) {
				String foodName = rs.getString("foodName");
				String foodType = rs.getString("foodType");
				String restName = rs.getString("restName");
				int salesCount = rs.getInt("salesCount");
				double foodPrice = rs.getDouble("foodPrice");
				Food_Info info = new Food_Info(foodName, foodType, restName, salesCount, foodPrice);
				System.out.println(foodType + foodName);
				foodData.add(info);
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
		
		return foodData;
	}
	
	//更新已售数量
	public Boolean updateSalesCount(String restName, int addCount) {
		Boolean result = false;
		Connection conn = DBConn.getConnection();
		try {
			String sql = "update food set salesCount = salesCount + ? where foodName = ?";
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
		Food_Bean bean = new Food_Bean();
		bean.getForRest("鸡不厌炸");
		bean.updateSalesCount("考神套餐", 2);
	}
}
