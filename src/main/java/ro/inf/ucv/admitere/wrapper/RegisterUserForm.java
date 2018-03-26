package ro.inf.ucv.admitere.wrapper;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import ro.inf.ucv.admitere.annotation.UniqueCnp;
import ro.inf.ucv.admitere.annotation.UniqueEmail;
import ro.inf.ucv.admitere.annotation.UniqueUsername;

public class RegisterUserForm {

	@UniqueUsername(message = "This username already exist! Please try another one!")
	@UniqueCnp(message = "This cnp was already registered")
	@Column(unique = true)
	@NotBlank
	private String username;

	@UniqueEmail(message = "This email already exist")
	@Column(unique = true)
	private String email;

	@NotBlank
	@Column(unique = true)
	private String phoneNumber;

	@NotBlank
	@Size(min = 8, max = 64)
	private String password;

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
