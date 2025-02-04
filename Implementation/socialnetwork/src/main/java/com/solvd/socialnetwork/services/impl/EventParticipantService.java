package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.IEventParticipantDAO;
import com.solvd.socialnetwork.daos.mySQLImpl.EventParticipantDAO;
import com.solvd.socialnetwork.models.EventParticipant;
import com.solvd.socialnetwork.services.IEventParticipantService;

public class EventParticipantService implements IEventParticipantService {
	private IEventParticipantDAO eventParticipantDAO = new EventParticipantDAO();
	@Override
	public EventParticipant getById(Long id) {
		
		return eventParticipantDAO.getById(id);
	}

	@Override
	public EventParticipant save(EventParticipant entity) {
		return eventParticipantDAO.save(entity);
	}

	@Override
	public EventParticipant update(EventParticipant entity) {
		return eventParticipantDAO.update(entity);
	}
	@Override
	public void removeById(Long id) {
		eventParticipantDAO.removeById(id);
		
	}

	@Override
	public List<EventParticipant> getByEventId(Long id) {
		
		return eventParticipantDAO.getByEventId(id);
	}

}
