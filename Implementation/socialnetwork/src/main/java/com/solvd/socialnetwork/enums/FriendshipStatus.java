package com.solvd.socialnetwork.enums;

public enum FriendshipStatus {
	PENDING("pending"),
	ACCEPTED("accepted"),
	BLOCKED("blocked");
	
	private final String status;

	private FriendshipStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	

}
