package com.solvd.socialnetwork.enums;

public enum NotificationType {
	LIKE("like"), COMMENT("comment"), FRIEND_REQUEST("friend request"), MESSAGE("message");

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
