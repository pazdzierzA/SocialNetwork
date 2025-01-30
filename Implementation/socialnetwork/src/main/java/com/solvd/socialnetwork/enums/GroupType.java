package com.solvd.socialnetwork.enums;

public enum GroupType {
	PUBLIC(1, "public"), PRIVATE(2, "private");

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
