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
	
	
	
	

}
