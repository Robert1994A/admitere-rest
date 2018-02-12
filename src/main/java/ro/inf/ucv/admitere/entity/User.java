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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ro.inf.ucv.admitere.annotation.UniqueCnp;
import ro.inf.ucv.admitere.annotation.UniqueEmail;
import ro.inf.ucv.admitere.annotation.UniqueUsername;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 9093304836112847216L;

	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column
	@Id
	private String id;

	@UniqueUsername(message = "This username already exist! Please try another one!")
	@UniqueCnp(message = "This cnp was already registered")
	@NotBlank
	private String username;

	@UniqueEmail(message = "")
	private String email;

	@JsonIgnore
	@NotBlank
	private String password;

	@JsonIgnore
	@Column(name = "register_token", unique = true)
	@NotBlank
	private String registerToken;

	@JsonIgnore
	@Column(name = "recover_password_token", unique = true)
	@NotBlank
	private String recoverPaswordToken;

	@Column(name = "creation_date")
	private Date creationDate = new Date();

	private boolean enabled;

	@ManyToMany(fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.TRUE)
	@JoinTable
	private List<Role> roles;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Profile profile;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Faculty faculty;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private University university;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRegisterToken() {
		return registerToken;
	}

	public void setRegisterToken(String registerToken) {
		this.registerToken = registerToken;
	}

	public String getRecoverPaswordToken() {
		return recoverPaswordToken;
	}

	public void setRecoverPaswordToken(String recoverPaswordToken) {
		this.recoverPaswordToken = recoverPaswordToken;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", registerToken=" + registerToken + ", recoverPaswordToken=" + recoverPaswordToken
				+ ", creationDate=" + creationDate + ", enabled=" + enabled + ", roles=" + roles + ", profile="
				+ profile + ", faculty=" + faculty + ", university=" + university + "]";
	}

}
