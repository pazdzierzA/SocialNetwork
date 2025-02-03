package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.models.Story;

public interface IStoryService extends IService<Story>{
	void removeByStoryCreatorId(Long id);
}