package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateSalesCount extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		String foodName = request.getParameter("foodName");
		String aCount = request.getParameter("addCount");
		int addCount = Integer.parseInt(aCount);
		Food_Bean bean = new Food_Bean();
		
		Boolean result = bean.updateSalesCount(foodName, addCount);
		
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
