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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ro.inf.ucv.admitere.service.utils.ConfigurationUtils;

@Entity
@Table(name = "applied_session")
public class AppliedSession implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column
	@Id
	private String id;

	@Column(name = "creation_date")
	@DateTimeFormat(pattern = ConfigurationUtils.DATE_FORMAT)
	private Date creationDate = new Date();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "appliedSession")
	@Fetch(FetchMode.SUBSELECT)
	private List<SampleResult> sampleResults;

	@NotNull
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private AdmissionSpecialization admissionSpecialization;

	@JsonBackReference
	@ManyToOne
	private User user;

	public AppliedSession() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<SampleResult> getSampleResults() {
		return sampleResults;
	}

	public void setSampleResults(List<SampleResult> sampleResults) {
		this.sampleResults = sampleResults;
	}

	public AdmissionSpecialization getAdmissionSpecialization() {
		return admissionSpecialization;
	}

	public void setAdmissionSpecialization(AdmissionSpecialization admissionSpecialization) {
		this.admissionSpecialization = admissionSpecialization;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
