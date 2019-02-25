package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLogin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
		
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		String name_tel = request.getParameter("name_tel");
		String password = request.getParameter("password");
		int result = 0;
		
		User_Bean bean = new User_Bean();
		User_Info user_info = bean.Login(name_tel, password);
		
		if (user_info != null) {
			result = 1;
		}
		
		System.out.println(result);
		
		out.println("[");
		
		out.println("{");
		out.println("\"username\":\"" + user_info.getUsername() + "\",");
		out.println("\"password\":\"" + user_info.getPassword() + "\",");
		out.println("\"telnum\":\"" + user_info.getTelnum() + "\",");
		out.println("\"yuE\":\"" + user_info.getYuE() + "\",");
		out.println("\"result\":\"" + result + "\"");
		out.println("}");
		
		out.println("]");
		
		out.flush();
		out.close();
	}

}
