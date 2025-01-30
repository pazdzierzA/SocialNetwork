package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.EventParticipant;

public interface IEventParticipantDAO extends IDAO <EventParticipant>{
	EventParticipant getById (Long id);
	EventParticipant save (EventParticipant entity);
	EventParticipant update (EventParticipant entity);
	void removeById(Long id);
}
