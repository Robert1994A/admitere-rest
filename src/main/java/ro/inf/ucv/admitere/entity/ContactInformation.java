package ro.inf.ucv.admitere.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "contact_information")
public class ContactInformation implements Serializable {

	private static final long serialVersionUID = 4257966829828401378L;

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String email;

	@NotBlank
	private String phoneNumber;

	@Column(name = "creation_date")
	private Date creationDate = new Date();

	public ContactInformation() {
		super();
	}

	public ContactInformation(@NotBlank String email, @NotBlank String phoneNumber) {
		super();
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "ContactInformation [id=" + id + ", email=" + email + ", phoneNumber=" + phoneNumber + ", creationDate="
				+ creationDate + "]";
	}

}
