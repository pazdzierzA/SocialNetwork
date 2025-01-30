package com.solvd.socialnetwork.daos.mySQLImpl;

import com.solvd.socialnetwork.daos.IEventDAO;
import com.solvd.socialnetwork.models.Event;

public class EventDAO implements IEventDAO{
	public final static String GET_BY_ID = "SELECT * FROM Event WHERE id = ?";
	public final static String GET_BY_NAME = "SELECT * FROM Comments WHERE name = ?";
	public final static String GET_BY_CREATOR_ID = "SELECT * FROM Comments WHERE creator_id = ?";
	public final static String GET_BY_LOCATION = "SELECT * FROM Comments WHERE location = ?";
	@Override
	public Event getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event save(Event entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event update(Event entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Event getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event getByCreatorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event getByLocation(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
