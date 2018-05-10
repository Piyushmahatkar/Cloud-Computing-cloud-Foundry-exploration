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

import com.DAO.HPermissionDAO;
import com.DAO.PPermissionDAO;
import com.DTO.HierarchicalPermissionDTO;
import com.DTO.PackagePermissionDTO;

/**
 * Servlet implementation class PPermedit
 */
@WebServlet("/PPermedit")
public class PPermedit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PPermedit() {
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
		HttpSession session = request.getSession();
		PPermissionDAO r = new PPermissionDAO();
		PackagePermissionDTO package_permission_DTO = new PackagePermissionDTO();
		List<PackagePermissionDTO> permissionDescriptionList = new ArrayList<PackagePermissionDTO>();
		permissionDescriptionList = r.findAll();
		session.setAttribute("Permission", permissionDescriptionList);
		RequestDispatcher rd = request.getRequestDispatcher("PPermissionedit.jsp");
		rd.forward(request, response);
	}

}
