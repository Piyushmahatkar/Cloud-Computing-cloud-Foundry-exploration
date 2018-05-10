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

import com.DAO.MapRoleDAO;
import com.DAO.PackageRoleDAO;
import com.DTO.PackagePermissionDTO;
import com.DTO.Package_Role_PermissionDTO;
import com.DTO.RoleDTO;
import com.DTO.User_Hier_RoleDTO;

public class Maph extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
    public Maph() {
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
	System.out.println("Map H Role");
	HttpSession session = req.getSession();
	MapRoleDAO r = new MapRoleDAO();
	RoleDTO role = new RoleDTO();
	List<RoleDTO> permissionDescriptionList1 = new ArrayList<RoleDTO>();
	permissionDescriptionList1 = r.findAll();
	session.setAttribute("permissionDescription1", permissionDescriptionList1);
	List<User_Hier_RoleDTO> permissionDescriptionList = new ArrayList<User_Hier_RoleDTO>();
	permissionDescriptionList = r.findAll1();
	session.setAttribute("permissionDescription", permissionDescriptionList);
	RequestDispatcher rd = req.getRequestDispatcher("MapHRole.jsp");
	rd.forward(req, resp);
	
}
	
}
