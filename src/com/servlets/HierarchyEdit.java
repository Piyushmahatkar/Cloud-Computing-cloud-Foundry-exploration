package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.DAO.HierarchicalRoleDAO;
import com.DTO.HierarchicalPermissionDTO;
import com.DTO.Hierarchical_Role_PermissionDTO;


/**
 * Servlet implementation class HierarchyEdit
 */
@WebServlet("/HierarchyEdit")
public class HierarchyEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HierarchyEdit() {
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
		System.out.println("Edit Hierarchy");
		HttpSession session = request.getSession();
		HierarchicalRoleDAO r = new HierarchicalRoleDAO();
		HierarchicalPermissionDTO role = new HierarchicalPermissionDTO();
		List<HierarchicalPermissionDTO> permissionDescriptionList1 = new ArrayList<HierarchicalPermissionDTO>();
		permissionDescriptionList1 = r.findAll();
		session.setAttribute("permissionDescription1", permissionDescriptionList1);
		List<Hierarchical_Role_PermissionDTO> permissionDescriptionList = new ArrayList<Hierarchical_Role_PermissionDTO>();
		permissionDescriptionList = r.findAll1();
		session.setAttribute("permissionDescription", permissionDescriptionList);
		RequestDispatcher rd = request.getRequestDispatcher("EditHierarchy.jsp");
		rd.forward(request, response);
		
	
	}
}
