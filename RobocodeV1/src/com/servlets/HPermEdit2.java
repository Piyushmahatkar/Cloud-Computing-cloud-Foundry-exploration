package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.HPermissionDAO;
import com.DTO.HierarchicalPermissionDTO;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class HPermEdit2
 */
@WebServlet("/HPermEdit2")
public class HPermEdit2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HPermEdit2() {
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
		String permission1 = request.getParameter("Hpermissionid");
		String[] permission2 = request.getParameterValues("HRoleNames");
		if (permission2 != null)
		{
		for(int i =0; i<permission2.length;i++){
			try {
				Connection connection = DriverManager
						.getConnection("jdbc:mysql://robocodedb.cloudapp.net:3306/Role?user=naren&password=naren");

				Statement statement = (Statement) connection.createStatement();
				String newstmt = "Delete from HierarchicalPermission where servicename='"+permission2[i]+"' and HierarchicalPermissionID='"+permission1+"'";
	System.out.println(newstmt);
				int resultSet = statement.executeUpdate(newstmt);
				
						 
		}
		catch (Exception e) {
			System.out.println("wrong entry" + e);
			//out.println("wrong entry" + e);
		}
		}
		}
		String msg = permissionDAO.add1(PList);
		RequestDispatcher rd = request.getRequestDispatcher("HPermissionedit.jsp");
		String message = "Hierarchy Permission Edition Saved";
		request.getSession().setAttribute("message6",message);
		rd.forward(request, response);
	}

}