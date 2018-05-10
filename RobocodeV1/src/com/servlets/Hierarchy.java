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
import com.DAO.HierarchicalRoleDAO;
import com.DTO.HierarchicalPermissionDTO;
import com.DTO.PackagePermissionDTO;

/**
 * Servlet implementation class Hierarchy
 */
@WebServlet("/Hierarchy")
public class Hierarchy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hierarchy() {
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
		HttpSession session = request.getSession();
		HierarchicalRoleDAO r = new HierarchicalRoleDAO();
		HierarchicalPermissionDTO role = new HierarchicalPermissionDTO();
		List<HierarchicalPermissionDTO> permissionDescriptionList = new ArrayList<HierarchicalPermissionDTO>();
		permissionDescriptionList = r.findAll();
		session.setAttribute("permissionDescription", permissionDescriptionList);
		
		RequestDispatcher rd = request.getRequestDispatcher("Hierarcy.jsp");

		rd.forward(request, response);
	}

}
