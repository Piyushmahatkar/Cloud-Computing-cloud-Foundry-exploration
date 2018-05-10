package servlets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Statement;

import DTO.RobotDTO;
import DTO.UserDTO;
import Service.UpdateRobotRestClientService;

/**
 * Servlet implementation class UpdateRobotServlet
 */

public class UpdateRobotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateRobotServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String robotCode = (String)request.getAttribute("robocode");
		System.out.println("Inside Post");
		HttpSession session = request.getSession();
		String url = "jdbc:mysql://localhost:3306/robocode";
		String user = "root";
		String password = "root";

		RobotDTO robotDTO = null;
		//String userName = session.getAttribute("userx").toString();
		String RobotCode = request.getParameter("textArea");
		//System.out.println(request.getParameter("RobotCode"));
		if (request.getParameter("RobotCode") != null) {
			RobotCode =request.getParameter("RobotCode");
		}
		//RobotCode=request.getParameter("textArea");
		System.out.println("Robocode:" +RobotCode);


		/*if (request.getParameter("editor") != null) {
			String selectedItem = request.getParameter("editor");
		}	*/
		robotDTO = (RobotDTO) session.getAttribute("RobObj");
		robotDTO.setUpdatedDate(String.valueOf(new Date()));
		robotDTO.setRobotCode(RobotCode);
		robotDTO.setFilePath("C:/robocode/robots/"+robotDTO.getPackageId()+"/"+robotDTO.getRobotName()+".java");
		request.setAttribute("User",robotDTO.getUserId());

		UpdateRobotRestClientService updateRobot = new UpdateRobotRestClientService();
		String message = updateRobot.updateRobot(robotDTO);

		System.out.println("updated message:"+message);
		request.setAttribute("message", message);
		request.setAttribute("date", robotDTO.getUpdatedDate());
		/*File file=new File(robotDTO.getRobotName()+".java");
		PrintWriter out2=new PrintWriter(file);
		out2.println(RobotCode);
		FileInputStream inputStream = new FileInputStream(file);*/
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			//System.out.println(robotDTO.getPackageId()+" "+robotDTO.getRobotId());
			//set updated date
			String sql = "UPDATE robot SET UpdatedDate='"+robotDTO.getUpdatedDate()+"'WHERE RobotID='"+robotDTO.getRobotName()+"'";
			//set the code for the robot
			String sql2 = "UPDATE robot SET RobotCode=? WHERE RobotID='"+robotDTO.getRobotName()+"'";
			//create a new file with the updated robot code

			PreparedStatement statement = conn.prepareStatement(sql);
			int count = statement.executeUpdate();
			PreparedStatement statement2 = conn.prepareStatement(sql2);
			statement2.setString(1, RobotCode);
			count = statement2.executeUpdate();
			//System.out.println(robotDTO.getRobotCode());
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("C://robocode//robots//"+robotDTO.getPackageId()+"//"+robotDTO.getRobotName()+".java"), "utf-8"))) {
				writer.write(robotDTO.getRobotCode());
			}
			String filePath2="C:/robocode/robots/"+robotDTO.getPackageId()+"/"+robotDTO.getRobotName()+".java";
			//update file in database
			sql="UPDATE robot SET file = ? WHERE robotID='"+robotDTO.getRobotName()+"'";
			//sql2="select convert(file using utf8) from robot";
			/*RobotCode has already been updated in the database, so we don't need the next statement
			String sql3="update robot set RobotCode = select convert(file using utf8) from robot where id='"+robotDTO.getRobotId()+"'";*/
			//sql="ALTER TABLE robot DROP COLUMN file";  
			//String sql3="ALTER TABLE robot ADD RobotCode2 TEXT";
			//sql2="UPDATE robot SET RobotCode2 = RobotCode";
			//statement = conn.prepareStatement(sql);
			//count = statement.executeUpdate();
			//statement = conn.prepareStatement(sql3);
			//count = statement.executeUpdate();
			//statement = conn.prepareStatement(sql2);
			//count = statement.executeUpdate();
			//sql="alter table robot change RobotCode2 file blob";
			statement = conn.prepareStatement(sql);
			File file=new File(filePath2);
			InputStream inputStream = new FileInputStream(file);
			statement.setBinaryStream(1,inputStream,(int)file.length());
			count = statement.executeUpdate();
			conn.close();
			request.setAttribute("robotID", robotDTO.getRobotName());
			request.setAttribute("packageID",robotDTO.getPackageId());
			request.setAttribute("Robocode",RobotCode);
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

		
		RequestDispatcher rd=request.getRequestDispatcher("Edit_Robot2.jsp");    
		rd.forward(request, response);
//		PrintWriter out = response.getWriter();
//				out.println(RobotCode);
//				out.close();
		//rd.include(request,response);
	}

}
