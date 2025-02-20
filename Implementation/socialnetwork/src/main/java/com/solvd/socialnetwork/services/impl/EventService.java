package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.IEventDAO;
import com.solvd.socialnetwork.models.Event;
import com.solvd.socialnetwork.services.IEventService;

public class EventService extends BaseService implements IEventService {
	private IEventDAO eventDAO;

	public EventService() {
		super();
		eventDAO = daoFactory.getEventDAO();
	}

	@Override
	public Event getById(Long id) {
		return eventDAO.getById(id);
	}

	@Override
	public Integer save(Event entity) {
		return eventDAO.save(entity);
	}

	@Override
	public Integer update(Event entity) {
		return eventDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
		eventDAO.removeById(id);

	}

	@Override
	public List<Event> getByName(String name) {
		return eventDAO.getByName(name);
	}

	@Override
	public List<Event> getByCreatorId(Long id) {
		return eventDAO.getByCreatorId(id);
	}

	@Override
	public List<Event> getByLocation(String location) {
		return eventDAO.getByLocation(location);
	}

}
