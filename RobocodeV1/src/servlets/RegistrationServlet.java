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
import DTO.RobotDTO;
import DTO.UserDTO;
import Service.LoginRestClientService;
import Service.RegistrationRestClientService;
import Service.SaveRobotRestClientService;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        System.out.println("Inside Registration Servlet");
        
        // TODO Auto-generated constructor stub
    }

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
/*gets username and password from registration.jsp; sets attributes of userDTO
 * 
 */
		String userName = request.getParameter("username");
		String password = request.getParameter("userpass");
		System.out.println("Uname =="+ userName);
		System.out.println("Pass  =="+ password);
		UserDTO userDTO = new UserDTO();
		System.out.println("1");
		userDTO.setUserName(userName);
		userDTO.setPassWord(password);
		userDTO.setCreatedDate(new Date());
		userDTO.setLastSignOnTime(new Date());
		userDTO.setUpdatedDate(new Date());
		userDTO.setAccountStatus(true);
		
		HttpSession session = request.getSession();
		session.setAttribute("userx", userName);

	System.out.println("Reached till here-->1");
		RegistrationRestClientService registrationRestClientService = new RegistrationRestClientService();
		String message1 = registrationRestClientService.registerUser(userDTO);
		System.out.println("Reached till here");	
		
		
		
		if(message1.contains("User created Successfully")){
			//request.setAttribute("message", message);
			String message = message1.split(":")[0];
			userDTO.setUserId(Integer.parseInt(message1.split(":")[1]));
			session.setAttribute("userDTO", userDTO);
			session.setAttribute("userName", userDTO.getUserName());
			session.setAttribute("UserId", userDTO.getUserId());
			
		RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");    
	     rd.include(request,response);
		}
		else if(message1.equalsIgnoreCase("User already exists"))
		{
			request.setAttribute("message", message1);
			RequestDispatcher rd=request.getRequestDispatcher("Registration.jsp");    
		     rd.include(request,response);
		}
	  	}
}