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
import com.DTO.HierarchicalPermissionDTO;
import com.DTO.Package_Role_PermissionDTO;

/**
 * Servlet implementation class HPermcreate
 */
@WebServlet("/HPermcreate")
public class HPermcreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HPermcreate() {
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
		HPermissionDAO permissionDAO = new HPermissionDAO();
		String HPermissionID = request.getParameter("Hpermissionid");
		String rowCount = request.getParameter("rowcount");
		System.out.println("Row Count:"+rowCount);
		Integer ifor = Integer.parseInt(rowCount);
		List<HierarchicalPermissionDTO> PList = new ArrayList<HierarchicalPermissionDTO>();
		
		String permission = null;
		for(int i=0;i<ifor;i++){
			
			HierarchicalPermissionDTO hierarchical_permission_DTO = new HierarchicalPermissionDTO();
			permission = request.getParameter("input"+i);
			hierarchical_permission_DTO.setHierarchicalPermissionID(HPermissionID);
			hierarchical_permission_DTO.setServicename(permission);
			PList.add(hierarchical_permission_DTO);		
		}
		String msg = permissionDAO.add1(PList);
		RequestDispatcher rd = request.getRequestDispatcher("HPermission.jsp");
		String message = "Hierarchy Permission Created";
		request.getSession().setAttribute("message5",message);
		rd.forward(request, response);
	}

}
