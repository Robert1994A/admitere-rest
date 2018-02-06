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
@Entity(name = "marital_status")
public class MaritalStatus implements Serializable {

	private static final long serialVersionUID = -7172699459957347140L;

	@Id
	@GeneratedValue
	private int id;

	@NotBlank
	@Column(unique = true)
	private String name;

	@Column(name = "creation_date")
	private Date creationDate = new Date();

	public MaritalStatus() {
	}

	public MaritalStatus(@NotBlank String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
