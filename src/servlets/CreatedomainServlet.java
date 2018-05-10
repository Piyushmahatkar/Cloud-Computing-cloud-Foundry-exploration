package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import DAO.RegistrationDAO;
import DTO.DomainDTO;
import DTO.UserDTO;
import Service.CreateDomainRestClientService;
import Service.LoginRestClientService;
import Service.RegistrationRestClientService;
import Service.SaveRobotRestClientService;

public class CreatedomainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreatedomainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String domainName = request.getParameter("domainName");
		
		DomainDTO domainDTO = new DomainDTO();
		domainDTO.setDomainName(domainName);
		CreateDomainRestClientService createdomainRestClientService = new CreateDomainRestClientService();
		String message1 = createdomainRestClientService.createdomain(domainDTO);

		if (message1.contains("Domain created Successfully")) {			
			domainDTO.setCreatedDate(new Date());
			HttpSession session = request.getSession();
			UserDTO userDTO = (UserDTO) session.getAttribute("userx");
			RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
			rd.include(request, response);
		} else if (message1.equalsIgnoreCase("Domain already exists")) {
			request.setAttribute("message", message1);
			RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
			rd.include(request, response);
		}
	}
}