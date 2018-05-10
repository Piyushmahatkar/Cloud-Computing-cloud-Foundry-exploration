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

public class Getrobots extends HttpServlet {

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
			
			String domainName = req.getParameter("domain_name");
			session.setAttribute("packageId", domainName);
			System.out.println("tenant name is" + domainName);
			HashMap<String, List<String>> domainMap = (HashMap<String, List<String>>)session.getAttribute("DomainMap");
			System.out.println(domainMap);
			List<String> setOfvalues = domainMap.get(domainName);
			out.println("<option>Select Robot</option>");
			for(int i =0 ; i < setOfvalues.size(); i++)
			{
				out.print(
						//"<option value='1'>1</option>"
						"<option value='" + setOfvalues.get(i) + "'>" + setOfvalues.get(i) + "</option>"
						);			
			}			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
