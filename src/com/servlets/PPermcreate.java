package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.PPermissionDAO;
import com.DTO.PackagePermissionDTO;

/**
 * Servlet implementation class PPermcreate
 */
@WebServlet("/PPermcreate")
public class PPermcreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PPermcreate() {
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
		PPermissionDAO packperm = new PPermissionDAO();
		PackagePermissionDTO packpermDTO = new PackagePermissionDTO();
		String PPermissionID = request.getParameter("PackagePermissionID");
		String Description = request.getParameter("Description");
		String PackageID = request.getParameter("PackageID");
		String RoleRights = request.getParameter("RoleRights");
		int rolerights = Integer.parseInt(RoleRights);
		String TenantID = request.getParameter("TenantID"); 
		packpermDTO.setPackagePermissionID(PPermissionID);
		packpermDTO.setDescription(Description);
		packpermDTO.setPackageID(PackageID);
		packpermDTO.setRoleRights(rolerights);
		packpermDTO.setTenantID(TenantID);
		String msg = packperm.add(packpermDTO);
		RequestDispatcher rd = request.getRequestDispatcher("PPermissionCreate.jsp");
		System.out.println("Message"+msg);
		String message = "Package Permission Saved";
		request.getSession().setAttribute("message7",message);
		rd.forward(request, response);
		
	}

}
