package Service;

import DAO.SaveRobotDAO;
import DTO.RobotDTO;

public class SaveRobotService {

	

	public String saveRobot(RobotDTO robotDTO) {
		// TODO Auto-generated method stub
		SaveRobotDAO saveRobotDAO = new SaveRobotDAO();
		String message = saveRobotDAO.saveRobot(robotDTO);
		return message;
	}
	
}
