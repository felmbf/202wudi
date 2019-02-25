package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDingDan extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		String O_user = request.getParameter("O_user");
		String O_restName = request.getParameter("O_restName");
		String O_food = request.getParameter("O_food");
		String O_date = request.getParameter("O_date");
		
		String totalPrice = request.getParameter("O_totalPrice");
		double O_totalPrice = new Double(totalPrice);
		
		Order_Bean bean = new Order_Bean();
		Boolean result = bean.AddData(O_user, O_restName, O_food, O_date, O_totalPrice);
		
		if (result) {
			System.out.println("插入成功！");
		} else {
			System.out.println("插入失败！");
		}
		
		out.flush();
		out.close();
	}

}
