package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
















import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
/**
 * Servlet implementation class CreateRobot
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.RobotDTO;
import DTO.Robot_DTO;
import DTO.UserDTO;

//import com.sun.xml.bind.v2.TODO;
//import com.utd.robocode.utils.DataStoreUtils;


/**
 * Servlet implementation class Login
 */
//@WebServlet("/createrobot")
public class NewRobotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String selectedItem = null;
		HttpSession session = request.getSession();
		System.out.println(request.getParameter("RobotInfo"));
		if (request.getParameter("RobotInfo") != null) {
			selectedItem = request.getParameter("RobotInfo");
		}
		String[] words = selectedItem.split("-");
		System.out.println(words[0] +" "+words[1]);
		String robotPackage =words[0];
		String name = words[1];
		String user = words[2];
		name=name.trim();
		session.setAttribute("roboName",name);
		request.setAttribute("roboName",name);
		request.setAttribute("package",robotPackage);
		session.setAttribute("package",robotPackage);
		String robotName = String.valueOf(request.getAttribute("roboName"));
		String packageName = String.valueOf(request.getAttribute("package"));
		System.out.println("roboName:"+robotName+" with package:"+packageName);
		String message = null;
		
		
		RobotDTO robotDTO = new RobotDTO();
		
		session.setAttribute("tenant_name", user);
		RobotDTO robotAccessDTO = new RobotDTO();
		robotAccessDTO.setUserId("User");
		robotAccessDTO.setRobotName(name);
		robotAccessDTO.setPackageId(robotPackage);


		//List<String> robotList = RobotDAO.getRobotList(robotAccessDTO);

		
		robotDTO.setRobotName(robotName);
		robotDTO.setPackageId(packageName);
		//robotDTO.setRobotDescription(robotDTO.getRobotName()+".java");
		robotDTO.setCreatedDate(String.valueOf(new Date()));
		
		
		String url = "jdbc:mysql://localhost:3306/robocode";
		String username = "root";
		String password = "root";
		String robotCode=
				"package "+packageName+";\n"+
				"import robocode.*;\n"+
				"//import java.awt.Color;\n"+
				"						\n"+
				"// API help: http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html\n"+
				"															\n"+	
				"/**\n"+
				 "* "+robotName+"- a robot by "+user+"\n"+
				" */\n"+
				"public class "+robotName+" extends Robot{"+
				"	/**\n"+
				"	 * run: "+robotName+"default behavior"+
				"	 */\n"+
				"	public void run() {\n"+
				"	// Initialization of the robot should be put here\n"+
				"												\n"+
				"	// After trying out your robot, try uncommenting the import at the top\n"+
				"	// and the next line:\n"+
				"						\n"+
				"	// setColors(Color.red,Color.blue,Color.green); // body,gun,radar\n"+
				"								\n"+
				"	// Robot main loop\n"+
				"		while(true) {\n"+
				"			// Replace the next 4 lines with any behavior you would like\n"+
				"			ahead(100);\n"+
				"			turnGunRight(360);\n"+
				"			back(100);\n"+
				"			turnGunRight(360);\n"+
				"		}\n"+
				"	}\n"+
				"	/**\n"+
				"	 * onScannedRobot: What to do when you see another robot\n"+
				"	 */\n"+
				"	public void onScannedRobot(ScannedRobotEvent e) {\n"+
				"		// Replace the next line with any behavior you would like\n"+
				"		fire(1);\n"+
				"	}\n"+
				"						\n"+
				"	/**\n"+
				"	 * onHitByBullet: What to do when you're hit by a bullet\n"+
				"	 */\n"+
				"	public void onHitByBullet(HitByBulletEvent e) {\n"+
				"		// Replace the next line with any behavior you would like\n"+
				"		back(10);\n"+
				"	}\n"+
				"					\n"+
				"	/**"+
				"	 * onHitWall: What to do when you hit a wall\n"+
				"	 */"+
				"	public void onHitWall(HitWallEvent e) {\n"+
				"		// Replace the next line with any behavior you would like\n"+
				"		back(20);\n"+
				"	}	\n"+
				"}	\n";
		System.out.println(robotDTO.getCreatedDate());
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			String sql = "INSERT INTO robot (CreatedDate,RobotCode,packageID,robotID,userID,filepath,dataaccess) VALUES(?, ?, ?, ?, ?, ?, ?) ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, robotDTO.getCreatedDate());
			statement.setString(4, robotName);
			statement.setString(3, packageName);
			statement.setString(5, user);
			statement.setString(2, robotCode);
			statement.setString(7, "Y");
			statement.setString(6, "C:/robocode/robots/"+packageName+"/"+robotName+".java");
			int count = statement.executeUpdate();
			//File file = new File("C://robocode//robots//robocode//robots//"+packageName+"//"+robotName+".java");
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("C://robocode//robots//"+packageName+"//"+robotName+".java"), "utf-8"))) {
				writer.write(robotCode);
			}
			String filePath2="C:/robocode/robots/"+packageName+"/"+robotDTO.getRobotName()+".java";
			//update file in database
			sql="UPDATE robot SET file = ? WHERE robotID='"+robotName+"'";
			//sql="UPDATE robot SET file = load_file ('C:/robocode/robots/"+packageName+"/"+robotDTO.getRobotName()+".java') WHERE robotID='"+robotName+"'";
			statement = conn.prepareStatement(sql);
			File file=new File(filePath2);
			InputStream inputStream = new FileInputStream(file);
			statement.setBinaryStream(1,inputStream,(int)file.length());
			count = statement.executeUpdate();
			conn.close();
			
			//BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			//writer.write(robotCode);
			// Write your data
			//writer.close();
			/*sql="ALTER TABLE robot DROP COLUMN file";  
			String sql3="ALTER TABLE robot ADD RobotCode2 TEXT";
			String sql2="UPDATE robot SET RobotCode2 = RobotCode";
			statement = conn.prepareStatement(sql);
			count = statement.executeUpdate();
			statement = conn.prepareStatement(sql3);
			count = statement.executeUpdate();
			statement = conn.prepareStatement(sql2);
			count = statement.executeUpdate();
			//sql2="ALTER TABLE robot DROP COLUMN file";
			sql="alter table robot change RobotCode2 file blob";
			
			statement = conn.prepareStatement(sql);
			count = statement.executeUpdate();*/
			robotAccessDTO.setFilePath("C:/robocode/robots/"+packageName+"/"+robotName+".java");
			/*//statement.setString(1, filePath2);
			PreparedStatement ps1 = conn.prepareStatement("UPDATE robot SET file=? WHERE robotID='"+robotDTO.getRobotName()+"'");
			byte[] byteData = RobotCode.getBytes("UTF-8");//Better to specify encoding
			Blob blobData = conn.createBlob();
			blobData.setBytes(1, byteData);
			//Blob blob = conn.createBlob();
			//blob.setBytes(1, RobotCode.getBytes());
			ps1.setBlob(1, inputStream,(long) file.length());
			ps1.executeUpdate();
			//statement2.setBinaryStream(1, inputStream, (int) file.length());

		     //int count = statement2.executeUpdate();*/
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		
		session.setAttribute("RobObj", robotAccessDTO);
		String userName = (String) session.getAttribute("userx");
		//System.out.println("userName:"+userName);
		if(userName != null){
			//robotDTO.setTenantId(session.getAttribute("tenantId").toString());
			//System.out.println("robot get tenantid:"+robotDTO.getTenantId());
			robotDTO.setUserId(userName);
			session.setAttribute("objCurrentRobot", robotDTO);
			session.setAttribute("message",message);
			//request.getRequestDispatcher("NewRobot2.jsp").forward(request,response);
			
			//resp.sendRedirect("Robo_creation2.jsp");		
		}/*else{
			//TODO: Redirect to login page
			req.setAttribute("msg_error", errorMsg);
			req.getRequestDispatcher("create_robot.jsp").forward(req,resp);
		}*/
		
		
		
	}
}