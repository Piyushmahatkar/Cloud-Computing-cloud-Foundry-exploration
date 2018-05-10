package DTO;

import java.io.File;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RobotDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer RobotId;
	private String RobotName;
	private String RobotCode;
	private String PackageId;
	private String UserId;
	private String TenantId;
	private File file;
	private String filePath;
	private byte[] compiledCode;
	public RobotDTO(){}
	public RobotDTO(String robotName,String packageName,byte[] compiledCode){
		this.RobotName=robotName;
		this.PackageId=packageName;
		this.compiledCode=compiledCode;
	}
	public byte[] getCompiledCode(){return this.compiledCode;}
	public String getTenantId() {
		return TenantId;
	}
	public void setTenantId(String tenantId) {
		TenantId = tenantId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String f) {
		filePath = f;
	}
	private String RobotDescription;
	private Integer RobotRanking;
	private String CreatedDate;
	private String UpdatedDate;
	public Integer getRobotId() {
		return RobotId;
	}
	public void setRobotId(Integer robotId) {
		RobotId = robotId;
	}
	public String getRobotName() {
		return RobotName;
	}
	public void setFile(File c) {
		file = c;
	}
	public File getFile() {
		return file;
	}
	public void setRobotName(String robotName) {
		RobotName = robotName;
	}
	public String getRobotCode() {
		return RobotCode;
	}
	public void setRobotCode(String robotCode) {
		RobotCode = robotCode;
	}
	
	public String getPackageId() {
		return PackageId;
	}
	public void setPackageId(String packageId) {
		PackageId = packageId;
	}
	
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getRobotDescription() {
		return RobotDescription;
	}
	public void setRobotDescription(String robotDescription) {
		RobotDescription = robotDescription;
	}
	public Integer getRobotRanking() {
		return RobotRanking;
	}
	public void setRobotRanking(Integer robotRanking) {
		RobotRanking = robotRanking;
	}
	public String getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}
	public String getUpdatedDate() {
		return UpdatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		UpdatedDate = updatedDate;
	}

	
}
