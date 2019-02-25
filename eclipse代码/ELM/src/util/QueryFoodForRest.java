package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryFoodForRest extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		String RName = request.getParameter("restName");
		Food_Bean bean = new Food_Bean();
		java.util.List<Food_Info> food_info = bean.getForRest(RName);
		int i = 0;
		
		//·â×°³ÉJSON
		out.println("[");
		Iterator<Food_Info> iter = food_info.iterator();
		while (iter.hasNext()) {
			Food_Info info = iter.next();
			out.println("{");
			out.println("\"foodName\":\"" + info.getFoodName() + "\",");
			out.println("\"foodType\":\"" + info.getFoodType() + "\",");
			out.println("\"restName\":\"" + info.getRestName() + "\",");
			out.println("\"salesCount\":\"" + info.getSalesCount() + "\",");
			out.println("\"foodPrice\":\"" + info.getFoodPrice() + "\"");
			out.println("}");
			++i;
			if (i < food_info.size()) {
				out.println(",");
			}
		}
		out.println("]");
		out.flush();
		out.close();
	}

}
