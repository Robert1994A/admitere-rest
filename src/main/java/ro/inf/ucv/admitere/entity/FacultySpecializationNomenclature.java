package ro.inf.ucv.admitere.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "faculty_specialization_nomenclature")
public class FacultySpecializationNomenclature implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@Column(columnDefinition = "TEXT", length = 1024)
	private String name;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String description;

	@NotBlank
	private String url;

	@NotBlank
	private String logo;

	@Column(name = "maximum_number_of_places")
	private int maxNumberOfPlaces;

	@NotBlank
	@Column(name = "form_of_learning")
	private String formOfLearning;

	@NotBlank
	private String accreditation;

	@Column(name = "number_of_credits")
	private int numberOfCredits;

	@Column(name = "creation_date")
	private Date creationDate = new Date();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "contact_information")
	private ContactInformation contactInformation;

	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private FacultyDomainNomenclature facultyDomainNomenclature;

	public FacultySpecializationNomenclature() {
	}

	public FacultySpecializationNomenclature(Long id, @NotBlank String name, @NotBlank String description,
			@NotBlank String url, @Min(1) @Max(2000) int maxNumberOfPlaces, @NotBlank String formOfLearning,
			@NotBlank String accreditation, @Min(1) int numberOfCredits, @NotNull ContactInformation contactInformation,
			@NotBlank String logo) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.url = url;
		this.maxNumberOfPlaces = maxNumberOfPlaces;
		this.formOfLearning = formOfLearning;
		this.accreditation = accreditation;
		this.numberOfCredits = numberOfCredits;
		this.contactInformation = contactInformation;
		this.logo = logo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxNumberOfPlaces() {
		return maxNumberOfPlaces;
	}

	public void setMaxNumberOfPlaces(int maxNumberOfPlaces) {
		this.maxNumberOfPlaces = maxNumberOfPlaces;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFormOfLearning() {
		return formOfLearning;
	}

	public void setFormOfLearning(String formOfLearning) {
		this.formOfLearning = formOfLearning;
	}

	public int getNumberOfCredits() {
		return numberOfCredits;
	}

	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	public String getAccreditation() {
		return accreditation;
	}

	public void setAccreditation(String accreditation) {
		this.accreditation = accreditation;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	public FacultyDomainNomenclature getFacultyDomainNomenclature() {
		return facultyDomainNomenclature;
	}

	public void setFacultyDomainNomenclature(FacultyDomainNomenclature facultyDomainNomenclature) {
		this.facultyDomainNomenclature = facultyDomainNomenclature;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
