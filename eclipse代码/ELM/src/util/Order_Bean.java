package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Order_Bean {

	//����ȫ��
		public List<Order_Info> getAll(String user) {
			
			List<Order_Info> orderData = new ArrayList<Order_Info>();
			
			Connection conn = DBConn.getConnection();//�������ݿ�
			try {
				String sql = "select * from dingdan where O_user = ?";
				java.sql.PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, user);
				ResultSet rs = st.executeQuery();//��ѯ
				System.out.println("��ѯ�����" + rs);
				
				while (rs.next()) {
					String O_user = rs.getString("O_user");
					String O_restName = rs.getString("O_restName");
					String O_food = rs.getString("O_food");
					String O_date = rs.getString("O_date");
					double O_totalPrice = rs.getDouble("O_totalPrice");
					Order_Info info = new Order_Info(O_user, O_restName, O_food, O_date, O_totalPrice);
					orderData.add(info);
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
			return orderData;
		}
		
		public Boolean AddData(String O_user, String O_restName, String O_food, String O_date, double O_totalPrice) {
			
			Boolean result = false;
			
			Connection conn = DBConn.getConnection();
			try {
				String sql = "insert into dingdan(O_restName,O_totalPrice,O_date,O_food,O_user) values(?,?,?,?,?)";
				java.sql.PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, O_restName);
				st.setDouble(2, O_totalPrice);
				st.setString(3, O_date);
				st.setString(4, O_food);
				st.setString(5, O_user);
				
				int rs = st.executeUpdate();
				
				if (rs > 0) {
					result = true;
					System.out.println("�������ݳɹ���");
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
			
			Order_Bean bean = new Order_Bean();
			bean.getAll("������");
			bean.AddData("������", "������ը", "�����ײ�", "2018-12-21 22:49", 58.88);
		}
}
