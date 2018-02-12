package ro.inf.ucv.admitere.entity;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "enrolled_user")
public class EnrolledUser {

	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column
	@Id
	private String id;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User user;

	@NotNull
	@NotEmpty
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SampleResult> sampleResults;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "addmission_specialization")
	private AddmisionSpecialization addmisionSpecialization;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SampleResult> getSampleResults() {
		return sampleResults;
	}

	public void setSampleResults(List<SampleResult> sampleResults) {
		this.sampleResults = sampleResults;
	}

	public AddmisionSpecialization getAddmisionSpecialization() {
		return addmisionSpecialization;
	}

	public void setAddmisionSpecialization(AddmisionSpecialization addmisionSpecialization) {
		this.addmisionSpecialization = addmisionSpecialization;
	}

}
