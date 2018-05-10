package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;



public class testing {


	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://robocodedb.cloudapp.net:3306/form";

	   //  Database credentials
	   static final String USER = "naren";
	   static final String PASS = "naren";
	   static Connection conn = null;
	   static Statement stmt = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		      //STEP 2: Register JDBC driver
		      try {
				Class.forName("com.mysql.jdbc.Driver");
			
			  //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      
		      String Query = "select * from user_domain ud join robot r where ud.packageId = r.Package_Id  and ud.TenantId = r.TenantId and ud.UserId = 1 and ud.TenantId = 1";
		      ResultSet rs = stmt.executeQuery(Query);
		      
		      while(rs.next())
		      {
		    	  System.out.println(rs.getInt("ud.TenantId"));
		      }
		      
		      } catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
		
	}

}
