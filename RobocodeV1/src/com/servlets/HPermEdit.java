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

import com.DAO.EditRoleDAO;
import com.DAO.HPermissionDAO;
import com.DTO.HierarchicalPermissionDTO;
import com.DTO.PackageRoleDTO;

/**
 * Servlet implementation class HPermEdit
 */
@WebServlet("/HPermEdit")
public class HPermEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HPermEdit() {
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
		HPermissionDAO r = new HPermissionDAO();
		HierarchicalPermissionDTO hierarchical_permission_DTO = new HierarchicalPermissionDTO();
		List<HierarchicalPermissionDTO> permissionDescriptionList = new ArrayList<HierarchicalPermissionDTO>();
		permissionDescriptionList = r.findAll();
		session.setAttribute("Permission", permissionDescriptionList);
		RequestDispatcher rd = request.getRequestDispatcher("HPermissionedit.jsp");
		rd.forward(request, response);
	}

}
