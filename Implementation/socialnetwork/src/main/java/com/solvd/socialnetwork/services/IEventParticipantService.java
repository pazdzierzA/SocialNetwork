package com.solvd.socialnetwork.services;

import java.util.List;

import com.solvd.socialnetwork.models.EventParticipant;

public interface IEventParticipantService extends IService<EventParticipant>{
	List<EventParticipant> getByEventId (Long id);
}