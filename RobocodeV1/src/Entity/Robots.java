package Entity;

import java.io.File;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="robot")
public class Robots {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Robot_Id")
	private Integer RobotId;
	
	@Column(name="Robot_Name")
	private String RobotName;
	
	@Column(name="RobotCode")
	private String RobotCode;
	
	@Column(name="Package_Id")
	private String PackageId;
	
	@Column(name="User_Id")
	private String UserId;
	
	@Column(name="TenantId")
	private String TenantId;
	
	@Column(name="filePath")
	private String filePath;
	
	@Column(name="file")
	private File codeFile;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String f) {
		filePath = f;
	}
	
	public String getPackageId() {
		return PackageId;
	}

	public void setPackageId(String packageId) {
		PackageId = packageId;
	}
	public void setFile(File c) {
		codeFile = c;
	}
	public File getFile() {
		return codeFile;
	}
	

	public String getTenantId() {
		return TenantId;
	}

	public void setTenantId(String tenantId) {
		TenantId = tenantId;
	}



	@Column(name="Robot_Description")
	private String RobotDescription;
	
	@Column(name="Robot_Ranking")
	private Integer RobotRanking;
	
	@Column(name="Created_Date")
	@Temporal(TemporalType.DATE)
	private Date CreatedDate;
	
	@Column(name="Updated_Date")
	private String UpdatedDate;
	@Column(name="Updated_Time")

	public Integer getRobotId() {
		return RobotId;
	}

	public void setRobotId(Integer robotId) {
		RobotId = robotId;
	}

	public String getRobotName() {
		return RobotName;
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

	public Date getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}

	public String getUpdatedDate() {
		return UpdatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		UpdatedDate = updatedDate;
	}

	
	
	
	
	
	
}
