package com.solvd.socialnetwork.enums;

public enum GroupType {
	PUBLIC(1, "Public"), PRIVATE(2, "Private");

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
