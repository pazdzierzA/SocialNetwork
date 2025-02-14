package com.solvd.socialnetwork.services.impl;

import com.solvd.socialnetwork.daos.mybatisImpl.LikePostDAO;
import com.solvd.socialnetwork.daos.ILikePostDAO;
import com.solvd.socialnetwork.models.LikePost;
import com.solvd.socialnetwork.services.ILikeService;

public class LikeService implements ILikeService {
	private ILikePostDAO likeDAO = new LikePostDAO();
	@Override
	public LikePost getById(Long id) {
		return likeDAO.getById(id);
	}

	@Override
	public Integer save(LikePost entity) {
		return likeDAO.save(entity);
	}

	@Override
	public Integer update(LikePost entity) {
		return likeDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
		likeDAO.removeById(id);
		
	}

	@Override
	public LikePost getByPostId(Long id) {
	
		return likeDAO.getByPostId(id);
	}

}
