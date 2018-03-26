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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "university")
public class University implements Serializable {

	private static final long serialVersionUID = 1842660963509694499L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotBlank
	private String name;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String description;

	@NotBlank
	private String url;

	@NotNull
	@Column(name = "creation_date")
	private Date creationDate = new Date();

	@NotNull
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ContactInformation contactInformation;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Faculty> faculties;

	public University() {
		super();
	}

	public University(@NotBlank String name, @NotBlank String description, @NotBlank String url,
			@NotNull ContactInformation contactInformation) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
		this.contactInformation = contactInformation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
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

	@Override
	public String toString() {
		return "University [name=" + name + "]";
	}

}
