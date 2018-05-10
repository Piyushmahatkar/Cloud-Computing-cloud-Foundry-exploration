package Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import DTO.UserDTO;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;




public class RegistrationRestClientService {
	
	private final String uri = "http://localhost:8080/Cloud_Project_OLD/rest/login/registerUser";

	public String registerUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		//System.out.println("Reached inside RegistrationRestClientService registerUser");
		ClientConfig clientConfig = new DefaultClientConfig();
		//System.out.println("Reached inside RegistrationRestClientService registerUser -- client config object creation");
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		
		Client client = Client.create(clientConfig);
		//System.out.println("Client object creation");	
		WebResource webResource = client.resource(uri);
		//System.out.println("web resources object creation");
		
		ObjectMapper mapper = new ObjectMapper();
		//System.out.println("Object mapper");
		ClientResponse clientResponse = null;
		try {
			System.out.println("Before Client Response");
			clientResponse = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,mapper.writeValueAsString(userDTO));
			
			System.out.println("response:"+clientResponse);
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
		 
		
		String message = clientResponse.getEntity(String.class);
		return message;
	}

	
	
	
	
}
