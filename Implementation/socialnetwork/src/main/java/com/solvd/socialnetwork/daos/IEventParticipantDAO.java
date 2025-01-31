package com.solvd.socialnetwork.daos;

import java.util.List;

import com.solvd.socialnetwork.models.EventParticipant;

public interface IEventParticipantDAO extends IDAO <EventParticipant>{
	List<EventParticipant> getByEventId (Long id);

}
