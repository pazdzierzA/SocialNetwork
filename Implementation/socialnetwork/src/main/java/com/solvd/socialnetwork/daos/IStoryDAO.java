package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.Story;

public interface IStoryDAO extends IDAO<Story>{
	Story getById (Long id);
	Story save (Story entity);
	Story update (Story entity);
	void removeById(Long id);
}
