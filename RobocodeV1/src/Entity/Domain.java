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
@Table(name = "domain")
public class Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DomainId")
	private Integer DomainId;

	@Column(name = "DomainName")
	private String DomainName;

	@Column(name = "CreatedDate")
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getDomainId() {
		return DomainId;
	}

	public void setDomainId(Integer domainId) {
		DomainId = domainId;
	}

	public String getDomainName() {
		return DomainName;
	}

	public void setDomainName(String domainName) {
		DomainName = domainName;
	}

}
