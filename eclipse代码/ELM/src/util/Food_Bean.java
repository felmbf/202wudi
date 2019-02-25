package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Food_Bean {
	
	//����ȫ��
	public List<Food_Info> getAll() {
		List<Food_Info> foodData = new ArrayList<Food_Info>();
		
		Connection conn = DBConn.getConnection();//�������ݿ�
		try {
			String sql = "select * from food";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();//��ѯ
			System.out.println("��ѯ�����" + rs);
			
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
			System.out.println("��ѯʧ��\n" + e.getMessage());
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
					System.out.println("�ر�����ʧ��\n" + e2.getMessage());
				}
			}
		}
	
		/*for (int i = 0; i < foodData.size(); i++) {
			System.out.println(foodData.get(i).getRestName());
		}*/
		return foodData;
	}
	
	//�����̼���������Ʒ
	public List<Food_Info> getForRest(String RName) {
		List<Food_Info> foodData = new ArrayList<Food_Info>();
		Connection conn = DBConn.getConnection();
		try {
			String sql = "select * from food where restName = ?";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, RName);
			ResultSet rs = st.executeQuery();//��ѯ
			
			System.out.println("��ѯ�����" + rs);
			
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
			System.out.println("��ѯʧ��\n" + e.getMessage());
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
					System.out.println("�ر�����ʧ��\n" + e2.getMessage());
				}
			}
		}
		
		return foodData;
	}
	
	//������������
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
				System.out.println("�������ݳɹ���");
				result = true;
			} else {
				System.out.println("��������ʧ�ܣ�");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("��ѯʧ��\n" + e.getMessage());
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
					System.out.println("�ر�����ʧ��\n" + e2.getMessage());
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Connection conn = DBConn.getConnection();
		Food_Bean bean = new Food_Bean();
		bean.getForRest("������ը");
		bean.updateSalesCount("�����ײ�", 2);
	}
}
