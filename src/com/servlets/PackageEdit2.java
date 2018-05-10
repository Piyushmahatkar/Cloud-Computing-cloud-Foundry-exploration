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
import com.DAO.PackageRoleDAO;
import com.DTO.HRoleTableDTO;
import com.DTO.Hierarchical_Role_PermissionDTO;
import com.DTO.PackagePermissionDTO;
import com.DTO.PackageRoleDTO;
import com.DTO.PackageTableDTO;
import com.DTO.Package_Role_PermissionDTO;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class HierarchyEdit2
 */
@WebServlet("/PackageEdit2")
public class PackageEdit2 extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PackageEdit2() {
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
		PackageRoleDAO HroleD = new PackageRoleDAO();
		PackageRoleDTO role = new PackageRoleDTO();
		Package_Role_PermissionDTO role1 = new Package_Role_PermissionDTO();
		String roleDesc = request.getParameter("Hroledesc");
		String roleid = request.getParameter("Ppermissionid");
		String rowCount = request.getParameter("rowcount");
		System.out.println("Row Count:" + rowCount);
		Integer ifor = Integer.parseInt(rowCount);
		List<Package_Role_PermissionDTO> PList = new ArrayList<Package_Role_PermissionDTO>();
		String permission = null;
		for (int i = 0; i < ifor; i++) {
			Package_Role_PermissionDTO package_role_permissionDTO = new Package_Role_PermissionDTO();
			permission = request.getParameter("select" + i);
			package_role_permissionDTO
					.setPackagePermissionID1(permission);
			package_role_permissionDTO.setPackageRoleID(roleid);
			PList.add(package_role_permissionDTO);

		}
		role.setPackageRoleId(roleid);
		role.setRoleDescription(roleDesc);
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
				String newstmt = "Delete from Package_Role_Permission where PackagePermissionID='"
						+ permission2[i]
						+ "' and PackageRoleid='"
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
			RequestDispatcher rd = request.getRequestDispatcher("EditPackage.jsp");
			String message = "Package Edition Saved";
			request.getSession().setAttribute("message3",message);
			rd.forward(request, response);
		}

	}

}
