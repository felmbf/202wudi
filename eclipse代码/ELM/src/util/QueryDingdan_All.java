package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryDingdan_All extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		String user = request.getParameter("user");
		
		Order_Bean bean = new Order_Bean();
		java.util.List<Order_Info> order_info = bean.getAll(user);
		int i = 0;
		
		//·â×°³ÉJSON
		out.println("[");
		Iterator<Order_Info> iter = order_info.iterator();
		while (iter.hasNext()) {
			Order_Info info = iter.next();
			out.println("{");
			out.println("\"O_user\":\"" + info.getO_user() + "\",");
			out.println("\"O_restName\":\"" + info.getO_restName() + "\",");
			out.println("\"O_food\":\"" + info.getO_food() + "\",");
			out.println("\"O_date\":\"" + info.getO_date() + "\",");
			out.println("\"O_totalPrice\":\"" + info.getO_totalPrice() + "\"");
			out.println("}");
			++i;
			if (i < order_info.size()) {
				out.println(",");
			}
		}
		out.println("]");
		out.flush();
		out.close();
	}

}
