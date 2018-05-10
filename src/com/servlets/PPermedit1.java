package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class PPermedit1
 */
@WebServlet("/PPermedit1")
public class PPermedit1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PPermedit1() {
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
		PPermissionDAO edit = new PPermissionDAO ();
		PackagePermissionDTO List1 = new PackagePermissionDTO();
		String perm = request.getParameter("pemission");
		perm=perm.trim();
		System.out.println("Permission is perm"+perm+"Hello");
		List1.setPackagePermissionID(perm);
		List<PackagePermissionDTO> PermissionIDList;
		PermissionIDList = edit.findAll1(List1);
		//PrintWriter out = response.getWriter();
		
		System.out.println("permissionIdList:"+PermissionIDList.size());
		
		String Description= PermissionIDList.get(0).getDescription();
		String PackageID = PermissionIDList.get(0).getPackageID();
		int RoleRights = PermissionIDList.get(0).getRoleRights();
		String TenantID = PermissionIDList.get(0).getTenantID();
		session.setAttribute("PackagePermissionID", perm);
		session.setAttribute("Description", Description);
		session.setAttribute("PackageID", PackageID);
		session.setAttribute("RoleRights", RoleRights);
		session.setAttribute("TenantID", TenantID);
		session.setAttribute("permission", PermissionIDList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("PPermissionedit1.jsp");
		requestDispatcher.forward(request, response);
		
	}	

	}


