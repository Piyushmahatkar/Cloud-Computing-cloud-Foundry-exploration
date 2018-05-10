package Service;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import DTO.RobotDTO;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class GetRobotRestClientService {

	private final String uri = "http://localhost:8080/RobocodeV1/rest/login/listRobots";

	public List<RobotDTO> getRobotList(RobotDTO robotDTO) {
		// TODO Auto-generated method stub
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		
		Client client = Client.create(clientConfig);
				
		WebResource webResource = client.resource(uri);
		
		
		ObjectMapper mapper = new ObjectMapper();
		ClientResponse clientResponse = null;
		try {
			clientResponse = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,mapper.writeValueAsString(robotDTO));
			//System.out.println("response:"+clientResponse);
		}  catch (UniformInterfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		
		List<RobotDTO> robotList = clientResponse.getEntity(new GenericType<List<RobotDTO>>() {});
		try {
			System.out.println("values are "+mapper.writeValueAsString(robotList));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return robotList;
	}

	
}
