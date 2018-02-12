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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
	private String description;

	@NotBlank
	private String url;

	private int maxNumberOfPlaces;

	@NotBlank
	private String formOfLearning;

	@NotBlank
	private String accreditation;

	private int numberOfCredits;

	@Column(name = "creation_date")
	private Date creationDate = new Date();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_information")
	private ContactInformation contactInformation;

	public FacultySpecializationNomenclature() {
	}

	public FacultySpecializationNomenclature(Long id, @NotBlank String name, @NotBlank String description,
			@NotBlank String url, @Min(1) @Max(2000) int maxNumberOfPlaces, @NotBlank String formOfLearning,
			@NotBlank String accreditation, @Min(1) int numberOfCredits) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.url = url;
		this.maxNumberOfPlaces = maxNumberOfPlaces;
		this.formOfLearning = formOfLearning;
		this.accreditation = accreditation;
		this.numberOfCredits = numberOfCredits;
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

	@Override
	public String toString() {
		return "FacultySpecializationNomenclature [name=" + name + "]";
	}
}
