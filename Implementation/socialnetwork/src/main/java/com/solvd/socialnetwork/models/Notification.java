package com.solvd.socialnetwork.models;

import com.solvd.socialnetwork.enums.NotificationType;

public class Notification extends BaseEntity{
	private String text;
	private NotificationType type;
	private Long userId;
	
	
	public Notification(Long id, String text, NotificationType type, Long userId) {
		super(id);
		this.text = text;
		this.type = type;
		this.userId = userId;
	}
	
	public Notification() {}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Notification [text=" + text + ", type=" + type + ", userId=" + userId + "]";
	}
	
	
	
	
	

}
