package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.IEventParticipantDAO;
import com.solvd.socialnetwork.models.EventParticipant;
import com.solvd.socialnetwork.services.IEventParticipantService;

public class EventParticipantService extends BaseService implements IEventParticipantService {
	private IEventParticipantDAO eventParticipantDAO;


	public EventParticipantService() {
		super();
		this.eventParticipantDAO = daoFactory.getEventParticipantDAO();
	}
	@Override
	public EventParticipant getById(Long id) {
		
		return eventParticipantDAO.getById(id);
	}

	@Override
	public Integer save(EventParticipant entity) {
		return eventParticipantDAO.save(entity);
	}

	@Override
	public Integer update(EventParticipant entity) {
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
