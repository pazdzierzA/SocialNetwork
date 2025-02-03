package com.solvd.socialnetwork.models;

import com.solvd.socialnetwork.enums.ParticipantStatus;

public class EventParticipant extends BaseEntity {

	private ParticipantStatus status;
	private Long eventId;
	private Long userId;

	public EventParticipant(Long id, ParticipantStatus status, Long eventId, Long userId) {
		super(id);
		this.status = status;
		this.eventId = eventId;
		this.userId = userId;
	}

	public EventParticipant() {
	
	}

	public ParticipantStatus getStatus() {
		return status;
	}

	public void setStatus(ParticipantStatus status) {
		this.status = status;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "EventParticipant [status=" + status + ", eventId=" + eventId + ", userId=" + userId + "]";
	}
	
	

}
