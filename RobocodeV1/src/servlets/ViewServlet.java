package servlets;

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

import DTO.RobotDTO;
import DTO.UserDTO;
import Service.GetRobotRestClientService;
/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewServlet	() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String userDTO = session.getAttribute("userx").toString();
		RobotDTO robotDTO  = new RobotDTO();
		String selectedItem = null;
		GetRobotRestClientService getRobotListService = new GetRobotRestClientService();
		if (request.getParameter("domain_name") != null) {
			selectedItem = request.getParameter("domain_name");
		}
		
		robotDTO.setPackageId(selectedItem);
		  
		
		if(userDTO != null){
			robotDTO.setUserId(userDTO);
			session.setAttribute("LoadRobotobj", robotDTO);
		}
		List<RobotDTO> robot_DTO = getRobotListService.getRobotList(robotDTO);
		System.out.println("RobotListSize:"+robot_DTO.size());
		if(robot_DTO.size()!=0)
		{
			session.setAttribute("robotList", robot_DTO);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("View_Robot2.jsp");
			requestDispatcher.forward(request, response);
		}
		
	}
}
