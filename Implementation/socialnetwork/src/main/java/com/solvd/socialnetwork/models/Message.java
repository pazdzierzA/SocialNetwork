package com.solvd.socialnetwork.models;

public class Message extends BaseEntity {
	private String text;
	private Long senderId;
	private Long recipientId;


	public Message(Long id, String text, Long senderId, Long recipientId) {
		super(id);
		this.text = text;
		this.senderId = senderId;
		this.recipientId = recipientId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}
	



}
