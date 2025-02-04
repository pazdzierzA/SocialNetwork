package com.solvd.socialnetwork.services.impl;

import com.solvd.socialnetwork.daos.IStoryDAO;
import com.solvd.socialnetwork.daos.mySQLImpl.StoryDAO;
import com.solvd.socialnetwork.models.Story;
import com.solvd.socialnetwork.services.IStoryService;

public class StoryService implements IStoryService {
	private IStoryDAO storyDAO = new StoryDAO();
	@Override
	public Story getById(Long id) {
		return storyDAO.getById(id);
	}

	@Override
	public Story save(Story entity) {
		
		return storyDAO.save(entity);
	}

	@Override
	public Story update(Story entity) {
	
		return storyDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
	
		storyDAO.removeById(id);
	}

	@Override
	public void removeByStoryCreatorId(Long id) {
		
		storyDAO.removeByStoryCreatorId(id);
	}
	
}
