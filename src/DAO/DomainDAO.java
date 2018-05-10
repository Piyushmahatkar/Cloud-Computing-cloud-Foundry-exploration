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

import DTO.DomainDTO;
import DTO.RobotDTO;
import DTO.UserDTO;
import Entity.Domain;
import Entity.Robots;
import Entity.Users;

public class DomainDAO {

	private static final SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	static {

		try {

			Configuration configuration = new Configuration();
			configuration.configure("hibernate.xml");
			serviceRegistry = new ServiceRegistryBuilder().applySettings(
					configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		} catch (Throwable th) {

			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);

		}

	}

	public String createDomain(DomainDTO domainDTO) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Domain domain = new Domain();
		domain.setDomainName(domainDTO.getDomainName());
		domain.setCreatedDate(new Date());
		session.save(domain);
		session.getTransaction().commit();
		session.close();
		String message = "Domain created Successfully";
		return message;
	}

	/*public List<DomainDTO> getRobotList(DomainDTO domainDTO) {

		Session session = sessionFactory.openSession();
		System.out.println("Robot Details:" + robotDTO.getUserId()
				+ " with domainId:" + robotDTO.getDomainId());
		String robotListString = " from Robots r where r.UserId = ? and r.DomainId = ?";
		Query robotListQuery = (Query) session.createQuery(robotListString);
		robotListQuery.setParameter(0, robotDTO.getUserId());
		robotListQuery.setParameter(1, robotDTO.getDomainId());
		final List<RobotDTO> robotList = new LinkedList<RobotDTO>();
		Robots robot = null;
		for (int counter = 0; counter < robotListQuery.list().size(); counter++) {
			robot = (Robots) robotListQuery.list().get(counter);
			RobotDTO robot_DTO = new RobotDTO();
			robot_DTO.setRobotDescription(robot.getRobotDescription());
			robotList.add(robot_DTO);

		}

		return robotList;

	}*/
}
