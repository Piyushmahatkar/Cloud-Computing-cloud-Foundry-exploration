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

import com.DAO.EditRoleDAO;
import com.DAO.HPermissionDAO;
import com.DTO.HierarchicalPermissionDTO;
import com.DTO.Package_Role_PermissionDTO;

/**
 * Servlet implementation class HPermEdit1
 */
@WebServlet("/HPermEdit1")
public class HPermEdit1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HPermEdit1() {
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
		HPermissionDAO edit = new HPermissionDAO ();
		HierarchicalPermissionDTO List1 = new HierarchicalPermissionDTO();
		String perm = request.getParameter("pemission");
		perm=perm.trim();
		System.out.println("Permission is perm"+perm+"Hello");
		List1.setHierarchicalPermissionID(perm);
		List<HierarchicalPermissionDTO> PermissionIDList;
		PermissionIDList = edit.findAll1(List1);
		PrintWriter out = response.getWriter();
		
		System.out.println("permissionIdList:"+PermissionIDList.size());
		for(int i=0; i< PermissionIDList.size() ;i++)
		{
			int j=i+1;
			System.out.println("value of permission "+j+ " "+PermissionIDList.get(i).getServicename());
		}
		session.setAttribute("PermissionId", PermissionIDList);
		if(PermissionIDList.size() == 0)
		{
			out.println(
					
					"<option selected = 'selected' disabled='disabled'>No service name</option>"
					);

		}
		else
		{
			out.println(
			"<option selected = 'selected' disabled='disabled'>Select service name</option>"
		);
		for(int i=0; i<PermissionIDList.size();i++)
		{
			out.println(
				
			"<option value='" +  PermissionIDList.get(i).getServicename() + "'>" +  PermissionIDList.get(i).getServicename() + "</option>"
			);
		}
		}
	}	
	
	
}
