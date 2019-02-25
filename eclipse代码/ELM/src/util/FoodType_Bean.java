package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodType_Bean {

	public List<FoodType_Info> getForRest(String RName) {
		List<FoodType_Info> typeData = new ArrayList<FoodType_Info>();
		
		Connection conn = DBConn.getConnection();
		try {
			String sql = "select * from foodtype where restName = ?";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, RName);
			ResultSet rs = st.executeQuery();//查询
			
			System.out.println("查询结果：" + rs);
			
			while (rs.next()) {
				String typeName = rs.getString("typeName");
				String restName = rs.getString("restName");
				FoodType_Info info = new FoodType_Info(typeName, restName);
				System.out.println(typeName);
				typeData.add(info);
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
		
		return typeData;
	}
	
	public static void main(String[] args) {
		Connection conn = DBConn.getConnection();
		FoodType_Bean bean = new FoodType_Bean();
		//bean.getForRest("鸡不厌炸");
	}
}
