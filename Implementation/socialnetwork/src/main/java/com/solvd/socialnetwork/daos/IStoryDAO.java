package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.Story;

public interface IStoryDAO extends IDAO<Story>{
	void removeByStoryCreatorId(Long id);
}
