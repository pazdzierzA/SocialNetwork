package com.solvd.socialnetwork.services.impl;


import com.solvd.socialnetwork.daos.ILikePostDAO;
import com.solvd.socialnetwork.models.LikePost;
import com.solvd.socialnetwork.services.ILikeService;

public class LikeService extends BaseService implements ILikeService {
	private ILikePostDAO likeDAO;

	public LikeService() {
		super();
		this.likeDAO = daoFactory.getLikePostDAO();
	}
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
