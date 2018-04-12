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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "faculty")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Faculty implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String name;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String description;

	@NotBlank
	private String url;

	@NotBlank
	private String logo;

	@Column(name = "creation_date")
	private Date creationDate = new Date();

	@NotNull
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "contact_information")
	private ContactInformation contactInformation;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "faculty")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<FacultyDomainNomenclature> facultyDomainNomenclatures;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "faculty")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<AdmissionSession> admissionSessions;

	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private University university;

	public Faculty() {
		super();
	}

	public Faculty(@NotBlank String name, @NotBlank String description, @NotBlank String url,
			@NotNull ContactInformation contactInformation, @NotBlank String logo) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
		this.contactInformation = contactInformation;
		this.logo = logo;
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

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admissionSessions == null) ? 0 : admissionSessions.hashCode());
		result = prime * result + ((contactInformation == null) ? 0 : contactInformation.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((facultyDomainNomenclatures == null) ? 0 : facultyDomainNomenclatures.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (admissionSessions == null) {
			if (other.admissionSessions != null)
				return false;
		} else if (!admissionSessions.equals(other.admissionSessions))
			return false;
		if (contactInformation == null) {
			if (other.contactInformation != null)
				return false;
		} else if (!contactInformation.equals(other.contactInformation))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (facultyDomainNomenclatures == null) {
			if (other.facultyDomainNomenclatures != null)
				return false;
		} else if (!facultyDomainNomenclatures.equals(other.facultyDomainNomenclatures))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

}
