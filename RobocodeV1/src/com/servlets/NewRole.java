package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.CreateRoleDAO;
import com.DTO.Hierarchical_Role_PermissionDTO;
import com.DTO.PackageRoleDTO;
import com.DTO.Package_Role_PermissionDTO;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class NewRole
 */
@WebServlet("/NewRole")
public class NewRole extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Method to handle POST method request.
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CreateRoleDAO r = new CreateRoleDAO();
		PackageRoleDTO role = new PackageRoleDTO();
		String check = request.getParameter("roledesc");
		String roleid = request.getParameter("roleid");
		String rowCount = request.getParameter("rowcount");
		System.out.println("Row Count:" + rowCount);
		Integer ifor = Integer.parseInt(rowCount);
	

		String permission = null;
		for (int i = 0; i < ifor; i++) {
			permission = request.getParameter("select" + i);

			try {
				Connection connection = DriverManager
						.getConnection("jdbc:mysql://robocodedb.cloudapp.net:3306/Role?user=naren&password=naren");
				
				Statement statement = (Statement) connection.createStatement();
				String newstmt = "INSERT INTO Package_Role_Permission(PackageRoleId, PackagePermissionID) VALUES ('"
						+ roleid + "','" + permission + "')";
				System.out.println(newstmt);
				int resultSet = statement.executeUpdate(newstmt);
				
			} catch (Exception e) {
				System.out.println("wrong entry" + e);
				// out.println("wrong entry" + e);
			}
		}
		role.setPackageRoleId(roleid);
		role.setRoleDescription(check);

		String msg = r.add(role);
		// String msg1 = r.add1(PList);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (msg.equalsIgnoreCase("RoleID")) {
			out.print("<pre style=\"color:red\">Sorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already existsSorry Role ID already exists</pre>");
			RequestDispatcher rd = request
					.getRequestDispatcher("CreateRole.jsp");
			request.setAttribute("message", msg);
			// request.setAttribute("message1", msg1);
			rd.forward(request, response);
		} else {
			String message = "Package Role Saved";
			request.getSession().setAttribute("message",message);
			RequestDispatcher rd = request.getRequestDispatcher("CreateRole.jsp");
			request.setAttribute("message1", msg);
			rd.forward(request, response);
		}
		/*
		 * } else {
		 * out.print("<p style=\"color:red\">Sorry username or password error</p>"
		 * ); RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		 * rd.include(request, response);
		 */
	}
}
