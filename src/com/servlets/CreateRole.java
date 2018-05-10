package com.servlets;

import java.io.IOException;
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
import com.DTO.PackagePermissionDTO;
import com.DTO.PackageRoleDTO;

/**
 * Servlet implementation class CreateRole
 */
@WebServlet("/CreateRole")
public class CreateRole extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateRole() {
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
		HttpSession session = request.getSession();
		CreateRoleDAO r = new CreateRoleDAO();
		PackagePermissionDTO role = new PackagePermissionDTO();
		List<PackagePermissionDTO> permissionDescriptionList = new ArrayList<PackagePermissionDTO>();
		permissionDescriptionList = r.findAll();
		for(int i=0; i< permissionDescriptionList.size();i++)
		{
			int j=i+1;
			System.out.println("value of permission "+j+ " "+permissionDescriptionList.get(i).getDescription());
		}
		session.setAttribute("permissionDescription", permissionDescriptionList);
		RequestDispatcher rd = request.getRequestDispatcher("CreateRole.jsp");
		rd.forward(request, response);
	}

}
