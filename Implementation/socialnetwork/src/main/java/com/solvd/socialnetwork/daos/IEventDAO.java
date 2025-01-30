package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.Event;

public interface IEventDAO extends IDAO <Event>{
	Event getById (Long id);
	Event save (Event entity);
	Event update (Event entity);
	void removeById(Long id);

}
