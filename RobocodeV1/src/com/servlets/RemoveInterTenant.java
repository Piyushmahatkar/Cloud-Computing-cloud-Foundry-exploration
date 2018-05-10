package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveInterTenant extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
			  {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
		

		Map<String, List<String>> inter_tenant = new TreeMap<String, List<String>>(Collections.reverseOrder());
			System.out.println("The hierarchy table is "+ req.getParameter("ht"));
			String ht = (String) req.getParameter("ht");
			String[] values = ht.split("&");
	        for (String string : values) {
	        	System.out.println("the values are "+ string);
	        	if(!string.equalsIgnoreCase("test=")){
		            String[] mapPair =string.split("=");
		            if(inter_tenant.get(mapPair[0]) == null){
		            	inter_tenant.put(mapPair[0], new ArrayList<String>());
		            	inter_tenant.get(mapPair[0]).add(mapPair[1]);
		            }	
		            else{
		            	inter_tenant.get(mapPair[0]).add(mapPair[1]);
		            }
	        	}

	        }
	        System.out.println("The inter_tenant is" + inter_tenant);
			System.out.println("the length of rows " + req.getParameter("numOfRows"));
			String num_rows = req.getParameter("numOfRows");
			Integer nr = Integer.parseInt(num_rows);
			for(int i =1;i< nr+1; i++){
				String d_n = "domain_name" + i;
				System.out.println("The d_n is"+ d_n);
				List<String> FromTenantID = inter_tenant.get(d_n);
				List<String> FromPackagePermissionID = inter_tenant.get("testing123" + i);
				List<String> ToTenantPermission = inter_tenant.get("roleid"+i);
				for(int j =0; j < ToTenantPermission.size(); j++){
					String role_values[] = ToTenantPermission.get(j).split("_");					
					try{
						Connection connection = DriverManager
								.getConnection("jdbc:mysql://robocodedb.cloudapp.net:3306/Role?user=naren&password=naren");
						Statement statement = connection.createStatement();
						System.out.println("INSERT INTO  Role.Inter_Tenant_Mapping (FromTenantID ,FromPackagePermissionID ,ToTenantID ,ToPackagePermissionID)VALUES (" + "'" + FromTenantID.get(0) + "'" +  "," +  "'" + FromPackagePermissionID.get(0) + "'" + "," + "'" + role_values[0] + "'" + "," + "'" + role_values[1] + "'" + ")");
						int rs = statement
								.executeUpdate("DELETE FROM Inter_Tenant_Mapping WHERE FromTenantID =" + "'" + FromTenantID.get(0) + "'" +  "and FromPackagePermissionID =" + "'" + FromPackagePermissionID.get(0) + "'" + "and ToTenantID =" + "'" + role_values[0] + "'" + "and ToPackagePermissionID =" + "'" + role_values[1] + "'");
						if(rs > 0)
						{
							out.println("Permission is revoked");
							
						}
						else if(rs == 0){
							out.println("Removal  is not possible. Already the permission was set from " + role_values[0] + "to" + role_values[1]);
						}
						
						
					}catch (Exception e) {
						// TODO Auto-generated catch block
						out.println("Insertion is not possible. Already the permission was set from " + role_values[0] +  " to " + role_values[1]);
					}		finally{
						out.close();
					}
					
				}
				
				System.out.println("the domain name is" + FromTenantID);
				System.out.println("the FromPackagePermissionID name is" + FromPackagePermissionID);
				System.out.println("ToTenantPermission " + ToTenantPermission);
			}
			//System.out.println("tenant Name value:"+tenantName);
			//String[] values = tenantName.split("_");
			/*try {
				//Connection connection = DriverManager
					//	.getConnection("jdbc:mysql://robocodedb.cloudapp.net:3306/Role?user=naren&password=naren");

				//Statement statement = connection.createStatement();
				//System.out.println("INSERT into Inter_Tenant_Mapping values()UPDATE robot SET Robot_Ranking = " + each_robot_info[1] +  " WHERE robot.Package_Id = '"	+ robot_domain_info[0] + "' AND robot.Robot_Name = '"	+ robot_domain_info[1] + "'");
				//int resultset = statement.executeUpdate("UPDATE robot SET Robot_Ranking = " + each_robot_info[1] +  " WHERE robot.Package_Id = '"	+ robot_domain_info[0] + "' AND robot.Robot_Name = '"	+ robot_domain_info[1] + "'");
				System.out.println("INSERT INTO  Role.Inter_Tenant_Mapping (FromTenantID ,FromPackagePermissionID ,ToTenantID ,ToPackagePermissionID)VALUES (" + values[0] + "," +  values[1] + "," +  values[2] + "," +  values[3] +")");;
				int rs = statement
						.executeUpdate("INSERT INTO  Role.Inter_Tenant_Mapping (FromTenantID ,FromPackagePermissionID ,ToTenantID ,ToPackagePermissionID)VALUES (" + "'" + values[0] + "'" +  "," +  "'" + values[1] + "'" + "," + "'" + values[2] + "'" + "," + "'" + values[3] + "'" + ")");
				System.out.println("rs is" + rs);
				if(rs > 0)
				{
					out.println("Permission is added");
					
				}
				else if(rs == 0){
					out.println("Insertion is not possible. Already the permission was set from " + values[0] + "to" + values[2]);
				}
			}catch (Exception e) {
				out.println("Insertion is not possible. Already the permission was set from " + values[0] +  " to " + values[2]);
			}*/

		


		
	}

	
	
	
}
