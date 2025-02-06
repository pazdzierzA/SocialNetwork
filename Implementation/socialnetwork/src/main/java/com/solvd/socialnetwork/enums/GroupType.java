package com.solvd.socialnetwork.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GroupType {
	@JsonProperty("Public")
	PUBLIC(1, "Public"), 
	@JsonProperty("Private")
	PRIVATE(2, "Private");

	private final Integer id;
	private final String type;

	private GroupType(Integer id, String type) {
		this.id = id;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public String getType() {
		return type;
	}

}
