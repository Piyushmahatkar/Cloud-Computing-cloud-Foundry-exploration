package Entity;


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
@Table(name="users")
public class Users {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="UserId")
private Integer userId;

@Column(name="UserName")
private String userName;
	
@Column(name="Password")
private String passWord;


@Column(name="CreatedDate")
@Temporal(TemporalType.DATE)
private Date createdDate;

@Column(name="UpdatedDate")
@Temporal(TemporalType.DATE)
private Date updatedDate;


@Column(name="LastSignOnTime")
@Temporal(TemporalType.DATE)
private Date lastSignOnTime;

@Column(name="AccountStatus",nullable=false,columnDefinition="BIT",length=1)
private boolean accountStatus;

public Integer getUserId() {
	return userId;
}

public void setUserId(Integer userId) {
	this.userId = userId;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassWord() {
	return passWord;
}

public void setPassWord(String passWord) {
	this.passWord = passWord;
}

public Date getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}

public Date getUpdatedDate() {
	return updatedDate;
}

public void setUpdatedDate(Date updatedDate) {
	this.updatedDate = updatedDate;
}

public Date getLastSignOnTime() {
	return lastSignOnTime;
}

public void setLastSignOnTime(Date lastSignOnTime) {
	this.lastSignOnTime = lastSignOnTime;
}

public boolean isAccountStatus() {
	return accountStatus;
}

public void setAccountStatus(boolean accountStatus) {
	this.accountStatus = accountStatus;
}




}
