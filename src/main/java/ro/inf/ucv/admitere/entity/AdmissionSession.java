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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ro.inf.ucv.admitere.service.utils.ConfigurationUtils;

@Entity
@Table(name = "admission_session")
public class AdmissionSession implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column
	@Id
	private String id;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String name;

	private boolean enabled = false;

	@Column(name = "creation_date")
	@DateTimeFormat(pattern = ConfigurationUtils.DATE_FORMAT)
	private Date creationDate = new Date();

	@NotNull
	@Column(name = "expiration_date")
	@DateTimeFormat(pattern = ConfigurationUtils.DATE_FORMAT)
	private Date expirationDate;

	@NotNull
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "admissionSession")
	private List<AdmissionSpecialization> admissionSpecializations;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Faculty faculty;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public List<AdmissionSpecialization> getAdmissionSpecializations() {
		return admissionSpecializations;
	}

	public void setAdmissionSpecializations(List<AdmissionSpecialization> admissionSpecializations) {
		this.admissionSpecializations = admissionSpecializations;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

}
