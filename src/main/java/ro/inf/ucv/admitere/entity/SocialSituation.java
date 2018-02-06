package ro.inf.ucv.admitere.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Table
@Entity(name = "social_situation")
public class SocialSituation implements Serializable {

	private static final long serialVersionUID = 2315524053201127684L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotBlank
	@Column(unique = true)
	private String name;

	@Column(name = "creation_date")
	private Date creationDate = new Date();

	public SocialSituation() {
		super();
	}

	public SocialSituation(@NotBlank String name) {
		super();
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}