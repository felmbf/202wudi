package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryRest_All extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		Rest_Bean bean = new Rest_Bean();
		java.util.List<Rest_Info> rest_info = bean.getAll();
		int i = 0;
		
		//·â×°³ÉJSON
		out.println("[");
		Iterator<Rest_Info> iter = rest_info.iterator();
		while (iter.hasNext()) {
			Rest_Info info = iter.next();
			out.println("{");
			out.println("\"restName\":\"" + info.getRestName() + "\",");
			out.println("\"salesCount\":\"" + info.getSalesCount() + "\",");
			out.println("\"restIconUrl\":\"" + info.getRestIconUrl() + "\"");
			out.println("}");
			++i;
			if (i < rest_info.size()) {
				out.println(",");
			}
		}
		out.println("]");
		out.flush();
		out.close();
	}

}
