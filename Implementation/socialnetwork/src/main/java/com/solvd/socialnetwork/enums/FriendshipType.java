package com.solvd.socialnetwork.enums;

public enum FriendshipType {
	CLOSE("close friends"),
	FAMILY("family"),
	OTHERS("others");
	
	private final String type;

	private FriendshipType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	

}
