package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.Event;

public interface IEventDAO extends IDAO <Event>{
	Event getByName (String name);
	Event getByCreatorId (Long id);
	Event getByLocation (String name);
	
}
