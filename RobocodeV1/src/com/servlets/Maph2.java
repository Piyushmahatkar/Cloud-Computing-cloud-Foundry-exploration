package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.CreateRoleDAO;
import com.DAO.HierarchicalRoleDAO;
import com.DAO.MapRoleDAO;
import com.DAO.PackageRoleDAO;
import com.DTO.HRoleTableDTO;
import com.DTO.Hierarchical_Role_PermissionDTO;
import com.DTO.PackagePermissionDTO;
import com.DTO.PackageRoleDTO;
import com.DTO.PackageTableDTO;
import com.DTO.Package_Role_PermissionDTO;
import com.DTO.UserDTO;
import com.DTO.User_Hier_RoleDTO;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class HierarchyEdit2
 */
@WebServlet("/Maph2")
public class Maph2 extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Maph2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MapRoleDAO HroleD = new MapRoleDAO();
		UserDTO user = new UserDTO();
		User_Hier_RoleDTO role1 = new User_Hier_RoleDTO();
		String roleDesc = request.getParameter("Hroledesc");
		String roleid = request.getParameter("Ppermissionid");
		String rowCount = request.getParameter("rowcount");
		System.out.println("Row Count:" + rowCount);
		Integer ifor = Integer.parseInt(rowCount);
		List<User_Hier_RoleDTO> RList = new ArrayList<User_Hier_RoleDTO>();
		String permission = null;
		for (int i = 0; i < ifor; i++) {
			User_Hier_RoleDTO user_Hier_RoleDTO = new User_Hier_RoleDTO();
			permission = request.getParameter("select" + i);
			user_Hier_RoleDTO.setRole(permission);
			user_Hier_RoleDTO.setUser(roleid);
			try {
				Connection connect = DriverManager
						.getConnection("jdbc:mysql://robocodedb.cloudapp.net:3306/Role?user=naren&password=naren");

				Statement statement = (Statement) connect.createStatement();
				String sql = "SELECT TenantId FROM Users where Userid = '" + roleid + "'";
				PreparedStatement ppl = (PreparedStatement) connect.prepareStatement(sql);
				ResultSet rl = (ResultSet) ppl.executeQuery();
				while(rl.next())
			    {
			    	user_Hier_RoleDTO.setTenant(rl.getString("TenantId"));
			    }
			} catch (Exception e) {
				System.out.println("wrong entry" + e);
				// out.println("wrong entry" + e);
			}
			RList.add(user_Hier_RoleDTO);

		}
		user.setUser(roleid);
		user.setDescription(roleDesc);
		String msg = HroleD.add(user);
		String msg1 = HroleD.add1(RList);
		String[] permission2 = request.getParameterValues("delete");
		String value;
		// System.out.println("permission1= "+permission1+"permission2= "+permission2[1]+permission2[0]+"permission3= "+permission3);
		if (permission2 != null)
		{
		for (int i = 0; i < permission2.length; i++) {
			try {
				Connection connection = DriverManager
						.getConnection("jdbc:mysql://robocodedb.cloudapp.net:3306/Role?user=naren&password=naren");

				Statement statement = (Statement) connection.createStatement();
				String newstmt = "Delete from UserHierarchicalRole where HierarchicalRoleId='"
						+ permission2[i]
						+ "' and UserId='"
						+ roleid + "'";
				System.out.println(newstmt);
				int resultSet = statement.executeUpdate(newstmt);

			} catch (Exception e) {
				System.out.println("wrong entry" + e);
				// out.println("wrong entry" + e);
			}
		}
		}
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		if (msg.equalsIgnoreCase("RoleID")) {
			out.print("<pre style=\"color:red\">Sorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already exists</pre>");
			RequestDispatcher rd = request
					.getRequestDispatcher("CreateRole.jsp");
			request.setAttribute("message", msg);

			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
			request.setAttribute("message", msg);
			rd.forward(request, response);
		}

	}

}
