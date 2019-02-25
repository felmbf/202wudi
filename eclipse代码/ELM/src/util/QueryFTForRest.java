package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryFTForRest extends HttpServlet {

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
		FoodType_Bean bean = new FoodType_Bean();
		java.util.List<FoodType_Info> type_info = bean.getForRest(RName);
		int i = 0;
		
		//·â×°³ÉJSON
		out.println("[");
		Iterator<FoodType_Info> iter = type_info.iterator();
		while (iter.hasNext()) {
			FoodType_Info info = iter.next();
			out.println("{");
			out.println("\"typeName\":\"" + info.getTypeName() + "\",");
			out.println("\"restName\":\"" + info.getRestName() + "\"");
			out.println("}");
			++i;
			if (i < type_info.size()) {
				out.println(",");
			}
		}
		out.println("]");
		out.flush();
		out.close();
	}

}
