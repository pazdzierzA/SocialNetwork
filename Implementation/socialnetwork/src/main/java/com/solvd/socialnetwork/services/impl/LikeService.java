package com.solvd.socialnetwork.services.impl;

import com.solvd.socialnetwork.daos.mySQLImpl.LikeDAO;
import com.solvd.socialnetwork.models.Like;
import com.solvd.socialnetwork.services.ILikeService;

public class LikeService implements ILikeService {
	private LikeDAO likeDAO;
	@Override
	public Like getById(Long id) {
		return likeDAO.getById(id);
	}

	@Override
	public Like save(Like entity) {
		return likeDAO.save(entity);
	}

	@Override
	public Like update(Like entity) {
		return likeDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
		likeDAO.removeById(id);
		
	}

}
