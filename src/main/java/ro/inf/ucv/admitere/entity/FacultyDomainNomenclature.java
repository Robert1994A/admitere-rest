package ro.inf.ucv.admitere.entity;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "faculty_domain_nomenclature")
public class FacultyDomainNomenclature {

	@Id
	@GeneratedValue
	private int id;

	@NotBlank
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

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "facultyDomainNomenclature")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<FacultySpecializationNomenclature> facultySpecializationNomenclatures;

	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Faculty faculty;

	public FacultyDomainNomenclature() {
		super();
	}

	public FacultyDomainNomenclature(@NotBlank String name, @NotBlank String description, @NotBlank String url,
			@NotBlank String logo) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
		this.logo = logo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public List<FacultySpecializationNomenclature> getFacultySpecializationNomenclatures() {
		return facultySpecializationNomenclatures;
	}

	public void setFacultySpecializationNomenclatures(
			List<FacultySpecializationNomenclature> facultySpecializationNomenclatures) {
		this.facultySpecializationNomenclatures = facultySpecializationNomenclatures;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
