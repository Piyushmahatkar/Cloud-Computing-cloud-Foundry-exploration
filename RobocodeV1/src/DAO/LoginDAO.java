package DAO;
import java.util.LinkedList;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import DTO.UserDTO;
import Entity.Users;
  
public class LoginDAO {  

	private static final SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	
	 static {

         try {

                 Configuration configuration = new Configuration();
                 configuration.configure("hibernate.xml");
                 serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
                 sessionFactory = configuration.buildSessionFactory(serviceRegistry);

         } 
         catch (Throwable th) {

                 System.err.println("Enitial SessionFactory creation failed" + th);
                 throw new ExceptionInInitializerError(th);

         }

	 }


	public List<UserDTO> validateUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		Users user = null;
		Session session = sessionFactory.openSession();
		String userSearch = " from Users u where u.userName=? and u.passWord=?";
		Query userQuery =  (Query) session.createQuery(userSearch);
		userQuery.setParameter(0, userDTO.getUserName());
		userQuery.setParameter(1,userDTO.getPassWord());
		//List<UserDTO> userList =  userQuery.list();
		
		final List<UserDTO> userList = new LinkedList<UserDTO>();
//		final List<Users> usersList = userQuery.list();
		for(int i=0; i<userQuery.list().size();i++) {
			 user =  (Users) userQuery.list().get(i);
			 UserDTO user_DTO = new UserDTO();
			 user_DTO.setUserName(user.getUserName());
			 user_DTO.setUserId(user.getUserId());
			 user_DTO.setPassWord(user.getPassWord());
			 user_DTO.setCreatedDate(user.getCreatedDate());
			 user_DTO.setAccountStatus(user.isAccountStatus());
			 user_DTO.setLastSignOnTime(user.getLastSignOnTime());
			 user_DTO.setUpdatedDate(user.getUpdatedDate());
			 
			 userList.add(user_DTO);
		}
		
		return userList;
		
	}  
}  
