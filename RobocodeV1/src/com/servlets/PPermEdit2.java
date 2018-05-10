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
 * Servlet implementation class PPermEdit2
 */
@WebServlet("/PPermEdit2")
public class PPermEdit2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PPermEdit2() {
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
		String msg = packperm.update(packpermDTO);
		RequestDispatcher rd = request.getRequestDispatcher("PPermissionedit.jsp");
		String message = "Hierarchy Edition Saved";
		request.getSession().setAttribute("message8",message);
		System.out.println("Message"+msg);
		rd.forward(request, response);
		
	}

}
