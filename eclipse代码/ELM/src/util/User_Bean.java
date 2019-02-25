package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User_Bean {

	//����ȫ��
	public List<User_Info> getAll() {
		
		List<User_Info> userData = new ArrayList<User_Info>();
		
		Connection conn = DBConn.getConnection();//�������ݿ�
		try {
			String sql = "select * from user";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();//��ѯ
			System.out.println("��ѯ�����" + rs);
			
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
		return userData;
	}
	
	//��¼�ж�
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
			
			//�����û����ж�
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

			//�����ֻ����ж�
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
				System.out.println("�˺�������ȷ��");
			} else {
				System.out.println("�˺��������");
				user_info = null;
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
		
		return user_info;
	}
	
	//�������
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
		User_Bean bean = new User_Bean();
		bean.getAll();
		bean.Login("�����Ұ�����", "123456");
		bean.Login("17367077037", "123456");
		//bean.UpdateYuE("�����Ұ�����", 55.88);
	}
}
