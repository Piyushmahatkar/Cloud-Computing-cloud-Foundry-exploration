package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class getroleid extends HttpServlet{

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
		
		if(tenantName!= null && removeName == null)
		{
			System.out.println("tenant Name value:"+tenantName);
			System.out.println("the map object is" + session.getAttribute("combination"));
			HashMap<String, List<String>> configParamsMap = (HashMap<String, List<String>>)session.getAttribute("combination");
			List<String> setOfvalues = configParamsMap.get(tenantName);
			out.println("<option>Select Domain</option>");
			for(int i =0 ; i < setOfvalues.size(); i++)
			{
				String[] values = setOfvalues.get(i).split("_");
				
				out.print(
						//"<option value='1'>1</option>"
						
						"<option value='" + setOfvalues.get(i) + "'>" + values[0] + " " + values[1] + "</option>"
						);			
			}			
		

		}
		else if(tenantName == null && removeName!= null){
			System.out.println("tenant Name value:"+tenantName);
			System.out.println("the map object is" + session.getAttribute("remove_combination"));
			HashMap<String, List<String>> configParamsMap = (HashMap<String, List<String>>)session.getAttribute("remove_combination");
			List<String> setOfvalues = configParamsMap.get(removeName);
			out.println("<option>Select Domain</option>");
			for(int i =0 ; i < setOfvalues.size(); i++)
			{
				String[] values = setOfvalues.get(i).split("_");
				
				out.print(
						//"<option value='1'>1</option>"
						
						"<option value='" + setOfvalues.get(i) + "'>" + values[0] + " " + values[1] + "</option>"
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
