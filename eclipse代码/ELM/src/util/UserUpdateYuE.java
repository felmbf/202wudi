package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserUpdateYuE extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String s = request.getParameter("updateCount");
		double updateCount = new Double(s);
		
		User_Bean bean = new User_Bean();
		Boolean result = bean.UpdateYuE(username, updateCount);
		
		if(result){
	    	out.println(1);
	    }
	    else{
	    	out.println(0);
	    }
		out.flush();
		out.close();
	}

}
