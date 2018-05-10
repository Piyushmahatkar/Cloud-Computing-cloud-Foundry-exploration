package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import DTO.DomainRobotListDTO;

public class GetDomainRobotListDAO {
	
	   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   final String DB_URL = "jdbc:mysql://robocodedb.cloudapp.net:3306/form";

	   //  Database credentials
	   final String USER = "naren";
	   final String PASS = "naren";
	   Connection conn = null;
	   Statement stmt = null;
	   
	   
	   public List<DomainRobotListDTO> getDomainRobotListDAO(Integer UserId, String TenantId){
		   List<DomainRobotListDTO> domainRobotList = new ArrayList<DomainRobotListDTO>();
		   
		   try {
				Class.forName("com.mysql.jdbc.Driver");
				
				System.out.println("Connecting to database...");
			    conn = DriverManager.getConnection(DB_URL,USER,PASS);
		        
			    System.out.println("Creating statement...");
			    stmt = conn.createStatement();
			    
			    String Query = "select * from user_domain ud join robot r where ud.packageId = r.Package_Id  and ud.TenantId = r.TenantId and ud.UserId = "+UserId+" and ud.TenantId = '"+TenantId+"'";
			    ResultSet rs = stmt.executeQuery(Query);
			    
			   while(rs.next())
			   {
				   DomainRobotListDTO domainRobotListDTO = new DomainRobotListDTO();
			    	domainRobotListDTO.setPackageId(rs.getString("ud.PackageId"));
			    	
			   }
			      
			      } catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
		   return domainRobotList;
		   
	   }
	   

}
