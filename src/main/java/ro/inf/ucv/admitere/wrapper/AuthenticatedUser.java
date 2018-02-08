package ro.inf.ucv.admitere.wrapper;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AuthenticatedUser {

	private Collection<? extends GrantedAuthority> authorities;

	private Object credentials;

	private Object details;

	private String name;

	private Object principal;

	public AuthenticatedUser() {
	}

	private AuthenticatedUser(Collection<? extends GrantedAuthority> collection, Object credentials, Object details,
			String name, Object principal) {
		super();
		this.authorities = collection;
		this.credentials = credentials;
		this.details = details;
		this.name = name;
		this.principal = principal;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Object getCredentials() {
		return credentials;
	}

	public void setCredentials(Object credentials) {
		this.credentials = credentials;
	}

	public Object getDetails() {
		return details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getPrincipal() {
		return principal;
	}

	public void setPrincipal(Object principal) {
		this.principal = principal;
	}

	public static AuthenticatedUser create(Authentication authentication) {
		return new AuthenticatedUser(authentication.getAuthorities(), authentication.getCredentials(),
				authentication.getDetails(), authentication.getName(), authentication.getPrincipal());
	}
}
