package com.solvd.socialnetwork.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserRole {
	@JsonProperty("Admin")
	ADMIN(1, "admin"),
	@JsonProperty("Member")
	MEMBER(2, "member");

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
