package com.solvd.socialnetwork.enums;

public enum UserRole {
	ADMIN(1, "admin"), MEMBER(2, "member");

	private final Integer id;
	private final String role;

	private UserRole(Integer id, String role) {
		this.id = id;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

}
