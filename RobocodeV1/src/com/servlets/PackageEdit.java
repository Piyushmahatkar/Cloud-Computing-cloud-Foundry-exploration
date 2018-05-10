package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.HierarchicalRoleDAO;
import com.DAO.PackageRoleDAO;
import com.DTO.HierarchicalPermissionDTO;
import com.DTO.Hierarchical_Role_PermissionDTO;
import com.DTO.PackagePermissionDTO;
import com.DTO.Package_Role_PermissionDTO;

public class PackageEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	    public PackageEdit() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		System.out.println("Edit Package");
		HttpSession session = req.getSession();
		PackageRoleDAO r = new PackageRoleDAO();
		PackagePermissionDTO role = new PackagePermissionDTO();
		List<PackagePermissionDTO> permissionDescriptionList1 = new ArrayList<PackagePermissionDTO>();
		permissionDescriptionList1 = r.findAll();
		session.setAttribute("permissionDescription1", permissionDescriptionList1);
		List<Package_Role_PermissionDTO> permissionDescriptionList = new ArrayList<Package_Role_PermissionDTO>();
		permissionDescriptionList = r.findAll1();
		session.setAttribute("permissionDescription", permissionDescriptionList);
		RequestDispatcher rd = req.getRequestDispatcher("EditPackage.jsp");
		rd.forward(req, resp);
		
	}

}
