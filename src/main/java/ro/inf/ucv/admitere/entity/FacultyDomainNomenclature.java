package ro.inf.ucv.admitere.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "faculty_domain_nomenclature")
public class FacultyDomainNomenclature {

	@Id
	@GeneratedValue
	private int id;

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	@NotBlank
	private String url;

	@Column(name = "creation_date")
	private Date creationDate = new Date();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<FacultySpecializationNomenclature> facultySpecializationNomenclatures;

	public FacultyDomainNomenclature() {
		super();
	}

	public FacultyDomainNomenclature(@NotBlank String name, @NotBlank String description, @NotBlank String url) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
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

	@Override
	public String toString() {
		return "FacultyDomainNomenclature [name=" + name + "]";
	}

}
