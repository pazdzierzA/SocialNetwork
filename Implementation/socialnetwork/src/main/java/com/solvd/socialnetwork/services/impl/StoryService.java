package com.solvd.socialnetwork.services.impl;

import com.solvd.socialnetwork.daos.IStoryDAO;
import com.solvd.socialnetwork.models.Story;
import com.solvd.socialnetwork.services.IStoryService;

public class StoryService extends BaseService implements IStoryService {
	private IStoryDAO storyDAO;


	public StoryService() {
		super();
		this.storyDAO = daoFactory.getStoryDAO();
	}
	@Override
	public Story getById(Long id) {
		return storyDAO.getById(id);
	}

	@Override
	public Integer save(Story entity) {
		
		return storyDAO.save(entity);
	}

	@Override
	public Integer update(Story entity) {
	
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
