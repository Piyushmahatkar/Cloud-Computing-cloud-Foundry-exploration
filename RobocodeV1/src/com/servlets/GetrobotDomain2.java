package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetrobotDomain2 extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			
			String tenantName = req.getParameter("tenant_name");
			session.setAttribute("tenant_name", tenantName);
			System.out.println("tenant name is " + tenantName);
			@SuppressWarnings("unchecked")
			HashMap<String, List<String>> tenantMap = (HashMap<String, List<String>>)session.getAttribute("tenantMap");
			System.out.println(tenantMap);
			List<String> setOfvalues = tenantMap.get(tenantName);
			out.println("<option value='all'>(All)</option>");
			for(int i =0 ; i < setOfvalues.size(); i++)
			{
				out.print(
						
						"<option value='" + setOfvalues.get(i) + "'>" + setOfvalues.get(i) + "</option>"
						);			
			}			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
