package com.solvd.socialnetwork.enums;

public enum ParticipantStatus {
	GOING("going"),
	INTERESTED("interested"),
	NOT_GOING("not going"),
	NO_INFORMATION("no inforamtion");
	
	private final String status;

	public String getStatus() {
		return status;
	}

	private ParticipantStatus(String status) {
		this.status = status;
	}
	
	
}
