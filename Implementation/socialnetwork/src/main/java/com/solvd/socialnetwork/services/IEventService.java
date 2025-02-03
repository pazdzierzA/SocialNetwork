package com.solvd.socialnetwork.services;

import java.util.List;

import com.solvd.socialnetwork.models.Event;
import com.solvd.socialnetwork.services.impl.EventService;

public interface IEventService extends IService<Event>{
	List<Event> getByName (String name);
	List<Event> getByCreatorId (Long id);
	List<Event> getByLocation ( String location);
}