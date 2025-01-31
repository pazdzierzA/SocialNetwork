package com.solvd.socialnetwork.daos;

import java.util.List;

import com.solvd.socialnetwork.models.Event;

public interface IEventDAO extends IDAO <Event>{
	List<Event> getByName (String name);
	List<Event> getByCreatorId (Long id);
	List<Event> getByLocation ( String location);
	
}
