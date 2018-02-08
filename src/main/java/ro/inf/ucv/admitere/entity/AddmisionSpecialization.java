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

import ro.inf.ucv.admitere.enumerations.SpecializationType;

@Entity
@Table
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
	@JoinColumn(name = "specialization_nomenclature")
	private SpecializationNomenclature specializationNomenclature;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "specialization_sample")
	private SpecializationSample specializationSample;

	@OneToMany(mappedBy = "addmisionSpecialization", cascade = CascadeType.ALL)
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

	public SpecializationNomenclature getSpecializationNomenclature() {
		return specializationNomenclature;
	}

	public void setSpecializationNomenclature(SpecializationNomenclature specializationNomenclature) {
		this.specializationNomenclature = specializationNomenclature;
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
