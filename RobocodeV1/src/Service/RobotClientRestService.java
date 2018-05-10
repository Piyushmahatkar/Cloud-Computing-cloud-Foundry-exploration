package Service;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import DTO.RobotDTO;

public class RobotClientRestService {

	private final String uri = "http://localhost:8080/RobocodeV1/rest/login/getRobotforBattle";
	
	public RobotDTO getRobotForBattle(String robotName) {
		// TODO Auto-generated method stub
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		
		Client client = Client.create(clientConfig);
				
		WebResource webResource = client.resource(uri);
		
		
		ObjectMapper mapper = new ObjectMapper();
		ClientResponse clientResponse = null;
		try {
			clientResponse = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,robotName);
			//System.out.println("response:"+clientResponse);
		}  catch (UniformInterfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		
		RobotDTO robotList = clientResponse.getEntity(RobotDTO.class);
		try {
			System.out.println("values are "+mapper.writeValueAsString(robotList));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return robotList;
		
		
		
	}

	
	
}
