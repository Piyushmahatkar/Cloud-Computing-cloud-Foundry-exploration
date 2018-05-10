package ServerService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import DAO.DomainDAO;
import DAO.LoginDAO;
import DAO.RegistrationDAO;
import DAO.SaveRobotDAO;
import DTO.DomainDTO;
import DTO.RobotDTO;
import DTO.UserDTO;

@Path("/login")
public class RestServerService {

	@POST
	@Path("/user")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response validateLogin(UserDTO userDTO)
			throws JsonGenerationException, JsonMappingException, IOException {

		LoginDAO loginDAO = new LoginDAO();

		List<UserDTO> user_DTO = loginDAO.validateUser(userDTO);

		ObjectMapper mapper = new ObjectMapper();
		System.out.println("testing:" + mapper.writeValueAsString(userDTO));

		return Response.status(201).entity(mapper.writeValueAsString(user_DTO))
				.build();
		// return user_DTO;
	}

	@POST
	@Path("/saveRobot")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response saveRobot(RobotDTO robotDTO)
			throws JsonGenerationException, JsonMappingException, IOException {

		SaveRobotDAO saveRobotDAO = new SaveRobotDAO();
		String message = saveRobotDAO.saveRobot(robotDTO);
		return Response.status(201).entity(message).build();
		// return user_DTO;
	}

	@POST
	@Path("/listRobots")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRobotList(RobotDTO robotDTO)
			throws JsonGenerationException, JsonMappingException, IOException {

		SaveRobotDAO saveRobotDAO = new SaveRobotDAO();
		List<RobotDTO> robotList = saveRobotDAO.getRobotList(robotDTO);
		ObjectMapper mapper = new ObjectMapper();

		return Response.status(201)
				.entity(mapper.writeValueAsString(robotList)).build();
		// return user_DTO;
	}

	@POST
	@Path("/getRobocode")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRobocode(RobotDTO robotDTO)
			throws JsonGenerationException, JsonMappingException, IOException {

		SaveRobotDAO saveRobotDAO = new SaveRobotDAO();
		RobotDTO robot_DTO = saveRobotDAO.getRobocode(robotDTO);
		ObjectMapper mapper = new ObjectMapper();

		return Response.status(201)
				.entity(mapper.writeValueAsString(robot_DTO)).build();
		// return user_DTO;
	}

	@POST
	@Path("/updateRobot")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateRobot(RobotDTO robotDTO)
			throws JsonGenerationException, JsonMappingException, IOException {

		SaveRobotDAO saveRobotDAO = new SaveRobotDAO();
		String message = saveRobotDAO.updateRobot(robotDTO);
		return Response.status(201).entity(message).build();
		// return user_DTO;
	}

	@POST
	@Path("/registerUser")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response registerUser(UserDTO userDTO)
			throws JsonGenerationException, JsonMappingException, IOException {
System.out.println("Inside the URI of Register User");
		RegistrationDAO registrationDAO = new RegistrationDAO();
		System.out.println("After the creation of RegistrationDAO object ");
		String message = registrationDAO.createUser(userDTO);
		return Response.status(201).entity(message).build();
		// return user_DTO;
	}

	@POST
	@Path("/createdomain")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createdomain(DomainDTO domainDTO)
			throws JsonGenerationException, JsonMappingException, IOException {

		DomainDAO domainD = new DomainDAO();
		String message = domainD.createDomain(domainDTO);
		return Response.status(201).entity(message).build();

	}

	@POST
	@Path("/getRobotforBattle")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRobotForBattle(String RobotName)
			throws JsonGenerationException, JsonMappingException, IOException {

		SaveRobotDAO saveRobotDAO = new SaveRobotDAO();
		RobotDTO robot_DTO = saveRobotDAO.getRoboCodeforBattle(RobotName);
	
		return Response.status(201).entity(robot_DTO).build();

	}
	
	
}
