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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

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

	@NotNull
	@NotEmpty
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SampleResult> sampleResults;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_specialization")
	private AdmissionSpecialization admissionSpecialization;

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

}
