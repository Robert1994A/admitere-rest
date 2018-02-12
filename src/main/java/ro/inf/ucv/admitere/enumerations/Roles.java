package ro.inf.ucv.admitere.enumerations;

public enum Roles {
	USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), FACULTY("ROLE_FACULTY"), UNIVERSITY("ROLE_UNIVERSITY");

	private String name;

	Roles(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
