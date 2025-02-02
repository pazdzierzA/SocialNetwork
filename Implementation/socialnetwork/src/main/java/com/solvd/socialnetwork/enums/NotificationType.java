package com.solvd.socialnetwork.enums;

public enum NotificationType {
	LIKE("Like"), COMMENT("Comment"), FRIEND_REQUEST("Friend_Request"), MESSAGE("Message");
	// in data base Friend_Requests
	private String name;

	private NotificationType(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
