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
import com.DTO.HRoleTableDTO;
import com.DTO.Hierarchical_Role_PermissionDTO;
import com.DTO.PackagePermissionDTO;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class HierarchyEdit2
 */
@WebServlet("/HierarchyEdit2")
public class HierarchyEdit2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HierarchyEdit2() {
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
		HierarchicalRoleDAO HroleD = new HierarchicalRoleDAO();
		HRoleTableDTO role = new HRoleTableDTO();
		Hierarchical_Role_PermissionDTO role1 = new Hierarchical_Role_PermissionDTO();
		String roleDesc = request.getParameter("Hroledesc");
		String roleid = request.getParameter("Hpermissionid");
		String rowCount = request.getParameter("rowcount");
		System.out.println("Row Count:" + rowCount);
		Integer ifor = Integer.parseInt(rowCount);
		List<Hierarchical_Role_PermissionDTO> PList = new ArrayList<Hierarchical_Role_PermissionDTO>();
		String permission = null;
		for (int i = 0; i < ifor; i++) {
			Hierarchical_Role_PermissionDTO hierarchial_role_permissionDTO = new Hierarchical_Role_PermissionDTO();
			permission = request.getParameter("select" + i);
			hierarchial_role_permissionDTO
					.setHierarchicalPermissionID(permission);
			hierarchial_role_permissionDTO.setHierarchicalRoleID(roleid);
			PList.add(hierarchial_role_permissionDTO);

		}
		role.setHierarchicalRoleID(roleid);
		role.setDescription(roleDesc);
		String msg = HroleD.add(role);
		String msg1 = HroleD.add1(PList);
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
				String newstmt = "Delete from Hierarchical_Role_Permission where HierarchicalPermissionID='"
						+ permission2[i]
						+ "' and HierarchicalRoleid='"
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
			
			RequestDispatcher rd = request.getRequestDispatcher("EditHierarchy.jsp");
			String message = "Hierarchy Edition Saved";
			request.getSession().setAttribute("message2",message);
			rd.forward(request, response);
		}

	}

}
