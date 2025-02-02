package com.solvd.socialnetwork.enums;

public enum ParticipantStatus {
	GOING("Going"),
	INTERESTED("Interested"),
	NOT_GOING("Not_going"),
	NO_INFORMATION("No_inforamtion");
	
	private final String status;

	public String getStatus() {
		return status;
	}

	private ParticipantStatus(String status) {
		this.status = status;
	}
	
	
}
