package DAO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import DTO.UserDTO;
import Entity.Robots;
import Entity.Users;

public class RegistrationDAO {

	private static final SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	static {

		try {
			System.out.println("Inside Constructor");
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.xml");
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		} catch (Throwable th) {

			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);

		}

	}

	

	public String createUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		System.out.println("Inside Create User");
		String message = "";
		Session session = sessionFactory.openSession();
		List<UserDTO> userList = new LoginDAO().validateUser(userDTO);
		//System.out.println("userList size:"+userList.get(0));
		if(!userList.isEmpty())
		{
			message = "User already exists";
		}
		else
		{
			session.beginTransaction();
			Users user = new Users();
			user.setAccountStatus(userDTO.isAccountStatus());
			user.setCreatedDate(userDTO.getCreatedDate());
			user.setLastSignOnTime(userDTO.getLastSignOnTime());
			user.setPassWord(userDTO.getPassWord());
			user.setUpdatedDate(userDTO.getUpdatedDate());
			user.setUserName(userDTO.getUserName());
			session.save(user);
			session.getTransaction().commit();
			session.close();
			message = "User created Successfully"+":"+user.getUserId();
		}
		return message;
	}
}
