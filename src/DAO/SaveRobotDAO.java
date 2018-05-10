package DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;








import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import DTO.RobotDTO;
import DTO.UserDTO;
import Entity.Robots;
import Entity.Users;

public class SaveRobotDAO {

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
	
	public String saveRobot(RobotDTO robotDTO) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Robots robot = new Robots();
		robot.setRobotName(robotDTO.getRobotName());
		robot.setTenantId(robotDTO.getTenantId());
		robot.setPackageId(robotDTO.getPackageId());
		robot.setRobotCode(robotDTO.getRobotCode());
		robot.setUserId(robotDTO.getUserId());
		robot.setRobotDescription(robotDTO.getRobotDescription());
		robot.setFilePath(robotDTO.getFilePath());
		robot.setRobotRanking(0);
		
		session.save(robot);
		session.getTransaction().commit();
		session.close();
		String message = "Robot Saved";
		return message;
	}
	
	
	public List<RobotDTO> getRobotList(RobotDTO robotDTO){
		
		Session session = sessionFactory.openSession();
		System.out.println("Robot Details:"+robotDTO.getUserId()+" with domainId:"+robotDTO.getPackageId());
		String robotListString = " from Robots r where r.UserId = ? and r.PackageId = ?";
		Query robotListQuery = (Query) session.createQuery(robotListString);
		robotListQuery.setParameter(0, robotDTO.getUserId());
		robotListQuery.setParameter(1, robotDTO.getPackageId());
		final List<RobotDTO> robotList = new LinkedList<RobotDTO>();
		Robots robot = null;
		for(int counter=0;counter<robotListQuery.list().size();counter++)
		{
			robot = (Robots) robotListQuery.list().get(counter);
			RobotDTO robot_DTO = new RobotDTO();
			robot_DTO.setRobotDescription(robot.getRobotDescription());
			robotList.add(robot_DTO);
			
		}
			
		return robotList;
		
	}


	public RobotDTO getRobocode(RobotDTO robotDTO) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		System.out.println("Robot Details:"+robotDTO.getUserId()+" with domainId:"+robotDTO.getPackageId()+" with description "+robotDTO.getRobotDescription());
		String robotListString = " from Robots r where r.UserId = ? and r.PackageId = ? and r.RobotDescription=?";
		Query robotListQuery = (Query) session.createQuery(robotListString);
		robotListQuery.setParameter(0, robotDTO.getUserId());
		robotListQuery.setParameter(1, robotDTO.getPackageId());
		robotListQuery.setParameter(2, robotDTO.getRobotDescription());
		RobotDTO robotList = new RobotDTO();
		Robots robot = (Robots)robotListQuery.list().get(0);
		robotList.setRobotCode(robot.getRobotCode());
		return robotList;
	}


	public String updateRobot(RobotDTO robotDTO) throws IOException {
		// TODO Auto-generated method stub
		String message = "Successfully updated";
		/*Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String robotUpdateString = "Update robot r set r.RobotCode=? where r.packageID = ?";
		Query robotUpdateQuery = session.createQuery(robotUpdateString);
		System.out.println("the robotDTO.getRobotCode()" + robotDTO.getRobotCode());
		System.out.println("the robotDTO.getPackageId()" + robotDTO.getPackageId());
	//	System.out.println("the robotDTO.getRobotDescription()" + robotDTO.getRobotDescription());
		robotUpdateQuery.setParameter(0, robotDTO.getRobotCode());
		robotUpdateQuery.setParameter(1, robotDTO.getPackageId());
	//	robotUpdateQuery.setParameter(2, robotDTO.getRobotDescription());
		int result = robotUpdateQuery.executeUpdate();
		System.out.println(result+" rows updated....");
		session.getTransaction().commit();*/
		BufferedWriter output = null;
		try {                
		    File file = new File(robotDTO.getFilePath());

		    //Providing true for second argument specifies it should be appended. 
		    output = new BufferedWriter(new FileWriter(file,false));    
		    output.write(robotDTO.getRobotCode());      

		} catch ( IOException e ) {
		       e.printStackTrace();
		}
		if(output!=null)
		  output.close();
		
		return message;
	}
	
	public RobotDTO getRoboCodeforBattle(String robotName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		//System.out.println("RobotName:"+robotName);
		String robotListString = " from Robots r where r.RobotName=?";
		Query robotListQuery = (Query) session.createQuery(robotListString);
		robotListQuery.setParameter(0, robotName);
		//System.out.println(robotListQuery.list().size());
		RobotDTO robotList = new RobotDTO();
		Robots robot = (Robots)robotListQuery.list().get(0);
		//System.out.println(robot.getRobotDescription());
		robotList.setRobotDescription(robot.getRobotDescription());
		robotList.setRobotCode(robot.getRobotCode());
		robotList.setPackageId(robot.getPackageId());
		robotList.setTenantId(robot.getPackageId());
		robotList.setRobotName(robot.getRobotName());
		robotList.setRobotRanking(robot.getRobotRanking());
		robotList.setUserId(robot.getUserId());
		return robotList;
		
	}
	

}
