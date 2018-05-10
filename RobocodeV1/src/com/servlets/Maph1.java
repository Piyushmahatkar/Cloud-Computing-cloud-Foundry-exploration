package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.MapRoleDAO;
import com.DTO.User_Hier_RoleDTO;
/**
 * Servlet implementation class HierarchyEdit1
 */
@WebServlet("/Maph1")
public class Maph1 extends HttpServlet {


	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Maph1() {
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
		MapRoleDAO edit = new MapRoleDAO();
		User_Hier_RoleDTO List1 = new User_Hier_RoleDTO();
		String perm = request.getParameter("pemission");
		perm=perm.trim();
		System.out.println("Permission is perm"+perm+"Hello");
		List1.setUser(perm);
		List<User_Hier_RoleDTO> PermissionIDList;
		PermissionIDList = edit.findAll2(List1);
		PrintWriter out = response.getWriter();
		
		System.out.println("permissionIdList:"+PermissionIDList.size());
		for(int i=0; i< PermissionIDList.size() ;i++)
		{
			int j=i+1;
			System.out.println("value of permission "+j+ " "+PermissionIDList.get(i).getRole());
		}
		session.setAttribute("PermissionId", PermissionIDList);
		if(PermissionIDList.size() == 0)
		{
			out.println(
					
					"<option selected = 'selected' disabled='disabled'>No roles </option>"
					);

		}
		else
		{
			out.println(
			"<option selected = 'selected' disabled='disabled'>Select roles</option>"
		);
		for(int i=0; i<PermissionIDList.size();i++)
		{
			out.println(
				
			"<option value='" +  PermissionIDList.get(i).getRole() + "'>" +  PermissionIDList.get(i).getRole() + "</option>"
			);
		}
		}
	}	

}
