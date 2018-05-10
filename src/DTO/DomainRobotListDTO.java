package DTO;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DomainRobotListDTO implements Serializable{
	
	private List<RobotDTO> robotList;
	private String PackageId;
	public List<RobotDTO> getRobotList() {
		return robotList;
	}
	public void setRobotList(List<RobotDTO> robotList) {
		this.robotList = robotList;
	}
	public String getPackageId() {
		return PackageId;
	}
	public void setPackageId(String packageId) {
		PackageId = packageId;
	}
	
	
	
	

}
