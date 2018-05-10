package DTO;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Robot table
 */

@XmlRootElement
public class Robot_DTO implements java.io.Serializable {

	private String robot_id;
	private String robot_name;
	private String robot_desc;
	private String robot_code;

	// Fixing it for http://jira.grails.org/browse/GRAILS-646
	private Integer robot_ranking;
	private Integer created_by;
	private Date created_date;
	private Date updated_date;
	private Integer robot_points;

	public Integer getRobot_points() {
		return robot_points;
	}

	public void setRobot_points(Integer robot_points) {
		this.robot_points = robot_points;
	}

	public String getRobot_id() {
		return robot_id;
	}

	public void setRobot_id(String robot_id) {
		this.robot_id = robot_id;
	}

	public String getRobot_name() {
		return robot_name;
	}

	public void setRobot_name(String robot_name) {
		this.robot_name = robot_name;
	}

	public String getRobot_desc() {
		return robot_desc;
	}

	public void setRobot_desc(String robot_desc) {
		this.robot_desc = robot_desc;
	}

	public String getRobot_code() {
		return robot_code;
	}

	public void setRobot_code(String robot_code) {
		this.robot_code = robot_code;
	}

	public Integer getRobot_ranking() {
		return robot_ranking;
	}

	public void setRobot_ranking(Integer robot_ranking) {
		this.robot_ranking = robot_ranking;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
}
