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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ro.inf.ucv.admitere.enumerations.SpecializationType;

@Entity
@Table(name = "admission_specialization")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdmissionSpecialization implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column
	@Id
	private String id;

	@Min(1)
	private int numberOfPlaces;

	@NotNull
	@Column(name = "creation_date")
	private Date creationDate = new Date();

	@NotNull
	@Column(name = "specialization_type")
	private SpecializationType specializationType;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "faculty_specialization_nomenclature")
	private FacultySpecializationNomenclature facultySpecializationNomenclature;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "specialization_sample")
	private SpecializationSample specializationSample;

	@JsonBackReference
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "admissionSpecialization")
	private List<AppliedSession> appliedSessions;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private AdmissionSession admissionSession;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNumberOfPlaces() {
		return numberOfPlaces;
	}

	public void setNumberOfPlaces(int numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public SpecializationType getSpecializationType() {
		return specializationType;
	}

	public void setSpecializationType(SpecializationType specializationType) {
		this.specializationType = specializationType;
	}

	public FacultySpecializationNomenclature getFacultySpecializationNomenclature() {
		return facultySpecializationNomenclature;
	}

	public void setFacultySpecializationNomenclature(
			FacultySpecializationNomenclature facultySpecializationNomenclature) {
		this.facultySpecializationNomenclature = facultySpecializationNomenclature;
	}

	public SpecializationSample getSpecializationSample() {
		return specializationSample;
	}

	public void setSpecializationSample(SpecializationSample specializationSample) {
		this.specializationSample = specializationSample;
	}

	public List<AppliedSession> getAppliedSessions() {
		return appliedSessions;
	}

	public void setAppliedSessions(List<AppliedSession> appliedSessions) {
		this.appliedSessions = appliedSessions;
	}

	public AdmissionSession getAdmissionSession() {
		return admissionSession;
	}

	public void setAdmissionSession(AdmissionSession admissionSession) {
		this.admissionSession = admissionSession;
	}

}
