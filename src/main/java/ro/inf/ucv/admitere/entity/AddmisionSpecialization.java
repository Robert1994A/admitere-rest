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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import ro.inf.ucv.admitere.enumerations.SpecializationType;

@Entity
@Table(name = "addmision_specialization")
public class AddmisionSpecialization implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Min(1)
	private int numberOfPlaces;

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

	@OneToMany(mappedBy = "addmisionSpecialization", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<EnrolledUser> enrolledUsers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<EnrolledUser> getEnrolledUsers() {
		return enrolledUsers;
	}

	public void setEnrolledUsers(List<EnrolledUser> enrolledUsers) {
		this.enrolledUsers = enrolledUsers;
	}

}
