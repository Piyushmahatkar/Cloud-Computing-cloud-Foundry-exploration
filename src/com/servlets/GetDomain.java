package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetDomain extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
			  {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
		try {
			
		String tenantName = req.getParameter("domain_name");
		String removeName = req.getParameter("remove_name");
		System.out.println("the tenantname and removename" + tenantName + removeName);
		if(tenantName!= null && removeName == null)
		{
			System.out.println("tenant Name value:"+tenantName);
			System.out.println("the map object is" + session.getAttribute("configParamsMap"));
			HashMap<String, List<String>> configParamsMap = (HashMap<String, List<String>>)session.getAttribute("configParamsMap");
			List<String> setOfvalues = configParamsMap.get(tenantName);
			out.println("<option>Select Domain</option>");
			for(int i =0 ; i < setOfvalues.size(); i++)
			{
				out.print(
						//"<option value='1'>1</option>"
						"<option value='" + setOfvalues.get(i) + "'>" + setOfvalues.get(i) + "</option>"
						);			
			}			
		
		}
		else if(tenantName == null && removeName!= null){
			System.out.println("tenant Name value:"+tenantName);
			System.out.println("the map object is" + session.getAttribute("remove_configParamsMap"));
			HashMap<String, List<String>> configParamsMap = (HashMap<String, List<String>>)session.getAttribute("remove_configParamsMap");
			List<String> setOfvalues = configParamsMap.get(removeName);
			out.println("<option>Select Domain</option>");
			for(int i =0 ; i < setOfvalues.size(); i++)
			{
				out.print(
						//"<option value='1'>1</option>"
						"<option value='" + setOfvalues.get(i) + "'>" + setOfvalues.get(i) + "</option>"
						);			
			}			
		
		}			
		

		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			out.close();
		}
		
	}

	
	
	
}
