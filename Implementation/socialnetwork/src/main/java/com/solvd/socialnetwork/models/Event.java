package com.solvd.socialnetwork.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event extends BaseEntity {
	private String name;
	private String location;
	private LocalDate startDate;
	private LocalDate endDate;
	private Long creatorId;

	private List<EventParticipant> participants = new ArrayList<EventParticipant>();

	public Event(Long id, String name, String location, LocalDate startDate, LocalDate endDate, Long creatorId,
			List<EventParticipant> participants) {
		super(id);
		this.name = name;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creatorId = creatorId;
		this.participants = participants;
	}

	public Event() {

	}
	
	public Event(Long id, String name, String location, LocalDate startDate, LocalDate endDate, Long creatorId) {
		super(id);
		this.name = name;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creatorId = creatorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public List<EventParticipant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<EventParticipant> participants) {
		this.participants = participants;
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", location=" + location + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", creatorId=" + creatorId + ", participants=" + participants + "]";
	}
	
	

	
}
