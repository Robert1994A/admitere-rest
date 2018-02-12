package ro.inf.ucv.admitere.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "faculty")
public class Faculty implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	@NotBlank
	private String url;

	@Column(name = "creation_date")
	private Date creationDate = new Date();

	@NotNull
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "contact_information")
	private ContactInformation contactInformation;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<FacultyDomainNomenclature> facultyDomainNomenclatures;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AdmissionSession> admissionSessions;

	public Faculty() {
		super();
	}

	public Faculty(@NotBlank String name, @NotBlank String description, @NotBlank String url,
			@NotNull ContactInformation contactInformation) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
		this.contactInformation = contactInformation;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<AdmissionSession> getAdmissionSessions() {
		return admissionSessions;
	}

	public void setAdmissionSessions(List<AdmissionSession> admissionSessions) {
		this.admissionSessions = admissionSessions;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<FacultyDomainNomenclature> getFacultyDomainNomenclatures() {
		return facultyDomainNomenclatures;
	}

	public void setFacultyDomainNomenclatures(List<FacultyDomainNomenclature> facultyDomainNomenclatures) {
		this.facultyDomainNomenclatures = facultyDomainNomenclatures;
	}

	@Override
	public String toString() {
		return "Faculty [name=" + name + "]";
	}

}
