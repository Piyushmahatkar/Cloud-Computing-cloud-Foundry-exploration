package servlets;
import robocode.RobocodeCompile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import DTO.RobotDTO;
import Service.UpdateRobotRestClientService;
import edu.utdallas.Compile;
@WebServlet("/CompileServlet")
public class CompileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public CompileServlet() {
		// TODO Auto-generated constructor stub
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String robotCode = (String)request.getAttribute("robocode");
		System.out.println("Inside compile servlet");
		HttpSession session = request.getSession();
		RobotDTO robotDTO = null;
		String RobotCode=null,selectedItem = null,robotID=null, packageID=null;

		/*if (request.getParameter("RobotCode") != null) {
			selectedItem = request.getParameter("RobotCode");
		}
		String[] words = selectedItem.split("blah");*/
		
		robotDTO = (RobotDTO) session.getAttribute("RobObj");
		packageID=robotDTO.getPackageId();
		robotID=robotDTO.getRobotName();
		RobotCode = request.getParameter("textArea");
		System.out.println("Info "+packageID+" "+robotID);
		//RobotCode =words[2];
		//robotName = words[0];
		//packageID=words[1];
//		RobocodeCompile.CompileFile(robotID,packageID);
		
		Connection conn=null;
		try{
			byte[] compiledCode=Compile.compile(packageID, robotID, RobotCode, getServletContext().getRealPath("/")+"robocode.jar");
			String url = "jdbc:mysql://localhost:3306/robocode";
			String user = "root";
			String password = "root";
			conn = DriverManager.getConnection(url, user, password);
			String sql = "UPDATE robot SET file=? WHERE RobotID='"+robotDTO.getRobotName()+"'";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBlob(1, new SerialBlob(compiledCode));
			statement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace(System.err);
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
		}
		
		
		System.out.println("Successfully compiled");
		request.setAttribute("message", "Successfully compiled");
		request.setAttribute("packageID", packageID);
		request.setAttribute("robotID", robotID);
		request.setAttribute("Robocode", RobotCode);
		//PrintWriter out = response.getWriter();
		//out.println(RobotCode);
		RequestDispatcher rd=request.getRequestDispatcher("Edit_Robot2.jsp");    
		rd.forward(request, response);
	}


}
