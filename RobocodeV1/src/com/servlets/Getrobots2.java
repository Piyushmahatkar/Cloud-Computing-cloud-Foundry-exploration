package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Getrobots2 extends HttpServlet {

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
			if(domainName.equals("all")){
				Map<String, List<String>> map = new TreeMap<String, List<String>>(domainMap);
				 Set set = map.keySet();
				 Iterator iter = set.iterator();
				while(iter.hasNext())
				{
					
					String j = (String)iter.next();
					List<String> setOfvalues = domainMap.get(j);
					java.util.Collections.sort(setOfvalues);
					for(int i =0 ; i < setOfvalues.size(); i++)
					{
						out.print(
								//"<option value='1'>1</option>"
										"<option value='" + setOfvalues.get(i) + "'>" + setOfvalues.get(i) + "</option>"
								);			
					}		
				}	
			}

			else{
				
				List<String> setOfvalues = domainMap.get(domainName);
				java.util.Collections.sort(setOfvalues);
				for(int i =0 ; i < setOfvalues.size(); i++)
				{
					out.print(
							//"<option value='1'>1</option>"
									"<option value='" + setOfvalues.get(i) + "'>" + setOfvalues.get(i) + "</option>"
							);			
				}	
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
